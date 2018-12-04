import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;                

public class StatisticsPanel extends JPanel
{
    public StatisticsPanel()
    {
        setLayout(new GridLayout(3,0));
        
        JRadioButton minimum = new JRadioButton("MINIMUM");
        JRadioButton average = new JRadioButton("AVERAGE");
        JRadioButton maximum = new JRadioButton("MAXIMUM");
        
        add(minimum);
        add(average);
        add(maximum);
        
        setVisible(true);
        setOpaque(true);
        
        setBackground(Color.GRAY);
        
        ButtonGroup group = new ButtonGroup();
        group.add(minimum);
        group.add(average);
        group.add(maximum);
        
    }
}
