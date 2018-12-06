import java.awt.Color;
import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;                

/**
 * @author Austin "Gabe" Scott
 * @version 2018-12-06
 * 
 * StatisticsPanel Class
 *
 */
public class StatisticsPanel extends JPanel
{
    private ButtonGroup buttonGroup;
    public StatisticsPanel()
    {
        // set layout
        setLayout(new GridLayout(3,0));
        
        // create buttons
        JRadioButton minimum = new JRadioButton("MINIMUM");
        JRadioButton average = new JRadioButton("AVERAGE");
        JRadioButton maximum = new JRadioButton("MAXIMUM");
        
        add(minimum);
        add(average);
        add(maximum);
        
        // add TitledBorder to top of panel
        setBorder(new TitledBorder(null, "Statistics", TitledBorder.LEFT, TitledBorder.TOP));
        
        setBackground(Color.GRAY);
        
        // button group makes one button selectable only
        buttonGroup = new ButtonGroup();
        buttonGroup.add(minimum);
        buttonGroup.add(average);
        buttonGroup.add(maximum);
        maximum.setSelected(true);
        
        // set visible
        setVisible(true);
        setOpaque(true);
    }
    
    /**
     * Get selected Statistic
     * @return String
     */
    public String getSelectedStatistic()
    {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            {
                if (button.isSelected())
                {
                    return button.getText();
                }
            }
        }
        return null;
    }
}
