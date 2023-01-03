// Virginia Tech Honor Code Pledge:
// f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- John Lutz jlutz2418
package prj5;

/**
 * CaseCalculator Class, this class will handle all of the major calculations
 * and decision making for the program
 * In charge of sorting the races in the states by CFR and alphabetically
 * Also chooses a display state
 * 
 * @author jlutz
 * @version 4.21.22
 */
public class CaseCalculator {

    private State[] statesArray;
    private State currentState;

    /**
     * Constructor for CaseCalculator class
     * 
     * @param states
     *            An array of states from the DataReader class
     * @throws IllegalArgumentException
     *             If the input parameter states is null
     */
    public CaseCalculator(State[] states) throws IllegalArgumentException {
        if (states == null) {
            throw new IllegalArgumentException();
        }
        statesArray = states;
        currentState = null;
        // currentState = statesArray[0];
    }


    /**
     * Getter method for the statesArray field. This method should
     * return the array that is referenced by the statesArray field.
     * 
     * @return State[]
     *         The array referenced by the statesArray field
     */
    public State[] getStatesArray() {
        return this.statesArray;
    }


    /**
     * Sorts all states data by CFR
     */
    public void sortStatesByCFR() {
        for (int i = 0; i < statesArray.length; i++) {
            statesArray[i].sortByCFR();
        }
    }


    /**
     * Sorts all states data by Alphabetical Order
     */
    public void sortStatesByAlphabetical() {

        for (int i = 0; i < statesArray.length; i++) {
            statesArray[i].sortByRace();
        }

    }


    /**
     * Chooses which state is the current state
     * 
     * @param inStateName
     *            string of state name to select as current state
     */
    public void chooseCurrentState(String inStateName) {
        for (int i = 0; i < statesArray.length; i++) {
            if (statesArray[i].getName().equals(inStateName)) {
                currentState = statesArray[i];
            }

        }
    }


    /**
     * Returns the current state
     * 
     * @return current State
     */
    public State getCurrentState() {
        return currentState;
    }
}
