/**
 * 
 */

/**
 * @author Austin "Gabe" Scott
 * @version 2018-12-06
 * t
 */
public abstract class AbstractObservation
{
    protected boolean valid;
    
    /**
     * Empty constructor
     */
    public AbstractObservation()
    {
        valid = false;
    }

    /**
     * method declaration for child classes
     * @return boolean value
     */
    public abstract boolean isValid();
    
    
        
    
    
}
