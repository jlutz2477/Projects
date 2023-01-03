// Virginia Tech Honor Code Pledge:
// f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- John Lutz jlutz2418
package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * DataReader class, this class will parse data from input csv file and
 * generate the states based on comma separated values
 * Gives caseCalculator this Array of States in order to tie everything
 * together
 * 
 * @author jlutz
 * @version 4.21.2022
 */
public class DataReader {

    private State[] states;
    private String[] data;

    /**
     * Constructor for DataReader Class
     * Sends the input file to readFile to populate an array of states
     * Instantiates a new GUIWindow with recently filled caseCalculator
     * as a parameter
     * 
     * @param inputFile
     *            String representing the file containing the states data
     * @throws java.text.ParseException
     *             If there are not eleven comma separated values on the line
     * @throws java.io.FileNotFoundException
     *             If the file is not found
     */
    public DataReader(String inputFile)
        throws ParseException,
        FileNotFoundException {
        this.states = new State[6];
        this.states = readFile(inputFile);
        for (int i = 0; i < states.length; i++) {
            System.out.println(states[i].getName());
            states[i].sortByRace();
            System.out.println(states[i].toString());
            states[i].sortByCFR();
            System.out.println(states[i].toString());
            
        }
        GUIWindow window = new GUIWindow(new CaseCalculator(states));
    }


    /**
     * Reads the inputFile and parses it to populate an array of states
     * 
     * @param inputFile
     *            The file of states to be read and parsed
     * @return
     *         The array of states populated by the states from the input file
     * @throws java.text.ParseException
     *             If there are not eleven comma separated values on the line
     * @throws java.io.FileNotFoundException
     *             If the file is not found
     */
    private State[] readFile(String inputFile)
        throws ParseException,
        FileNotFoundException {

        // Total data from the file is 6 rows and 11 columns
        data = new String[77];
        State[] stateList = new State[6];
        Scanner file = new Scanner(new File(inputFile));
        int lineNum = 0;
        int dataNum = 0;

 
        file.nextLine();
        while (file.hasNextLine() && lineNum < 7) {
            String line = file.nextLine();
            Scanner currLine = new Scanner(line).useDelimiter(",\\s*");
            String[] values = new String[11];
            int valueNum = 0;

            while (currLine.hasNext() && valueNum < 11) {
                values[valueNum++] = currLine.next();
            }
            currLine.close();
            if (valueNum == 11) {
                for (int i = 0; i < 11; i++) {
                    data[dataNum++] = values[i];
                }
            }
            else {
                throw new ParseException("Parse Exception", 1);
            }
            lineNum++;
        }
        
        double whiteCases;
        double blackCases;
        double latinxCases;
        double asianCases;
        double otherCases;
        
        double whiteDeaths;
        double blackDeaths;
        double latinxDeaths;
        double asianDeaths;
        double otherDeaths;

        // Adds the states to the states array
        int stateNum = 0;
        for (int i = 0; i < 66; i += 11) {
            if (data[i + 1].equals("NA")) {
                whiteCases = -1.0;
            }
            else {
                whiteCases = Double.valueOf(data[i + 1]);
            }
            if (data[i + 6].equals("NA")) {
                whiteDeaths = -1.0;
            }
            else {
                whiteDeaths = Double.valueOf(data[i + 6]);
            }
            
            
            // black cases and deaths 
            if (data[i + 2].equals("NA")) {
                blackCases = -1.0;
            }
            else {
                blackCases = Double.valueOf(data[i + 2]);
            }
            if (data[i + 7].equals("NA")) {
                blackDeaths = -1.0;
            }
            else {
                blackDeaths = Double.valueOf(data[i + 7]);
            }
            
            
            
            if (data[i + 3].equals("NA")) {
                latinxCases = -1.0;
            }
            else {
                latinxCases = Double.valueOf(data[i + 3]);
            }
            
            if (data[i + 8].equals("NA")) {
                latinxDeaths = -1.0;
            }
            else {
                latinxDeaths = Double.valueOf(data[i + 8]);
            }
            
            
            
            
            if (data[i + 4].equals("NA")) {
                asianCases = -1.0;
            }
            else {
                asianCases = Double.valueOf(data[i + 4]);
            }
            
            if (data[i + 9].equals("NA")) {
                asianDeaths = -1.0;
            }
            else {
                asianDeaths = Double.valueOf(data[i + 9]);
            }
            
            
            
            if (data[i + 5].equals("NA")) {
                otherCases = -1.0;
            }
            else {
                otherCases = Double.valueOf(data[i + 5]);
            }
            
            if (data[i + 10].equals("NA")) {
                otherDeaths = -1.0;
            }
            else {
                otherDeaths = Double.valueOf(data[i + 10]);
            }
            
            Race white = new Race("white", whiteCases, whiteDeaths);
            Race black = new Race("black", blackCases, blackDeaths);
            Race latinx = new Race("latinx", latinxCases, latinxDeaths);
            Race asian = new Race("asian", asianCases, asianDeaths);
            Race other = new Race("other", otherCases, otherDeaths);

            SLList<Race> stateInfo = new SLList<Race>();
            stateInfo.add(white);
            stateInfo.add(black);
            stateInfo.add(latinx);
            stateInfo.add(asian);
            stateInfo.add(other);

            
            // Creates a currentState and adds it to states array
            State currentState = new State(data[i], stateInfo);
            stateList[stateNum++] = currentState;
        }
        return stateList;

    }
}
