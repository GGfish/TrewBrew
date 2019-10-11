/* Author: Brady Gordish            IntroPanel.java
*
*  Descriptions: A simple JPanel used as an introduction panel for the program
*/
import java.awt.*;
import javax.swing.*;

public class IntroPanel extends JPanel {
   public IntroPanel() {
   
      setPreferredSize(new Dimension(500,500));
      setBackground(Color.gray);
      
      //Adds a friendly greeting for user
      JLabel l1 = new JLabel("Welcome to Trew Brew!");
      JLabel l2 = new JLabel("Choose a tab to start your brewing adventure!");
      
      add(l1);
      add(l2);
   }
   
}