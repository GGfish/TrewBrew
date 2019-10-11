/* Author: Brady Gordish            YeastPane.java
*  
*  Description: 
*  --------------------
*  Class used to view list of yeasts as well as add and remove yeasts from list
*
*  Logic:
*  ----------
*  
*  YeastPane()
*  -----------------
*  Declare variables
*  load YeastList
*  create JPanel and objects used in panel
*  print yeastlist in JLabel
*
*  RemoveListener()
*  ----------------
*  Ask user for name of yeast to remove
*  find yeast in list by it's name and remove the yeast
*  save list to YeastList.txt
*  update String to show new list
*
*  AddListener()
*  ---------------
*  Ask user for name of new yeast
*  add a new yeast to list with given name
*  save list to YeastList.txt
*  update String to show new list
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class YeastPane extends JPanel {

   private JPanel container, bContainer;
   private JButton addButton, removeButton;
   private JLabel yeastList;
   private JScrollPane jsp;
   private YeastList yeast;
   private String list;
   
   public YeastPane() throws IOException {
   
      
      setLayout(new BorderLayout());
      setBackground(Color.gray);
      //Load YeastList.txt
      try {
         yeast = yeast.load("YeastList.txt");
      }
      catch(ClassNotFoundException e) {
         e.getStackTrace();
      }
      
      //Set up JPanel
      container = new JPanel();
      bContainer = new JPanel();
      
      addButton = new JButton("Add");
      removeButton = new JButton("Remove");
      
      AddListener addListener = new AddListener();
      RemoveListener removeListener = new RemoveListener();
      
      addButton.addActionListener(addListener);
      removeButton.addActionListener(removeListener);
      
      list = yeast.toString();
      
      yeastList = new JLabel(list);
      container.add(yeastList);
      bContainer.add(addButton);
      bContainer.add(removeButton);
      
      JScrollPane jsp = new JScrollPane(container);
      
      add(jsp);
      add(bContainer, BorderLayout.SOUTH);
   }
   
   //Removes a Yeast from list with a selected name
   public class RemoveListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         String name;
         name = JOptionPane.showInputDialog("Name of yeast you'd like to remove: ");
         yeast.removeYeast(yeast.find(name));
         list = yeast.toString();
         yeastList.setText(list);
      }
   }
   //Asks user for name of yeast and adds it to the list
   public class AddListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         String name;
         
         name = JOptionPane.showInputDialog("Yeast Name: ");
         
         yeast.addYeast(new Yeast(name));
         
         try {
            yeast.save("YeastList.txt");
         }
         catch(IOException e) {
            e.printStackTrace();
         }
         
         list = yeast.toString();
         yeastList.setText(list);
      }
   }
}