import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Austin "Gabe" Scott
 * @version 2018-12-06
 * 
 * TabelPanel Class
 *
 */
public class TabelPanel extends JPanel
{
    private JTable table;
    public TabelPanel()
    {
        super();
        setLayout(new BorderLayout());
        
        // Create JTable at top
        DefaultTableModel model = new DefaultTableModel(); 
        JTable table = new JTable(model); 
        this.table =table;
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
    
    /**
     * Get table
     * @return JTable
     */
    public JTable getTable()
    {
        return table;
    }
    
    /**
     * Add row to table
     * 
     * @param station
     * @param parameter
     * @param statistics
     * @param value
     * @param reportingStations
     * @param date
     */
    public void addRow(String station, String parameter, String statistics, String value, String 
            reportingStations, String date)
    {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(new Object[]{station, parameter, statistics, value, reportingStations, date});
    }
    
}
