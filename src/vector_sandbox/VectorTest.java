package vector_sandbox;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class VectorTest {
    static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

    int[] arr1;
    int[] arr2;

    @Param({"100", "1000", "10000", "100000", "1000000"})
    int size;
    static public int addTwoScalarArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        for(int i = 0; i< arr1.length; i++) {
            result[i] = arr1[i] + arr2[i];
            result[i] = arr1[i] + arr2[i];
        }
        var sum = 0;
        for (int i : result) {
            sum += i;
        }

        return sum;
    }
    static public int arraySum(int[] arr1) {
        var sum = 0;
        for (int i : arr1) {
            sum += i;
        }
        return sum;
    }

    public int addTwoVectorArrays(int[] a, int[] b) {
//        var c = new int[a.length];
        var upperBound = SPECIES.loopBound(a.length);
        var sum = 0;

        var i = 0;
        for (; i < upperBound; i += SPECIES.length()) {
            var va = IntVector.fromArray(SPECIES, a, i);
            var vb = IntVector.fromArray(SPECIES, b, i);
            var vc = va.add(vb);
//            vc.intoArray(c, i);
            sum += vc.reduceLanes(VectorOperators.ADD);
        }

        // Compute elements not fitting in the vector alignment.
        for (; i < a.length; i++) {
            sum += a[i] + b[i];
        }

        return sum;
    }

    static public int vectorSum(int[] a) {
//        var c = new int[a.length];
        var upperBound = SPECIES.loopBound(a.length);
        var sum = 0;

        var i = 0;
        for (; i < upperBound; i += SPECIES.length()) {
            var va = IntVector.fromArray(SPECIES, a, i);
            sum += va.reduceLanes(VectorOperators.ADD);
        }

        // Compute elements not fitting in the vector alignment.
        for (; i < a.length; i++) {
            sum += a[i];
        }

        return sum;
    }

    @Setup
    public void setup() {
        var rnd = new Random(1L);

        arr1 = new int[size];
        arr2 = new int[size];
        for (int i = 0; i < size; i++) {
            arr1[i] = rnd.nextInt();
            arr2[i] = rnd.nextInt();
        }
    }

    @Benchmark
    public void measureAddArrays(Blackhole bh) {
        bh.consume(addTwoScalarArrays(arr1, arr2));
    }

    @Benchmark
    public void measureAddVectors(Blackhole bh) {
       bh.consume(addTwoVectorArrays(arr1,arr2));
    }

    @Benchmark
    public void measureSumArray(Blackhole bh) {
        bh.consume(arraySum(arr1));
    }

    @Benchmark
    public void measureSumVector(Blackhole bh) {
        bh.consume(vectorSum(arr1));
    }
}
