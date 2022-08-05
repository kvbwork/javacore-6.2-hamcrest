package kvbdev;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Collection<Person> persons = generatePersons(10_000_000);

        System.out.println("Количество несовершеннолетних: ");
        System.out.println(countUnderage(persons));

        System.out.println("Список фамилий призывников (без повторов):");
        System.out.println(
                getConscriptsFamilies(persons).stream()
                        .distinct()
                        .collect(Collectors.toList())
        );

        System.out.println("Список работоспособных людей с высшим образованием (первые 50):");
        getWorkablePersons(persons).stream()
                .limit(50)
                .forEach(System.out::println);

    }

    public static long countUnderage(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
    }

    public static List<String> getConscriptsFamilies(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> Sex.MAN.equals(person.getSex()))
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
    }

    public static List<Person> getWorkablePersons(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> Education.HIGHER.equals(person.getEducation()))
                .filter(person -> person.getAge() >= 18)
                .filter(person ->
                        (Sex.MAN.equals(person.getSex()) & person.getAge() < 65) ||
                                (Sex.WOMAN.equals(person.getSex()) & person.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }

    public static Collection<Person> generatePersons(int size) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        return persons;
    }
}
