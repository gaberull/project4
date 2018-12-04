import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Austin "Gabe" Scott
 * @version 2018-10-28
 * 
 * JUnit Test Case for testing MapData class
 *
 */
public class MapDataTest
{

    /**
     * Tests MapData constructor
     */
    @Test
    public void testMapData()
    {
        MapData mapData = new MapData(2018, 8, 1, 7, 0, "data");
        Assert.assertNotNull(mapData);
    }

    /**
     * Tests CreateFileName method
     */
    @Test
    public void testCreateFileName()
    {
         MapData mapData = new MapData(2018, 8, 1, 7, 0, "data");
         String fileName = mapData.createFileName(2018, 8, 1, 7, 0, "data");
         Assert.assertEquals(fileName, "data/201808010700.mdf");
    }
    
    /**
     * Tests getIndexOf method
     * @throws IOException 
     */
    @Test
    public void testGetIndexOf() throws IOException
    {
        MapData mapData = new MapData(2018, 8, 1, 7, 0, "data");
        mapData.parseFile();
        int actual = mapData.getIndexOf("TAIR");
        Assert.assertEquals(4, actual);
    }
    
    /**
     * Tests parseFile method
     * @throws IOException 
     * 
     */
    @Test
    public void testParseFile() throws IOException
    {
        MapData mapData = new MapData(2018, 8, 1, 7, 0, "data");
        mapData.parseFile();
        Assert.assertEquals(21.7, mapData.getStatistics(StatsType.MAXIMUM, "TAIR").getValue(), 0.01);
        Assert.assertEquals(18, mapData.getStatistics(StatsType.AVERAGE, "TAIR").getValue(), 0.1);
    }
    
    /**
     * Tests getStatistics method
     * @throws IOException 
     */
    @Test
    public void testGetStatistics() throws IOException
    {
        MapData mapData = new MapData(2018, 8, 1, 7, 00, "data");
        mapData.parseFile();
        Statistics stat = mapData.getStatistics(StatsType.AVERAGE, "TAIR");
        Assert.assertEquals(18, stat.getValue(), 0.1);
        
    }

    /**
     * Tests that the toString method has intended output
     * @throws IOException 
     * 
     */
    @Test
    public void testToString() throws IOException
    {
        MapData mapData = new MapData(2018, 8, 30, 17, 45, "data");
        mapData.parseFile();
        Assert.assertEquals("=========================================================\n"
+ "=== 2018-08-30 17:45 ===\n"
+ "=========================================================\n"
+ "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\n"
+ "Minimum Air Temperature[1.5m] = 20.8 C at MIAM\n"
+ "Average Air Temperature[1.5m] = 32.4 C at Mesonet\n"
+ "=========================================================\n"
+ "=========================================================\n"
+ "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\n"
+ "Minimum Air Temperature[9.0m] = 20.7 C at MIAM\n"
+ "Average Air Temperature[9.0m] = 31.6 C at Mesonet\n"
+ "=========================================================\n"
+ "=========================================================\n"
+ "Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\n"
+ "Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\n"
+ "Average Solar Radiation[1.5m] = 828.1 W/m^2 at Mesonet\n"
+ "=========================================================\n"
, mapData.toString());
    }

}
