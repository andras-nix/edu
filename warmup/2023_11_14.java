import java.util.*;
import java.time.*;

class Solution {

  /**
   * NOTE: You should keep every collection intact.
   * So you can reuse the previous results, but avoid sideeffects.
  */
  public static void main(String[] args) {
    List<Integer> oneDigits = null;   // List of numbers from 0 to 9 (incl. both)
    Set<Integer> primes  = null;      // Set of primes: 2, 3, 5, 7
    System.out.printf("oneDigits: %s%nprimes: %s%n%n", oneDigits, primes);

    // Map for every enum constant with the day of month (from current week)
    // DayOfWeek.MONDAY -> 13, DayOfWeek.TUESDAY -> 14, ...
    Map<DayOfWeek, Integer> currentWeek = null;
    System.out.printf("currentWeek: %s%n%n", currentWeek);

    List<Integer> nonPrimes = null; // elements of oneDigit without elements of primes
    System.out.printf("oneDigits: %s%nnonPrimes: %s%n%n", oneDigits, nonPrimes);

    // seconds between : 08:34:18 and 09:09:29, 13:47:28 and 14:55:59
    List<Integer> otherPrimes = null;
    System.out.printf("otherPrimes: %s%n%n", otherPrimes);

    // every number : elements from oneDigit and primes, day of month values from currentWeek
    List<Integer> unio = null;
    System.out.printf("unio: %s%nsize: %s%n%n", unio, unio == null ? "-" : unio.size());

    List<Integer> ascending = null;   // elements of unio, but in ascending order
    List<Integer> descending = null;  // elements of unio, but in descending order
    List<Integer> random = null;      // elements of unio, but in random order
    System.out.printf("ascending: %s%ndescending: %s%nrandom: %s%n%n", ascending, descending, random);
  }
}
