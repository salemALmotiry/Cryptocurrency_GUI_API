import javax.swing.JFrame;
import javax.swing.UIManager;


public class MainWindow extends JFrame{
   
    MainWindow(){
        setLocation(500,270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
       
        
       
    }



    public static void main(String[] args) {
       
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
      
        ConvertWindow w = new ConvertWindow();
         w.pack();
         w.setVisible(true);
    
         
        


   
        

        
    } 
}
