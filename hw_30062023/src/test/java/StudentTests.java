import by.teachmeskills.model.Sex;
import by.teachmeskills.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTests {
    private static Student student1;
    private static Student student2;
    private static Student student3;

    private static List<Student> actual;
    private static List<Student> actualBySex_MALE;
    private static List<Student> actualBySex_FEMALE;
    private static Double actualAverageAge;
    private static Double actualAverageAgeBySex_MALE;
    private static Double actualAverageAgeBySex_FEMALE;

    private static int cmpDouble(double num1, double num2, double relativeEps, double absoluteAbs) {
        double difference = Math.abs(num1 - num2);
        int index = (num1 > num2) ? 1 : -1;
        if (difference > absoluteAbs || difference > Math.max(num1, num2) * relativeEps) {
            return index;
        }
        return 0;
    }

    @BeforeAll
    public static void setUp() {
        student1 = new Student("Ivan", 17, Sex.MALE);
        student2 = new Student("Anna", 19, Sex.FEMALE);
        student3 = new Student("Polina", 18, Sex.FEMALE);
        actual = new ArrayList<>(List.of(student1, student2, student3));
        actualBySex_MALE = new ArrayList<>(List.of(student1));
        actualBySex_FEMALE = new ArrayList<>(List.of(student2, student3));
        actualAverageAge = 18.0;
        actualAverageAgeBySex_MALE = 17.0;
        actualAverageAgeBySex_FEMALE = 18.5;
    }

    @Test
    public void checkAllStudentsReturned() {
        List<Student> expected = Student.getAllStudents();
        assertEquals(expected, actual);
    }

    @Test
    public void checkAllStudentsReturned_NotNull() {
        List<Student> expected = Student.getAllStudents();
        assertNotNull(expected);
    }

    @Test
    public void getAllUsers_MALE(){
        List<Student> expected = Student.getAllStudentsBySex(Sex.MALE);
        assertEquals(expected, actualBySex_MALE);
    }

    @Test
    public void getAllUsers_FEMALE() {
        List<Student> expected = Student.getAllStudentsBySex(Sex.FEMALE);
        assertEquals(expected, actualBySex_FEMALE);
    }

    @Test
    public void checkAverageAgeOfAllStudents() {
        assertEquals(actualAverageAge, Student.getAverageAgeOfAllStudents());
    }

    @Test
    public void checkAverageAgeBySex_MALE() {
        assertEquals(actualAverageAgeBySex_FEMALE, Student.getAverageOfAllStudentsBySex(Sex.FEMALE));
    }

    @Test
    public void checkAverageAgeBySex_FEMALE() {
        assertEquals(actualAverageAgeBySex_MALE, Student.getAverageOfAllStudentsBySex(Sex.MALE));
    }

}
