# Theorie

## Interface `Collection`

<!-- see http://openbook.rheinwerk-verlag.de/javainsel/javainsel_13_001.html#dodtp4d4c07eb-d8d3-46d3-a44e-90f8eb052a92
-->

```java
public class MyFirstCollection
{
  private static void fill( Collection<String> c )
  {
    c.add( "Juvy" );
    c.add( "Tina" );
    c.add( "Joy" );
  }

  public static void main( String[] args )
  {
    List<String> c = new LinkedList<String>();
    fill( c );
    System.out.println( c );     // [Juvy, Tina, Joy]
    Collections.sort( c );
    System.out.println( c );     // [Joy, Juvy, Tina]
  }
}
```

* Statt `LinkedList<String> c = new LinkedList<String>()` schreiben wir `List<String> c = ...`
    * Man braucht die Angebe des Elementtyps nur einmal:

        `List<String> c = new LinkedList<>()`


* Unterschied
    * `Collection` eine Schnittstelle
    * `Collections` eine Utililty-Klasse mit vielen Hilfsmethoden, etwa  `Collections.sort()`.

**Tipp:**

* Es gibt auch `List` -- dann sind es aber lauter `Object`e in der Liste
* Nutze immer den kleinstnötigen Typ^[weiter oben im UML-Diagramm, Basisklasse]!
    * `fill(LinkedList<String> c)` besser: `fill(Collection<String> c)`
    * `LinkedList<String> c = new LinkedList<String>()`  besser:
      `List<String> c = new LinkedList<String>()`.
        * Rechts statt `LinkedList` geht auch `ArrayList`
* Unserer eigenen statischen Methode `fill()` ist es egal, welche `Collection` wir ihr geben.
    * `List` implementiert `Collection`.

### Methoden

* `boolean add(E o)`
* `boolean contains(Object o)`
* `int size()`

*  Implementiert `Iterable` == `foreach` funktioniert

* `boolean equals(Object o)` -- auch Container, alle Elements mit `equals()` gleich,
   eventuell gleich sortiert.

    * Alle *Dinge* in Containern brauchen fast immer `equals()`

* `int hashCode()`
    * *Prüfsumme*
        * je eindeutiger desto besser
    * Verschiedener `hashCode()` --> verschiedene Objekte
    * Gleicher `hashCode()` --> gleiche oder verschiedene Objekte
    * `x.equals(y) == true` -->  `hashCode(x) == hashCode(y)`

*   `Object[]  toArray()`  oder
*   `<T> Tf[]   toArray(T[] a)`  in ein *normales* Array

    Bsp:

    ```java
     String[] a = bla.toArray(new String[0]);
    ```

* Hilfsfunktionen

     z.B. `Collections.sort()`,  `Collections.shuffle()`

### Schleifen

* foreach

    Die Elemente werden gelesen, es können innerhalb der Schleife aber keine Elemente hinzugefügt oder gelöscht werden.

```java
     List<String> strings = ...;

     for ( String s : strings )
         result += s.length();

```

* Iterator <https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html>

    * Ein Iterator besitzt die Methode `remove()` -- entfernt das Element, das der Iterator
      zuletzt bei `next()` geliefert hat

```java
     for ( Iterator<String> i = c.iterator(); i.hasNext(); )
     {
       String s = i.next();
       ...
     }
```



#### Siehe online:

* <https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html>
* <https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html>
* <http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_13_001.htm>

#### Arten

* Listen
    * Reihenfolge bleibt erhalten
    * `LinkedList` sowie `ArrayList`
    * Aus `java.util.List` -- nicht `java.awt.List`
* Set
    * Menge,
    * Jedes Element nur einmal enthalten (`equals()`)
    * `HashSet` - keine Ordnung 
    * `TreeSet` - sortiert

\needspace{3cm}
* Map^[ist keine Collection]
    * Assoziativspeicher, Zuordnung: key --> value (Array mit beliebigen Indextyp)
    * `TreeMap`, `HashMap`


![Collection (aus java ist auch eine insel)](collectionapiuml.gif){width=50%}

### Set

* Eine Menge ist eine (meist) ungeordnete Sammlung von Elementen.
* Jedes Element darf nur einmal vorkommen

Varianten (Implementierungen):

* `HashSet`: Schnelle Mengenimplementierung durch Hashing-Verfahren (ähnlich `HashMap`)
* `TreeSet`: Mengen werden durch balancierte Binärbäume realisiert, die eine Sortierung ermöglichen.

* Sonderfälle:
    * `LinkedHashSet`: Schnelle Mengenimplementierung unter Beibehaltung der Einfügereihenfolge
    * `EnumSet`: Eine spezielle Menge ausschließlich für Enum-Objekte
    * `CopyOnWriteArraySet`: Schnelle Datenstruktur für viele lesende Operationen


#### `HashSet`

* Speicherung über `hashCode()`
  * Element wird mit einem Zugriff gefunden
  * Nachteil:
      * Objekte dürfen sich nicht ändern -- neuer `hashCode()`
          *  löschen und neu reingeben.
      * Guter `hashCode()` ohne Kollision

\needspace{5cm}
#### `TreeSet`

Sortierte Speicherung als Baum:

* Je Knoten: kleine Elemente im linken Teilbaum, die großen im rechten Teilbaum
  * Jeder Schritt halbiert den Suchbereich
  * Ordnung O(ln n) -- Anzahl der Schritte ist etwa Logarithmus von n
* Braucht Konzept: kleiner/größer
  * `compareTo(other)` den Elementen definiert oder bzw. `Comparator` angeben
     * Dreiwertig
     * < 0 (kleiner), 0 (gleich), > 0 (größer)
  * `Comparator`-Objekt als Parameter beim Konstruktor: `TreeSet(Comparator<?> comparator)`
          hat Methode `compare(x,y)`
* Problem: *Mittleres* Element als Wurzel
  * Immer wieder ausbalancieren
* Vorteil
  * Leicht erweiterbar

## Map

Etwa wie Array, aber mit beliebigen Typ als Index dh. mit einem `key` (~Index) kommt man zu einem Element (`value`).
Auch genannt: Assoziativspeicher, Dictionary

Methoden:

* `V put(K key, V value)`  Fügt das Wertepaar key/value hinzu und liefert
    den vorigen Wert oder `null`
* `V remove(Object key)`   Entfernt das Wertepaar zum Schlüssel `key` und
    liefert den vorigen Wert oder `null`
* `V get(Object key)`   Liefert den Wert zum Schlüssel `key`, oder `null`
* `boolean containsKey(Object)`   Liefert true, wenn es den Schlüssel `key` gibt
* `containsValue(Object value)`   Liefert true, wenn es den Wert `value` gibt
* `Set<K> keySet()`   Liefert ein Set aller Schlüssel
* `Collection<V> values()`   Liefert eine Collection aller Werte
* `Set<Map.Entry<K, V>> entrySet()`  Liefert ein Set aller Wertepaare als `Map.Entry`
    * `Map.Entry` hat `getKey()` und `getValue()`

### Map + Schleifen:

```java
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//iterating over keys only
for (Integer key : map.keySet()) {
    System.out.println("Key = " + key);
}
//iterating over values only
for (Integer value : map.values()) {
    System.out.println("Value = " + value);
}
```

Wenn man Key+Value braucht:

```java

Map<String,Thing> map = ...;
for (Map.Entry<String,Thing> entry : map.entrySet()) {
    String key = entry.getKey();
    Thing thing = entry.getValue();
    ...
}

// umständlicher mit Iterator
long i = 0;
Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
while (it.hasNext()) {
    Map.Entry<Integer, Integer> pair = it.next();
    i += pair.getKey() + pair.getValue();
}

// mit Lambdas
map.forEach( (k,v) -> System.out.println("Key: " + k + ": Value: " + v));

// mit Streams
final long[] i = {0};
map.entrySet().stream().forEach(e -> i[0] += e.getKey() + e.getValue());
//  mit Streams + parallel
map.entrySet().stream().parallel().forEach(e -> i[0] += e.getKey() + e.getValue()
```


##  Details

### Methode `equals( Object o)`

Sind zwei Dinge gleich (`this` und `o`)?

Üblicherweise:

* Vergleich aller Attribute (*Werte* in der Klasse).
* Nicht vergessen: Attribute einer vorhandenen Basisklasse!  `super.equals(o)`

Achtung:

* Parameter `o` ist ein Object, muss man casten.
* `o` kann auch `null` sein

Muster:

```java
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MyClass that = (MyClass) o;

    if (this.member == that.member)
      ...
```

Das kann Intellij (und Eclipse):
<https://www.jetbrains.com/help/idea/2018.3/generate-equals-and-hashcode-wizard.html>
Alt+Insert

### Theorie: Hash

Element suchen

* (Unsortierte) Liste: alle Elemente durchgehen
* Sortierte Liste/sortierter Baum: *binäre* Suche (Intervallschachtelung)
* Den Index *berechnen* -- dort muss es sein = `hashcode()`
    * Verschiedene Konzepte für den Problemfall *gleicher Hashcode* (Hash-Kollision)
    * Sinnvoll: nicht mehr als ca. 70% Füllgrad ansonsten gibt es zu viele Kollisionen.
        * Neuaufbau mit mehr Platz = sehr aufwändig
    * Berechnung sollte nicht zu aufwändig sein

 Sinnvoll:

 * `xor` für die Kombination von Teil-Hashes.
 * `int`-Werte kann man direkt nehmen
   * Achtung auf die Verteilung: zB. Netzwerkmasken haben viele 0-er am Ende
 * *Hilfsfunktionen*: `Objects.hash(Object... values)` und `Arrays.hashCode(Object[])`

Vorteil:

* Ordnung O(1) == konstante Zeit
* Ohne Kollision = eindeutige `hashCode()`
* Elemente mit gleichem `hashCode()` meist in Liste -- lineare Suche -- langsam


#### Bedingungen

a) Wenn die `equals()`-Methode für zwei Elemente `true` zurückgibt, muss die `hashCode()`-Methode
    für diese zwei Elemente die gleiche Zahl zurückgeben.

    Wenn die `hashCode()`-Methode für zwei Elemente die gleiche Zahl zurückgibt, muss die
    `equals()`-Methode für diese zwei Elemente nicht unbedingt `true` zurückgeben.
b) Wenn die `hashCode()`-Methode für zwei Elemente verschiedene Zahlen zurückgibt, wird die
    `equals()`-Methode nicht benötigt.
c) compareTo()-Methode in einer Klasse definiert eine bestimmte Art der Sortierung. Sie wird
    benützt, wenn die sortierte Collection ohne Comparator erstellt wird.
d) In einem Comparator können mehrere Arten der Sortierung definiert werden, welche nach Bedarf
    mittels eines Parameters im Konstruktor angewendet werden können. Wenn eine sortierte
    Collection mit einem Comparator erstellt wird, dann wird die Collection sortiert mittels der
    `compare()`-Methode des Comparators.
e) Wenn eine Klasse keine compareTo()-Methode besitzt, können die Instanzen dieser Klasse nur
    mittels eines Comparators sortiert werden.



### Aufwand `O()`

`O()`  Ordnung, nur der *am schnellsten wachsende Ausdruck* ohne konstanten Faktor

Bsp:

* Suchen in unsortierter Liste  `O(n)`  -- doppelte Elementanzahl, doppelter Aufwand
* Suchen in sortierter Liste `O(ln n)`  -- n^2 (quadriert), doppelter Aufwand
* Bubble-Sort `O(n^2)` -- doppelte Elementanzahl, 4-facher Aufwand
* Hashcode `O(1)` -- konstante Zeit
    * `O(n)` -- *worstcase*: bei vielen Kollisionen: Suche in einer Liste

 Achtung:

 Besonders bei kleinem `n` können die *ignorierten* Terme und
 konstanten Faktoren ausschlaggebend sein.