/**
 * 
 */

/**
 * @author Austin "Gabe" Scott
 * @version 2018-10-28
 */
public class Observation extends AbstractObservation
{

    private double value;
    private String stid;

    /**
     * Constructor
     * @param value
     * @param stid
     */
    public Observation(double value, String stid)
    {
        super();
        this.value = value;
        this.stid = stid;
        this.valid = this.value > 0;
    }

    /**
     * Gets double value of observation object
     * @return
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Tests that the value is valid
     * @return boolean value 
     */
    public boolean isValid()
    {
        return this.valid;
    }

    /**
     * Gets station ID and returns it as a string
     * @return Station ID as string
     */
    public String getStid()
    {
        return stid;
    }

    /**
     * outputs value of Observation object in String format
     * @return String value
     */
    public String toString()
    {
        return Double.toString(value);
    }

}
