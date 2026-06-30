package org.example.menu;

import lombok.RequiredArgsConstructor;
import org.example.model.*;
import org.example.shelter.Shelter;
import org.example.util.AnimalStats;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            printMenu();
            System.out.println("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    listAllAnimals();
                    break;
                case 3:
                    findBySpecies();
                    break;
                case 4:
                    listAvailableAnimals();
                    break;
                case 5:
                    markAnimalAsAdopted();
                    break;
                case 6:
                    sortByAge();
                    break;
                case 7:
                    sortByName();
                    break;
                case 8:
                    showStatistics();
                    break;
                case 9:
                    listAdoptionHistory();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("""
            1. Add animal
            2. List all animals
            3. Find animals by species
            4. List available animals
            5. Mark animal as adopted
            6. Sort animals by age
            7. Sort animals by name
            8. Show statistics
            9. View adoption history
            0. Exit
            """);
    }

    private void addAnimal() {
        System.out.println("Enter animal species(DOG, CAT, BIRD, RABBIT): ");
        Species species;
        try {
            species = Species.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown species. Animal not added.");
            return;
        }

        System.out.println("Enter animal name: ");
        String name = scanner.nextLine();
        if (name.isBlank()) {
            System.out.println("Name cannot be empty. Animal not added.");
            return;
        }

        System.out.println("Enter animal age: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Age must be a number. Animal not added.");
            return;
        }

        if (age < 0) {
            System.out.println("Age cannot be negative. Animal not added.");
            return;
        }

        Animal animal;
        switch (species) {
            case DOG:
                animal = new Dog(new AnimalId(), name, age);
                break;
            case CAT:
                animal = new Cat(new AnimalId(), name, age);
                break;
            case BIRD:
                animal = new Bird(new AnimalId(), name, age);
                break;
            case RABBIT:
                animal = new Rabbit(new AnimalId(), name, age);
                break;
            default:
                System.out.println("Invalid species. Animal not added.");
                return;
        }

        shelter.addAnimal(animal);
        System.out.println("Added: " + animal);
    }

    private void listAllAnimals() {
        List<Animal> animals = shelter.getAllAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals in the shelter.");
            return;
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private void findBySpecies() {
        System.out.println("Enter species(DOG, CAT, BIRD, RABBIT) to search for: ");
        Species species;
        try {
            species = Species.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown species. Animal not added.");
            return;
        }

        List<Animal> matches = shelter.findBySpecies(species);

        if (matches.isEmpty()) {
            System.out.println("No animals found for species: " + species);
            return;
        }
        for (Animal animal : matches) {
            System.out.println(animal);
        }
    }

    private void listAvailableAnimals() {
        List<Animal> availableAnimals = shelter.findAvailableAnimals();

        if (availableAnimals.isEmpty()) {
            System.out.println("No available animals for adoption.");
            return;
        }
        for (Animal animal : availableAnimals) {
            System.out.println(animal);
        }
    }

    private void markAnimalAsAdopted() {
        System.out.println("Enter the ID of the animal to mark as adopted: ");
        String idString = scanner.nextLine();
        AnimalId id = new AnimalId(idString);

        System.out.println("Enter adopter's name: ");
        String adopterName = scanner.nextLine();
        if (adopterName.isBlank()) {
            System.out.println("Adopter name cannot be empty. Adoption not recorded.");
            return;
        }

        shelter.markAsAdopted(id, adopterName);
        System.out.println("Animal with ID " + id + " marked as adopted.");
    }

    private void sortByAge() {
        List<Animal> sorted = shelter.sortByAge();
        if (sorted.isEmpty()) {
            System.out.println("No animals in the shelter.");
            return;
        }
        sorted.forEach(System.out::println);
    }

    private void sortByName() {
        List<Animal> sorted = shelter.sortByName();
        if (sorted.isEmpty()) {
            System.out.println("No animals in the shelter.");
            return;
        }
        sorted.forEach(System.out::println);
    }

    private void showStatistics() {
        List<Animal> animals = shelter.getAllAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals in the shelter yet.");
            return;
        }

        System.out.printf("Average age: %.1f years%n", AnimalStats.averageAge(animals));

        AnimalStats.oldest(animals).ifPresent(oldest ->
                System.out.println("Oldest animal: " + oldest));

        Map<Species, Long> counts = AnimalStats.countBySpecies(animals);
        counts.forEach((species, count) -> System.out.println(species + ": " + count));
    }

    private void listAdoptionHistory() {
        List<AdoptionRecord> history = shelter.getAdoptionHistory();

        if (history.isEmpty()) {
            System.out.println("No adoptions recorded yet.");
            return;
        }

        history.forEach(record ->
                System.out.println(record.adopterName() + " adopted " + record.animal().getName()
                        + " on " + record.adoptionDate()));
    }
}