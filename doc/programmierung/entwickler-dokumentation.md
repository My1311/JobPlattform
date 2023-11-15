## Entwickler Dokumentation

### User Story Features erarbeiten
- Erstellen eines neuen Branches (abgeleitet von Main) mit dem Namen "feature/[User Story Name]'.
- Commited auf den neuen Branch die Codeaenderungen.
- Es muss nach dem MVC-Pattern entwickelt werden
- Hierbei gilt es Domain-Spezifisch (Nach einem Feature) zu entwickeln.
- Beispiel Student
  - Im Model-Ordner Student-Verzeichnis erstellen und dort
    - Model-Klasse Student, DTO-Klasse StudentDTO und Repo: StudentRepository
  - Im Cotroll-Ordner
    - eine Service-Klasse StudentService erstellen
  - Im View-Ordner unter routes eine passende Route erstellen
    - Dort dann eine Klasse fuer die view erstellen
- Achtung
  - Wir benutzen in den View-Klassen (Frontend) immer nur Service-Klassen (Control-Verzeichnis).
    Diese Service-Klassen nutzen dann erst die Domian-Spezifischen Repositorys (StudentRepository).

- Erstelle einen [Merge Request](https://vm-2d21.inf.h-brs.de/inf_se2_wise23_Team_7/inf_se2_wise23_Team_7/-/merge_requests/new) und beantragt das, dass neue Feature auf Main kommt.