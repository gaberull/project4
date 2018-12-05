import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @author Austin "Gabe" Scott
 * @version 2018-12-06
 * 
 * Parameter Panel
 *
 */
public class ParameterPanel extends JPanel
{
    ArrayList<JCheckBox> boxes = new ArrayList<>();
    public ParameterPanel()
    {
        super();
        setLayout(new GridLayout(5,0));
        
        JCheckBox tair = new JCheckBox("TAIR");
        JCheckBox ta9m = new JCheckBox("TA9M");
        JCheckBox srad = new JCheckBox("SRAD");
        JCheckBox wspd = new JCheckBox("WSPD");
        JCheckBox pres = new JCheckBox("PRES");
        
        boxes.add(tair);
        boxes.add(ta9m);
        boxes.add(srad);
        boxes.add(wspd);
        boxes.add(pres);
        
        add(tair);
        add(ta9m);
        add(srad);
        add(wspd);
        add(pres);
        
        setBorder(new TitledBorder(null, "Parameter",TitledBorder.LEFT, TitledBorder.TOP));
        setBackground(Color.LIGHT_GRAY);
        
        
        
        setVisible(true);
        setSize(30, 140);
        
    }
    
    public ArrayList<String> getSelectedCheckBoxes()
    {
        ArrayList<String> list = new ArrayList<>();
        
        for (JCheckBox box : boxes)
        {
            if (box.isSelected())
            {
                list.add(box.getText());
            }
        }
        return list;
    }
}
