# package [java.time](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/package-summary.html)

This extract highlights only a little of the functionality of the `java.time` package, just a few classes. My intention was to make an easy architectural cheat sheet for learning purposes. This can be useful for the first steps, but not enough to understand it deeply.

The main goal to present handy constants (_public static final_ fields) and some useful _static_ creation method. Those are like entry points, so every constant points to an instance of the given class, as well as every creation method returns with one of it. Because of this abstraction I did not mark the fields and methods of classes as _static_, even though each of them are. Similarly I did not indicate the type of the constant or the returning value.

There are _instance_ methods as well, but only on the dotted lines, between the classes. But be aware, this line in general UML class diagram represents _dependency_, here its primary function to show transformation opportunities.

The parameter lists are also simplified: In most of the time there are only parameter names to describe the functionality, and I only indicate the type of the given parameter if it plays a significant role in this summary.

I hope it will be useful, but never forget: It is only an extract.

Read the [Java API Specification](https://docs.oracle.com/en/java/javase/11/docs/api/index.html), especially [java.time](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/package-summary.html) section (also, every class on diagram has link to the related part of it) and there are a lot of useful articles on the internet as well. In addition there is [the source code of java.time package](https://github.com/AdoptOpenJDK/openjdk-jdk11/tree/master/src/java.base/share/classes/java/time).

(Sidenote: All of the presented classes from _java.time_ package are [value-based](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/doc-files/ValueBased.html).)

## LocalDate – LocalDateTime – LocalTime
```mermaid
classDiagram

class LocalDate {
  +MIN
  +MAX
  +EPOCH
  +now()
  +of(year, month, dayOfMonth)
  +ofInstant​(Instant instant, ZoneId zone)
  +parse(text)
  +parse(text, formatter)
}

link LocalDate "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDate.html" "java.time.LocalDate (Java SE 11 API Specification)"
LocalDate ..> LocalDateTime : atStartOfDay()
LocalDate ..> LocalDateTime : atTime(LocalTime time)
LocalDate ..> LocalDateTime : atTime(hour, minute, second)

class LocalTime {
  +MIN
  +MAX
  +MIDNIGHT
  +NOON
  +now()
  +of(hour, minute, second)
  +ofInstant​(Instant instant, ZoneId zone)
  +parse(text)
  +parse(text, formatter)
}

link LocalTime "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalTime.html" "java.time.LocalTime (Java SE 11 API Specification)"
LocalTime ..> LocalDateTime : atDate(LocalDate date)

class LocalDateTime {
  +MIN
  +MAX
  +now()
  +of(LocalDate date, LocalTime time)
  +of(year, month, dayOfMonth, hour, minute)
  +ofInstant​(Instant instant, ZoneId zone)
  +parse(text)
  +parse(text, formatter)
}

link LocalDateTime "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDateTime.html" "java.time.LocalDateTime (Java SE 11 API Specification)"
LocalDateTime ..> LocalDate : toLocalDate()
LocalDateTime ..> LocalTime : toLocalTime()
```

## Instant – ZonedDateTime – ZoneId
```mermaid
classDiagram

`java.sql.Date` ..> Instant : toInstant()
`java.sql.Time` ..> Instant : toInstant()
`java.sql.Timestamp` ..> Instant : toInstant()
`java.util.Date` ..> Instant : toInstant()
`java.util.Calendar` ..> Instant : toInstant()

link `java.sql.Date` "https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/Date.html" "java.sql.Date (Java SE 11 API Specification)"
link `java.sql.Time` "https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/Time.html" "java.sql.Time (Java SE 11 API Specification)"
link `java.sql.Timestamp` "https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/Timestamp.html" "java.sql.Timestamp (Java SE 11 API Specification)"
link `java.util.Date` "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Date.html" "java.util.Date (Java SE 11 API Specification)"
link `java.util.Calendar` "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Calendar.html" "java.util.Calendar (Java SE 11 API Specification)"

class Instant {
  +MIN
  +MAX
  +EPOCH
  +now()
  +ofEpochMilli(epochMilli)
  +ofEpochSecond​(epochSecond)
  +ofEpochSecond​(epochSecond, nanoAdjustment)
  +parse​(text)
}

link Instant "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/Instant.html" "java.time.Instant (Java SE 11 API Specification)"
Instant ..> ZonedDateTime : atZone(ZoneId zone)

LocalDateTime ..> ZonedDateTime : atZone(ZoneId zone)
LocalDate ..> ZonedDateTime : atStartOfDay(ZoneId zone)

class ZonedDateTime {
  +now()
  +of​(LocalDate date, LocalTime time, ZoneId zone)
  +of​(LocalDateTime localDateTime, ZoneId zone)
  +ofInstant​(Instant instant, ZoneId zone)
  +parse(text)
  +parse(text, formatter)
}

link ZonedDateTime "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/ZonedDateTime.html" "java.time.ZonedDateTime (Java SE 11 API Specification)"
ZonedDateTime ..> LocalDate : toLocalDate()
ZonedDateTime ..> LocalDateTime : toLocalDateTime()
ZonedDateTime ..> LocalTime : toLocalTime()

link LocalDate "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDate.html" "java.time.LocalDate (Java SE 11 API Specification)"
link LocalDateTime "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDateTime.html" "java.time.LocalDateTime (Java SE 11 API Specification)"
link LocalTime "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalTime.html" "java.time.LocalTime (Java SE 11 API Specification)"

ZonedDateTime ..> OffsetDateTime : toOffsetDateTime()
OffsetDateTime ..> Instant : toInstant()
link OffsetDateTime "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/OffsetDateTime.html" "java.time.OffsetDateTime (Java SE 11 API Specification)"

ZonedDateTime ..> ZoneId : getZone()

class ZoneId {
  +of(String zoneId) ZoneId
  +systemDefault() ZoneId
  +getAvailableZoneIds() Set~String~
}
link ZoneId "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/ZoneId.html" "java.time.ZoneId (Java SE 11 API Specification)"
```

## TemporalAmount – ChronoPeriod – Duration – Period
```mermaid
classDiagram

TemporalAmount <|-- ChronoPeriod
TemporalAmount <|.. Duration
ChronoPeriod <|.. Period

class TemporalAmount {
  <<interface>>
}
link TemporalAmount "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/temporal/TemporalAmount.html" "java.time.TemporalAmount (Java SE 11 API Specification)"

class ChronoPeriod {
  <<interface>>
}
link ChronoPeriod "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/chrono/ChronoPeriod.html" "java.time.ChronoPeriod (Java SE 11 API Specification)"

class Duration {
  +ZERO
  +between​(Temporal startInclusive, Temporal endExclusive)
  +of​(amount, unit)
  +parse(text)
}
link Duration "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/Duration.html" "java.time.Duration (Java SE 11 API Specification)"

class Period {
  +ZERO
  +between​(LocalDate startDateInclusive, LocalDate endDateExclusive)
	+of​(years, months, days)
  +parse(text)
}
link Period "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/Period.html" "java.time.Period (Java SE 11 API Specification)"
```

## TemporalAccessor – Temporal
```mermaid
classDiagram

class TemporalAccessor {
  <<interface>>
}
link TemporalAccessor "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/temporal/TemporalAccessor.html" "java.time.TemporalAccessor (Java SE 11 API Specification)"

TemporalAccessor <|-- Temporal

class Temporal {
  <<interface>>
}
link Temporal "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/temporal/Temporal.html" "java.time.Temporal (Java SE 11 API Specification)"

Temporal <|.. Instant
Temporal <|.. LocalDate
Temporal <|.. LocalDateTime
Temporal <|.. LocalTime
Temporal <|.. ZonedDateTime

link Instant "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/Instant.html" "java.time.Instant (Java SE 11 API Specification)"
link LocalDate "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDate.html" "java.time.LocalDate (Java SE 11 API Specification)"
link LocalDateTime "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDateTime.html" "java.time.LocalDateTime (Java SE 11 API Specification)"
link LocalTime "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalTime.html" "java.time.LocalTime (Java SE 11 API Specification)"
link ZonedDateTime "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/ZonedDateTime.html" "java.time.ZonedDateTime (Java SE 11 API Specification)"
```
