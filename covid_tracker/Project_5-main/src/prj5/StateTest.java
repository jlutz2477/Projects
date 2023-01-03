package prj5;

import student.TestCase;

// Virginia Tech Honor Code Pledge:
// f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Brenden Duffy bdeduffy

/**
 * Test class for State
 * 
 * @author Brenden Duffy bdeduffy
 * @version 4/20/2022
 */

public class StateTest extends TestCase {

    /**
     * Multiple state objects to compare to each other
     */

    private State state1;
    // private State state3;
    private SLList<Race> raceList;

// s
    /**
     * For variable instantiation before testing
     */
    public void setUp() {

        Race newRace = new Race("white", 10, 10);
        Race newRacea = new Race("black", 10, 10);
        Race newRaces = new Race("latinx", 10, 10);
        Race newRaced = new Race("asian", 10, 10);
        Race newRacef = new Race("other", 10, 10);
        raceList = new SLList<Race>();
        raceList.add(newRace);
        raceList.add(newRacea);
        raceList.add(newRaces);
        raceList.add(newRaced);
        raceList.add(newRacef);

        state1 = new State("VA", raceList);

    }


    /**
     * Tests the State's setName() method.
     */
    public void testGetName() {

        assertEquals(state1.getName(), "VA");

    }


    /**
     * Tests the State's getList() method.
     */
    public void testGetList() {

        assertEquals(state1.getList(), raceList);

        // assertEquals(state1.getList().getDataAtNode(0), asianList1);
        // assertEquals(state1.getList().getDataAtNode(1), blackList1);
        // assertEquals(state1.getList().getDataAtNode(2), latinxList1);
        // assertEquals(state1.getList().getDataAtNode(3), otherList1);
        // assertEquals(state1.getList().getDataAtNode(4), whiteList1);

    }


    /**
     * Tests the State's toString() method.
     */
    public void testToString() {

        String compare = "white: 10 cases, 100% CFR\n"
            + "black: 10 cases, 100% CFR\n" + "latinx: 10 cases, 100% CFR\n"
            + "asian: 10 cases, 100% CFR\n" + "other: 10 cases, 100% CFR\n"
            + "=====";
        assertEquals(state1.toString(), compare);
        
        
        

        // assertEquals(state2.getName(), "MD");
        // assertEquals(state3.getName(), "NC");

    }


    /**
     * Tests the State's toString() method.
     */
    public void testSortByCFR() {
        String compare = "other: 10 cases, 100% CFR\n"
            + "white: 10 cases, 100% CFR\n" + "black: 10 cases, 100% CFR\n"
            + "latinx: 10 cases, 100% CFR\n" + "asian: 10 cases, 100% CFR\n"
            + "=====";
        state1.sortByCFR();
        assertEquals(state1.toString(), compare);
        

        // state2 = new State("MD", 7, 2, 5, 6.0, 10.0);
        // assertEquals(state2.getName(), "MD");
        // assertEquals(state3.getName(), "NC");

    }


    /**
     * Tests the State's toString() method.
     */
    public void testSortByRace() {
        String compare = "asian: 10 cases, 100% CFR\n"
            + "black: 10 cases, 100% CFR\n"
            + "latinx: 10 cases, 100% CFR\n"
            + "other: 10 cases, 100% CFR\n"
            + "white: 10 cases, 100% CFR\n"
            + "=====";
        state1.sortByRace();
        assertEquals(state1.toString(), compare);
        
        /**
        state2.sortByCFR();
        assertEquals(state2.toString(), "MD: [[white, 10.0], [asian, 7.0], "
            + "[other, 6.0], [latinx, 5.0], [black, 2.0]]");
        state2.sortByRace();
        assertEquals(state2.toString(), "MD: [[asian, 7.0], [black, 2.0], "
            + "[latinx, 5.0], [other, 6.0], [white, 10.0]]");
            */

        // state2 = new State("MD", 7, 2, 5, 6.0, 10.0);
        // assertEquals(state2.getName(), "MD");
        // assertEquals(state3.getName(), "NC");

    }

}
