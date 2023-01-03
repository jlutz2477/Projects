package prj5;

import java.text.DecimalFormat;
import java.util.Iterator;
import cs2.Button;
import cs2.Shape;
import cs2.TextShape;
import cs2.Window;
import cs2.WindowSide;

/**
 * Window for this class. This class represents the front end as it deals
 * with the buttons and what the users see/ interact with
 * 
 * @author srinjoydey
 * @version 2022.04.25
 *
 */
public class GUIWindow {
    // ~ Fields....................................
    private Window window;
    private Button quitButton;
    private Button sortByCFR;
    private Button sortByAlpha;
    private Button dcButton;
    private Button vaButton;
    private Button gaButton;
    private Button tnButton;
    private Button ncButton;
    private Button mdButton;
    private CaseCalculator calculator;
    private State[] stateArr;
    private double normalized;
    private State currState;

    /**
     * Represents the max height of a bar on the window
     */
    private static final int MAX_BAR_HEIGHT = 200;

    /**
     * Represents the width of a bar in the window. This will be held at
     * 25 to ensure that all bars are the same width
     */
    private static final int BAR_WIDTH = 25;

    /**
     * Distance between each of the bars. This will make sure that there is a
     * constant distance between each of the bars represented.
     */
    private static final int BAR_DISTANCE = 133;

    /**
     * Represents the lowest y that a bar can be at. This y coordinate will be
     * the y cord of the NA bars
     */
    private static final int lowestY = 250;

    // ~ Constructor..................................

    /**
     * Constructor for the GUIWindow class. This constructor will intialize
     * all the fields needed for the program to run correctly
     */
    public GUIWindow(CaseCalculator calc) {
        this.calculator = calc;
        window = new Window();
        stateArr = calc.getStatesArray();
        quitButton = new Button("Quit"); // quitButton functions
        quitButton.onClick(this, "clickedQuit");

        sortByCFR = new Button("Sort by CFR");

        sortByAlpha = new Button("Sort by Alpha");

        dcButton = new Button("Represent DC");
        vaButton = new Button("Represent VA");
        mdButton = new Button("Represent MD");
        ncButton = new Button("Represent NC");
        tnButton = new Button("Represent TN");
        gaButton = new Button("Represent GA");

        /**
         * dcButton.onClick(this, "displayDC");
         * vaButton.onClick(this, "displayVA");
         * mdButton.onClick(this, "displayMD");
         * ncButton.onClick(this, "displayNC");
         * tnButton.onClick(this, "displayTN");
         * gaButton.onClick(this, "displayGA");
         */

        window.addButton(sortByCFR, WindowSide.NORTH);
        window.addButton(quitButton, WindowSide.NORTH);
        window.addButton(sortByAlpha, WindowSide.NORTH);
        window.addButton(dcButton, WindowSide.SOUTH);
        window.addButton(vaButton, WindowSide.SOUTH);
        window.addButton(mdButton, WindowSide.SOUTH);
        window.addButton(ncButton, WindowSide.SOUTH);
        window.addButton(tnButton, WindowSide.SOUTH);
        window.addButton(gaButton, WindowSide.SOUTH);

        dcButton.onClick(this, "displayDC");
        vaButton.onClick(this, "displayVA");
        mdButton.onClick(this, "displayMD");
        ncButton.onClick(this, "displayNC");
        tnButton.onClick(this, "displayTN");
        gaButton.onClick(this, "displayGA");
        sortByCFR.onClick(this, "sortByCFR");
        sortByAlpha.onClick(this, "sortByAlpha");
        // width is 807 by 294

        normalized = this.findNormal();
        currState = null;

    }

    // ~ Methods.........................................


    /**
     * Method to implement the functionality of the quit button.
     * Upon clicking the quit button the Java Application should end
     * 
     * @param button
     *            : The button that will implement this method
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }


    /**
     * Method to clear the entire window of all the shapes, thereby
     * leaving an empty window(apart from the buttons)
     */
    private void emptyBoard() {
        window.removeAllShapes();
    }


    /**
     * Method to handle the which state is being displayed in the calculator
     * 
     * @param state
     *            : The state that will be put into the current display
     */
    private void displayState(State state) {
        int numBars = 1;
        emptyBoard(); // empty the window of all shapes
        Iterator<Race> iterator = state.getList().iterator();

        while (iterator.hasNext()) {
            Race temp = iterator.next();
            if (temp.getCFR() != -1.0) {
                Shape added = new Shape((numBars * BAR_DISTANCE) - BAR_WIDTH,
                    lowestY - scaleHeight(temp.getCFR()), BAR_WIDTH,
                    scaleHeight(temp.getCFR()));
                window.addShape(added);
                
                TextShape description = new TextShape(0, 0, "");
                description.setText(temp.getRace());
                description.setX((numBars * BAR_DISTANCE) - 
                    ((description.getWidth() + BAR_WIDTH) / 2));
                description.setY(lowestY);
                
                TextShape cfrText = new TextShape(0, 0, "");
                cfrText.setText(roundCFR(temp.getCFR()) + "%");
                cfrText.setX((numBars * BAR_DISTANCE) - 
                    ((cfrText.getWidth() + BAR_WIDTH) / 2));
                cfrText.setY(lowestY + 20);
                
                window.addShape(description);
                window.addShape(cfrText);
            }
            else {
                TextShape added = new TextShape(0, 0, "NA");
                added.setX((numBars * BAR_DISTANCE) - ((added.getWidth() + BAR_WIDTH) / 2));
                added.setY(lowestY - added.getHeight());
                
                TextShape raceName = new TextShape(0, 0, temp.getRace());
                raceName.setX((numBars * BAR_DISTANCE) - ((raceName.getWidth() + BAR_WIDTH) / 2));
                raceName.setY(lowestY);
               
                window.addShape(added);
                window.addShape(raceName);
            }
            numBars++;
            TextShape title = new TextShape(0, 0, "");
            title.setText(state.getName() + " Case Fatality Ratios by Race");
            title.setX((window.getWidth() - title.getWidth()) / 2);
            title.setY(10);
            window.addShape(title);
        }
    }

    
    /**
     * Handles the display of clicking the VA button, changes 
     *            current state displayed to VA
     * 
     * @param button
     *            : Button clicked specifically the VA button
     */
    public void displayDC(Button button) {
        this.displayState(this.getDesiredState("DC"));
        currState = this.getDesiredState("DC");
    }


    /**
     * Handles the display of clicking the VA button, changes 
     *            current state displayed to VA
     * 
     * @param button
     *            : Button clicked specifically the VA button
     */
    public void displayVA(Button button) {
        this.displayState(this.getDesiredState("VA"));
        currState = this.getDesiredState("VA");
    }


    /**
     * Handles the display of clicking the MD button, changes 
     *            current state displayed to MD
     * 
     * @param button
     *            : Button clicked specifically the MD button
     */
    public void displayMD(Button button) {
        this.displayState(this.getDesiredState("MD"));
        currState = this.getDesiredState("MD");
    }


    /**
     * Handles the display of clicking the NC button, changes 
     *            current state displayed to NC
     * 
     * @param button
     *            : Button clicked specifically the NC button
     */
    public void displayNC(Button button) {
        this.displayState(this.getDesiredState("NC"));
        currState = this.getDesiredState("NC");
    }


    /**
     * Handles the display of clicking the TN button, changes 
     *            current state displayed to TN
     * 
     * @param button
     *            : Button clicked specifically the TN button
     */
    public void displayTN(Button button) {
        this.displayState(this.getDesiredState("TN"));
        currState = this.getDesiredState("TN");
    }

    /**
     * Handles the display of clicking the GA button, changes 
     *            current state displayed to GA
     * 
     * @param button
     *            : Button clicked specifically the GA button
     */
    public void displayGA(Button button) {
        this.displayState(this.getDesiredState("GA"));
        currState = this.getDesiredState("GA");
    }


    /**
     * Method to scale the height of the bar based on the CFR given,.
     * 
     * @param CFR
     *            This will be the CFR of a state given in double format
     * @return int
     *         This will be the height of the bar in question
     */
    private int scaleHeight(double CFR) {
        return (int)(CFR * normalized);
    }

    /**
     * Gets the state associated with the state name passed to the method
     * 
     * @param stateName
     *            : Name of the state were looking for
     * @return State
     *         The state pulled from the array with the name passed
     */
    private State getDesiredState(String stateName) {
        for (int i = 0; i < stateArr.length; i++) {
            if (stateArr[i].getName().equals(stateName)) {
                return stateArr[i];
            }
        }
        return null;

    }

    /**
     * Finds the highest CFR value found of the current state and returns
     * 
     * @return double
     *         Highest CFR value
     */
    private double findMaxCFR() {
        double maxCFR = -1;
        for (int i = 0; i < stateArr.length; i++) {
            Iterator<Race> state = stateArr[i].getList().iterator();
            while (state.hasNext()) {
                double compare = state.next().getCFR();
                if (compare > maxCFR) {
                    maxCFR = compare;
                }
            }
        }
        return maxCFR;
    }

    /**
     * Normalizes the value of CFR by dividing the max height by the highest CFR
     * 
     * @return double
     *         Highest CFR value
     */
    private double findNormal() {
        return MAX_BAR_HEIGHT / this.findMaxCFR();
    }

    /**
     * Rounds the CFR value passed to the format we want for display
     * 
     * @param rounded
     *            : The number passed to round
     * @return String
     *         A String representing the rounded number
     */
    private String roundCFR(Double rounded) {
        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(rounded);
    }

    /**
     * Returns the current state of the window object
     * 
     * @return State
     *         Current State
     */
    public State getCurrState() {
        return this.currState;
    }


    /**
     * Sorts all states by CFR and pulls the current state into display
     * 
     * @param button
     *            : The button we use to trigger the event
     */
    public void sortByCFR(Button button) {
        calculator.sortStatesByCFR();
        displayState(this.getCurrState());
    }


    /**
     * Sorts all states by Alphabetical order and pulls the current state into display
     * 
     * @param button
     *            : The button we use to trigger the event
     */
    public void sortByAlpha(Button button) {
        calculator.sortStatesByAlphabetical();
        displayState(this.getCurrState());
    }

}
