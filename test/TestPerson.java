import de.kacper.main.Child;
import de.kacper.main.Grandparent;
import de.kacper.main.Parent;
import de.kacper.main.Person;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPerson {

    List<Person> refList;
    List<Person> testList;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    void init() {
        refList = new ArrayList<>();
        refList.add(new Parent(LocalDate.of(1998,1,7), 196, "Kacper", "S"));
        refList.add(new Grandparent(LocalDate.of(1996,5,3), 179, "Anton", "F"));
        refList.add(new Child(LocalDate.of(2002,4,23), 180, "Rebecca", "M"));
        System.setOut(new PrintStream(out));
    }

    @AfterAll
    void finish() {
        System.setOut(originalOut);
    }

    @AfterEach
    void cleanup() {
        out.reset();
    }



    @BeforeEach
    void setup() {
        testList = new ArrayList<>(refList);
    }

    @Test
    @DisplayName("Test sorting Person List by height")
    void testSortHeight() {
        List<Person> sorted = Person.sortByHeight(testList, false);
        assertEquals(179, sorted.get(0).getHeight());
        assertEquals(180, sorted.get(1).getHeight());
        assertEquals(196, sorted.get(2).getHeight());
        sorted = Person.sortByHeight(testList, true);
        assertEquals(196, sorted.get(0).getHeight());
        assertEquals(180, sorted.get(1).getHeight());
        assertEquals(179, sorted.get(2).getHeight());
    }

    @Test
    @DisplayName("Test sorting Person List by age")
    void testSortingAge() {
        List<Person> sorted = Person.sortByAge(testList, false);
        assertEquals(20, sorted.get(0).getAge());
        assertEquals(25, sorted.get(1).getAge());
        assertEquals(26, sorted.get(2).getAge());
        sorted = Person.sortByAge(testList, true);
        assertEquals(26, sorted.get(0).getAge());
        assertEquals(25, sorted.get(1).getAge());
        assertEquals(20, sorted.get(2).getAge());
    }

    @Test
    @DisplayName("Test relations by printing info")
    void testRelation() {
        Parent parent = (Parent) testList.get(0);
        Grandparent grandparent = (Grandparent) testList.get(1);
        Child child = (Child) testList.get(2);
        child.addParent(parent);
        parent.addChild(child);
        parent.addParent(grandparent);
        grandparent.addChild(parent);
        grandparent.printInfo();
        String res = out.toString();
        Assertions.assertTrue(res.contains("Anton F") && res.contains("Kacper S") && res.contains("Rebecca M"));
        out.reset();
        child.printInfo();
        res = out.toString();
        Assertions.assertTrue(res.contains("Anton F") && res.contains("Kacper S") && res.contains("Rebecca M"));
        out.reset();
        parent.printInfo();
        res = out.toString();
        Assertions.assertTrue(res.contains("Anton F") && res.contains("Kacper S") && res.contains("Rebecca M"));

    }

}
