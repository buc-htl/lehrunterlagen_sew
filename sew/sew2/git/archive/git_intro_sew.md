---
title: "Git Grundlagen"
---

# Git

## Einführung

Git ist das weltweit am meisten genutzte Versionsverwaltungssystem, auf englisch  **Version Control System (VCS)**. Es wird von Einzelpersonen, Open-Source-Projekten und Unternehmen aller Größen eingesetzt. Kenntnisse in Git werden für Softwareentwickler*innen als Standard vorausgesetzt.

In der zweiten Klasse lernen wir die Basics von Git für schnelle und einfache Abgaben. Mehr Details, und wie man git im Team verwendet, behandeln wir dann in der dritten Klasse.

Die Grundidee eines VCS ist, den **Zustand eines Projekts** immer wieder zu **speichern/einfrieren**. So kann man nachverfolgen, wer welche Änderungen vorgenommen hat, jederzeit zu einer alten Version zurückkehren, Änderungen gleichzeitig vornehmen und wieder zu einem gemeinsamen Code zusammenführen (statt sich Dateien gegenseitig zu überschreiben).

Prinzipiell speichert Git die einzelnen "Versionen" eines Projekts nur lokal. Um ein Projekt auf mehreren Rechnern, bzw. im Team, zu bearbeiten, werden die Versionen auf auf einen zentralen Server übertragen. So hat man auch gleich ein Backup. Bekannte öffentliche git-Server sind [Github](https://www.github.com) oder [Gitlab](https://www.gitlab.com). Diese Plattformen stellen meist noch viele weitere Funktionen (für die Zusammenarbeit im Team, Projektmanagement, Bugtracking, ...) zur Verfügung.

Um ein besseres Verständnis für Git zu bekommen und es unabhängig von einer spezifischen IDE einsetzen zu können, arbeiten wir zu Beginn in der Kommandozeile. Später können wir auf die in IDEs (IntelliJ, Visual Studio Code,...) integrierte Funktionen zurückgreifen.

Willst du mehr über Git lernen, schaue auf folgende Seite: <https://git-scm.com/book/de/v2/>.

## Vorbereitung

### Software installieren

<https://git-scm.com/book/de/v2/Erste-Schritte-Git-installieren>

### SSH-Keys erzeugen

*Anmerkung: Wenn du bereits SSH-Keys erzeugt hast, brauchst du die folgenden Schritte nicht durchführen*

Für die Authentifizierung am Server verwenden wir statt einem Passwort einen [**SSH-Key**](https://de.wikipedia.org/wiki/Public-Key-Authentifizierung). Voraussetzung dafür ist, dass der private Schlüssel auf deinem Rechner liegt, der öffentliche Schlüssel am Server.

Wir generieren ein Schlüsselpaar mit folgendem Befehl:

```bash
    ssh-keygen
```

Alle Fragen mit Return beantworten, eventuell zur zusätzlichen Sicherung eine Passphrase vergeben (kann aber leer gelassen werden).

Bitte nicht Putty verwenden, diese Keys haben ein anderes Format.

In `$HOME` gibt es dann einen (versteckten) Ordner `.ssh/` mit den Dateien `id_rsa` (privater Schlüssel) und  `id_rsa.pub` (öffentlicher (public) Schlüssel). Diese Dateien **nicht umbenennen**!


## Auf mehreren Rechner arbeiten (oder Backup der ssh Keys einspielen)

Die SSH-Schlüssel müssen auf jedem Rechner vorhanden sein, auf dem man arbeitet. Achtung: Ohne Schlüssel gibt es keinen Zugriff zum Server! Ohne Passphrase (falls sie nicht leer ist) kein Zugriff auf den Schlüssel!

Kopiere folgende Daten auf jeden deiner Rechner:

-------------------  -----------------------------------------
`$HOME/.ssh`         Keys, einfach den ganzen Ordner kopieren

`$HOME/.gitconfig`   kopieren, enthält globale Konfiguration
-------------------  -----------------------------------------

Falls auf deinem Rechner im Benutzerverzeichnis kein  `.ssh`-Ordner vorhanden ist, führe zuerst *einmal* `ssh git@netzwerktechnik.htl.rennweg.at` aus. Damit wird der `.ssh`-Ordner mit den richtigen Rechten angelegt.

Falls weiterhin der Zugriff verweigert wird:

```bash
    chmod -Rv g=,o= ~/.ssh
```

## Benutzerdaten setzen

Bevor man mit git arbeitet, konfiguriert man einmalig seinen Benutzernamen und seine E-Mail mit folgenden Befehlen:

```bash
    git config --global user.name "Your Name"
    git config --global user.email "your.name@htl.rennweg.at"

    git config --global push.default simple
```

Gib als Namen deinen vollen Vor- und Nachnamen an. Der letzte Befehl nimmt eine notwendige Konfiguration vor. Führt man die Befehle nicht aus wird man beim ersten Commit erinnert.

## Begriffe

**Repository**: ein Projekt und seine Geschichte, gespeichert in einem Ordner namens `.git` im Projektordner

**Commit**: Ein aktueller Projektzustand, der im Repository gespeichert wird. Die Projektgeschichte ist eine Liste von `commit`s. `git` denkt in `commit`s (ein Zustand des Projekts -- nicht in einzelnen Dateien). 

**Push**: neue *Commits* zum Server schicken

**Pull**: neue *Commits* vom Server holen

**Merge**: unterschiedliche Commits/Änderungen zusammenführen (z.B. Version am Server mit lokalen Änderungen zusammenführen)

## Neues Repository anlegen

*Anmerkung: NICHT relevant wenn du ein existierendes Repository (das du vielleicht sogar selbst angelegt hast) von einem Server klonen willst.*

Um ein neues git Repository anzulegen, wechselt man in den Ordner, dessen Inhalt man mit git verwalten will, und führt folgenden Befehl aus:

```bash
    git init .
```

Im aktuellen Ordner sollte jetzt ein (versteckter) `.git` Ordner angelegt worden sein und damit ein neues git Repository.

### Repository für die SEW Abgaben

Für unseren Unterricht legen wir pro Semester ein Projekt/Repository an (z.B. "sew2_sem2"), Jede Übung kommt dann in ein eigenes Package.

Erstelle ein Projekt mit IntelliJ, wechsle dann **in das Projektverzeichnis** (das ist der Ordner in dem sich z.B. die Ordner src und .idea befinden) und führe dort ` git init .` aus.

### .gitignore

Jetzt sollte man auch gleich eine Datei namens `.gitignore` im *Projektverzeichnis* anlegen^[https://git-scm.com/book/en/v2/Git-Basics-Recording-Changes-to-the-Repository#_ignoring]. In dieser Datei wird festgelegt, welche Ordner und Daten **nicht** im Repository gespeichert werden sollen. Kompilierte Dateien (*.class) oder rechnerspezifische IDE-Einstellungen werden üblicherweise nicht im Repository gespeichert.

Die Datei `.gitignore` muss **genau so heißen**! Sie beginnt mit einem Punkt und hat keine Dateiendung (.gitignore.txt ist falsch!).

Auf GitHub findet man eine Sammlung von gebräuchlichen [`.gitignore` Dateien](https://github.com/github/gitignore). Wir verwenden folgende Datei für Java-Projekte mit IntelliJ: <https://github.com/github/gitignore/blob/main/Global/JetBrains.gitignore>. Kopiere den Inhalt in deine `.gitignore` Datei (bzw. kopiere die Datei und benenne sie um).

Lege diese Datei unbedingt **vor deinem ersten Commit** an! Die Datei `.gitignore` muss, wie alle anderen Dateien, mit einem Commit zum Repository hinzugefügt werden!

*Anmerkung*: Wenn in einem Unterverzeichnis (z.B. `.idea`) bereits ein `.gitignore` existiert, ist das ok. Lege trotzdem wie beschrieben ein `.gitignore` im Projektverzeichnis an.

## Änderungen commmiten

Jetzt können wir beginnnen, Änderungen des Projektzustandes zu speichern. Jede Zustandsspeicherung wird als *commit* bezeichnet. Nach jeder größeren Änderung, Abschluss eines Tasks etc. sollte ein commit erfolgen.

Aus git-Sicht befindet sich jede Datei in einem spezifischen Status. Zum Beispiel befinden sich alle Dateien, die sich noch nicht im Repository befinden, im Status "untracked". Dateien, die sich seit dem letzten Commit geändert haben, sind "modified". Mit dem Befehl

```bash
git status
```

wird der Status aller Dateien angezeigt, die sich seit dem letzten Commit verändert haben oder neu angelegt worden sind. Probiere es aus!

Neue und veränderte Dateien können zur sogenannten **"Staging Area"** hinzugefügt werden. Ein Commit besteht dann aus allen zur Staging Area hinzugefügten Veränderungen. Wir fügen Dateien zur Staging Area mit dem Befehl `git add` hinzu.

```bash
    git add Readme.md           #Fügt Readme.md zur Staging Area
    git add Haus.java Auto.java #Fügt beide Dateien zur Staging Area
    git add *.java              #Fügt alle Java-Dateien im aktuellen Verzeichnis hinzu
    git add .                   #Fügt alle Dateien im aktuellen Verzeichnis hinzu
    git add -A                  #Fügt alle veränderten/neuen Dateien im Repository hinzu 
```

Versuche einige Dateien zur Staging Area hinzuzufügen und rufe dazwischen immer wieder `git status` auf und beobachte was sich verändert. Die Ausgabe von `git status` gibt dir auch einen Hinweis wie du Dateien wieder aus der Staging Area wieder entfernst.

Wenn du eine Datei nochmals veränderst, nachdem du sie in die Staging Area hinzugefügt hast, musst du sie erneut hinzufügen, wenn auch die neuen Änderungen Teil des nächsten Commits werden sollen. Git merkt eine Datei in exakt dem Zustand für den Commit vor, in dem sie sich befindet, wenn du den Befehl `git add` ausführst.

Jetzt können wir endlich unseren ersten Commit machen! Ein Commit erfolgt mit dem folgenden Befehl:

```bash
  git commit -m "Nachricht"
```

Die Nachricht beschreibt deinen Commit (z.B "Nullpointer Exception bei UE18/Task3 behoben").

Nach dem Commit kannst du weiterarbeiten, Änderungen wieder zur Staging-Area hinzufügen und einen neuen Commit machen. Der übliche Ablauf ist:

```bash
    «edit» dateien

    git add dateien   # <-- Datei zum commit hinzufügen
    git status           # <-- Kontrolle!

    git commit -m "Nachricht"
```

Achtung: Mache kein Commit ohne vorher mittels `git status` zu kontrollieren, welche Dateien man commited.

Der Befehl `git log` zeigt dir eine Liste deiner Commits an.

## Respository auf einen Server übertragen ("Push")

Bis jetzt sind alle Änderungen lokal gespeichert. Wir wollen nun das Repository auf einen Server übertragen, um einerseits ein Backup zu haben und andererseits das Projekt einfach auf andere Rechner zu übertragen.

### Remote Repository festlegen (einmalig)

Wenn ein Repository lokal **neu angelegt** wurde (nicht wenn man ein existierendes Projekt von einem Server [klont]{#clone}!) und es mit einem Repository auf einem Server synchronisiert werden soll, muss man git den Pfad zu diesem remote repository bekannt geben.

Wurde das Repository von einem Server geklont (und nicht neu angelegt) ist der Pfad bereits bekannt und muss nicht konfiguriert werden.

Wir legen den Pfad mit folgendem Befehl fest:

```bash
git remote add origin <serverURL:repoName>
```

"origin" ist dabei die lokale Bezeichnung des Endpunkts. Das könnte auch ein anderer Name sein und es können auch mehrere Endpunkte konfiguriert werden, "origin" ist aber die Standardbezeichnung.

Wie die serverURL und der repoName lauten, teilen dir deine Lehrer mit.

Für ein Repository am Netzwerkserver kann der Befehl zum Beispiel folgendermaßen aussehen:

```bash
git remote add origin git@netzwerktechnik.htl.rennweg.at:22-2ai/buc/sew2
```

**Achtung! Finde heraus wie der Pfad zu *deinem persönlichen Repository* lautet bevor du den Befehl ausführst.**

Um zu sehen welches remote Repository konfiguriert ist, dient folgender Befehl:

```bash
git remote -v
```

Wenn man sich vertippt hat, kann man den Pfad für einen Endpunkt auch ändern.

```bash
git remote set-url origin <serverURL:repoName>
```

Anmerkung: Der git-Server in der Schule ist so konfiguriert, dass beim ersten Übertragen eines neuen Repositories auf den Server das Repository am Server automatisch angelegt wird. Der Schulserver erlaubt aber nur fix vorgegebene Repository-Namen. Dein lokaler Ordnername kann aber völlig anders lauten als der Repository-Name am Server!

### Änderungen auf den Server übertragen

Jedes Mal wenn wir alle neuen Commits an den Server übertragen wollen, machen wir einen git push - wir "pushen" die Änderungen an den Server.

Der allererste Push wird mit folgendem Befehl ausgeführt^[Warum das so ist lernen wir später - Stichwort "Branch"]:

```bash
 git push --set-upstream origin main
```

Für jede weitere Übertragung reicht ein:

```bash
 git push 
```

Das heißt nach jedem Commit, oder spätestens bevor du deine Arbeit beendest, solltest du ein git push ausführen, damit deine Arbeit am Server gesichert ist.

## Ein Repository vom Server holen ("Klonen") {#clone}

Nehmen wir an, du hast dein git-Repository auf einem Server gespeichert und möchtest nun zuhause auf einem anderen Rechner weiter arbeiten. Es könnte auch sein, dass du dir ein Repository ansehen möchtest, das du gar nicht selbst angelegt hast (z.B. ein Repository mit Codebeispielen). Dann "klonst" du ein git Repository von einem Server auf deinen lokalen Rechner.

Stelle dich also in den Ordner, in dem du das Repository speichern möchtest und führe den Befehl zum Klonen eines Repositories aus.

```bash
git clone <serverURL:repoName>
```

Beispiel:

```bash
git clone git@netzwerktechnik.htl.rennweg.at:22-2ai/buc/sew2
```

Wenn der lokale Ordnername anders als der Repository-Namen am Server sein soll, kannst du den lokalen Ordnernamen ("sew2_uebungen" im Beispiel) am Ende des Befehls angeben:

```bash
git clone git@netzwerktechnik.htl.rennweg.at:22-2ai/buc/sew2 sew2_uebungen
```

Wechsle anschließend **in den angelegten Repository-Ordner**. Hier kannst du nun alle bekannten git Befehle (add, commit, push...) ausführen.

## Änderungen vom Server abgleichen ("Pull")

Nehmen wir folgendes Szenario an: Du hast in der Schule ein Repository angelegt und dieses auf einen Server übertragen. Zuhause hast du das Repository geklont, weiter gearbeitet, die Änderungen comittet und an den Server übertragen (push). Jetzt sitzt du wieder in der Schule und möchtest mit dem Status von zuhause weiterarbeiten. Dafür musst du jetzt **nicht** das Repository erneut klonen, weil es ist auf deinem Schulrechner ja bereits vorhanden. Du möchtest lediglich die aktuellen Änderungen (i.e. Commits) vom Server laden.

Dafür sorgt folgender Befehl:

```bash
git pull
```

Bevor du also an einem Projekt weiter arbeitest solltest du immer zuerst ein git pull ausführen.

## Üblicher Ablauf

Ein oder mehrmals:

```bash
    <<edit>>
    git add ...
    git commit ...
```

dann die "Änderungen" zum Server schicken (*ganze Geschichte* dh. alle commits die der Server nicht hat):

```bash
    git push
```

Falls es Änderungen am Server gibt -- vorher alle lokalen Änderungen sichern:

```bash
    git add ...
    git commit ...
```

und dann Änderungen holen

```bash
    git pull
```

<!-- ## Webinterface

Man kann im Browser seine Repositories am Server kontrollieren.

Dazu muss man ein Passwort mit folgendem Befehl setzen: 

```bash
ssh git@netzwerktechnik.htl.rennweg.at htpasswd
```

Achtung: Das Passwort ist bei der Eingabe sichtbar.

Die Adresse der Weboberfläche lautet <https://netzwerktechnik.htl.rennweg.at/gitweb/>.
 -->

## Allgemeines

### Repositories am Netzwerktechnik Server

Eine Liste der möglichen Repositories (mit Wildcards) kann mit folgendem Befehl angezeigt werden:

```bash
    ssh git@netzwerktechnik.htl.rennweg.at
```
