package org.example.model;


import lombok.Getter;

@Getter
public sealed abstract class Animal permits Dog, Cat, Bird, Rabbit {
    private final AnimalId id;
    private String name;
    private int age;
    private AdoptionStatus adoptionStatus = AdoptionStatus.AVAILABLE;

    protected Animal(AnimalId id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void markAsAdopted() {
        this.adoptionStatus = AdoptionStatus.ADOPTED;
    }

    public abstract Species getSpecies();

    @Override
    public String toString() {
        return id + " | " + name + " | " + age + " years old | " + getSpecies() + " | " + adoptionStatus;
    }
}