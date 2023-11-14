import java.time.*;
import java.time.temporal.*;

class Solution {

  public static void main(String[] args) {
    LocalDate dateA = null; // 1864 February 4
    LocalDate dateB = null; // 1864 August 8
    System.out.printf("dateA: %s\tdateB: %s%n", dateA, dateB);

    LocalDateTime dateTimeA = null; // dateA at 12.00
    LocalDateTime dateTimeB = null; // dateB at 00.00
    System.out.printf("dateTimeA: %s\tdateTimeB: %s%n", dateTimeA, dateTimeB);

    OffsetDateTime offsetA = null;  // dateTimeA + offset +03:40
    ZonedDateTime zonedA = null;    // dateTimeB + Pacific/Honolulu
    System.out.printf("offsetA: %s\tzonedA: %s%n", offsetA, zonedA);

    OffsetDateTime offsetB = null;  // offsetA with last Thursday in the month
    ZonedDateTime zonedB = null;    // zonedA - 1 day + 1 hour - 232_667 ms
    System.out.printf("offsetB: %s\tzonedB: %s%n", offsetB, zonedB);

    ZonedDateTime from = null;  // offsetB but in Europe/Athens (same moment)
    ZonedDateTime to = null;    // zonedB but in Europe/Jersey (same moment)
    System.out.printf("from: %s\tto: %s%n", from, to);

    Duration result = null;     // guess what :-)
    System.out.printf("result: %s%n", result);
  }
}
