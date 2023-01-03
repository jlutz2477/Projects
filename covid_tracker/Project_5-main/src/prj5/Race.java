package prj5;

import java.text.DecimalFormat;


/**
* Race class, this class will be used to categorize the datapoints read
* from the input file, for each state statistics on races and their CFR's
* are recorded. This data will be put into RAce objects who will be sorted
* within a linked list to display to the user races with their CFR's by
* total CFR and alphabetical order
* 
* @author bdeduffy
* @version 4/20/2022
*/

public class Race {

    private String raceName;
    private double raceCFR;
    private double cases;
    private double deaths;
    
    /**
     * Constructor for Race object, takes in raceName and CFR
     * for the given race to store into member variables
     * 
     * @param inRace
     *            string for name of race.
     * @param cases
     *            Number of cases
     * @param deaths
     *            Number of deaths
     */
    public Race(String inRace, double cases, double deaths) {
        raceName = inRace;
        this.cases = cases;
        this.deaths = deaths;
        
        if (cases == -1.0 || deaths == -1.0) {
            raceCFR = -1.0;
            //System.out.println("Cases/ Deaths are invalid");
            //System.out.println(cases);
            //System.out.println(deaths);
        }
        else {
            raceCFR = calculateCFR(cases, deaths);
        }
        
    
        
        
    }
    
    /**
     * Returns the raceName of the Race
     * 
     * @return race name string
     */
    public String getRace() {
        return raceName;
    }
    
    /**
     * Returns the raceCFR of the Race
     * 
     * @return race CFR double
     */
    public double getCFR() {
        return raceCFR;
    }
    
    /**
     * Set method to modify CFR of Race object
     * 
     * @param inCFR
     *            CFR we want to set to.
     */
    public void setCFR(double inCFR) {
        raceCFR = inCFR;
    }
    
    /**
     * Set method to modify raceName of Race object
     * 
     * @param inRace
     *            raceName we want to set to.
     */
    public void setRace(String inRace) {
        raceName = inRace;
    }
    
    /**
     * Returns a string representing the Races
     * 
     * @return race String
     */
    public String toString() {
        
        //return  raceName + " " + (int)cases + " cases, " + CFR + "%" + 
           //" CFR" + "\n";
        
        
        DecimalFormat df = new DecimalFormat("#.#");
        StringBuilder builder = new StringBuilder();
        builder.append(raceName + ": " + (int)cases + " cases, ");
        if (cases > 0 && deaths > 0 ) {
            
            builder.append(df.format(raceCFR));
            builder.append("% CFR");
            builder.append("\n");
        }
        else {
            builder.append("-1% CFR");
            builder.append("\n");
        }
        return builder.toString();
        
        
        
    }
    

    /**
     * Returns deaths of race
     * 
     * @return race deaths
     */
    public double getDeaths() {
        return this.deaths;
    }
    
    /**
     * Returns cases of race
     * 
     * @return race cases
     */
    public double getCases() {
        return this.cases;
    }
    
    /**
     * Set method to modify Cases of Race object
     * 
     * @param newCases
     *            newCases we want to set to.
     */
    public void setCases(double newCases) {
        cases = newCases;
    }
    
    /**
     * Set method to modify Deaths of Race object
     * 
     * @param newDeaths
     *            newDeaths we want to set to.
     */
    public void setDeaths(double newDeaths) {
        deaths = newDeaths;
    }
    
    /**
     * Checks for equality between two race objects
     * 
     * @param other
     *            other object to compare to.
     * @return whether they are equal or not
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() == other.getClass()) {
            Race otherRace = ((Race)other);
            if (this.getRace() == otherRace.getRace() &&
                this.getCFR() == otherRace.getCFR()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Helper method to calculate the CFR of the race given the cases and the 
     * deaths. 
     * @param cases
     *        The number of total cases
     * @param deaths
     *        The number of total deaths 
     * @return  
     *        The rounded value of the CFR to one decial place
     */
    private double calculateCFR(double inCases, double inDeaths) {
        
        double result = ((inDeaths / inCases) * 100);
        //result = round(result);
        return result;
    }
    
   // /**
    // * Helper method to round a value to one decimal place
    // * 
    // * @param decimal
    // *            The decimal that we want to round
    // * @return double
    // *         The parameter decimal rounded to the tenths place
    // */
    //private double round(double decimal) {
    //    DecimalFormat df = new DecimalFormat("0.0");
    //    String result = df.format(decimal);
    //    return Double.valueOf(result);
//
    //}
    
}
