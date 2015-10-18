(ns concurrent-test.core
  (:gen-class))

(defn future-test
  "Future Test"
  []
  (time
    (let [x (future (do
                      (Thread/sleep 1000)
                      (+ 41 1)))]
      (println [@x @x]))))

(defn promise-test
  "Promise Test"
  []
  (time
    (let [x (promise)
          y (promise)
          z (promise)
          fn1 #(do (println "fn1 start") (deliver z (+ @x @y)) (println "fn1 end"))
          fn2 #(do (println "fn2 start") (Thread/sleep 2000) (deliver x 52) (println "fn2 end"))
          fn3 #(do (println "fn3 start") (Thread/sleep 1000) (deliver y 86) (println "fn3 end"))]
      (dorun (pcalls fn1 fn2 fn3))
      (println @z))))

(defn stm-test
  "STM Test"
  []
  (time
    (let [x (ref 0)
          fn4 #(do (println "fn4 start") (Thread/sleep 1000) (dosync (ref-set x 49)) (println "fn4 end"))]
      (println @x)
      (dorun (pcalls fn4))
      (Thread/sleep 1200)
      (println @x))))

(defn atom-test
  "ATOM Test"
  []
  (time
    (let [x (atom 0)
          fn5 #(do (println "fn5 start") (Thread/sleep 1000) (reset! x 51) (println "fn5 end"))]
      (println @x)
      (dorun (pcalls fn5))
      (Thread/sleep 1200)
      (println @x))))

(defn agent-test
  "Agent Test"
  []
  (time
    (let [x (agent 5)
          fn6 #(do (println "fn6 start") (Thread/sleep 1000) (println "fn6 end") (+ %1 %2))]
      (println @x)
      (send-off x fn6 52)
      (await x)
      (println @x))))

(defn stm-test2
  "STM Test 2"
  [r w max]
  (time
    (let [rs (repeatedly r #(agent 0))
          ws (repeatedly w #(agent 0))
          c (ref 0)
          fn7 (fn [_]
                (loop []
                  (when (< @c max)
                    (Thread/yield)
                    (recur))))
          fn8 (fn [_]
                (loop []
                  (when (< @c max)
                    (dosync (alter c inc))
                    (Thread/yield)
                    (recur))))]
      (println "stm-test2" r w max)
      (dorun (map #(send-off % fn7) rs))
      (dorun (map #(send-off % fn8) ws))
      (apply await (concat rs ws))
      (loop []
        (Thread/yield)
        (when (< @c max)
          (recur))))))

(defn locking-test
  "Locking Test"
  [r w max]
  (time
    (let [rs (repeatedly r #(agent 0))
          ws (repeatedly w #(agent 0))
          c (int-array 1)
          fn9 (fn [_]
                (loop []
                  (let [d (locking c (aget c 0))]
                    (when (< d max)
                      (Thread/yield)
                      (recur)))))
          fn10 (fn [_]
                 (loop []
                   (letfn [(f [] (locking c (let [d (aget c 0)] (when (< d max) (aset c 0 (inc d))))))]
                     (when (f)
                       (Thread/yield)
                       (recur)))))]
      (println "locking-test" r w max)
      (dorun (map #(send-off % fn9) rs))
      (dorun (map #(send-off % fn10) ws))
      (apply await (concat rs ws))
      (loop []
        (Thread/yield)
        (let [d (locking c
                  (aget c 0))]
          (when (< d max)
            (recur)))))))

(defn java-thread-test
  "Java Thread Test"
  [r w max]
  (time
    (let [c (int-array 1)
          fn9 (fn []
                (loop []
                  (let [d (locking c (aget c 0))]
                    (when (< d max)
                      (Thread/yield)
                      (recur)))))
          fn10 (fn []
                 (loop []
                   (letfn [(f [] (locking c (let [d (aget c 0)] (when (< d max) (aset c 0 (inc d))))))]
                     (when (f)
                       (Thread/yield)
                       (recur)))))
          rs (repeatedly r #(Thread. fn9))
          ws (repeatedly w #(Thread. fn10))]
      (println "java-thread-test" r w max)
      (dorun (map #(.start %) rs))
      (dorun (map #(.start %) ws))
      (loop []
        (Thread/yield)
        (let [d (locking c
                  (aget c 0))]
          (when (< d max)
            (recur)))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (do
    (println "START")
    (future-test)
    (promise-test)
    (stm-test)
    (atom-test)
    (agent-test)
    (stm-test2 0 1 100000)
    (stm-test2 1 1 100000)
    (stm-test2 2 1 100000)
    (stm-test2 5 1 100000)
    (stm-test2 10 1 100000)
    (stm-test2 20 1 100000)
    (stm-test2 100 1 100000)
    (stm-test2 0 10 100000)
    (stm-test2 1 10 100000)
    (stm-test2 2 10 100000)
    (stm-test2 5 10 100000)
    (stm-test2 10 10 100000)
    (stm-test2 20 10 100000)
    (stm-test2 100 10 100000)
    (stm-test2 0 100 100000)
    (stm-test2 1 100 100000)
    (stm-test2 2 100 100000)
    (stm-test2 5 100 100000)
    (stm-test2 10 100 100000)
    (stm-test2 20 100 100000)
    (stm-test2 100 100 100000)
    (locking-test 0 1 100000)
    (locking-test 1 1 100000)
    (locking-test 2 1 100000)
    (locking-test 5 1 100000)
    (locking-test 10 1 100000)
    (locking-test 20 1 100000)
    (locking-test 100 1 100000)
    (locking-test 0 10 100000)
    (locking-test 1 10 100000)
    (locking-test 2 10 100000)
    (locking-test 5 10 100000)
    (locking-test 10 10 100000)
    (locking-test 20 10 100000)
    (locking-test 100 10 100000)
    (locking-test 0 100 100000)
    (locking-test 1 100 100000)
    (locking-test 2 100 100000)
    (locking-test 5 100 100000)
    (locking-test 10 100 100000)
    (locking-test 20 100 100000)
    (locking-test 100 100 100000)
    (java-thread-test 0 1 100000)
    (java-thread-test 1 1 100000)
    (java-thread-test 2 1 100000)
    (java-thread-test 5 1 100000)
    (java-thread-test 10 1 100000)
    (java-thread-test 20 1 100000)
    (java-thread-test 100 1 100000)
    (java-thread-test 0 10 100000)
    (java-thread-test 1 10 100000)
    (java-thread-test 2 10 100000)
    (java-thread-test 5 10 100000)
    (java-thread-test 10 10 100000)
    (java-thread-test 20 10 100000)
    (java-thread-test 100 10 100000)
    (java-thread-test 0 100 100000)
    (java-thread-test 1 100 100000)
    (java-thread-test 2 100 100000)
    (java-thread-test 5 100 100000)
    (java-thread-test 10 100 100000)
    (java-thread-test 20 100 100000)
    (java-thread-test 100 100 100000)
    (println "END")
    (shutdown-agents)))
