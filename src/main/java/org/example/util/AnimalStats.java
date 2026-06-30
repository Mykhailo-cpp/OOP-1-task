package org.example.util;

import org.example.model.Animal;
import org.example.model.Species;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class AnimalStats {

    private AnimalStats() {
    }

    public static <T extends Animal> double averageAge(List<T> animals) {
        return animals.stream()
                .mapToInt(Animal::getAge)
                .average()
                .orElse(0.0);
    }

    public static <T extends Animal> Optional<T> oldest(List<T> animals) {
        return animals.stream()
                .max(Comparator.comparingInt(Animal::getAge));
    }

    public static <T extends Animal> Map<Species, Long> countBySpecies(List<T> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::getSpecies, Collectors.counting()));
    }
}