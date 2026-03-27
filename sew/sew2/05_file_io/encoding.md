%Kodierung von Text
# Kodierung von Text

## Kodierungen

Jede digitale Datei speichert immer nur Nullen und Einsen. Bei Textdateien werden diese Bits nun als Text interpretieren. Für eine korrekte Interpretation muss man aber die Kodierung kennen, also welches Zeichen welcher Bitfolge entspricht. Die Kodierung einer Datei muss bekannt sein (und kann nicht "geschätzt" werden).

Beispiele für Kodierungen:

* [ASCII](https://de.wikipedia.org/wiki/American_Standard_Code_for_Information_Interchange): 7-Bit Zeichenkodierung (128 Zeichen), ein Byte je Zeichen verwendet, sehr eingeschränkt, zB. keine Umlaute
* erweitertes ASCII - das 8. Bit wird genutzt um den Zeichensatz zu erweitern.
  * zB: [ISO 8859](https://de.wikipedia.org/wiki/ISO_8859) - `latin-1` ... `latin-10`. Die verschiedenen latin-Standards enthalten regionale Sonderzeichen aus.
  * zB. [Windows cp1252](https://de.wikipedia.org/wiki/Windows-1252) - OS-spezifische Kodierungen

Diese Kodierungen spielen heutzutage eine untergeordnete Rolle, da in modernen Systemen immer [Unicode](https://home.unicode.org/) verwendet wird.

## Unicode

Der durch den [Unicode-Standard](https://home.unicode.org/) festgelegte Zeichensatz enthält mittlerweile ~160.000 Zeichen (Version 17 - September 2025). Das Unicode-Konsortium hat dazu 168 moderne und alte Schriften berücksichtigt, wie auch Symbole, Emojis und nicht druckbare Steuerzeichen. Wenn dir die fast 4000 Emojis noch nicht ausreichen, kannst du auch ein [neues Emoji vorschlagen](http://unicode.org/emoji/proposals.html).

Jedes im Unicode-Standard codierte Zeichen ist einem Codepunkt (engl. code points) zugeordnet. Diese werden üblicherweise hexadezimal und mit einem vorangestellten U+ dargestellt, z. B. U+00DF für das ß. Die Darstellung erfolgt mindestens vierstellig, d. h. ggf. mit führenden Nullen. Zur Eingabe spezieller Zeichen, die nicht über die Tastatur eingegeben werden können, können Codepunkte auch direkt angegeben werden (z.B. [Windows](https://support.microsoft.com/de-de/office/einf%C3%BCgen-von-ascii-und-unicode-symbolen-oder-zeichen-westliche-sprachen-d13f58d3-7bcb-44a7-a4d5-972ee12e50e0), [macOS](https://www.heise.de/ratgeber/Tipp-Unicode-am-Mac-eingeben-6307562.html),[HTML](https://wiki.selfhtml.org/wiki/Codepoint), [Java](https://dirask.com/posts/Java-insert-unicode-character-to-string-1xy2bD))

Insgesamt stehen für die Zeichencodierung 1.111.998 Codepunkte zur Verfügung. Es können also noch viele weitere Zeichen in zukünftigen Versionen kodiert werden. Es existieren in Unicode auch zusammengesetzte Zeichen, die mehr als einen Codepoint benötigen (z. B. Zeichen mit ungewöhnlichen oder mehrfachen Akzenten, wie sie z. B. im Vietnamesischen vorkommen).

Ein Codepunkt (= eine Zahl) muss zur Verarbeitung und Speicherung schlußendlich auch in Nullen und Einsen übersetzt werden. Eine solche Umwandlung wird als Unicode Transformation Format (UTF) bezeichnet. Die verschiedenen Formate (wie z.B. UTF-8) unterscheiden sich hinsichtlich ihres Platzbedarfs auf Speichermedien (Speichereffizienz), dem Kodierungs- und Dekodierungsaufwand (Laufzeitverhalten) sowie in ihrer Kompatibilität zu anderen (älteren) Kodierungsarten, zum Beispiel ASCII. Während beispielsweise einige Formate sehr effizienten Zugriff (wahlfreier Zugriff) auf einzelne Zeichen innerhalb der Zeichenkette erlauben, gehen andere sparsam mit Speicherplatz um.

**UTF-32** kodiert ein Zeichen immer in genau 32 Bit und ist damit am einfachsten, da keine variable Zeichenlänge benutzt wird und kein intelligenter Algorithmus benötigt wird, allerdings auf Kosten der Speichergröße – werden nur Zeichen des ASCII-Zeichensatzes verwendet, wird mehr als viermal so viel Speicherplatz benötigt wie bei einer Kodierung in ASCII (7 Bit benötigt). Je nach Abfolge der Bytes, ob zuerst das niederwertigste oder das höchstwertige Byte übertragen wird, spricht man von Little Endian (UTF-32LE) oder Big Endian (UTF-32BE).

**UTF-16** ist das älteste Kodierungsverfahren, bei dem ein oder zwei 16-Bit-Einheiten (2 oder 4 Bytes) zur Kodierung eines Zeichens verwendet werden. Auch hier unterscheidet man je nach Abfolge der Bytes zwischen dem häufigeren UTF-16LE und UTF-16BE. Für Sprachen mit nicht-lateinischen Zeichen ist dies die platzsparende Variante, da sie üblicherweise mit 2 Byte auskommen.

**UTF-8** kodiert Zeichen mit variabler Byte-Anzahl. Dabei wird ein Unicodezeichen in 1 bis 4 Bytes kodiert. Die Codepoints 0 bis 127, die dem ASCII-Zeichensatz entsprechen, werden in einem Byte kodiert, wobei das höchstwertige Bit stets 0 ist. Mithilfe des achten Bits kann ein längeres Unicode-Zeichen eingeleitet werden, das sich auf 2, 3 oder 4 Byte erstreckt. Damit wird, bei auf dem lateinischen Alphabet basierenden Schriften (z.B. Deutsch, Englisch,...), am effizientesten mit dem Speicherplatz umgegangen.

Bei allen Formaten kann zu Beginn optional noch eine eindeutige Bytefolge stehen - der *Byte Order Mark (BOM)*. Vor allem bei der Bearbeitung von Dateien mit unterschiedlichen Programmen und auf unterschiedlichen Systemen ermöglicht die BOM die richtige Identifizierung der Kodierung. Ist die Kodierung bereits eindeutig definiert oder wird die Information anders übertragen (etwa durch die „charset“-Metainformation in einem HTML-Dokument), so wird die BOM weggelassen.


UTF-8 ist am meisten verbreitet. Vor allem bei Webseiten hat es sich als De-facto-Standard-Zeichencodierung durchgesetzt. Im März 2025 verwendeten 98,5 % aller Websites UTF-8 ([Quelle][https://w3techs.com/technologies/history_overview/character_encoding]). 
Java speichert Strings intern als UTF-16, verwendet aber für die Ein- und Ausgabe von Textdateien standardmäßig UTF-8. 

<!-- 
Quellen:
https://de.wikipedia.org/wiki/Unicode 
https://de.wikipedia.org/wiki/Unicode_Transformation_Format
https://wiki.selfhtml.org/wiki/Zeichencodierung/Unicode
https://de.wikipedia.org/wiki/Byte_Order_Mark 
-->

### Beispiele für Unicode Transformation Formate (UTF)

Das folgende Beispiel zeigt mit welchen Bytes (als Hexzahl dargestellt) der String "Gemüse!" je nach Kodierungsverfahren gespeichert wird. 

#### UTF-16LE (Little Endian)

```Hexdump
G     e     m     ü     s     e     !  
47 00 65 00 6D 00 FC 00 73 00 65 00 21 00
```

#### UTF-16BE (Big Endian)

```Hexdump
G     e     m     ü     s     e     !  
00 47 00 65 00 6D 00 FC 00 73 00 65 00 21
```

#### UTF-8

```Hexdump
G  e  m  ü     s  e  !  
47 65 6D C3 BC 73 65 21
```

#### UTF-8 mit BOM

```Hexdump
         G  e  m  ü     s  e  !  
EF BB BF 47 65 6D C3 BC 73 65 21
```

