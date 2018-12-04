import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Austin "Gabe" Scott
 * @version 2018-10-28
 * 
 * MapData 
 * 
 */
public class MapData
{
    private HashMap<String, ArrayList<Observation>> dataCatalog;
    private EnumMap<StatsType, TreeMap<String, Statistics>> statistics;
    private TreeMap<String, Integer> paramPosition;
    private Integer numberOfStations = null;
    private final String TA9M = "TA9M";
    private final String TAIR = "TAIR";
    private final String SRAD = "SRAD";
    // private final String STID = "STID";  Commenting out b/c costing points with Webcat
    private final String MESONET = "Mesonet";
    private String fileName;
    private GregorianCalendar utcDateTime;

    /**
     * Constructor takes in data from Driver and stores all variables accordingly
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param directory
     */
    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        this.utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
        createFileName(this.utcDateTime.get(Calendar.YEAR), this.utcDateTime.get(Calendar.MONTH),
                this.utcDateTime.get(Calendar.DAY_OF_MONTH), 
                this.utcDateTime.get(Calendar.HOUR_OF_DAY), 
                this.utcDateTime.get(Calendar.MINUTE), directory);
        
    }

    /**
     * Formats to correct length the name of file and returns a string containing
     * file name
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param directory
     * @return name of the file
     */
    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {
        String paddedMonth = "";
        String paddedDay = "";
        String paddedHour = "";
        String paddedMinute = "";

        // Format month, day, hour, and minute to be padded with zeros if less than 2
        // digits
        paddedMonth = String.format("%02d", month);
        paddedDay = String.format("%02d", day);
        paddedHour = String.format("%02d", hour);
        paddedMinute = String.format("%02d", minute);

        this.fileName = directory + "/" + year + paddedMonth + paddedDay + paddedHour + paddedMinute + 
                ".mdf";
        return this.fileName;
    }

    /**
     * Parses the Parameter header and puts it in paramPosition TreeMap
     * 
     * @param inParamStr
     */
    private void parseParamHeader(String inParamStr)
    {
        String[] paramHeaders = inParamStr.trim().split("\\s+");
        paramPosition = new TreeMap<String, Integer>();
        // Start from index 1 to skip STID
        for (int i = 1; i < paramHeaders.length; ++i)
        {      
            paramPosition.put(paramHeaders[i], i);
        }
    }
    
    
    
    /**
     * Gets index element matching input String, returns -1 if not there
     * @param inParamStr
     * @return Integer
     */
    public Integer getIndexOf(String inParamStr)
    {
        if (paramPosition.containsKey(inParamStr))
        {
            return paramPosition.get(inParamStr);
        }
        else
        {
            return -1;
        }
    }

    /**
     * Parses file and separates data into appropriate arrayLists. Then calls the
     * methods to calculate statistics
     * 
     * @throws IOException
     */
    public void parseFile() throws IOException
    {
        // setting a string variable to hold the file path and calling function to
        // create the file name
        String filePath = System.getProperty("user.dir") + "/" + this.fileName;
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        // dump first few lines to get to first line of data that we want
        String tempData = br.readLine();
        tempData = br.readLine();
        tempData = br.readLine();
        // accounting for potential null values to be input
        if (tempData != null)
        {
            // trimming whitespace
            tempData = tempData.trim();
        }
        parseParamHeader(tempData);
        
        prepareDataCatalog();
        tempData = br.readLine();
        while (tempData != null)
        {
            // Trim due to whitespace before line of data so it doesn't mess up index
            // variables
            tempData = tempData.trim();
            // splitting data up into array at one or more whitespace
            String[] tempString = tempData.split("\\s+");
            
            for(Map.Entry<String, Integer> entry : paramPosition.entrySet())
            {
                String measurementType = entry.getKey();
                
                // Parsing values from the file
                Double tempValue = Double.parseDouble(tempString[entry.getValue()]);
                // creating new observation object with value and stid 
                // in the temporary string
                Observation a = new Observation(tempValue, tempString[0]);

                //add new observation to data catalog
                // Double check this
                dataCatalog.get(measurementType).add(a);
            }
            // read the next line
            tempData = br.readLine();
        }
        // Call the calculateStatistics method
        calculateStatistics();
    }
    
    /**
     * Calculates statistics
     */
    private void calculateAllStatistics()
    {
        // Create EnumMap and initialize empty TreeMaps
        statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>(StatsType.class);
        statistics.put(StatsType.AVERAGE, new TreeMap<String, Statistics>());
        statistics.put(StatsType.MAXIMUM, new TreeMap<String, Statistics>());
        statistics.put(StatsType.TOTAL, new TreeMap<String, Statistics>());
        statistics.put(StatsType.MINIMUM, new TreeMap<String, Statistics>());
        for (Map.Entry<String, ArrayList<Observation>> pair: dataCatalog.entrySet())
        {
            ArrayList <Observation> tempList = pair.getValue();
            double sum = 0.0;
            int count = 0;
            int max = 0;
            int min = 0;
            // Iterate through values to find max and min
            for (int i = 0; i < tempList.size(); ++i)
            {
                Observation temp = tempList.get(i);
                if (temp.isValid())
                {
                    double value = temp.getValue();
                    sum += value;
                    if (value > tempList.get(max).getValue())
                    {
                        max = i;
                    }
                    if (value < tempList.get(min).getValue())
                    {
                        min = i;
                    }
                    count++;
                }
            }
            numberOfStations = count;
            double average = sum / numberOfStations;
            Statistics avgStat = new Statistics(average, MESONET, this.utcDateTime, numberOfStations, 
                    StatsType.AVERAGE);
            Statistics totStat = new Statistics(sum, MESONET, this.utcDateTime, numberOfStations, 
                    StatsType.TOTAL);
            Statistics maxStat = new Statistics(tempList.get(max).getValue(), tempList.get(max).getStid(), 
                    this.utcDateTime, numberOfStations, StatsType.MAXIMUM);
            Statistics minStat = new Statistics(tempList.get(min).getValue(), tempList.get(min).getStid(), 
                    this.utcDateTime, numberOfStations, StatsType.MINIMUM);
            
            // add statistics to EnumMap
            statistics.get(StatsType.MAXIMUM).put(pair.getKey(), maxStat);
            statistics.get(StatsType.MINIMUM).put(pair.getKey(), minStat);
            statistics.get(StatsType.AVERAGE).put(pair.getKey(), avgStat);
            statistics.get(StatsType.TOTAL).put(pair.getKey(), totStat);
        }
    }
    
    /**
     * Prepares dataCatalog HashMap
     */
    private void prepareDataCatalog()
    {
        // Create HashMap and initialize with empty ArrayLists
        dataCatalog = new HashMap<String, ArrayList<Observation>>();
        for (String param: paramPosition.keySet())
        {
            dataCatalog.put(param, new ArrayList<Observation>());
        }
    }

    /**
     * Calculates Statistics according to the paramID string
     * 
     * @param inData
     * @param paramId
     */
    private void calculateStatistics()
    {
        calculateAllStatistics();
        
    }
    
    /**
     * Gets Statistics
     * @param type
     * @param paramId
     * @return Statistics object
     */
    public Statistics getStatistics(StatsType type, String paramId)
    {
        return statistics.get(type).get(paramId);
    }

    /**
     * formats a string to hold output in the correct format Outputting toString
     * 
     * @return string containing output
     */
    public String toString()
    {
        // Creating statistics objects for each statistic for less cluttered code below
        Statistics tairMax = getStatistics(StatsType.MAXIMUM, TAIR);
        Statistics tairMin = getStatistics(StatsType.MINIMUM, TAIR);
        Statistics tairAvg = getStatistics(StatsType.AVERAGE, TAIR);
        
        Statistics ta9mMax = getStatistics(StatsType.MAXIMUM, TA9M);
        Statistics ta9mMin = getStatistics(StatsType.MINIMUM, TA9M);
        Statistics ta9mAvg = getStatistics(StatsType.AVERAGE, TA9M);
        
        Statistics sradMax = getStatistics(StatsType.MAXIMUM, SRAD);
        Statistics sradMin = getStatistics(StatsType.MINIMUM, SRAD);
        Statistics sradAvg = getStatistics(StatsType.AVERAGE, SRAD);
        
        StringBuffer output = new StringBuffer();
        output.append("=========================================================\n");
        output.append(String.format("=== %d-%02d-%02d %02d:%02d ===\n", utcDateTime.get(Calendar.YEAR),
                utcDateTime.get(Calendar.MONTH), utcDateTime.get(Calendar.DAY_OF_MONTH),
                utcDateTime.get(Calendar.HOUR_OF_DAY), utcDateTime.get(Calendar.MINUTE)));
        output.append("=========================================================\n");
        output.append(String.format("Maximum Air Temperature[1.5m] = %.1f C at %s\n", 
                tairMax.getValue(), tairMax.getStid()));
        output.append(String.format("Minimum Air Temperature[1.5m] = %.1f C at %s\n", 
                tairMin.getValue(), tairMin.getStid()));
        output.append(String.format("Average Air Temperature[1.5m] = %.1f C at %s\n", 
                tairAvg.getValue(), tairAvg.getStid()));
        output.append("=========================================================\n");
        output.append("=========================================================\n");
        output.append(String.format("Maximum Air Temperature[9.0m] = %.1f C at %s\n", ta9mMax.getValue(),
                ta9mMax.getStid()));
        output.append(String.format("Minimum Air Temperature[9.0m] = %.1f C at %s\n", ta9mMin.getValue(),
                ta9mMin.getStid()));
        output.append(String.format("Average Air Temperature[9.0m] = %.1f C at %s\n", 
                ta9mAvg.getValue(), ta9mAvg.getStid()));
        output.append("=========================================================\n");
        output.append("=========================================================\n");
        output.append(String.format("Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", 
                sradMax.getValue(), sradMax.getStid()));
        output.append(String.format("Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", 
                sradMin.getValue(), sradMin.getStid()));
        output.append(String.format("Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", 
                sradAvg.getValue(), sradAvg.getStid()));
        output.append("=========================================================\n");

        return output.toString();
    }

}
