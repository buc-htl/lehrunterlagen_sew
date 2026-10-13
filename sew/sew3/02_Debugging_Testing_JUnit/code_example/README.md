# JUnit-Beispiel: BankAccount

Maven-Projekt (in IntelliJ als Maven-Projekt öffnen). Tests ausführen: `mvn test`

Struktur (Tests getrennt vom Produktivcode):

- `src/main/java/at/htl/junit/BankAccount.java` – zu testende Klasse
- `src/test/java/at/htl/junit/BankAccountTest.java` – Testklasse

## Vorschlag für den Ablauf im Unterricht

1. **Warum JUnit?** `BankAccount` mit `System.out.println` in einer `main` testen, Nachteile besprechen.
2. **Abhängigkeit** in `pom.xml` (JUnit ist nicht Teil von Java).
3. **Testklasse** `BankAccountTest` von IntelliJ generieren lassen (Namenskonvention).
4. **Erster Test** mit `@Test` + `assertEquals` (`deposit_positiveAmount_increasesBalance`), AAA zeigen.
5. **Weitere Assertions**: `assertTrue`, Fehlermeldung, `delta` bei `double`.
6. **Testfälle**: Normal-, Grenz-, Fehlerfälle, ungültige Eingaben (Abschnitte in der Testklasse).
7. **Exceptions**: `assertThrows`.
8. **Parametrisierte Tests**: `@ParameterizedTest` + `@CsvSource`.
9. **Vorbereitung**: `@BeforeEach` einführen (Duplikate `new BankAccount(...)` entfernen), dann `@BeforeAll`, `@AfterEach`, `@AfterAll` (Konsolenausgabe beobachten).
10. **Gute Tests** (unabhängig, gezielt, benannt) – ein Test ändert das Konto nie für andere Tests.
11. **Code Coverage**: „Run with Coverage“ in IntelliJ → 100 %. Danach eine Testmethode auskommentieren und Abdeckung vergleichen.
12. **TDD**: Neue Regel (z. B. `transfer(BankAccount to, double amount)` oder Überziehungsrahmen) zuerst als Test schreiben (rot), dann implementieren (grün), dann refaktorisieren.
