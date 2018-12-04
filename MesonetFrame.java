import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MesonetFrame extends JFrame
{
    
    public MesonetFrame(String title)
    {
        super(title);
        this.setSize(850, 610);

        this.setLocationRelativeTo(null);

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FileMenuBar mb = new FileMenuBar();  
        this.setJMenuBar(mb);  
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

