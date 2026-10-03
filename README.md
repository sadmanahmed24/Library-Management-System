
# Library Management System

A Java console application for managing books, members, and borrowing, built to demonstrate core Object-Oriented Programming principles with Oracle database integration.


#Table of Contents

- [About](#about)
- [Features](#features)
- [OOP Concepts Demonstrated](#oop-concepts-demonstrated)
- [Project Structure](#project-structure)
- [Class Design](#class-design)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Development Journey](#development-journey)
- [Future Improvements](#future-improvements)
- [Author](#author)

## About

This project was developed for the **Object-Oriented Programming 1 (Java)** course at American International University-Bangladesh (AIUB). It simulates the day-to-day operations of a library: cataloguing books, registering members, issuing and returning books, and keeping a record of library activity.

The system was designed class-first (class diagram, then responsibilities and methods, then implementation) and later upgraded from flat-file storage to a relational **Oracle** database through JDBC.

# Features
- **Book management:** add, view, search, and update books in the catalogue
- **Member management:** register and manage library members
- **Issue and return:** borrow and return books with records kept up to date
- **Persistent storage:** data stored in an Oracle database (the earlier version used file I/O)
- **Automated librarian:** `AutoLibrarian` handles library operations automatically <!-- TODO: describe exactly what AutoLibrarian does -->
- **Menu-driven interface:** simple console flow launched from `Start.java`

> Edit this list so it matches exactly what your program does. Delete anything that isn't implemented and add anything that is missing.

#OOP Concepts Demonstrated

| Concept | Where it appears |
| --- | --- |
| **Encapsulation** | Private fields with getters and setters in `Book`, `Member`, `Person` |
| **Inheritance** | `Member` extends `Person` |
| **Abstraction** | `Library` hides storage and borrowing logic behind simple methods |
| **Polymorphism** | <!-- TODO: e.g. overridden methods such as toString() or display() --> |
| **Composition / Association** | `Library` manages collections of `Book` and `Member` objects |
| **Separation of concerns** | Database logic is isolated in `DBconnectiion.java` |

> Verify the table against your code before publishing. Examiners and recruiters will check.

# Project Structure

```
Library-Management-System/
├── Start.java            # Entry point, main menu
├── Library.java          # Core logic: books, members, issue/return
├── Book.java             # Book entity
├── Person.java           # Base class for people in the system
├── Member.java           # Library member (extends Person)
├── AutoLibrarian.java    # Automated librarian operations
├── DBconnectiion.java    # Oracle JDBC connection handling
├── File/                 # Data files from the original file-based version
├── Run Commands          # Compile and run instructions
├── logo.png
└── README.md
```

## Class Design

```
        Person
          ▲
          │ extends
        Member

   Library ──── manages ───▶ Book
      │
      └──── manages ───▶ Member

   Library ──── uses ───▶ DBconnectiion
``` 
===== Library Management System =====
1. Add Book
2. Register Member
3. Issue Book
4. Return Book
5. Exit
```

## Development Journey

1. **Design:** drew the class diagram and decided each class's responsibilities and methods.
2. **Requirements:** wrote down the system requirements feature by feature.
3. **Version 1:** implemented using **file I/O** for storage.
4. **Version 2:** migrated storage to an **Oracle database** with JDBC.


## Future Improvements

- [ ] Use `PreparedStatement` for all SQL to prevent SQL injection
- [ ] Add a data access layer (DAO) to separate SQL from business logic
- [ ] Add fines for late returns
- [ ] Add book reservations
- [ ] Add unit tests with JUnit
- [ ] Build a GUI with JavaFX or Swing
- [ ] Move to Maven or Gradle for dependency management

## Author

**Sadman Ahmed**
CSE student, American International University-Bangladesh (AIUB)
GitHub: [@sadmanahmed24](https://github.com/sadmanahmed24)