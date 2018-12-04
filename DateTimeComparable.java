import java.time.ZonedDateTime;
import java.util.GregorianCalendar;


/**
 * @author Austin "Gabe" Scott
 * @version 2018-10-28
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
