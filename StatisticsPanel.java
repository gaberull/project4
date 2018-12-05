import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;                

public class StatisticsPanel extends JPanel
{
    private ButtonGroup buttonGroup;
    public StatisticsPanel()
    {
        setLayout(new GridLayout(3,0));
        
        JRadioButton minimum = new JRadioButton("MINIMUM");
        JRadioButton average = new JRadioButton("AVERAGE");
        JRadioButton maximum = new JRadioButton("MAXIMUM");
        
        add(minimum);
        add(average);
        add(maximum);
        
        // add TitledBorder to top of panel
        setBorder(new TitledBorder(null, "Statistics", TitledBorder.LEFT, TitledBorder.TOP));
        
        setBackground(Color.GRAY);
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(minimum);
        buttonGroup.add(average);
        buttonGroup.add(maximum);
        maximum.setSelected(true);
        //setSize(150, 140);
        
        setVisible(true);
        setOpaque(true);
    }
    
    public ButtonGroup getButtonGroup()
    {
        return buttonGroup;
    }
}
