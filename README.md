# Homeaway From Home! 🏠

**Object-Oriented Programming**
NOVA School of Science and Technology (FCT NOVA)

## Authors

- Bernardo Lobão — 68022
- Guilherme Santos — 65443

## Overview

**Homeaway From Home** is a console-based application that supports international students in a campus-oriented town by providing information about location-based services useful to them. The system was built for the Lisbon Metropolitan area (Almada, Monte da Caparica, Costa de Caparica) but is designed to generalize to any town, with a strong focus on extensibility for new service and student types.

The application manages:
- **Services**: location-based points of interest within a defined bounding rectangle
- **Students**: registered users with different behavioral profiles
- **Evaluations**: star-based ratings for services
- **Student mobility**: tracking where students go, live, and (depending on type) what they've visited

## Domain Model

### Services

All services have a `name` and a point location (`latitude`, `longitude`, stored as integers — real values × 10⁹). Every service must lie within the system's defined bounding box.

| Type | Extra Attributes |
|------|-------------------|
| **Eating** | Price of the daily student menu |
| **Lodging** | Weekly price of the student room |
| **Leisure** | Ticket price + student discount percentage |

The service model is designed to be extended with new service types without breaking existing functionality.

### Students

| Type | Behavior |
|------|----------|
| **Bookish** | Focused on studying and leisure; stores only leisure locations visited |
| **Outgoing** | Focused on eating out and exploring; stores every location visited |
| **Thrifty** | Budget-driven; always tracks the cheapest known eating and lodging service, and only relocates when a cheaper option is found |

Each student has a current location (either their home lodging or any known service) and a home (a lodging service).

### Evaluations

- Services are rated with **stars** (1–5).
- A newly created service starts with a default evaluation of **4 stars**.
- The displayed evaluation is the **rounded average** (`Math.round`) of all submitted ratings.
- Services can be listed sorted by evaluation, or filtered by type and specific average.

## Commands

| Command | Description |
|---|---|
| `bounds` | Defines the geographic bounding rectangle of the system (resets all data) |
| `eating` | Adds a new eating service |
| `lodging` | Adds a new lodging service |
| `leisure` | Adds a new leisure service |
| `services` | Lists all services in order of insertion |
| `student` | Registers a new student (bookish / outgoing / thrifty) |
| `students` | Lists all registered students |
| `leave` | Removes a student from the system |
| `go` | Moves a student to a service or back home |
| `move` | Changes a student's home lodging |
| `star` | Submits a star evaluation (1–5) for a service |
| `where` | Shows a student's current location |
| `visited` | Lists locations visited by a student (bookish/outgoing only) |
| `ranking` | Lists all services sorted by descending evaluation |
| `ranked` | Lists services of a given type with a given star average |
| `find` | Finds the most relevant service (nearest, or cheapest) for a student |
| `help` | Displays all available commands |
| `exit` | Terminates the program |

Commands are **case-insensitive**; string arguments (names) are **case-sensitive**. Unknown tokens are each reported individually as `Unknown command`. Error conditions for each command are checked strictly in the order specified by the project statement — the first failing condition determines the output message, but remaining input parameters are always consumed.

### Distance metric

The `find` command uses **Manhattan distance** to determine the nearest service for bookish/outgoing students:

```
d(l1, l2) = |l1.lat - l2.lat| + |l1.long - l2.long|
```

Ties (equal distance or equal price) are resolved by **order of insertion**.

## Design & Architecture

This project emphasizes:
- **Extensibility**: new service types and student types can be added with minimal changes to existing code, via interfaces + abstract base classes + polymorphism (`ServiceType`/`StudentType` enums for classification, dedicated subclasses for behavior).
- **Encapsulation**: interfaces (`Service`, `Student`, `Eating`, `Lodging`, `Leisure`, `Bookish`, `Outgoing`, `Thrifty`, `VisitingStudent`, `LimitedService`) define contracts; `...Class` suffixed classes provide the implementations.
- **Custom data structures only**: per course constraints, this project does **not** use `java.util` Collections. All lists/maps/trees/hash tables under `dataStructures/` are the generic array/BST/AVL/hash-table/linked-list implementations provided in lectures/labs, used unmodified (wrapped where extra behavior was needed).
- **Exception-driven error handling**: every documented error case from the spec (invalid bounds, invalid prices, already-exists, unknown location, etc.) maps to its own checked exception under `exceptions/`, keeping `Commands.java` readable and each business rule self-documenting.

### Project structure

```
src/
├── Main.java                  # Entry point
├── Commands.java              # Command parsing, dispatch & I/O feedback
├── Tests.java                 # Test runner
│
├── system/                    # Core domain
│   ├── App.java / AppClass.java           # Application-level state & orchestration
│   ├── Area.java / AreaClass.java         # Bounding rectangle ("bounds") logic
│   ├── Country.java / CountryClass.java   # Country/region grouping
│   ├── Coordinates.java                   # Lat/long point representation
│   │
│   ├── service/
│   │   ├── Service.java                       # Base interface
│   │   ├── LimitedService.java                # Interface for services with capacity limits
│   │   ├── AbstractServiceClass.java          # Shared service implementation
│   │   ├── AbstractLimitedServiceClass.java
│   │   ├── Eating.java / EatingServiceClass.java
│   │   ├── Lodging.java / LodgingServiceClass.java
│   │   ├── Leisure.java / LeisureServiceClass.java
│   │   ├── ServiceType.java                   # eating / lodging / leisure classification
│   │   ├── ServiceTypeFilter.java             # Filter services by type
│   │   ├── ServiceDistanceFilter.java         # Filter by Manhattan distance (for `find`)
│   │   ├── ServiceTagFilter.java / TagComparator.java
│   │
│   └── student/
│       ├── Student.java                       # Base interface
│       ├── VisitingStudent.java                # Interface for students that record visits
│       ├── AbstractStudentClass.java
│       ├── AbstractVisitingStudentClass.java
│       ├── Bookish.java / BookishStudentClass.java
│       ├── Outgoing.java / OutgoingStudentClass.java
│       ├── Thrifty.java / ThriftyStudentClass.java
│       └── StudentType.java                   # bookish / outgoing / thrifty classification
│
├── exceptions/                 # One exception per documented error case
│   ├── UndefinedBoundsException / InvalidBoundsException / ExistingBoundException
│   ├── InvalidMenuPriceException / InvalidRoomPriceException / InvalidTicketPriceException
│   ├── InvalidDiscountException / InvalidLocationException / InvalidCapacityException
│   ├── AlreadyExistsException / AlreadyHomeException / AlreadyThereException
│   ├── NonExistingStudentException / NonExistingLodgingException / NonExistingBoundsException
│   ├── UnknownLocationException / UnacceptableMoveException / ThriftyStudentException
│   ├── InvalidEvaluationException / InvalidStudentTypeException / InvalidServiceTypeException
│   ├── NoServicesWithAvgException / NoServicesWithTypeException
│   ├── ServiceFullException / InvalidServiceException / InvalidOrderException
│
└── dataStructures/             # Provided generic structures — used, never modified
    ├── List.java / ListInArray.java / SortedList.java
    ├── SinglyLinkedList.java / DoublyLinkedList.java / TwoWayList.java / SortedDoublyLinkedList.java
    ├── Map.java / MapSinglyList.java / SortedMap.java / BSTSortedMap.java / AVLSortedMap.java
    ├── HashTable.java / ClosedHashTable.java / SepChainHashTable.java
    ├── Tree.java / BTree.java / AdvancedBSTree.java / AVLNode.java / BTNode.java
    ├── Iterator variants (Array/Singly/Doubly/TwoWay/Filter/InOrder/Keys/Values)
    └── exceptions/              # Structure-level exceptions (empty stack/queue/map, invalid position, etc.)
```

## Building & Running

Using IntelliJ's project file (`Homeaway from Home.iml`) or plain `javac`:

```bash
# Compile
javac -d out/production/"Homeaway from Home" src/*.java src/**/*.java

# Run interactively
java -cp out/production/"Homeaway from Home" Main
```

## Testing

The `tests/` directory holds paired input/expected-output files (`input1`…`input18`, `output1`…`output18`), mirroring Mooshak's evaluation format:

```bash
java -cp out/production/"Homeaway from Home" Main < tests/input1 > actual1
diff actual1 tests/output1
```

`Tests.java` can be used to automate running all 18 cases and diffing results in one go.

Tests were built incrementally alongside each command, following the recommended order:

1. `help` / `exit`
2. `bounds`
3. `eating` / `lodging` / `leisure` / `services`
4. `student` / `students` / `leave`
5. `go` / `move` / `where` / `visited`
6. `star` / `ranking` / `ranked`
7. `find` (most complex — implemented last)

## Serialization

`.ser` files at the repo root (e.g. `costa-da-caparica.ser`, `portugal.ser`, `tokyo.ser`, `algeria.ser`, `buenos-aires.ser`, `madagascar.ser`, `costa-anywhere.ser`, `costa-do-sol.ser`, `portugal-continental.ser`) are serialized snapshots of different `bounds`/area configurations, used for saving and restoring system state across runs or test scenarios.

## Submission History

Incremental Mooshak submissions are tracked as `sub1.zip` … `sub10.zip`, corresponding to each checkpoint/feature increment as the command set was built out.

## Submission Notes

- **Mooshak contest**: POO2025-TP1
- **Mooshak username**: `65443_68022` (smallest student number first)
- **Deadline**: 23h55 (Lisbon time), May 2nd, 2025
- Code must not be shared outside the group and must comply with the NOVA University Code of Ethics.

## License

Academic project developed for the Object-Oriented Programming course at NOVA FCT — not intended for external distribution.
