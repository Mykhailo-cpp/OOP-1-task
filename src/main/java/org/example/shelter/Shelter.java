package org.example.shelter;

import org.example.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();
    private final List<AdoptionRecord> adoptionHistory = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return new ArrayList<>(animals);
    }

    public List<T> findBySpecies(Species species){
        return animals.stream()
                .filter(animal -> animal.getSpecies() == species)
                .toList();
    }

    public List<T> findAvailableAnimals(){
        return animals.stream()
                .filter(animal -> animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE)
                .toList();
    }

    public void markAsAdopted(AnimalId id, String adopterName){
        animals.stream()
                .filter(animal -> animal.getId().equals(id))
                .findFirst()
                .ifPresent(animal -> {
                    animal.markAsAdopted();
                    adoptionHistory.add(new AdoptionRecord(animal, LocalDate.now(), adopterName));
                });
    }

    public List<T> sortByAge(){
        List<T> sorted = new ArrayList<>(animals);
        sorted.sort(Comparator.comparingInt(Animal::getAge));
        return sorted;
    }

    public List<T> sortByName(){
        List<T> sorted = new ArrayList<>(animals);
        sorted.sort(Comparator.comparing(Animal::getName));
        return sorted;
    }

    public List<AdoptionRecord> getAdoptionHistory(){
        return new ArrayList<>(adoptionHistory);
    }
}
