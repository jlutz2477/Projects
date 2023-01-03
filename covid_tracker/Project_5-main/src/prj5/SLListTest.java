/**
 * 
 */
package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import student.TestCase;

/**
 * @author srinjoydey
 * @version 2022.04.21
 *          Test class for the SLList class. This will ensure that all the
 *          methods
 *          that are in the SLList class are working correctly.
 *
 */
public class SLListTest extends TestCase {
    // ~Fields...............................................
    private SLList<String> list;

    // ~Methods.............................................

    /**
     * Setup method for the Test class. This method will instantiate the list
     * field and run before every test method
     */
    public void setUp() {
        list = new SLList<String>();
    }


    /**
     * Test for the isEmpty method. THis should return true if the list is empty
     * (size == 0) else false should be returned
     */
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("String1");
        assertFalse(list.isEmpty());
    }


    /**
     * Test for the add method. This method should add a element to the linked
     * list, therefore updating its size.
     */
    public void testAdd() {
        assertEquals(list.getNumberOfEntries(), 0);
        for (int i = 0; i < 5; i++) {
            list.add("String" + i);
        }
        assertEquals(list.getNumberOfEntries(), 5);
        assertEquals(list.getDataAtNode(0), "String0");
        for (int i = 0; i < 5; i++) {
            assertEquals(list.getDataAtNode(i), "String" + i);
        }

    }


    /**
     * Test for the getFirstNode method. This method should return the first
     * node of the class
     */
    public void testGetFirstNode() {
        assertNull(list.getFirstNode());
        list.add("Hello");

    }


    /**
     * Test for the toString method. This method should return a string
     * representation of the object.
     */
    public void testToString() {
        assertEquals(list.toString(), "[]");
        
        Race newRace = new Race("white", 10, 10);
        Race newRacea = new Race("black", 10, 10);
        Race newRaces = new Race("latinx", 10, 10);
        Race newRaced = new Race("asian", 10, 10);
        Race newRacef = new Race("other", 10, 10);
        SLList<Race> raceList = new SLList<Race>();
        raceList = new SLList<Race>();
        raceList.add(newRace);
        raceList.add(newRacea);
        raceList.add(newRaces);
        raceList.add(newRaced);
        raceList.add(newRacef);
        String compare = "white: 10 cases, 100% CFR\n"
            + "black: 10 cases, 100% CFR\n"
            + "latinx: 10 cases, 100% CFR\n"
            + "asian: 10 cases, 100% CFR\n"
            + "other: 10 cases, 100% CFR\n"
            + "=====";
        assertEquals(raceList.toString(), compare);
        // list.add("Hello");
        // assertEquals(list.toString(), "[Hello]"); will need to be tested with
        // toString of state or race
    }


    /**
     * Test for the clear method. This method should clear the list by setting
     * the first node equal to null and setting the number of entries to be 0
     */
    public void testClear() {
        list.add("Hamilton");
        list.add("Verstappen");
        list.add("Bottas");
        assertNotNull(list.getFirstNode());
        list.clear();
        assertEquals(list.getNumberOfEntries(), 0);
        assertNull(list.getFirstNode());
    }


    /**
     * Test for the getData at node method. This method should return the
     * appropriate data at the node
     */
    public void testGetDataAtNode() {
        list.add("Hamilton");
        list.add("Verstappen");
        list.add("Bottas");
        assertEquals(list.getDataAtNode(0), "Hamilton");
        assertEquals(list.getDataAtNode(1), "Verstappen");
        assertEquals(list.getDataAtNode(2), "Bottas");
    }


    /**
     * Test for the iterator method, and therefore the methods inside the
     * iterator class.
     */
    public void testIterator() {
        Exception empty = null;
        try {
            list.iterator().next();
        }
        catch (NoSuchElementException e) {
            empty = e;
        }
        assertNotNull(empty);
        
        list.clear();
        list.add("RedBull");
        list.add("Mercedes");
        list.add("Ferrari");
        
        Iterator<String> iterator = list.iterator();
        assertEquals(iterator.next(), "RedBull");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "Mercedes");
        assertEquals(iterator.next(), "Ferrari");
        assertFalse(iterator.hasNext());
        
        
        

    }

}
