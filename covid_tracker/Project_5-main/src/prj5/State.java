package prj5;

// Virginia Tech Honor Code Pledge:
// f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Brenden Duffy bdeduffy

/**
 * State Class, THis class represents a state with its corresponding
 * data related to specific races and their CFR's. The state will
 * have its name and the dataset which can be sorted by CFR or
 * in alphabetical order
 * 
 * @author Brenden Duffy bdeduffy
 * @version 4/21/2022
 */
public class State {

    private String stateName;
    private SLList<Race> dataList;

    /**
     * Constructor for State object, takes in stateName as well
     * as the FR's for each race in the state
     * to construct a singly linked list of the data
     * 
     * @param inName
     *            string for name of state.
     * @param races
     *              singly linked list of races           
     */
    public State(String inName, SLList<Race> races) {
        this.stateName = inName;
        dataList = races;

    }


    /**
     * Returns the stateName of the State
     * 
     * @return state name string
     */
    public String getName() {
        return stateName;
    }


    /**
     * Returns the stateData of the State
     * 
     * @return state data singly linked list
     */
    public SLList<Race> getList() {
        return dataList;
    }
    

    /**
     * Returns a string representing the state
     * 
     * @return state representative string
     */
    public String toString() {
        return dataList.toString();
    }


    /**
     * Sorts races by CFR
     * 
     */
    public void sortByCFR() {
        for (int i = 0; i < dataList.getNumberOfEntries(); i++) {
            Double cfrNode = dataList.getDataAtNode(i).getCFR();
            String raceNode = dataList.getDataAtNode(i).getRace();
            Double casesNode = dataList.getDataAtNode(i).getCases();
            Double deathsNode = dataList.getDataAtNode(i).getDeaths();
            Double currCFR = cfrNode;
            String currRace = raceNode;
            Double currCases = casesNode;
            Double currDeaths = deathsNode;
            int nodePos = i;
            for (int j = i; j < dataList.getNumberOfEntries(); j++) {
                if (dataList.getDataAtNode(j).getCFR() > cfrNode) {
                    cfrNode = dataList.getDataAtNode(j).getCFR();
                    raceNode = dataList.getDataAtNode(j).getRace();
                    deathsNode = dataList.getDataAtNode(j).getDeaths();
                    casesNode = dataList.getDataAtNode(j).getCases();
                    nodePos = j;
                }
                if (dataList.getDataAtNode(j).getCFR() == cfrNode) {
                    cfrNode = dataList.getDataAtNode(j).getCFR();
                    raceNode = dataList.getDataAtNode(j).getRace();
                    deathsNode = dataList.getDataAtNode(j).getDeaths();
                    casesNode = dataList.getDataAtNode(j).getCases();
                    nodePos = j;
                }
            }
            dataList.getDataAtNode(nodePos).setCFR(currCFR);
            dataList.getDataAtNode(nodePos).setRace(currRace);
            dataList.getDataAtNode(nodePos).setCases(currCases);
            dataList.getDataAtNode(nodePos).setDeaths(currDeaths);
            
            
            dataList.getDataAtNode(i).setCFR(cfrNode);
            dataList.getDataAtNode(i).setRace(raceNode);
            dataList.getDataAtNode(i).setCases(casesNode);
            dataList.getDataAtNode(i).setDeaths(deathsNode);
            
            
            
        }
    }


    /**
     * Sorts races by CFR
     * 
     */
    public void sortByRace() {
        for (int i = 0; i < dataList.getNumberOfEntries(); i++) {
            Double cfrNode = dataList.getDataAtNode(i).getCFR();
            String raceNode = dataList.getDataAtNode(i).getRace();
            Double casesNode = dataList.getDataAtNode(i).getCases();
            Double deathsNode = dataList.getDataAtNode(i).getDeaths();
            Double currCFR = cfrNode;
            String currRace = raceNode;
            Double currCases = casesNode;
            Double currDeaths = deathsNode;
            int nodePos = i;
            for (int j = i; j < dataList.getNumberOfEntries(); j++) {
                if (dataList.getDataAtNode(j).getRace().compareTo(
                    raceNode) < 0) {
                    cfrNode = dataList.getDataAtNode(j).getCFR();
                    raceNode = dataList.getDataAtNode(j).getRace();
                    deathsNode = dataList.getDataAtNode(j).getDeaths();
                    casesNode = dataList.getDataAtNode(j).getCases();
                    nodePos = j;
                }
            }
            dataList.getDataAtNode(nodePos).setCFR(currCFR);
            dataList.getDataAtNode(nodePos).setRace(currRace);
            dataList.getDataAtNode(nodePos).setCases(currCases);
            dataList.getDataAtNode(nodePos).setDeaths(currDeaths);
            
            
            dataList.getDataAtNode(i).setCFR(cfrNode);
            dataList.getDataAtNode(i).setRace(raceNode);
            dataList.getDataAtNode(i).setCases(casesNode);
            dataList.getDataAtNode(i).setDeaths(deathsNode);
        }
    }
}
