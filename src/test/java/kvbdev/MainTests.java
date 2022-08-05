package kvbdev;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MainTests {

    private static Collection<Person> testPersons = null;
    private static int expectedUnderageCount = -1;
    private static int expectedConscriptsFamiliesCount = -1;
    private static int expectedWorkablePersonsCount = -1;

    @BeforeAll
    public static void beforeAll() {
        testPersons = Collections.unmodifiableCollection(testPersonsSource());

        // count[0..18) * sex * education = 18 * 2 * 4 = 144
        expectedUnderageCount = 144;

        // count[18..27) * sex * education = 9 * 1 * 4 = 36
        expectedConscriptsFamiliesCount = 36;

        // (count[18..60) + count[18..65)) * education = (42 + 47) * 1 = 89
        expectedWorkablePersonsCount = 89;
    }

    @AfterAll
    public static void afterAll() {
        testPersons = null;
    }

    private static Collection<Person> testPersonsSource() {
        ArrayList<Person> persons = new ArrayList<>();
        for (int age = 0; age < 100; age++) {
            for (Education edu : Education.values()) {
                for (Sex sex : Sex.values()) {
                    persons.add(new Person("Man" + age, "Family" + age, age, sex, edu));
                }
            }
        }
        return persons;
    }

    @Test
    public void testGeneratePersons_nonEmptyCollection() {
        // given:
        Collection<Person> randomPersons = null;
        int size = 42;

        // when:
        randomPersons = Main.generatePersons(size);

        // then:
        assertThat(randomPersons, allOf(
                is(notNullValue()),
                is(instanceOf(Collection.class)),
                is(not(emptyCollectionOf(Person.class)))
        ));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 100})
    public void testGeneratePersons_positiveSizeArg_success(int sizeArg) {
        // given:
        Collection<Person> randomPersons = null;

        // when:
        randomPersons = Main.generatePersons(sizeArg);

        // then:
        assertThat(randomPersons, hasSize(sizeArg));
    }


    @Test
    public void testCountUnderage_testPersons_success() {
        // given:
        Collection<Person> persons = testPersons;
        long expected = expectedUnderageCount;

        // when:
        long count = Main.countUnderage(persons);

        // then:
        assertThat(count, is(expected));
    }

    @Test
    public void testGetConscriptsFamilies_testPersons_count_success() {
        // given:
        Collection<Person> persons = testPersons;
        int expected = expectedConscriptsFamiliesCount;
        String[] expectedItems = {"Family18", "Family26"};
        String[] notExpectedItems = {"Family17", "Family27"};

        // when:
        List<String> familiesList = Main.getConscriptsFamilies(persons);

        // then
        assertThat(familiesList, hasSize(expected));
        assertThat(familiesList, both(
                hasItems(expectedItems)).and(not(hasItems(notExpectedItems)))
        );

    }

    @Test
    public void testGetWorkablePersons_testPersons_count_success() {
        // given:
        Collection<Person> persons = testPersons;
        int expected = expectedWorkablePersonsCount;

        // when:
        Collection<Person> workablePersons = Main.getWorkablePersons(persons);

        // then
        assertThat(workablePersons, hasSize(expected));
    }
}