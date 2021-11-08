import org.apache.hc.core5.http.ParseException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ConvertWindow extends MainWindow implements ActionListener {
    private JLabel l1,l2;
    private JTextField TextField;
    private JButton b1,b2;
    private JComboBox CryptocurrencyBox,CurrencyBox;
    private String Cryptocurrency[]={"BTC","ETH"};
    private String Currency[]= {"SAR","USD"};

    public ConvertWindow() { 
        
       

        JPanel p = (JPanel) this.getContentPane();
        p.setLayout(new GridLayout(2,1));

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
            
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
    
        // first part from main Jframe (left side)
        l1 = new JLabel("Cryptocurrency");
        TextField = new JTextField();
        TextField.setPreferredSize(new Dimension(50,20));
        CryptocurrencyBox = new JComboBox(Cryptocurrency);
        
       // right side
        l2 = new JLabel("Currency");
        CurrencyBox = new JComboBox(Currency);
        
        p1.add(l1);
        p1.add(TextField);
        p1.add(CryptocurrencyBox);
        p1.add(l2);
        p1.add(CurrencyBox);
        p.add(p1);
    // sconde part 

      b1 = new JButton("Convert");
      b2 = new JButton("Reset");
 
     p2.add(b1);
     p2.add(b2);
     p.add(p2);

     
     b1.addActionListener(this);

    }
 
    public void actionPerformed(ActionEvent e)
        {
           if (e.getSource() == b1)
           {   
             try {
                convert();
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
           }
            
        }
    
    public void Rebort_Window(String[] rebort){
               JFrame frame = new JFrame();
               frame.setLayout(new GridLayout(3,1));
            //    frame.setSize(500,500);
               
               frame.setVisible(true);
              
               JPanel panel1 = new JPanel();
               panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
               JLabel label1 = new JLabel("Last updated : "+rebort[1]+ "(Coordinated Universal Time)");
               label1.setFont(new Font("Serif",Font.PLAIN,16));
               panel1.add(label1);
               frame.add(panel1);
               
               JPanel panel2 = new JPanel();
               panel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
                
               JLabel label2 = new JLabel();
               label2.setText("Cryptocurrency : "+rebort[0]); 
               label2.setFont(new Font("Serif",Font.PLAIN,16));

               JLabel label3 = new JLabel();
               label3.setText("Amount : "+rebort[2]); 
               label3.setFont(new Font("Serif",Font.PLAIN,16));
               panel2.add(label2);
               panel2.add(label3);
               frame.add(panel2);
              
               JPanel panel3 = new JPanel();
               panel3.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
               JLabel label13 = new JLabel();
               label13.setText("<html> Price :"+rebort[3] + "<br>     AT  "+rebort[4]+"<br></html>");
               label13.setFont(new Font("Serif",Font.PLAIN,16));
               panel3.add(label13);
              frame.add(panel3);
              
              frame.pack();

 

    }
   
    public void convert() throws ParseException {
        String s = TextField.getText();
        double value ; 
        cryptocurrency p = new cryptocurrency();
        if (s.isEmpty()){
            JOptionPane.showMessageDialog(null  , "please enter a number","Vaule missing",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            value = Double.parseDouble(s);
            if (value <=0){
                JOptionPane.showMessageDialog(null  , "value is negative","negative value",JOptionPane.WARNING_MESSAGE);
                return;
                }


        }catch(Exception e){
           
            JOptionPane.showMessageDialog(null  , "please enter correct value"+ "\n\nError code : "+ e.toString(),"incorret",JOptionPane.INFORMATION_MESSAGE );
            return;
        }
        
        
        String rebort[] = p.Cryptocurrency_Rebort(String.valueOf(value), Cryptocurrency[CryptocurrencyBox.getSelectedIndex()], Currency[CurrencyBox.getSelectedIndex()]);
        Rebort_Window(rebort);
    }
   
   
}

