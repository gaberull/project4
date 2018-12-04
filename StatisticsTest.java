import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class StatisticsTest
{

    /**
     * Tests first Statistics constructor that takes in dateTime string
     * @throws ParseException 
     */
    @Test
    public void testStatistics1() throws ParseException
    {
        LocalDate date =  LocalDate.of(2007, 12, 3);
        LocalTime time = LocalTime.of(10, 15, 30);
        LocalDateTime localDateTime =  LocalDateTime.of(date , time);
        ZoneOffset zone =  ZoneOffset.of("+01");
        ZonedDateTime expected = ZonedDateTime.of(localDateTime , zone);
        Statistics stat = new Statistics(36.5, "HOOK", expected, 110, StatsType.MAXIMUM);
        Assert.assertEquals(36.5, stat.getValue(), 0.01);
        Assert.assertEquals(110, stat.getNumberOfReportingStations());
        
    }

    /**
     * Tests second Statistics constructor that takes in a GregorianCalendar dateTime object
     * 
     */
    @Test
    public void testStatistics2() 
    {
        GregorianCalendar dateTime = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        Statistics stat = new Statistics(36.5, "HOOK", dateTime, 110, StatsType.MAXIMUM);
        Assert.assertEquals(36.5, stat.getValue(), 0.01);
        Assert.assertEquals(110, stat.getNumberOfReportingStations());
        
    }

    /**
     * Tests createDateFromString method
     * @throws ParseException 
     * 
     */
    @Test
    public void testCreateDateFromString() throws ParseException 
    {
        GregorianCalendar dateTime = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        Statistics stat = new Statistics(36.5, "HOOK", dateTime, 110, StatsType.MAXIMUM);
        GregorianCalendar cal = stat.createDateFromString("2010-04-05T17:16:00Z");
        Assert.assertEquals(cal.get(Calendar.YEAR), 2010);
        Assert.assertEquals(cal.get(Calendar.MONTH), 3);
    }
    
    /**
     * Tests that the date created from createZDateFromString matches the string input
     * @throws ParseException 
     */
    @Test
    public void testCreateZDateFromString() throws ParseException
    {
        
        LocalDate date =  LocalDate.of(2007, 12, 3);
        LocalTime time = LocalTime.of(10, 15, 30);
        LocalDateTime localDateTime =  LocalDateTime.of(date , time);
        ZoneOffset zone =  ZoneOffset.of("+01");
        ZonedDateTime expected = ZonedDateTime.of(localDateTime , zone);
        Statistics stat = new Statistics(36.5, "HOOK", expected, 110, StatsType.MAXIMUM);
        ZonedDateTime actual = stat.createZDateFromString("2007-12-03T10:15:30+01:00");
        Assert.assertEquals(expected, actual);
        
    }

    /**
     * Tests createStringFromDate method
     * 
     */
    @Test
    public void testCreateStringFromDate() 
    {
        GregorianCalendar dateTime = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        Statistics stat = new Statistics(36.5, "HOOK", dateTime, 110, StatsType.MAXIMUM);
        Assert.assertEquals("2018-09-01T07:00:00 CDT",stat.createStringFromDate(dateTime));
    }
    
    /**
     * Tests zdt createStringFromDate
     * @throws ParseException 
     */
    @Test
    public void testZdtCreateStringFromDate() throws ParseException
    {
        LocalDate date =  LocalDate.of(2007, 12, 3);
        LocalTime time = LocalTime.of(10, 15, 30);
        LocalDateTime localDateTime =  LocalDateTime.of(date , time);
        ZoneOffset zone =  ZoneOffset.of("+01");
        ZonedDateTime expected = ZonedDateTime.of(localDateTime , zone);
        Statistics stat = new Statistics(36.5, "HOOK", expected, 110, StatsType.MAXIMUM);
        String actual = stat.createStringFromDate(expected);
        Assert.assertEquals("ZdtDatetimeString did not match", "2007-12-03T10:15:30+01:00", actual);
    }

    /**
     * Tests getNumberOfReportingStations
     */
    @Test
    public void testGetNumberOfReportingStations()
    {
        GregorianCalendar dateTime = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        Statistics stat = new Statistics(36.5, "HOOK", dateTime, 110, StatsType.MAXIMUM);
        Assert.assertEquals(110, stat.getNumberOfReportingStations());
        
    }

    /**
     * Tests getUTCDateTimeString method
     */
    @Test
    public void testGetUTCDateTimeString()
    {
        GregorianCalendar dateTime = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        Statistics stat = new Statistics(36.5, "HOOK", dateTime, 110, StatsType.MAXIMUM);
        Assert.assertEquals("UTCDateTimeString did not match", "2018-09-01T07:00:00 CDT", stat.getUTCDateTimeString());
    }

    /**
     * Tests newerThan method
     */
    @Test
    public void testNewerThan()
    {
        GregorianCalendar old1 = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        GregorianCalendar lessOld1 = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        Statistics lessOld2 = new Statistics(36.5, "HOOK", lessOld1, 110, StatsType.MAXIMUM);
        Assert.assertTrue(lessOld2.newerThan(old1));
                
        GregorianCalendar old = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        GregorianCalendar lessOld = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        Statistics old2 = new Statistics(36.5, "HOOK", old, 110, StatsType.MAXIMUM);
        Assert.assertFalse(old2.newerThan(lessOld));
    }

    /**
     * Tests olderThan method
     */
    @Test
    public void testOlderThan()
    {
        GregorianCalendar old = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        GregorianCalendar lessOld = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        Statistics old2 = new Statistics(36.5, "HOOK", old, 110, StatsType.MAXIMUM);
        Assert.assertTrue(old2.olderThan(lessOld));
        
        GregorianCalendar old1 = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        Statistics lessOld2 = new Statistics(36.5, "HOOK", old, 110, StatsType.MAXIMUM);
        Assert.assertFalse(lessOld2.olderThan(old1));
    }

    /**
     * Tests sameAs method
     */
    @Test
    public void testSameAs()
    {
        GregorianCalendar firstCal = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        GregorianCalendar secondCal = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        Statistics firstStat = new Statistics(36.5, "HOOK", firstCal, 110, StatsType.MAXIMUM);
        Assert.assertTrue(firstStat.sameAs(secondCal));
        
        GregorianCalendar thirdCal = new GregorianCalendar(2018, 8, 9, 0, 0);
        Assert.assertFalse(firstStat.sameAs(thirdCal));
    }
    
    /**
     * Tests zdt newerThan method
     * @throws ParseException 
     */
    @Test
    public void testZdtNewerThan() throws ParseException
    {
        LocalDate date =  LocalDate.of(2007, 12, 3);
        LocalTime time = LocalTime.of(10, 15, 30);
        LocalDateTime localDateTime =  LocalDateTime.of(date , time);
        ZoneOffset zone =  ZoneOffset.of("+01");
        ZonedDateTime expected = ZonedDateTime.of(localDateTime , zone);
        Statistics stat = new Statistics(36.5, "HOOK", expected, 110, StatsType.MAXIMUM);
        ZonedDateTime actual = stat.createZDateFromString("2006-12-03T10:15:30+01:00");
        Assert.assertTrue(stat.newerThan(actual));
    }
    
    /**
     * Tests zdt olderThan method
     * @throws ParseException 
     */
    @Test
    public void testZdtOlderThan() throws ParseException
    {
        LocalDate date =  LocalDate.of(2007, 12, 3);
        LocalTime time = LocalTime.of(10, 15, 30);
        LocalDateTime localDateTime =  LocalDateTime.of(date , time);
        ZoneOffset zone =  ZoneOffset.of("+01");
        ZonedDateTime expected = ZonedDateTime.of(localDateTime , zone);
        Statistics stat = new Statistics(36.5, "HOOK", expected, 110, StatsType.MAXIMUM);
        ZonedDateTime actual = stat.createZDateFromString("2008-12-03T10:15:30+01:00");
        Assert.assertTrue(stat.olderThan(actual));
    }
    
    /**
     * Tests zdt sameAs method
     * @throws ParseException 
     */
    @Test
    public void testZdtSameAs() throws ParseException
    {
        LocalDate date =  LocalDate.of(2007, 12, 3);
        LocalTime time = LocalTime.of(10, 15, 30);
        LocalDateTime localDateTime =  LocalDateTime.of(date , time);
        ZoneOffset zone =  ZoneOffset.of("+01");
        ZonedDateTime expected = ZonedDateTime.of(localDateTime , zone);
        Statistics stat = new Statistics(36.5, "HOOK", expected, 110, StatsType.MAXIMUM);
        ZonedDateTime actual = stat.createZDateFromString("2007-12-03T10:15:30+01:00");
        Assert.assertTrue(stat.sameAs(actual));
    }

    /**
     * Tests that the toString method has correct output
     */
    @Test
    public void testToString()
    {
        GregorianCalendar dateTime = new GregorianCalendar(2018, 8, 1, 7, 0, 0);
        Statistics firstStat = new Statistics(36.5, "HOOK", dateTime, 110, StatsType.MAXIMUM);
        String actual = firstStat.toString();
        Assert.assertEquals("36.5", actual);
    }

}
