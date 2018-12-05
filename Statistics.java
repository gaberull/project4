import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * 
 */

/**
 * @author Austin "Gabe" Scott
 * @version 2018-10-28
 *
 */
public class Statistics extends Observation implements DateTimeComparable
{
    protected final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    protected DateTimeFormatter format;
    private GregorianCalendar utcDateTime;
    private ZonedDateTime zdtDateTime;
    private int numberOfReportingStations;
    private StatsType statType;   

    /**
     * Statistics Constructor that takes in GregorianCalendar Object instead of dateTimeString
     * @param value
     * @param stid
     * @param dateTime
     * @param numberOfValidStations
     * @param inStatType
     */
    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        numberOfReportingStations = numberOfValidStations;
        utcDateTime = dateTime;
        statType = inStatType;
        format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
    }
    
    /**
     * Statistics Constructor that takes in ZonedDateTime Object
     * @param value
     * @param stid
     * @param dateTime
     * @param numberOfValidStations
     * @param inStatType
     * @throws ParseException 
     */
    public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations,
            StatsType inStatType) throws ParseException
    {
        super(value, stid);
        numberOfReportingStations = numberOfValidStations;
        zdtDateTime = dateTime;
        statType = inStatType;
        format = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        String tempDateStr = createStringFromDate(dateTime);
        
        
    }

    /**
     * Creates a GregorianCalendar date from a dateTime String
     * Got this formatting code from Stack Overflow
     * @param dateTimeStr
     * @return the Gregorian Calendar date of the input string
     * @throws ParseException
     */
    public GregorianCalendar createDateFromString(String dateTimeStr) throws ParseException
    {
        Instant instant = Instant.parse(dateTimeStr);
        Date date = Date.from(instant);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        
        return cal;
    }
    
    /**
     * Creates a ZonedDateTime object from a string
     * @param dateTimeStr
     * @return
     */
    public ZonedDateTime createZDateFromString(String dateTimeStr)
    {
        return ZonedDateTime.parse(dateTimeStr);
    }

    /**
     * Creates a date in the format of a string from a ZonedDateTime object
     * @param calendar
     * @return String 
     */
    public String createStringFromDate(ZonedDateTime calendar)
    {
        return calendar.format(format); 
    }
    
    /**
     * Creates a date in the format of a string from a GregorianCalendar date object
     * Got part of this from Stack overflow
     * @param calendar
     * @return
     */
    public String createStringFromDate(GregorianCalendar calendar)
    {
        Date date = calendar.getTime();
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_TIME_FORMAT); 
        fmt.setCalendar(calendar);
        TimeZone utc = TimeZone.getTimeZone("UTC");
        fmt.setTimeZone(utc);
        String dateFormatted = fmt.format(date);
        
        return dateFormatted;
    }

    /**
     * Gets the number of valid reporting Stations
     * @return numberOfReportingStations
     */
    public int getNumberOfReportingStations()
    {
        return this.numberOfReportingStations;
    }

    /**
     * Gets a String of the UTC date time
     * @return String of utcDateTime
     */
    public String getUTCDateTimeString()
    {
        return createStringFromDate(this.utcDateTime);
    }

    /**
     * Compares utcDateTime to input GregorianCalendar inDateTime object
     * @return boolean value
     */
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        return this.utcDateTime.compareTo(inDateTime) > 0;
    }

    /**
     * Compares utcDateTime to input GregorianCalendar inDateTime object
     * @return boolean value
     */
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        return this.utcDateTime.compareTo(inDateTime) < 0;
    }

    /**
     * Compares utcDateTime to input GregorianCalendar inDateTime object
     * @return boolean value
     */
    public boolean sameAs(GregorianCalendar inDateTime)
    {
        return this.utcDateTime.compareTo(inDateTime) == 0;
    }
    
    /**
     * Compares ZonedDateTime objects
     * @return boolean value
     * 
     */
    public boolean newerThan(ZonedDateTime inDateTime)
    {
        return this.zdtDateTime.compareTo(inDateTime) > 0;
        
        
    }
    
    /**
     * Compares zdtDateTime to input ZonedDateTime object
     * @return boolean value
     */
    public boolean olderThan(ZonedDateTime inDateTime)
    {
        return this.zdtDateTime.compareTo(inDateTime) < 0;
    }
    
    /**
     * Compares zdtDateTime to input ZonedDateTime object
     * @return boolean value
     */
    public boolean sameAs(ZonedDateTime inDateTime)
    {
        return this.zdtDateTime.compareTo(inDateTime) == 0;
    }

    /**
     * outputs String value
     * @return String value
     */
    public String toString()
    {
        return super.toString();
    }

}
