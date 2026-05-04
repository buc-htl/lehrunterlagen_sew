%Die Klasse Files

# Die Klasse Files

Die Klasse [`Files`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html) bietet, neben dem reinen Lesen und Schreiben von Dateien, viele nützliche Methoden zum Arbeiten mit Dateien und Ordnern.

## Datei kopieren

[`Files.copy(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#copy-java.nio.file.Path-java.nio.file.Path-java.nio.file.CopyOption...-) kopiert eine Datei.

Achtung: Falls ein Ordner kopiert wird, so wird nur der Ordner, nicht jedoch sein Inhalt, kopiert.

```java
Files.copy(
    Paths.get("resources/meinBild.png"),      // Source-File
    Paths.get("resources/meinBild_neu.png"),  // Destination-File
    StandardCopyOption.COPY_ATTRIBUTES,       // optional, kopiert auch Datei-Attribute
    StandardCopyOption.REPLACE_EXISTING       // optional: schreibt über eine vorhandene Datei
);
```

## Datei oder Ordner verschieben bzw. umbenennen

[`Files.move(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#move-java.nio.file.Path-java.nio.file.Path-java.nio.file.CopyOption...-) verschiebt (umbenennt) eine Datei oder einen Ordner samt seinem gesamten Inhalt

```Java
Files.move(
    Paths.get("resources"),
    Paths.get("resources_neu"),
    StandardCopyOption.REPLACE_EXISTING  // optional: schreibt über eine vorhandene Datei/Ordner
);
```

## Ordner erzeugen

[`Files.createDirectories(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#createDirectories-java.nio.file.Path-java.nio.file.attribute.FileAttribute...-) erzeugt einen Ordner und falls notwendig alle Ordner des Pfades.

Im Beispiel: »pictures« und falls notwendig auch »resources«, »demo« und »byteio«.

```Java
Files.createDirectories(Paths.get("resources/demo/byteio/pictures"));
```

Achtung! Es gibt auch die Methode [`Files.createDirectory(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#createDirectory-java.nio.file.Path-java.nio.file.attribute.FileAttribute...-), die fehlende Elternordner nicht erzeugt, sondern in diesem Fall fehlschlägt.

## Datei erzeugen

[`Files.createFile(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#createFile-java.nio.file.Path-java.nio.file.attribute.FileAttribute...-) erzeugt eine leere Datei.

```Java
Files.createFile(Paths.get("resources/emptyTextFile.txt"));
```

## Ordner oder Datei löschen

[`Files.delete(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#delete-java.nio.file.Path-)  löscht eine existierende Datei oder einen leeren, existierenden Ordner. Wenn der Ordner nicht leer ist, oder die Datei bzw. der Ordner nicht existiert, dann wird eine Exception geworfen (`DirectoryNotEmptyException`bzw. `NoSuchFileException`)

```Java
Files.delete(Paths.get("resources/demo/byteio/pictures"));
```

[`Files.deleteIfExists(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#deleteIfExists-java.nio.file.Path-) 
Gleich wie `Files.delete(...)`, nur dass die Methode `true` zurück liefert, wenn die Datei gelöscht werden konnte und `false`, falls sie nicht existiert (statt eine Exception zu werfen.). Wenn der Ordner nicht leer ist, dann wird eine Exception geworfen (`DirectoryNotEmptyException`).

```Java
boolean couldDeleteFile = Files.deleteIfExists(Paths.get("resources/emptyTextFile.txt"));
```

## Dateieigenschaften

Es können für eine Datei oder für einen Ordner zahlreiche Eigenschaften ermittelt werden.
Eine reguläre Datei ist eine ganz normale Datei im Gegensatz zu einem Link.

Nehmen wir an, dass die Datei "meinBild.png" eine normale Bilddatei mit 27.206 Bytes ist, dann erzeugt der Code:

```Java
Path file = Paths.get("resources/meinBild.png");
System.out.printf ("Die Datei »%s«:%n", file);
System.out.println("existiert              : " + Files.exists(file));
System.out.println("ist ein Verzeichnis    : " + Files.isDirectory(file));
System.out.println("ist eine reguläre Datei: " + Files.isRegularFile(file));
System.out.println("ist lesbar             : " + Files.isReadable(file));
System.out.println("ist schreibbar         : " + Files.isWritable(file));
System.out.println("hat die Größe in Bytes : " + Files.size(file));
```

die Ausgabe:

```text
Die Datei »resources/meinBild.png«:
existiert              : true
ist ein Verzeichnis    : false
ist eine reguläre Datei: true
ist lesbar             : true
ist schreibbar         : true
hat die Größe in Bytes : 27206
```

## Ein Verzeichnis mit allen Unterverzeichnissen durchwandern

### `Files.walk()`

Mittels [`Files.walk(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#walk-java.nio.file.Path-int-java.nio.file.FileVisitOption...- ) kann man einen Ordner mit allen Subordnern und allen Sub-Subordnern usw. durchwandern und für jedes einzelne Ordner- oder Dateiobjekt Befehle ausführen.

```Java
// Das Beispiel zählt alle Dateien im Projekt-Ordner (in der Variable counter[0]
int[] counter = new int[1];

Files.walk(
    Paths.get(""),   // der zu durchwandernde Ordner
    2                // optional: maximale Ordnertiefe (= max. Ebenen)
).forEach(path -> {  // foreach wird für jede Datei und jeden Ordner aufgerufen.
                     //   path ist dann dieses Datei- oder Ordner-Objekt

    // Hier darf beliebiger Code stehen. Man kann jedoch nicht auf eine normale
    // Variable außerhalb der geschwungenen Klammern schreibend zugreifen,
    // deswegen der Trick, dass wir ein Array verwenden.
    if  (Files.isDirectory(path)) {
        counter[0]++;
    }
});

System.out.printf("Ich habe %d Ordner gefunden.%n", counter[0]);
```

### `DirectoryStream` -- ohne Unterordner

[`Files.newDirectoryStream(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#newDirectoryStream-java.nio.file.Path-java.lang.String-) liefert einen [`DirectoryStream`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/DirectoryStream.html) um über alle Einträge eines Ordners zu iterieren. Da es sich um einen Stream handelt, verwenden wir try-with-resources, damit der Stream am Ende des Blocks automatisch wieder geschlossen wird.

```java
 try (DirectoryStream<Path> stream = Files.newDirectoryStream(
                          Paths.get("Ordner"),  // Ordner
                          "*.csv")              // Muster (String oder Lambda)
      ) {
        for (Path entry : stream) {
            ...etwas mit entry machen...
        }
      }
```
