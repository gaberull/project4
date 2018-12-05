import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelPanel extends JPanel
{
    public TabelPanel()
    {
        super();
        setLayout(new BorderLayout());
        
        // Create JTable at top
        DefaultTableModel model = new DefaultTableModel(); 
        JTable table = new JTable(model); 

        // add scroll pane
        JScrollPane scrollPane = new JScrollPane();

        // add columns
        model.addColumn("Station"); 
        model.addColumn("Parameter"); 
        model.addColumn("Statistics"); 
        model.addColumn("Value"); 
        model.addColumn("Reporting Stations"); 
        model.addColumn("Date"); 
        
        // set scroll pane view and add scrollPane
        scrollPane.setViewportView(table);
        this.add(scrollPane);
        
        setVisible(true);
    }
    
    public void addRow()
    {
        
    }
    
}
