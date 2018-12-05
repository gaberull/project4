import java.time.ZonedDateTime;
import java.util.GregorianCalendar;


/**
 * @author Austin "Gabe" Scott
 * @version 2018-12-06
 */
public interface DateTimeComparable
{
    boolean newerThan(GregorianCalendar inDateTimeUTC);

    boolean olderThan(GregorianCalendar inDateTimeUTC);

    boolean sameAs(GregorianCalendar inDateTimeUTC);
    
    boolean newerThan(ZonedDateTime inDateTimeUTC);
    
    boolean olderThan(ZonedDateTime inDateTimeUTC);
    
    boolean sameAs(ZonedDateTime inDateTimeUTC);

}
