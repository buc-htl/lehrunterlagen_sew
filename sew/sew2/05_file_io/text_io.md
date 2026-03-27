%Text I/O
# Lesen und Schreiben von Textdateien

## IO-Exception

* Jeder Zugriff auf die Festplatte (USB, etc.) kann schief gehen (Datei fehlt, wir haben keine Zugriffsrechte,
    der USB-Stick wurde abgezogen, ...).
* Falls es schief geht, wird eine `IOException` geworfen.
* Diese müssen wir entweder fangen:

    ```java
    try {
      ...
    } catch (IOException e) {
      ...
    }
    ```

    oder im Methodenkopf ankündigen, dass diese Methode eine `IOException` werfen kann:

    ```... convertFileToUpperCase (String srcFile, String destFile) throws IOException```{.java}

## Lesen und Schreiben ganzer Textdateien

Die einfachste Methode alle Zeilen einer Datei zu lesen/schreiben liefern die Methoden [`Files.readAllLines(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#readAllLines-java.nio.file.Path-java.nio.charset.Charset-) und [`Files.write(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#write-java.nio.file.Path-java.lang.Iterable-java.nio.charset.Charset-java.nio.file.OpenOption...-). Diese Methoden öffnen die Datei, schreiben/lesen den gesamten Inhalt und schließen sie auch wieder.

Die folgende Methode demonstriert das Lesen und das Schreiben einer Textdatei anhand der Methode
`copyTextFile(...)`, die die Datei mit dem Namen (inkl. Pfad) `srcFile` in die Datei `destFile` kopiert.

```Java
public static void copyTextFile (String srcFile, String destFile) throws IOException {
        
        List<String> allLines = Files.readAllLines(Paths.get(srcFile), Charset.forName("UTF-8"));
        Files.write(Paths.get(destFile), allLines, StandardCharsets.UTF_8);
}
```

Zeichensatz: Entweder mit einem String wie im Beispiel angeben oder, besser, ein vordefinierter Zeichensatz aus der Klasse [`StandardCharsets`](https://docs.oracle.com/javase/10/docs/api/java/nio/charset/StandardCharsets.html) verwenden. Wird kein Charset angegeben, so wird UTF-8 verwendet.

Wichtig: Beim Ausführen des Programms ist das *working directory* der *oberste* Ordner des Projekts.

***Tipp:*** Erzeuge dir im Projektverzeichis einen Ordner `resources` speichere dort alle Dateien, die dein Programm benötigt. Zusätzlich kann/sollte man den Ordner auch in IntelliJ als "Ressource" markieren (Rechtsklick auf den Ordner -> "Mark Directory As" -> "Resources Root"). 

Gib die Pfade dann relativ zum Projektverzeichnis an. Beispielaufruf der oben angeführten Methode: `copyTextFile("resources/meinText.txt", "resources/meinText.txt.copy")`

### Neuen Inhalt hinzufügen (append)

Sollte eine bereits existierende Datei zum Schreiben geöffnet werden, so wird normalerweise der alte Dateiinhalt komplett gelöscht. In der Datei steht dann nur der neu geschriebene Text.

Möchte man das verhindern und den neuen Text an den schon existierenden hinten anhängen, so muss man das beim Schreiben der Datei entsprechend angeben:

```Java

    public static void copyTextFile (String srcFile, String destFile) throws IOException {
        
        List<String> allLines = Files.readAllLines(Paths.get(srcFile), Charset.forName("UTF-8"));
        
        //allLines werden angehängt, existierender Inhalt bleibt erhalten
        Files.write(Paths.get(destFile), allLines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }
```

### Nachteile dieser Methoden

Bis jetzt haben wir gelernt, wie man mit ```Files.readAllLines(...)```{.java} und ```Files.write(...)```{.java} Text aus einer Datei lesen, bzw. in eine Datei schreiben kann.

Diese Befehle führen drei Schritte durch.

1) File öffnen
2) gesamten Inhalt lesen/schreiben
3) File schließen

Dem Vorteil eines kompakten Codes stehen auch etliche Nachteile gegenüber:

* Es wird immer der gesamte Inhalt des Files auf einmal gelesen und in den Speicher geladen. Das ist nachteilig wenn man eigentlich nur an einem Teil (z.B. der ersten Zeile) interessiert ist und/oder das File sehr groß ist.
* Für jede Lese-/Schreiboperation wird das File auch geöffnet und geschlossen. Wenn mehrere Schreib-/Leseoperationen notwendig sind (z.B. jede Benutzereingabe gleich speichern) kann es effizienter sein das File geöffnet zu lasen.

## Lesen und Schreiben einzelner Zeilen

### IO-Exception

Im folgenden wollen wir die einzelnen Schritte mit getrennten Befehlen durchführen:

1) open
2) read/write (mehrere Operationen möglich)
3) close

Achtung: jede der drei Operationen kann eine IO-Exception auslösen.

Pseudocode:

```pseudocode
   try
      open
      read/write
   catch IOException
      Fehler beim Öffnen oder Schreiben
   finally
      close
```

aber das reicht eigentlich nicht:

* Wurde der Fehler beim Öffnen oder erst beim Schreiben ausgelöst?
* Close könnte auch fehlschlagen

Eigentlich bräuchten wir folgende Struktur:

```pseudocode
   try
      open
      try
        read/write
      catch IOException
        Fehler beim Lesen/Schreiben
   catch IOException
      Fehler beim Öffnen
   finally
      try
         close
      catch IOException
        Fehler beim Close
```

Lösung: Ein [`try-with-resources` Statement]([https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html]) sorgt dafür, dass jede Ressource automatisch geschlossen wird beim Verlassen des Blocks. Tritt ein Fehler beim Lesen/Schreiben UND beim Schließen auf, hat der Schreib-/Lesefehler immer Priorität.

```java
try (BufferedReader in = ...) { //try-with resources statement

   ...

} // automatisch: close beim Verlassen des Blocks (egal ob eine Exception geworfen wurde oder nicht)
```

### Text-Input/Output

Schauen wir uns nun anhand der oben angeführten Methode `copyTextFile(...)` an, wie man ein File kopieren kann, wenn man jede Zeile einzeln liest. Wir verwenden dazu die Klasse [`BufferedReader`](https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/io/BufferedReader.html) und [`BufferedWriter`](https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/io/BufferedWriter.html).

Beachte, dass man auch Zeilenumbrüche extra hinzufügen muss!

```Java
public static void copyTextFile (String srcFile, String destFile) throws IOException {
    // Die Dateien werden mit einem try-mit-resources geöffnet
    try (
            // Öffnet eine Datei zum Lesen,
            BufferedReader in = Files.newBufferedReader(
                    Paths.get(srcFile),          // die zu lesende Datei
                    Charset.forName("UTF-8")     // ihr Zeichensatz
            );

            // Öffnet eine Datei zum Schreiben
            BufferedWriter out = Files.newBufferedWriter(
                    Paths.get(destFile),         // die zu schreibende Datei
                    Charset.forName("UTF-8")     // ihr Zeichensatz
            );

            // Beide Dateien werden automatisch geschlossen, sobald der try-Block verlassen wird!
    ) {
        String line;

        // Lies die gesamte Eingabe-Datei (in) zeilenweise, das Dateiende wird daran erkannt,
        // dass "in.readLine()" null zurück gibt.
        while ((line = in.readLine()) != null) {
            // Schreibt die gesamte Zeile in die Ausgabe-Datei (out).
            // Zusätzlich muss noch ein Zeilenumbruch (+ System.lineSeparator()) erzeugt werden.
            out.write(line + System.lineSeparator());
        }
    } // Ende try-Block -- close
}
```

### Text-Output with append

So kann man einem `BufferedWriter`{.java} beim Öffnen mitteilen, den neuen Text an den schon existierenden hinten anzuhängen.

```Java
// Öffnet eine Datei zum Schreiben, ohne dass der alte Dateiinhalt gelöscht wird
BufferedWriter out = Files.newBufferedWriter(
    Paths.get(destFile),         // die zu schreibende Datei
    Charset.forName("UTF-8"),    // ihr Zeichensatz
    StandardOpenOption.APPEND    // die Datei wird erweitert --> der neue Text wird hinten angehängt
);
```

Man kann die StandardOpenOptions auch kombinieren. Der folgende Code legt ein File an, falls es noch nicht existiert. Andernfalls wird der neue Inhalt am Ende angehängt.

```Java
BufferedWriter out = Files.newBufferedWriter(
                    Paths.get(this.filename),
                    Charset.forName("UTF-8"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            )
```

### BufferedWriter und Flush

Ein [`Writer`](https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/io/Writer.html) (eine Überklasse von `BufferedWriter`) sendet jeden Output sofort an das darunter liegende I/O-System. Da dies sehr ineffizient ist verwendet man meist einen `BufferedWriter`, der Output ein wenig sammelt (also "buffert") bevor er ihn dann gesammelt weiterleitet.

Wenn man zu einem gewissen Zeitpunkt sicherstellen will, dass der Output wirklich geschrieben wird, verwendet man die Methode [`flush()`](https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/io/BufferedWriter.html#flush()). Mit jedem Aufruf von `flush()` wird der gebufferte Inhalt geschrieben (was sonst nur automatisch geschieht wenn der Buffer voll ist).

```Java
try( BufferedWriter out = Files.newBufferedWriter(...
    ...
    out.write("erste Zeile" + System.lineSeparator());
    out.write("zweite Zeile" + System.lineSeparator());
    out.write("dritte Zeile" + System.lineSeparator());
    out.flush();    //ich stelle sicher, dass die drei Zeilen geschrieben werden
    ...
```
