import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
    
    public MesonetFrame(String title)
    {
        super(title);
        this.setSize(1000, 610);

        this.setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        //BorderLayout layout = new BorderLayout();
        //this.setLayout(layout);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create menubar
        setJMenuBar(new FileMenuBar());
        // FileMenuBar mb = new FileMenuBar();  
        // this.setJMenuBar(mb);  
         
        
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
        
        // Create JLabel for top of Parameter and Statistics boxes
        JLabel param = new JLabel("Parmater");
        JLabel stat = new JLabel("Statistics");
        
        // add statSubPanel to frame
        this.add(westSubPanel, BorderLayout.WEST);
        
        // add TabelPanel
        TabelPanel table = new TabelPanel();
        this.add(table, BorderLayout.CENTER);
        
        // add actions for when buttons are hit
        calculate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
            }
        });
        
        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
            }
        });
        
        this.setVisible(true);
    }
        public class FileMenuBar extends JMenuBar
        {
            public FileMenuBar() 
            {
                super();
                JMenu menu = new JMenu("File");   
                JMenuItem i1 = new JMenuItem("Open Data File"); 
                JMenuItem i2 = new JMenuItem("Exit");  
                
                i1.addActionListener(new ActionListener()
                {
                    
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        
                    }
                });
                
                i2.addActionListener(new ActionListener()
                {
                    
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        
                    }
                });
                
                menu.add(i1);    
                menu.add(i2); 
                this.add(menu);  
                
            }
            
        }
        
        
    
        
        public static void main(String[] args)
        {
            MesonetFrame frame = new MesonetFrame("Oklahoma Mesonet - Statistics Calculator");
        }

}

