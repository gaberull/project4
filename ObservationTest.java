import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

/**
 * 
 */

/**
 * @author Austin "Gabe" Scott
 * @version 2018-10-28
 *
 */
public class ObservationTest
{

    /**
     * Tests that Observation constructor works
     */
    @Test
    public void testObservation()
    {
        Observation ob = new Observation(21.0, "HOOK");
        Assert.assertEquals(21.0, ob.getValue(), 0.01);
        Assert.assertEquals("HOOK", ob.getStid());
    }

    /**
     * Test that getValue returns the correct value
     */
    @Test
    public void testGetValue()
    {
        Observation ob = new Observation(21.0, "HOOK");
        Assert.assertEquals(21.0, ob.getValue(), 0.01);
    }

    /**
     * Test that the isValid method works to verify that the value is valid
     */
    @Test
    public void testIsValid()
    {
        Observation ob = new Observation(21.0, "HOOK");
        Assert.assertTrue(ob.isValid());
    }

    /**
     *  Test that the method to getStid returns the correct station ID
     */
    @Test
    public void testGetStid()
    {
        Observation ob = new Observation(21.0, "HOOK");
        Assert.assertEquals("HOOK", ob.getStid());
    }

    /**
     * Test that the toString method's output matches what is expected
     */
    @Test
    public void testToString()
    {
        Observation ob = new Observation(21.0, "HOOK");
        String expected = "21.0";
        Assert.assertEquals(expected, ob.toString());
    }

}
