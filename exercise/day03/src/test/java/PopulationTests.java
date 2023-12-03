import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import people.Person;
import people.Pet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static people.PetType.BIRD;
import static people.PetType.CAT;
import static people.PetType.DOG;
import static people.PetType.HAMSTER;
import static people.PetType.SNAKE;
import static people.PetType.TURTLE;

class PopulationTests {
    private static List<Person> population;

    @BeforeAll
    static void setup() {
        population = Arrays.asList(
                // use static import for enum to be less verbose
                new Person("Peter", "Griffin")
                        .addPet(CAT, "Tabby", 2),
                new Person("Stewie", "Griffin")
                        .addPet(CAT, "Dolly", 3)
                        .addPet(DOG, "Brian", 9),
                new Person("Joe", "Swanson")
                        .addPet(DOG, "Spike", 4),
                new Person("Lois", "Griffin")
                        .addPet(SNAKE, "Serpy", 1),
                new Person("Meg", "Griffin")
                        .addPet(BIRD, "Tweety", 1),
                new Person("Chris", "Griffin")
                        .addPet(TURTLE, "Speedy", 4),
                new Person("Cleveland", "Brown")
                        .addPet(HAMSTER, "Fuzzy", 1)
                        .addPet(HAMSTER, "Wuzzy", 2),
                new Person("Glenn", "Quagmire")
        );
    }

    @Test
    void whoOwnsTheYoungestPet() {
        // always go to next line in a stream, it is easier to read
        var filtered = population.stream()
                // extracting comparator in a method facilitating readability, I want to concentrate on my business logic
                // I let this method in the test because I don't want to add production code for my test purpose
                .min(Comparator.comparingInt(this::getYoungestPetAge))
                .orElse(null);

        assert filtered != null;
        assertThat(filtered.firstName()).isEqualTo("Lois");
    }

    private int getYoungestPetAge(Person person) {
        return person.pets().stream()
                .mapToInt(Pet::age)
                .min()
                .orElse(Integer.MAX_VALUE);
    }
}
