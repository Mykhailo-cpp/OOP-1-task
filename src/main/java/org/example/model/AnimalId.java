package org.example.model;

import java.util.UUID;

public final class AnimalId {
    private final String value;
    public AnimalId(){
        this.value = UUID.randomUUID().toString();
    }

    public AnimalId(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof AnimalId animalId)) return false;
        return value.equals(animalId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
