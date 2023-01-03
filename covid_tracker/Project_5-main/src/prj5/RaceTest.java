package prj5;

import student.TestCase;

//Virginia Tech Honor Code Pledge:
//f
//As a Hokie, I will conduct myself with honor and integrity at all times.
//I will not lie, cheat, or steal, nor will I accept the actions of those who
//do.
//-- Brenden Duffy bdeduffy

/**
* Test class for Race
* 
* @author Brenden Duffy bdeduffy
* @version 4/20/2022
*/

public class RaceTest extends TestCase {

    /**
     * Multiple race objects to compare to each other
     */
    
    private Race race1;
    private Race race2;
    private Race race3;
    private Object otherObj;
    private Race race4;
    private Race race5;
    private Race race6;
    private Race race7;
    private Race raceNoCase;
    private Race raceNoBoth;
    
    /**
     * For variable instantiation before testing
     */
    
    public void setUp() {

        race1 = new Race("white", 10, 1);
        race1.setCFR(10.0);
        race2 = new Race("latinx", 7, 1);
        race2.setCFR(7);
        race3 = new Race("black", 12, 1);
        race3.setCFR(12);
        
        race4 = new Race("white", 7.0, 1);
        race4.setCFR(7);
        race5 = new Race("black", 10, 1);
        race5.setCFR(10);
        race6 = new Race("white", 10, 1);
        race6.setCFR(10);
        race7 = new Race("white", 10, -1.0);
        raceNoCase = new Race("Bot", -1.0, 10);
        raceNoBoth = new Race("Bot", -1.0, -1.0);
        otherObj = new Object();
    }
    
    /**
     * Tests the Race's setRace() method.
     */
    public void testGetRace() {

        assertEquals(race1.getRace(), "white");
        assertEquals(race2.getRace(), "latinx");
        assertEquals(race3.getRace(), "black");

    }
    
    /**
     * Tests the Race's getCFR() method.
     */
    public void testGetCFR() {

        assertEquals(race1.getCFR(), 10.0, 0.1);
        assertEquals(race2.getCFR(), 7.0, 0.1);
        assertEquals(race3.getCFR(), 12.0, 0.1);
        
        assertEquals(raceNoCase.getCFR(), -1.0, 0.1);
        assertEquals(raceNoBoth.getCFR(), -1.0, 0.1);

    }
    
    /**
     * Tests the Race's setCFR() method.
     */
    public void testSetCFR() {
        race1.setCFR(2);
        assertEquals(race1.getCFR(), 2, 0.1);
        race2.setCFR(4);
        assertEquals(race2.getCFR(), 4, 0.1);
        race3.setCFR(17);
        assertEquals(race3.getCFR(), 17, 0.1);

    }
    
    /**
     * Tests the Race's setRace() method.
     */
    public void testSetRace() {
        race1.setRace("black");
        assertEquals(race1.getRace(), "black");
        race2.setRace("asian");
        assertEquals(race2.getRace(), "asian");
        race3.setRace("latinx");
        assertEquals(race3.getRace(), "latinx");

    }
    
    /**
     * Tests the Race's toString() method.
     */
    public void testToString() {
        assertEquals(race1.toString(), "white: 10 cases, 10% CFR\n");
        assertEquals(race7.toString(), "white: 10 cases, -1% CFR\n");
        //raceNoCase = new Race("Bot", -1.0, 10);
        assertEquals(raceNoCase.toString(), "Bot: -1 cases, -1% CFR\n");
        assertEquals(raceNoBoth.toString(), "Bot: -1 cases, -1% CFR\n");
        
        Race easyNum = new Race("Race", 100.0, 5);
        assertEquals(easyNum.toString(), "Race: 100 cases, 5% CFR\n");
        
        //raceNoBoth = new Race("Bot", -1.0, -1.0);
        //assertEquals(race2.toString(), "[latinx, 7.0]");
        //assertEquals(race3.toString(), "[black, 12.0]");

    }
    
    /**
     * Tests the Race's getDeaths() method.
     */
    public void testGetDeaths() {
        assertEquals(race1.getDeaths(), 1, 0.1);
        assertEquals(race2.getDeaths(), 1, 0.1);
        assertEquals(race3.getDeaths(), 1, 0.1);
    }
    
    /**
     * Tests the Race's getCases() method.
     */
    public void testGetCases() {
        assertEquals(race1.getCases(), 10, 0.1);
        assertEquals(race2.getCases(), 7, 0.1);
        assertEquals(race3.getCases(), 12, 0.1);
        
        assertEquals(raceNoCase.getCases(), -1.0, 0.1);
        assertEquals(raceNoBoth.getCases(), -1.0, 0.1);
    }
    
    /**
     * Tests the Race's setCases() method.
     */
    public void testSetCases() {
        assertEquals(race1.getCases(), 10, 0.1);
        race1.setCases(5);
        assertEquals(race1.getCases(), 5, 0.1);
    }
    
    /**
     * Tests the Race's setDeaths() method.
     */
    public void testSetDeaths() {
        assertEquals(race1.getDeaths(), 1, 0.1);
        race1.setDeaths(5);
        assertEquals(race1.getDeaths(), 5, 0.1);
    }
    
    /**
     * Tests the Race's equals() method.
     */
    public void testEquals() {
        assertEquals(race1.equals(race4), false);
        assertEquals(race1.equals(race1), true);
        assertEquals(race1.equals(race5), false);
        assertEquals(race1.equals(race6), true);
        assertEquals(race1.equals(null), false);
        assertEquals(race1.equals(otherObj), false);

    }
    
}
