package il.co.site_building.euler_45_solver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guy Yafe
 */
public class SimpleSolver {

  public static void main(String... args) {

    long start = System.currentTimeMillis();
    System.out.println("Start time: " + start);

    long MAX_VALUE_TO_CALCULATE = Long.parseLong(args[0]);

    findEuler45(MAX_VALUE_TO_CALCULATE);

    long end = System.currentTimeMillis();
    System.out.println("End time: " + end);
    System.out.println("Calculation took: " + (end - start) + "millis");
  }

  private static void findEuler45(long max) {
    Map<Long, Long> triangleBuckets = new HashMap<>(((int) max) * 2);
    Map<Long, Long> pentagonalBuckets = new HashMap<>();
    for (long t = 0; t < max; t++) {
      triangleBuckets.put(calcTriangle(t), t);
    }

    for (long p = 0; p < max; p++) {
      long pentagonal = calcPentagonal(p);
      if (triangleBuckets.containsKey(pentagonal)) {
        pentagonalBuckets.put(pentagonal, p);
      }
    }

    for (long h = 0; h < max; h++) {
      long hexagonal = calcHexagonal(h);
      if (pentagonalBuckets.containsKey(hexagonal)) {
        System.out.println(
            "Found value: " + hexagonal + " with inidices: Triangle: " + triangleBuckets.get(hexagonal) +
            " Pentagonal: " + pentagonalBuckets.get(hexagonal) + " Hexagonal: " + h);
      }
    }
  }

  private static long calcTriangle(long n) {
    return n * (n + 1) / 2;
  }

  private static long calcPentagonal(long n) {
    return n * (3 * n - 1) / 2;
  }

  private static long calcHexagonal(long n) {
    return n * (2 * n - 1);
  }
}
