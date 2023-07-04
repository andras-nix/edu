# Maven

## 01. First steps

- __SDKMAN!__
	- [SDKMAN! website](https://sdkman.io/)
	- `curl -s "https://get.sdkman.io" | bash`
	- `sdk update`
- __Java 11__
	- [Java 11 SE : API Specification](https://docs.oracle.com/en/java/javase/11/docs/api/index.html)
	- [OpenJDK 11](https://github.com/AdoptOpenJDK/openjdk-jdk11/tree/master/src/java.base/share/classes/java)
	- `sdk list java` 
	- `sdk install java 11.0.19-amzn`
- __Maven__
	- [Apache Maven Project](https://maven.apache.org/) : [Maven Users Centre](https://maven.apache.org/users/index.html)
		- [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
		- [Getting Started Guide](https://maven.apache.org/guides/getting-started/index.html)
			- [Naming Conventions](https://maven.apache.org/guides/mini/guide-naming-conventions.html)
			- [The Build Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
			- [The POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)
			- [Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
		- [Settings Reference](https://maven.apache.org/settings.html)
		- [POM Reference](https://maven.apache.org/pom.html)
	- [Maven Central](https://central.sonatype.com)
	- `sdk list maven` 
	- `sdk install maven 3.9.2`
<br>

## 02. Simple

#### Hello.java
```java
import java.util.Arrays;

class Hello {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(args));
  }
}
```

Since Java 11 there's an easy way for the one-off run[^1]:
```bash
java Hello.java
```

Don't forget the arguments!
```bash
java Hello.java know how
```

And the classic one: Compiling and running.
```bash
javac Hello.java

java Hello know how
```
[^1]: Just a sidenote: `jshell` is available since Java 9...
<br>

## 03. Dependency : [Soundex](https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/language/Soundex.html)
> Soundex is a phonetic algorithm for indexing names by sound, as pronounced in English. The goal is for homophones to be encoded to the same representation so that they can be matched despite minor differences in spelling. – [Wikipedia](https://en.wikipedia.org/wiki/Soundex)

#### Hello.java
```java
import java.util.Arrays;
import org.apache.commons.codec.language.Soundex;

class Hello {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(args));
    
    Arrays.stream(args)
        .map(Soundex.US_ENGLISH::soundex)
        .forEach(System.out::println);
  }
}
```

Let's compile it!
```bash
javac Hello.java
```

Compile time error...
```bash
Hello.java:2: error: package org.apache.commons.codec.language does not exist
import org.apache.commons.codec.language.Soundex;
                                        ^
Hello.java:9: error: package Soundex does not exist
        .map(Soundex.US_ENGLISH::soundex)
                    ^
2 errors
```

[Apache Commons Codec](https://commons.apache.org/proper/commons-codec/)

Download source code, check, extract, and copy everything from the folder `./commons-codec-X.YY-src/src/main/java/` to next to the `Hello.java`:
```bash
wget https://dlcdn.apache.org//commons/codec/source/commons-codec-1.15-src.zip
wget https://www.apache.org/dist/commons/codec/source/commons-codec-1.15-src.zip.sha512
echo "$(head -n1 *sha512)  $(find *zip)" > index
sha512sum -c index
rm index *sha512

unzip commons-codec-*-src.zip
cp -rn commons-codec-*-src/src/main/java/* .
```

```bash
javac Hello.java

java Hello know how
```
<br>

## 04. Dependency : [DaitchMokotoffSoundex](https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/language/DaitchMokotoffSoundex.html)

#### Hello.java
```java
import java.util.Arrays;
import org.apache.commons.codec.language.DaitchMokotoffSoundex;

class Hello {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(args));

    var soundex = new DaitchMokotoffSoundex();

    Arrays.stream(args)
        .map(soundex::soundex)
	.forEach(System.out::println);
  }
}
```

```bash
javac Hello.java
java Hello know how

[know, how]
Exception in thread "main" java.lang.ExceptionInInitializerError
	at Hello.main(Hello.java:8)
Caused by: java.lang.IllegalArgumentException: Unable to resolve required resource: org/apache/commons/codec/language/dmrules.txt
	at org.apache.commons.codec.Resources.getInputStream(Resources.java:38)
	at org.apache.commons.codec.language.DaitchMokotoffSoundex.<clinit>(DaitchMokotoffSoundex.java:228)
	... 1 more
```

Copy everything from the folder `./commons-codec-X.YY-src/src/main/resources/` to next to the `Hello.java`:

```bash
cp -rn commons-codec-*-src/src/main/resources/* .
java Hello know how
```
<br>

## 05. Package

```java
package hu.zza.lesson.maven;

import java.util.Arrays;
import org.apache.commons.codec.language.DaitchMokotoffSoundex;

class Hello {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(args));

    var soundex = new DaitchMokotoffSoundex();

    Arrays.stream(args)
        .map(soundex::soundex)
	.forEach(System.out::println);
  }
}
```

```bash
javac Hello.java
java Hello know how

Error: Could not find or load main class Hello
Caused by: java.lang.NoClassDefFoundError: hu/zza/lesson/maven/Hello (wrong name: Hello)
```

And this isn't the only problem:  
There is a nice little chaos, the source files are mixed with compiled ones, the resources lie around, and so on...

Let's make it a bit better:
```bash
rm -r Hello.class org
mkdir src src/hu src/hu/zza src/hu/zza/lesson src/hu/zza/lesson/maven
cp Hello.java src/hu/zza/lesson/maven/
cp -rn commons-codec-*-src/src/main/java/* src

javac -cp src -d out src/hu/zza/lesson/maven/Hello.java
cp -rn commons-codec-*-src/src/main/resources/* out

jar cfe Hello.jar hu.zza.lesson.maven.Hello -C out .
java -jar Hello.jar know how
```
<br>

## 06. Maven

> groupId: `hu.zza.lesson.maven`  
> artifactId: `first-maven`  
> version: `1.0-SNAPSHOT`  
> package: `hu.zza.lesson.maven`

```shell
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4

cd first-maven
mvn test
```

Copy `Hello.java`...
```bash
cp ../Hello.java src/main/java/hu/zza/lesson/maven/
```
...and update the [POM.xml](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html):
- change language level
```xml
<!-- FROM -->
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>

<!-- TO -->
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
```
- add [`commons-codec`](https://central.sonatype.com/artifact/commons-codec/commons-codec/1.15) as a dependency
```xml
<!--
  <dependencies>
    ...
-->
    <dependency>
      <groupId>commons-codec</groupId>
      <artifactId>commons-codec</artifactId>
      <version>1.15</version>
    </dependency>
<!--
  </dependencies>
-->
```
- add [`maven-assembly-plugin`](https://central.sonatype.com/artifact/org.apache.maven.plugins/maven-assembly-plugin/3.6.0) to config entry point (_Main-Class_ for jar) and dependency handling
```xml
<!--
    </pluginManagement>
-->
    <plugins>
      <plugin>
        <artifactId>maven-assembly-plugin</artifactId>
        <version>3.6.0</version>
        <configuration>
          <archive>
            <manifest>
              <mainClass>hu.zza.lesson.maven.Hello</mainClass>
            </manifest>
          </archive>
          <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
          </descriptorRefs>
        </configuration>
        <executions>
          <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals>
              <goal>single</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
<!--
  </build>
</project>
-->
```

```bash
mvn package
java -jar target/first-maven-1.0-SNAPSHOT-jar-with-dependencies.jar know how
```
