%CSV-Files
# Persistenz von Daten und passende Datenformate


## Persistenz von Daten

Werte, die in einer Java Variable gespeichert sind, existieren nur im Hauptspeicher (RAM) und gehen verloren, sobald das Programm beendet wird. Um Daten dauerhaft zu speichern, müssen sie in auf einem Speichermedium (z.B. Festplatte) abgelegt werden.

**Persistenz** bedeutet, dass Daten über die Lebensdauer eines Programms hinaus erhalten bleiben. Man spricht auch von "Daten persistieren".

Man kann Dateien in Datenbanken oder auch in Dateien speichern. In diesem Kapitel beschäftigen wir uns mit der Speicherung in Dateien. 

## Datenformate

Nehmen wir an unser Programm soll Informationen über Personen speichern, z.B. Name, Geburtsdatum und Wohnort. Dazu haben wir die Klasse Person mit den entsprechenden Attributen definiert. Wie können wir nun die Informationen über Personen in einer Datei speichern?

Es bietet sich an eine Textdatei zu verwenden, da sie einfach zu erstellen und zu lesen ist. Wie strukturieren wir die Informationen in dieser Textdatei? Das könnten wir uns selbst ausdenken (z.B. "Name: Max Mustermann, Geburtsdatum: 01.01.1990, Wohnort: Musterstadt"), aber es gibt auch bereits etablierte Formate, die sich für die Speicherung von strukturierten Daten eignen. Durch die Verwendung eines etablierten Formats können wir von bestehenden Tools und Bibliotheken profitieren, die das Lesen und Schreiben dieser Formate erleichtern. Auch erleichtert es die Zusammenarbeit mit anderen Programmen oder Entwicklern, da das Format bereits bekannt ist.

### JSON

JSON (JavaScript Object Notation) ist ein weit verbreitetes Format zur Speicherung und Übertragung von Daten. Es ist leicht lesbar und unterstützt komplexe Datenstrukturen wie Objekte und Arrays. JSON wird häufig in Webanwendungen verwendet. Wir werden uns in höheren Klassen ausführlich mit JSON beschäftigen.

Beispiel für die Speicherung einer Person in JSON:

```json
{
  "name": "Max Mustermann",
  "geburtsdatum": "1990-01-01",
  "wohnort": "Musterstadt"
}
```

### XML

XML (eXtensible Markup Language) ist ein weiteres Format zur Speicherung von Daten. Es verwendet Tags, ähnlich zu HTML, um die Struktur der Daten zu definieren. XML ist ebenfalls leicht lesbar und unterstützt komplexe Datenstrukturen. Mit fXML von JavaFX haben wir bereits die Struktur von XML-Dokumenten kennengelernt. XML benötigt viel Platz, da die Tags viel Overhead verursachen. Es wird in aktuellen Systemen daher nur mehr selten verwendet.

Beispiel für die Speicherung einer Person in XML:

```xml
<Person>
  <name>Max Mustermann</name>
  <geburtsdatum>1990-01-01</geburtsdatum>
  <wohnort>Musterstadt</wohnort>
</Person>
```

### CSV

Für Daten, die sich als Tabellen darstellen lassen, eignet sich das CSV-Format (**C**omma-**S**eparated **V**alues). Es ist ein einfaches Textformat, bei dem jede Zeile einen Datensatz repräsentiert und die Werte durch Kommas ("," oder ";") getrennt sind. Kommt im Wert selbst ein Komma vor, wird er in Anführungszeichen gesetzt. Komt ein Anführungszeichen vor, wird es durch zwei Anführungszeichen ersetzt. Die erste Zeile enthält oft die Spaltenüberschriften.

CSV ist leicht lesbar und wird von vielen Anwendungen unterstützt, insbesondere von Tabellenkalkulationsprogrammen wie Microsoft Excel oder Google Sheets.

Beispiel für die Speicherung von Personen in CSV:

```Name,Geburtsdatum,Wohnort
Max Mustermann,1990-01-01,Musterstadt
Erika Musterfrau,1992-05-15,Musterstadt
``` 

Als Nachteile von CSV können genannt werden, dass es keine Unterstützung für komplexe Datenstrukturen gibt (z.B. verschachtelte Objekte oder Arrays) und dass keine standardisierte Möglichkeit zur Angabe von Datentypen besteht (alle Werte werden als Strings gespeichert). Es ist daher nicht immer die beste Wahl, aber für einfache tabellarische Daten ist es eine gute Option.
