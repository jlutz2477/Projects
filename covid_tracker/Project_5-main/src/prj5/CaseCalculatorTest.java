
package prj5;

import student.TestCase;


/**
 * Test class for CaseCalculator.java
 * @author jlutz
 * @version 4.22.2022
 */
public class CaseCalculatorTest extends TestCase {

    
    private Race cats1;
    private Race dogs1;
    private Race fish1;
    private Race cats2;
    private Race dogs2;
    private Race fish2;
    
    private State va;
    private State md;
    private State wv;
    private CaseCalculator calc;
    /**
     * Sets up class for testing by instantiating a state array
     */
    public void setUp() {
        cats1 = new Race("cats", 20, 5);
        dogs1 = new Race("dogs", 80, 5);
        fish1 = new Race("fish", 40, 5);
        SLList<Race> slList1 = new SLList<Race>();
        slList1.add(dogs1);
        slList1.add(cats1);
        slList1.add(fish1);
        
        cats2 = new Race("cats", 50, 5);
        dogs2 = new Race("dogs", 70, 5);
        fish2 = new Race("fish", 90, 5);
        SLList<Race> slList2 = new SLList<Race>();
        slList2.add(dogs2);
        slList2.add(cats2);
        slList2.add(fish2);
        
        va = new State("va", slList1);
        md = new State("md", slList2);
        wv = new State("wv", slList2);
        State[] stateList = new State[] {va, md, wv};
        calc = new CaseCalculator(stateList);
    }
    
    
    /**
     * Tests if IllegalArgumentException is thrown
     */
    public void testException() {
        CaseCalculator testExcep;
        Exception exception = null;
        try
        {
            testExcep = new CaseCalculator(null);
            fail("CaseCalculator() is not "
                + "throwing an exception when it should");
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertTrue("CaseCalculator() is throwing "
            + "the wrong type of exceptions",
                   exception instanceof IllegalArgumentException);
    }
    
    
    
    /**
     * Tests the sortByStateCFR() method
     */
    public void testSortStatesByStateCFR() {
        calc.chooseCurrentState("va");
        calc.sortStatesByCFR();
        
        assertEquals(calc.getCurrentState().getList().getDataAtNode(0), dogs1);
        assertEquals(calc.getCurrentState().getList().getDataAtNode(1), cats1);
        assertEquals(calc.getCurrentState().getList().getDataAtNode(2), fish1);
    }
    
    
    /**
     * Tests the sortByAlphaBetical() method
     */
    public void testSortStatesByAlphabetical() {
        
        calc.chooseCurrentState("wv");
        calc.sortStatesByAlphabetical();
        assertEquals(calc.getCurrentState().getList().getDataAtNode(0), dogs2);
        assertEquals(calc.getCurrentState().getList().getDataAtNode(1), cats2);
        assertEquals(calc.getCurrentState().getList().getDataAtNode(2), fish2);
        
    }
    
    
    /**
     * Tests the chooseCurrentState() method 
     */
    public void testChooseCurrentState() {
        calc.chooseCurrentState("va");
        assertEquals(calc.getCurrentState(), va);
        
        calc.chooseCurrentState("md");
        assertEquals(calc.getCurrentState(), md);
    }
    
    
    /**
     * Tests the getCurrentState() method
     */
    public void testGetCurrentState() {
        calc.chooseCurrentState("wv");
        assertEquals(calc.getCurrentState(), wv);
        
        calc.chooseCurrentState("va");
        assertEquals(calc.getCurrentState(), va);
    }
    
    /**
     * Test for the getStatesArray method
     */
    public void testGetStatesArray() {
        State[] arr = calc.getStatesArray();
        assertEquals(arr[0], va);
        assertEquals(arr[1], md);
        assertEquals(arr[2], wv);
    }
}
