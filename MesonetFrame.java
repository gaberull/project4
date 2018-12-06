import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author Austin "Gabe" Scott
 * @version 2018-12-06
 * 
 * Oklahoma Mesonet top Frame
 *
 */
public class MesonetFrame extends JFrame
{
    private MapData mapData;
    private TabelPanel tabelPanel;
    private File file;
    
    /**
     * Constructor
     * 
     * @param title
     */
    public MesonetFrame(String title)
    {
        super(title);
        this.setSize(1000, 610);
        this.setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create menubar
        setJMenuBar(new FileMenuBar());
         
        // adding buttons to bottom of frame
        JButton calculate = new JButton("Calculate");
        JButton exit = new JButton("Exit");
        
        // create subPanel to hold calculate and exit buttons
        JPanel subPanel = new JPanel();
        
        // create top label and a JPanel that it sits in
        JLabel topLabel = new JLabel("Mesonet - We don't set records, we report them!");
        JPanel topPanel = new JPanel();
        
        // creating layout to center the topLabel within the panel
        GridBagLayout topPanelLayout = new GridBagLayout();
        topPanel.setLayout(topPanelLayout);
        topPanel.add(topLabel);
        this.add(topPanel, BorderLayout.NORTH);
        topPanel.setVisible(true);
       
        // add calculate and exit buttons to subPanel and set in south region of BorderLayout
        subPanel.add(calculate);
        subPanel.add(exit);
        this.add(subPanel, BorderLayout.SOUTH);
        
        // create panels for Statistics and Parameter buttons and checkboxes
        StatisticsPanel statPanel = new StatisticsPanel();
        ParameterPanel paramPanel = new ParameterPanel();
        
        // create subPanel for Parameter checkboxes and statistics buttons
        JPanel westSubPanel = new JPanel(new GridLayout(0,2));
        westSubPanel.add(paramPanel);
        westSubPanel.add(statPanel); 
        
        
        // add statSubPanel to frame
        this.add(westSubPanel, BorderLayout.WEST);
        
        // add TabelPanel
        tabelPanel = new TabelPanel();
        this.add(tabelPanel, BorderLayout.CENTER);
        
        // add actions for when buttons are hit
        calculate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (MesonetFrame.this.file == null)
                {
                    return;
                }
                String statistic = statPanel.getSelectedStatistic();
                ArrayList<String> parameterList = paramPanel.getSelectedCheckBoxes();
                for (String param : parameterList)
                {
                    loadStatistic(param, statistic);
                }
            }
        });
        
        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        
        this.setVisible(true);
    }
    
    /**
     * Load file
     * @param file
     * @throws IOException
     */
    private void loadFile(File file) throws IOException
    {
        if (file == null)
        {
            return;
        }
        this.file = file;
        
        // parse integer from file name
        String name = file.getName();
        Integer year = Integer.parseInt(name.substring(0, 4));
        Integer month = Integer.parseInt(name.substring(4, 6));
        Integer day = Integer.parseInt(name.substring(6, 8));
        Integer hour = Integer.parseInt(name.substring(8, 10));
        Integer minute = Integer.parseInt(name.substring(10, 12));
        
        // create mapData object and parseFile
        mapData = new MapData(year, month, day, hour, minute);
        mapData.parseFile(file);
        
    }
    
    /**
     * Get TabelPanel
     * @return TabelPanel
     */
    private TabelPanel getTabelPanel()
    {
        return tabelPanel;
    }
    
    /**
     * Load Statistic
     * @param parameter
     * @param statistic
     */
    private void loadStatistic(String parameter, String statistic)
    {
        StatsType statType = StatsType.valueOf(statistic);
        Statistics stat = mapData.getStatistics(statType, parameter);
        getTabelPanel().addRow(stat.getStid(), parameter, statistic, Double.toString(stat.getValue()), 
                Integer.toString(stat.getNumberOfReportingStations()), stat.getUTCDateTimeString());
    }
        public class FileMenuBar extends JMenuBar
        {

            public FileMenuBar() 
            {
                super();
                // create menu options 
                JMenu menu = new JMenu("File");   
                JMenuItem i1 = new JMenuItem("Open Data File"); 
                JMenuItem i2 = new JMenuItem("Exit");  
                
                i1.addActionListener(new ActionListener()
                {
                    
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JFileChooser fc = new JFileChooser();
                        int returnVal = fc.showOpenDialog(i1);
                        
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            try
                            {
                                MesonetFrame.this.loadFile(file);
                            }
                            catch (IOException e1)
                            {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                
                i2.addActionListener(new ActionListener()
                {
                    
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        System.exit(0);
                    }
                });
                
                // add items to menubar
                menu.add(i1);    
                menu.add(i2); 
                this.add(menu);  
                
            }
            
        }
    
        /**
         * Main
         * @param args
         */
        public static void main(String[] args)
        {
            MesonetFrame frame = new MesonetFrame("Oklahoma Mesonet - Statistics Calculator");
        }

}