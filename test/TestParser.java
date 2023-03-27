import de.kacper.main.Main;
import de.kacper.main.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParser {

    @Test
    @DisplayName("Test validation of input")
    void testInputValidation() {
        String[] inputs = new String[]{"31.07.1909 Boris Brand 187cm",
                "31.07.2020 Boris Brand 187cm",
                "31.47.1989 Boris Brand 187cm",
                "ffffff Boris Brand 187cm",
                "31.07.1989 Boris Brand 205cm",
                "31.07.1989 Boris Brand 120cm",
                "31.07.1989 Boris Brand einszweinull",
                "31.07.1989 Borisssssssss Brand 187cm",
                "31.07.1989 Boris Brandddddddddddddd 187cm",
                "31.07.1989 B Brand 187cm",
                "31.07.1989 Boris B 187cm"};
        for(String input : inputs) {
            assertEquals(0, Main.parseInput(input.split(" ")).size());
        }

        assertEquals(1, Main.parseInput("28.12.1969 Linus Torvalds 177cm".split(" ")).size());
        assertEquals(1, Main.parseInput("28.12.1969 Linus Torvalds 177cm 28.12.1969 Linus".split(" ")).size());
    }

    @Test
    @DisplayName("Test sorting of data")
    void testSorting() {
        String input = "31.07.1999 Aaa Aaa 187cm " +
                "04.06.1981 Bbb Bbb 169cm " +
                "12.03.1982 Ccc Ccc 179cm " +
                "12.03.1980 Ddd Ddd 179cm " +
                "12.03.1988 Eee Eee 179cm " +
                "12.03.1984 Fff Fff 179cm " +
                "12.03.1992 Ggg Ggg 179cm " +
                "09.12.1987 Hhh Hhh 179cm";
        List<Person> list = Main.parseInput(input.split(" "));
        String nameList = list.stream().map(Person::getName)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString().replace(" ", "");
        String refNameList = "BbbBbbGggGggEeeEeeHhhHhhFffFffCccCccDddDddAaaAaa";
        assertEquals(refNameList, nameList);
    }

}
