# concurrent_test

　Clojureの様々な並列処理関連のテストです。

## テスト

- futureの実行テスト
- promiseの実行テスト
- STMの実行テスト
- ATOMの実行テスト
- Agentの実行テスト
- STM + Agentのパフォーマンステスト
- locking + Agentのパフォーマンステスト
- locking + Javaスレッドのパフォーマンステスト

## 実行結果
START
[42 42]
"Elapsed time: 1103.273042 msecs"
fn1 start
fn2 start
fn3 start
fn3 end
fn2 end
fn1 end
138
"Elapsed time: 2014.141055 msecs"
0
fn4 start
fn4 end
49
"Elapsed time: 2209.261305 msecs"
0
fn5 start
fn5 end
51
"Elapsed time: 2204.812223 msecs"
5
fn6 start
fn6 end
57
"Elapsed time: 1132.817691 msecs"
stm-test2 0 1 100000
"Elapsed time: 2620.047216 msecs"
stm-test2 1 1 100000
"Elapsed time: 2194.598724 msecs"
stm-test2 2 1 100000
"Elapsed time: 2295.828695 msecs"
stm-test2 5 1 100000
"Elapsed time: 9359.903716 msecs"
stm-test2 10 1 100000
"Elapsed time: 11229.270998 msecs"
stm-test2 20 1 100000
"Elapsed time: 15289.467294 msecs"
stm-test2 100 1 100000
"Elapsed time: 100423.49535 msecs"
stm-test2 0 10 100000
"Elapsed time: 1040.599189 msecs"
stm-test2 1 10 100000
"Elapsed time: 1155.764815 msecs"
stm-test2 2 10 100000
"Elapsed time: 1371.2111 msecs"
stm-test2 5 10 100000
"Elapsed time: 1463.712791 msecs"
stm-test2 10 10 100000
"Elapsed time: 1948.766069 msecs"
stm-test2 20 10 100000
"Elapsed time: 4937.050514 msecs"
stm-test2 100 10 100000
"Elapsed time: 26153.103787 msecs"
stm-test2 0 100 100000
"Elapsed time: 1320.84475 msecs"
stm-test2 1 100 100000
"Elapsed time: 1407.44166 msecs"
stm-test2 2 100 100000
"Elapsed time: 1551.441673 msecs"
stm-test2 5 100 100000
"Elapsed time: 1374.007166 msecs"
stm-test2 10 100 100000
"Elapsed time: 2989.45337 msecs"
stm-test2 20 100 100000
"Elapsed time: 5046.983351 msecs"
stm-test2 100 100 100000
"Elapsed time: 28692.051906 msecs"
locking-test 0 1 100000
"Elapsed time: 178.719145 msecs"
locking-test 1 1 100000
"Elapsed time: 310.899756 msecs"
locking-test 2 1 100000
"Elapsed time: 509.333326 msecs"
locking-test 5 1 100000
"Elapsed time: 860.091034 msecs"
locking-test 10 1 100000
"Elapsed time: 1614.313821 msecs"
locking-test 20 1 100000
"Elapsed time: 3092.025097 msecs"
locking-test 100 1 100000
"Elapsed time: 22613.815035 msecs"
locking-test 0 10 100000
"Elapsed time: 305.478153 msecs"
locking-test 1 10 100000
"Elapsed time: 265.681437 msecs"
locking-test 2 10 100000
"Elapsed time: 228.018125 msecs"
locking-test 5 10 100000
"Elapsed time: 259.230464 msecs"
locking-test 10 10 100000
"Elapsed time: 294.472015 msecs"
locking-test 20 10 100000
"Elapsed time: 347.788765 msecs"
locking-test 100 10 100000
"Elapsed time: 2255.711076 msecs"
locking-test 0 100 100000
"Elapsed time: 172.459665 msecs"
locking-test 1 100 100000
"Elapsed time: 166.496592 msecs"
locking-test 2 100 100000
"Elapsed time: 163.373822 msecs"
locking-test 5 100 100000
"Elapsed time: 205.873371 msecs"
locking-test 10 100 100000
"Elapsed time: 256.152674 msecs"
locking-test 20 100 100000
"Elapsed time: 307.780031 msecs"
locking-test 100 100 100000
"Elapsed time: 400.312361 msecs"
java-thread-test 0 1 100000
"Elapsed time: 400.600132 msecs"
java-thread-test 1 1 100000
"Elapsed time: 445.731336 msecs"
java-thread-test 2 1 100000
"Elapsed time: 583.865979 msecs"
java-thread-test 5 1 100000
"Elapsed time: 1017.105935 msecs"
java-thread-test 10 1 100000
"Elapsed time: 1813.18117 msecs"
java-thread-test 20 1 100000
"Elapsed time: 3483.797476 msecs"
java-thread-test 100 1 100000
"Elapsed time: 27927.448624 msecs"
java-thread-test 0 10 100000
"Elapsed time: 179.07634 msecs"
java-thread-test 1 10 100000
"Elapsed time: 232.493593 msecs"
java-thread-test 2 10 100000
"Elapsed time: 226.84066 msecs"
java-thread-test 5 10 100000
"Elapsed time: 278.314342 msecs"
java-thread-test 10 10 100000
"Elapsed time: 338.617313 msecs"
java-thread-test 20 10 100000
"Elapsed time: 462.7416 msecs"
java-thread-test 100 10 100000
"Elapsed time: 6093.026228 msecs"
java-thread-test 0 100 100000
"Elapsed time: 212.241517 msecs"
java-thread-test 1 100 100000
"Elapsed time: 250.955601 msecs"
java-thread-test 2 100 100000
"Elapsed time: 223.276659 msecs"
java-thread-test 5 100 100000
"Elapsed time: 394.57092 msecs"
java-thread-test 10 100 100000
"Elapsed time: 441.163478 msecs"
java-thread-test 20 100 100000
"Elapsed time: 518.617619 msecs"
java-thread-test 100 100 100000
"Elapsed time: 4498.585648 msecs"
END

