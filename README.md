# Practical Task: Animal Shelter Console App

Starter project for M3A practical task: Create a simple Java console application for managing animals in an animal shelter.

## Task
Complete all of the 'TODO' portions of the code. Finished application should support:
- [x] Adding a new animal
- [x] Listing all animals
- [x] Searching animals by species
- [x] Marking an animal as adopted
- [x] Displaying only available animals

## OOP Requirements
- [x] Class anatomy: fields, constructors, methods
- [x] Object isntantiation and usage
- [x] Constructor overloading
- [x] Immutable class usage (AnimalId)
- [x] Lombok usage for reducing boilerplate
- [x] A basic sealed class hierarchy
- [x] Generic (Shelter<T>) class that stores animals

## Project Structure
``` text
src
├── Main.java
├── menu/
│   ├── ConsoleMenu.java
│   └── MenuOption.java
├── model/
│   ├── Animal.java
│   ├── Dog.java
│   ├── Cat.java
│   ├── Bird.java
│   ├── Rabbit.java
│   ├── Species.java
│   ├── AnimalId.java
│   ├── AdoptionStatus.java
│   └── AdoptionRecord.java
├── shelter/
│   └── Shelter.java
└── util/
    └── AnimalStats.java
```

## Stretch goals
- [x] Add a new animal type without modifying existing functionality *(Rabbit added)*
- [x] Allow sorting animals by age or name
- [x] Validate user input (no empty names, age cannot be negative etc.)
- [x] Create a generic utility class for searching and filtering collections (Average animal age, Oldest animal, Number of animals of each species etc.) *(`AnimalStats`)*
- [x] Add adoption history that tracks:
  - [x] Animal
  - [x] Adoption Date
  - [x] Adopter Name
