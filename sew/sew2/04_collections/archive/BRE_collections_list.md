# Collections - List

<!-- 
übernommen von BRE/HOR
offen: Collections vs Collection
Übersicht Datentypen
Iterator -->

* Die [Java API](https://docs.oracle.com/en/java/javase/12/docs/api/index.html) gibt einen Überblick über alle verfügbaren Methoden einer Collection bzw. List/ArrayList.


    * [Collection](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/Collection.html) ist ein Interface, von dem weitere Interfaces für die unterschiedlichen Arten von Collections (List, Set, Queue,...) erben. D.h. alle Methoden des Collection-Interface müssen auch in allen davon abegeleiteten Interfaces implementiert werden (siehe https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/Collection.html).
    

    * Eine Liste steht für eine Sequenz von Daten, bei der die Elemente eine feste Reihenfolge besitzen. Zusätzlich zu den Collection Methoden enthält das [List](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/List.html) Interface Methoden um die Elemente über einen Index, ähnlich zu einem Array, zu manipulieren (siehe https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/List.html).
    

    * Die Klasse [ArrayList](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/ArrayList.html) implementiert das [List](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/List.html) Interface. Von ArrayList können also Objekte erzeugt werden (siehe https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/ArrayList.html).

    * Mehr Informationen findest du unter folgenden Links:
        * http://openbook.rheinwerk-verlag.de/javainsel/16_001.html#u16.1  
        * https://docs.oracle.com/javase/tutorial/collections/
        
* Eine ArrayList, um Integer zu speichern, erzeugt du wie folgt:

`List<Integer> zahlen = new ArrayList<>();`{.java}

Als Datentyp soll immer der möglichst allgemeinste^[weiter oben im UML-Diagramm, Basisklasse] Datentyp verwendet werden. So bleibt der Code am flexiblesten! Daher hat die Variable den Datentyp `List` und nicht `ArrayList`. Statt rechts einer `ArrayList` könntest du genauso eine `LinkedList` verwendet. Es handelt sich dabei ledigliche um eine andere Implementierung des `List` Interfaces.

In Collections können keine primitive Datentypen (int, double, float,...) gespeichert werden, sondern nur Objekte. In Java besitzt jeder primitiver Datentyp eine entsprechende Klasse (Integer, Double, Float,...). Daher `Integer`{.java} im obigen Codebeispiel.

Dank "Boxing" werden primitive Typen aber automatisch in Objekte umgewandelt wenn dies notwendig ist.

```java
List<Double> list = new ArrayList<>();
list.add(1.1);
```

* Einen Satz von Testdaten erzeugst Du am besten folgendermaßen:

`List<Integer> zahlen = new ArrayList<>(Arrays.asList(2,1,3));`{.java}

* Schleifen sind natürlich auch bei Collections nützlich, um über die Elemente der Collection zu iterieren:

```java
for (int i = 0; i < zahlen.size(); i++) {
    System.out.println(zahlen.get(i));
}
```

Praktischer ist hier oft eine for-each Schleife:

```java
  for (int zahl : zahlen) {
    System.out.println(zahl);
}
```

Die Elemente werden gelesen, dürfen aber innerhalb der Schleife nicht geändert/gelöscht werden (dafür braucht es einen Iterator)!