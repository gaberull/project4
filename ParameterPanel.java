import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * @author Austin "Gabe" Scott
 * @version 2018-12-06
 * 
 * Parameter Panel
 *
 */
public class ParameterPanel extends JPanel
{
    public ParameterPanel()
    {
        super();
        setLayout(new GridLayout(5,0));
        
        JCheckBox tair = new JCheckBox("TAIR");
        JCheckBox ta9m = new JCheckBox("TA9M");
        JCheckBox srad = new JCheckBox("SRAD");
        JCheckBox wspd = new JCheckBox("WSPD");
        JCheckBox pres = new JCheckBox("PRES");
        
        add(tair);
        add(ta9m);
        add(srad);
        add(wspd);
        add(pres);
        
        setVisible(true);
        setSize(30, 140);
        
    }
}
