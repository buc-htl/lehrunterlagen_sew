# Checkliste: Reguläre Ausdrücke – Grundlagen

Prüfung des gesamten Foliendokuments [`regex_core.html`](./regex_core.html). Die Punkte sind nach ihrer Auswirkung auf Syntax und fachliche Verständlichkeit priorisiert.

## P1 – zuerst korrigieren




## P2 – Verhalten und Genauigkeit verbessern

- [ ] **Anker für Java genauer erklären** (Folie „Wo im Text soll der Treffer liegen?“). `^` und `$` beschreiben im Java-Standardmodus Anfang und Ende, wobei `$` auch vor einem abschließenden Zeilenumbruch passen kann; mit `MULTILINE` ändern sich die Anker auf Zeilenanfänge und -enden. Die Aussage, beide Anker bedeuteten immer, dass die ganze Eingabe passen muss, entsprechend einschränken und für Validierung `matches()` gegenüberstellen.
- [ ] **Zustand des `Matcher` in der Ergebnistabelle verdeutlichen** (Folien „Java: Pattern und Matcher erstellen“ und „Java: Match oder Suche?“). Die Beispiele mit `matches()`, `find()` und `lookingAt()` sollten jeweils einen frischen Matcher verwenden oder den Reset erklären. Insbesondere setzt wiederholtes `find()` die Suche an der zuletzt erreichten Position fort.
- [ ] **Grenzverhalten von `String.split()` nennen** (Folie „Java: Regex-Methoden von String“). Java verwirft bei der üblichen `split(regex)`-Variante abschließende leere Felder. Ein kurzer Hinweis oder ein Beispiel mit Trennzeichen am Ende verhindert falsche Erwartungen.

## P3 – Verständlichkeit und redaktionelle Qualität

- [ ] **Begriffserklärungen zum Backslash konsistent halten.** Die Folie „Ein Sonderzeichen suchen“ beschreibt den Backslash derzeit pauschal als Aufhebung einer Sonderbedeutung; mit den Zeichenklassen `\d`, `\w` und `\s` auf der vorherigen Folie kollidiert das. Die Erklärung an „maskiert Sonderzeichen oder leitet eine Kurzschreibweise ein“ angleichen.
- [ ] **Java-Regex-Engine als Bezugsrahmen durchgehend kenntlich machen.** Die Bedeutung von `\d`, `\w`, `\b` und Zeilenumbrüchen kann von Engine und Optionen abhängen. Die vorhandene allgemeine Fußnote sollte bei den Java-spezifischen Beispielen auf Java `Pattern` und dessen Standardoptionen bezogen werden.
- [ ] **Deutsche Schreibweise und Fachbegriffe vereinheitlichen.** Beispielsweise „English“/„i.e.“ eindeutschen, „eines Datum“ zu „eines Datums“ korrigieren und „matcht“ einheitlich durch „passt auf“ oder „liefert einen Treffer“ ersetzen.
- [ ] **Unbenutzte bzw. doppelte Ressourcenangaben bereinigen.** Im Kopfbereich liegen bereits lokale Stylesheets, die später nochmals dynamisch geladen werden; den auskommentierten alten CDN-Block entfernen oder klar dokumentieren, wenn er bewusst als Referenz erhalten bleiben soll.

## Prüfergebnis

- Das JavaScript-Modul besteht die Syntaxprüfung.
- Die Java-Beispiele zu `matches()`, `lookingAt()` und `find()` liefern die angegebenen Wahrheitswerte.
- Ein Java-Test bestätigt, dass `$` im Standardmodus auch vor einem abschließenden Zeilenumbruch matchen kann.
- Die HTML-Strukturprüfung findet die überzähligen `</code></pre>`-Tags in der Punkt-Folie.
