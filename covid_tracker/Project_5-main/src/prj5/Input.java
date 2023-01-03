// Virginia Tech Honor Code Pledge:
// f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- John Lutz jlutz2418

package prj5;

/**
 * Main method to be used for class
 * @author jlutz
 * @version 4.23.2022
 */
public class Input {
    
    /**
     * Instantiates a DataReader with given parameter
     * @param args
     *          The String of the filename to be passed to DataReader
     *          
     */
    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                DataReader reader = new DataReader(args[0]);
            }
            else {
                DataReader reader = new DataReader("Cases_and_Deaths_CRDT_NEW.csv");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
