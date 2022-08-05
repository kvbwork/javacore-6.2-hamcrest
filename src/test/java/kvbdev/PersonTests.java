package kvbdev;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

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
        assertThat("name", person.getName(), is(name));
        assertThat("family", person.getFamily(), is(family));
        assertThat("age", person.getAge(), is(age));
        assertThat("sex", person.getSex(), is(sex));
        assertThat("education", person.getEducation(), is(edu));
    }

}
