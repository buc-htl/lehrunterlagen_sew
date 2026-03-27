# Encodings

- HexEditor in VS Code installieren
- utf8 File zeigen -> jedes Zeichen wird mit einem Byte kodiert, das höchstwertige Bit ist immer 0. Außer bei Umlauten, die mit 2 Byte kodiert werden (z.B. ü = C3 BC)
- UTF-16LE File zeigen -> jedes Zeichen wird mit 2 Byte kodiert.
- UTF-16BE File zeigen -> jedes Zeichen wird mit 2 Byte kodiert, aber die Reihenfolge der Bytes ist umgekehrt.
- BOM vom Hex Code entfernen. Inhalt änder sich nicht.
- Codierung in VSC ändern (rechts unten) -> Inhalt ändert sich, da die Bytes anders interpretiert werden.
