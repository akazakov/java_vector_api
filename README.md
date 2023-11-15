# Benchmark JAVA vector API 

## Apple M2 MacBookAir 
```
Benchmark                      (size)  Mode  Cnt    Score    Error  Units
VectorTest.measureAddArrays       100  avgt   25    0.065 ±  0.004  us/op
VectorTest.measureAddArrays      1000  avgt   25    0.855 ±  0.041  us/op
VectorTest.measureAddArrays     10000  avgt   25    8.926 ±  0.269  us/op
VectorTest.measureAddArrays    100000  avgt   25   90.009 ±  5.274  us/op
VectorTest.measureAddArrays   1000000  avgt   25  944.619 ± 20.113  us/op
VectorTest.measureAddVectors      100  avgt   25    0.018 ±  0.001  us/op
VectorTest.measureAddVectors     1000  avgt   25    0.147 ±  0.003  us/op
VectorTest.measureAddVectors    10000  avgt   25    1.614 ±  0.008  us/op
VectorTest.measureAddVectors   100000  avgt   25   15.807 ±  0.112  us/op
VectorTest.measureAddVectors  1000000  avgt   25  161.334 ±  2.053  us/op
VectorTest.measureSumArray        100  avgt   25    0.028 ±  0.001  us/op
VectorTest.measureSumArray       1000  avgt   25    0.488 ±  0.001  us/op
VectorTest.measureSumArray      10000  avgt   25    5.187 ±  0.008  us/op
VectorTest.measureSumArray     100000  avgt   25   52.159 ±  0.058  us/op
VectorTest.measureSumArray    1000000  avgt   25  521.306 ±  0.738  us/op
VectorTest.measureSumVector       100  avgt   25    0.012 ±  0.001  us/op
VectorTest.measureSumVector      1000  avgt   25    0.143 ±  0.001  us/op
VectorTest.measureSumVector     10000  avgt   25    1.660 ±  0.004  us/op
VectorTest.measureSumVector    100000  avgt   25   17.003 ±  0.206  us/op
VectorTest.measureSumVector   1000000  avgt   25  168.403 ±  0.350  us/op
```