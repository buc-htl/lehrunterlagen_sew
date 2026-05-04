% Byte I/O 

# Lesen und Schreiben von Binärdateien

Beim Lesen von Textdateien werden die gespeicherten Bytes entsprechend der Kodierung in Zeichen umgewandelt. Beim Lesen von Binärdateien werden die Bytes unverändert gelesen.

## Byte-Input/Output - ganze Binärdateien

Die folgende Methode demonstriert das Lesen und das Schreiben einer beliebigen Datei anhand der Methode `copyFile`,welche die Datei mit dem Namen (inkl. Pfad) `srcFile` in die Datei `destFile` kopiert.

Da die Datei als Ganzes eingelesen wird, muss man sich sicher sein, dass das sinnvoll ist und sich auch wirklich im Hauptspeicher ausgeht.

```Java
public static void copyFile(String srcFile, String destFile) throws IOException {

    // Alle Bytes einer Datei in ein byte-Array einlesen
    byte[] content = Files.readAllBytes(Paths.get(srcFile));

    //Das byte-Array wird in das destFile geschrieben
    Files.write(Paths.get(destFile),content);
}
```

## Byte-Input/Output - Lesebuffer

Man kann auch festlegen, wie viele Bytes auf einmal gelesen werden sollen.
Lies dir in der Dokumentation der Java API die Details zu [read(...)](https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html#read-byte:A-int-int-) und [write(...)](https://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html#write-byte:A-int-int-) durch um die Bedeutung der Parameter korrekt zu verstehen.

Mittels kann man n Bytes beim Einlesen überspringen.

```Java
public static void copyFile(String srcFile, String destFile) throws IOException
{
    // Die Dateien werden mit einem try-mit-resources geöffnet
    try (
            // Öffnet eine Datei zum byteweisen-Lesen,
            InputStream in  = Files.newInputStream(
                    Paths.get(srcFile)          // die zu lesende Datei
            );

            // Öffnet eine Datei zum byteweisen-Schreiben
            OutputStream out = Files.newOutputStream(
                    Paths.get(destFile)         // die zu schreibende Datei
            );

            // Beide Dateien werden automatisch geschlossen, sobald der
            // try-Block verlassen wird!
    ) {
        byte[] buffer = new byte[8192]; // Lesebuffer
        int    bytesRead;               // Anzahl der gelesenen Bytes

        // Kopiert die gesamte Datei
        while ((bytesRead = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer,0, bytesRead);
        }
    }
}
```

Man kann den Input- und OutputStream auch wieder buffern, um die Performance zu verbessern. Buffern bedeutet, dass die Daten nicht direkt von der Festplatte gelesen oder auf die Festplatte geschrieben werden, sondern zuerst in einem Zwischenspeicher (Buffer) gehalten werden. Das reduziert die Anzahl der Zugriffe auf die Festplatte und verbessert dadurch die Performance.

```Java
try (
    InputStream in  = new BufferedInputStream(Files.newInputStream(Paths.get(srcFile));

    OutputStream out = new BufferedOutputStream(Files.newOutputStream(Paths.get(destFile)));
    ) {
        ...
    }

***Tipp:*** Erzeuge dir im Projektverzeichis einen Ordner `resources` mit einer Bilddatei mit dem Namen "meinBild.png". Du kannst dann die Datei mit `copyFile("resources/meinBild.png", "resources/meinBild_neu.png")` kopieren.

## Byte-Output with append

Sollte eine bereits existierende Datei zum Schreiben geöffnet werden, so wird normalerweise der alte Dateiinhalt komplett gelöscht. In der Datei steht dann nur die neu geschriebenen Bytes.

Möchte man das verhindern und die neuen Bytes an die schon existierenden hinten anhängen, so muss man das beim Öffnen entsprechend angeben:

```Java
// Öffnet eine Datei zum Schreiben, ohne dass der alte Dateiinhalt gelöscht wird
OutputStream out = Files.newOutputStream(
    Paths.get(destFile),         // die zu schreibende Datei
    StandardOpenOption.APPEND    // die Datei wird erweitert
                                 // --> die neuen Bytes werden hinten angehängt
);
```

### Bytes überspringen

Mit der Methode [`in.skip(count)`](https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html#skip-long-) kann man *count* Bytes überspringen, dh. das Einlesen beginnt danach bzw. geht danach weiter. Die Methode liefert die tatsächlich übersprungenen Bytes zurück, welche kleiner als *count* sein können, wenn das Ende der Datei erreicht wird.

