package kvbdev;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PersonTests {

    @Test
    public void testAllArgsConstructor_newInstance() {
        // given:
        String name = "NAME";
        String family = "FAMILY";
        int age = 99;
        Sex sex = Sex.MAN;
        Education edu = Education.ELEMENTARY;

        // when:
        Person person = new Person(name, family, age, sex, edu);

        // then:
        assertThat("Person constructor is worked", person, isA(Person.class));
    }

    @Test
    public void testAllGettersReturnsValues() {
        // given:
        String name = "NAME";
        String family = "FAMILY";
        int age = 99;
        Sex sex = Sex.MAN;
        Education edu = Education.ELEMENTARY;

        // when:
        Person person = new Person(name, family, age, sex, edu);

        // then:
        assertThat(person.getName(), is(name));
        assertThat(person.getFamily(), is(family));
        assertThat(person.getAge(), is(age));
        assertThat(person.getSex(), is(sex));
        assertThat(person.getEducation(), is(edu));
    }

}
