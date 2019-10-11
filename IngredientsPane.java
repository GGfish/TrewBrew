/* Author: Brady Gordish         IngredientsPane.java
*
*  Description: A JPanel that is used to hold the JTabbedPane for ingredients JPanels
*
*  Logic:
*  ---------
*  Set characteristics for size, color, and BorderLayout
*  Initilize JTabbedPane tab
*  Add tabs to tab for MaltsPane(), HopsPane(), YeastPane()
*  Add tab to JPanel at the top
*/


import java.awt.*;
import javax.swing.*;
import java.io.*;


public class IngredientsPane extends JPanel {
   
   
   public IngredientsPane() throws IOException {
      setLayout(new BorderLayout());
      setBackground(Color.gray);
      setPreferredSize(new Dimension(600,1000));
      
      //Initialize tab and add tabs for JPanels
      JTabbedPane tab = new JTabbedPane();
      tab.addTab("Malts", new MaltsPane());
      tab.addTab("Hops", new HopsPane());
      tab.addTab("Yeast", new YeastPane());
      
      //Adds tab to top of IngredientsPane
      add(tab, BorderLayout.NORTH);
      
   }
}



