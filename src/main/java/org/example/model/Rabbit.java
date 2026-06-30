package org.example.model;

public final class Rabbit extends Animal{
    public Rabbit(AnimalId id, String name, int age){
        super(id,name,age);
    }

    @Override
    public Species getSpecies(){
        return Species.RABBIT;
    }
}
