# HomeAway From Home 🏠

**Data Structures and Algorithms (AED) — Project 2025/2026**
NOVA School of Science & Technology

**Authors**
- Guilherme Santos — 65443
- Bernardo Cortez de Lobão — 68022

---

## About the project

`HomeAway From Home` is a console-based Java application built for the AED course. It helps
international students in a campus-oriented town find location-based services relevant to
student life.

The app manages a **geographic bounding area** containing **services** and **students**. A
single area is active at a time, but multiple areas can be saved to and loaded from secondary
memory (serialized `.ser` files kept in the `data` folder).

### Services

Every service has a name and a geographic location (latitude/longitude, stored as integers —
the real decimal value multiplied by 1,000,000,000). Three types of services are supported:

| Type | Extra info |
|---|---|
| **Eating** | daily student menu price, number of seats |
| **Lodging** | monthly room price, number of single rooms |
| **Leisure** | ticket price, student discount percentage |

Services can be evaluated with **star ratings (1–5)** plus a text review, and can be tagged and
searched by keyword.

### Students

Three types of students are supported, each with different behavior:

| Type | Behavior |
|---|---|
| **Bookish** | studies and attends Leisure events; stores every Leisure place visited |
| **Outgoing** | eats out and explores the town; stores every location visited |
| **Thrifty** | always seeks the cheapest eating/lodging option; doesn't store visit history |

Students can move around the area, change their home lodging, and be tracked per country,
per service, or by location.

---

## Commands

The application is driven by a text-based command interpreter (case-insensitive commands).

| Command | Description |
|---|---|
| `bounds` | Defines a new geographic bounding rectangle (area) |
| `save` | Saves the current area to a text file |
| `load` | Loads a previously saved area |
| `service` | Adds a new service (eating, lodging or leisure) |
| `services` | Lists all services, in insertion order |
| `student` | Adds a new student |
| `students` | Lists all students, or students from a given country |
| `leave` | Removes a student from the system |
| `go` | Moves a student to an eating or leisure service |
| `move` | Changes a student's home (lodging) |
| `users` | Lists students currently present in a given service |
| `star` | Evaluates a service (1–5 stars + description) |
| `where` | Locates a student |
| `visited` | Lists the locations visited by a student |
| `ranking` | Lists all services ordered by star rating |
| `ranked` | Lists the closest service(s) of a type with a given rating, relative to a student |
| `tag` | Lists services whose reviews contain a given word |
| `find` | Finds the most relevant service of a type for a given student |
| `help` | Shows all available commands |
| `exit` | Saves the current area (if any) and terminates the program |

Full behavior, arguments and error messages for each command are detailed in the project
specification (`AED_2025_26_TP_v2.pdf`).

---

## Architecture

The design follows an object-oriented model centered on an `App` class that manages the
current `Area`. Key class groups:

- **`App`** — entry point / façade for all commands, holds the `currentArea`.
- **`Area`** — core of the system; owns services, students and countries, and answers
  all area-level queries (search, ranking, tagging, filtering).
- **`Service`** hierarchy — `Service` → `LimitedService` → `Eating` / `Lodging`, plus
  `Leisure` (unlimited capacity). `ServiceType` enum acts as a factory for service creation.
- **`Student`** hierarchy — `Student` → `VisitingStudent` (`Bookish`, `Outgoing`) and
  `Thrifty` (no visit history, cheapest-service logic). `StudentType` enum is the factory.
- **`Country`** — groups students by nationality for the `students <country>` command.
- **`Coordinates`** — record type; encapsulates latitude/longitude and Manhattan distance
  calculation, used by the `ranked` command.

Only classes/interfaces from the provided `dataStructures` package may be used (no
`java.util`, except for I/O such as `Scanner`).

### Data structures used

| Class | Structure | Purpose |
|---|---|---|
| `Area.servicesByInsertion` | `List<Service>` | insertion order — `services` command |
| `Area.servicesByTag` | `Map<String, List<Service>>` | services grouped by review tag — `tag` command |
| `Area.servicesByName` | `SortedMap<String, Service>` | alphabetical lookup — `go`, `move` |
| `Area.servicesByRank` | `List<List<Service>>` | services bucketed by rounded star average — `ranking`, `ranked`, `find` |
| `Area.studentsByName` | `SortedMap<String, Student>` | alphabetical listing — `students` |
| `Area.countries` | `Map<String, Country>` | per-country student grouping |
| `Country.citizens` | `List<Student>` | insertion order for `students <country>` |
| `VisitingStudent.visits` | `List<Service>` | visit history — `visited` command |
| `Service.tags` | `SortedList<String>` | tags applied to a service's reviews |
| `LimitedService.presentStudents` | `TwoWayList<Student>` | bidirectional traversal — `users` (ascending/descending) |

Concrete implementations rely on separate-chaining hash tables (`SepChainHashTable`) and
AVL-based sorted maps (`AVLSortedMap`) where they improve efficiency over simpler
alternatives.

---

## Complexity

Estimated space complexity: **O(3n + 2m + c + 0.5nm + 0.1nm)**, where:

- `n` — number of services in the system
- `m` — number of students in the system
- `c` — number of countries in the system
- `0.5nm` — expected number of services visited per student
- `0.1nm` — expected number of students present per service

Most operations that touch a single service or student run in **O(1)** best case; operations
that must traverse the area's services or students run in **O(n)** worst case.

---

## Project structure & grading

This is a group project (2 students) split into two phases, each with a class diagram
report, a program submission (via Mooshak), and a final report:

| Deliverable | Weight | Deadline |
|---|---|---|
| 1st Class Diagram Report | 1% | 3 Oct 2025 |
| 1st Program (Mooshak, problem A) | 10% | 31 Oct 2025 |
| Final Report of 1st Program | 2% | 31 Oct 2025 |
| 2nd Class Diagram Report | 1% | 7 Nov 2025 |
| 2nd Program (Mooshak, problem B) | 16% | 5 Dec 2025 |
| Final Report of 2nd Program | 5% | 5 Dec 2025 |

For programs that pass all Mooshak tests, functionality is worth 20% and code quality 80% of
the program grade. Each phase also includes an oral discussion (0–20), with the final stage
grade being the minimum of the submitted work and oral discussion grades.

**Constraints:** only classes/interfaces from the provided `dataStructures` package may be
used; the `java.util` package is forbidden except for I/O; for the 2nd program, the `tag`
command's text processing must be implemented without `String` methods.

---

## Testing

Local test results (18 tests, run on a MacBook Air M2): all passing, total runtime **587 ms**
(individual tests ranging from 9 ms to 113 ms).

---

## Notes for future improvement

The group considered replacing `servicesByTag` (`Map<String, List<Service>>`) with a
`SepChainHashTable<String, Service>` to further improve the efficiency of the `tag` command.
