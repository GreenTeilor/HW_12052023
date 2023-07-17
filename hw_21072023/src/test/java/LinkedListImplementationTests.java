import by.teachmeskills.linkedList.LinkedListImplementation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListImplementationTests {

    private static LinkedListImplementation<Integer> list1;
    private static List<Integer> actualList1;

    private static LinkedListImplementation<Integer> list2;
    private static List<Integer> actualList2;

    @BeforeAll
    public static void setup() {
        list1 = new LinkedListImplementation<>(List.of(1, 4, 5, 16, 32, 11, 13, 92, 6));
        actualList1 = new LinkedList<>(List.of(1, 4, 5, 16, 32, 11, 13, 92, 6));
        list2 = new LinkedListImplementation<>();
        actualList2 = new LinkedList<>();
    }

    @Test
    public void checkAddByIndex() {
        list1.add(2, 6);
        actualList1.add(2, 6);
        assertEquals(list1.toString(), actualList1.toString(), "Add in the middle doesn't work");

        list1.add(0, 62);
        actualList1.add(0, 62);
        assertEquals(list1.toString(), actualList1.toString(), "Add in the head doesn't work");

        list1.add(7, 31);
        actualList1.add(7, 31);
        assertEquals(list1.toString(), actualList1.toString(), "Add in the tail doesn't work");

        list2.add(0, 1);
        actualList2.add(0, 1);
        assertEquals(list2.toString(), actualList2.toString(), "Add in the empty list doesn't work");
    }

    @Test
    public void checkAdd() {
        list1.add(17);
        actualList1.add(17);
        assertEquals(list1.toString(), actualList1.toString(), "Add doesn't work");
    }

    @Test
    public void checkRemoveByIndex() {
        list1.remove(3);
        actualList1.remove(3);
        assertEquals(list1.toString(), actualList1.toString(), "Remove by index doesn't work");

        list1.remove(0);
        actualList1.remove(0);
        assertEquals(list1.toString(), actualList1.toString(), "Remove by index in the head doesn't work");

        list1.remove(2);
        actualList1.remove(2);
        assertEquals(list1.toString(), actualList1.toString(), "Remove by index in the tail doesn't work");
    }

    @Test
    public void checkRemoveByElement() {
        list1.remove(Integer.valueOf(16));
        actualList1.remove(Integer.valueOf(16));
        assertEquals(list1.toString(), actualList1.toString(), "Remove by element doesn't work");
    }

    @Test
    public void checkGet() {
        assertEquals(list1.get(3), actualList1.get(3), "Get doesn't work");
    }

    @Test
    public void checkContains() {
        assertEquals(list1.contains(32), actualList1.contains(32), "Contains when present doesn't work");
        assertEquals(list1.contains(1001), actualList1.contains(1001), "Contains when not present doesn't work");
    }

    @Test
    public void checkIsEmpty() {
        assertEquals(list1.isEmpty(), actualList1.isEmpty(), "IsEmpty doesn't work");
    }

    @Test
    public void checkIndexOf() {
        assertEquals(list1.indexOf(32), actualList1.indexOf(32), "IndexOf when present doesn't work");
        assertEquals(list1.indexOf(-6), actualList1.indexOf(-6), "IndexOf when not present doesn't work");
    }

    @Test
    public void checkLastIndexOf() {
        assertEquals(list1.lastIndexOf(32), actualList1.lastIndexOf(32), "LastIndexOf when present doesn't work");
        assertEquals(list1.lastIndexOf(-6), actualList1.lastIndexOf(-6), "LastIndexOf when not present doesn't work");
    }

    @Test
    public void checkAddAll() {
        list1.addAll(List.of(1, 2, 3));
        actualList1.addAll(List.of(1, 2, 3));
        assertEquals(list1.toString(), actualList1.toString(), "AddAll doesn't work");
    }

    @Test
    public void checkRetainAll() {
        list1.retainAll(List.of(1, 2, 3, 32));
        actualList1.retainAll(List.of(1, 2, 3, 32));
        assertEquals(list1.toString(), actualList1.toString(), "RetainAll doesn't work");
    }

    @Test
    public void checkRemoveAll() {
        list1.removeAll(List.of(1, 13));
        actualList1.removeAll(List.of(1, 13));
        assertEquals(list1.toString(), actualList1.toString(), "RemoveAll doesn't work");
    }

}
