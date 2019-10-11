/* Author: Brady Gordish            MaltsPane.java
*  
*  Description:
*  ---------------
*  JPanel used to view malts in MaltsList and add and remove malts to and from list
*
*  Logic:
*  ---------------

*  MaltsPane()
*  ---------------
*  Set JPanel characteristics
*  initiate MaltsList
*  loads malts to MaltsList
*  add malts to string
*  Initiate objects for JLabel and ActionListeners for JButtons
*  add ActionListeners to JButtons and add visual objects to JPanel
*
*  RemoveListener()
*  ---------------
*  Ask user of name of Malt they want to remove from list and add name to String
*  find malt in list with String
*  remove Malt from list
*  
*/
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;


public class MaltsPane extends JPanel {

   private JButton addButton;
   private JButton removeButton;
   private MaltsList malts;
   private JLabel maltsList;
   private JPanel container, bContainer;
   private JScrollPane jsp;
   private String list;
   
   public MaltsPane()throws IOException {
      // set JPanel characteristics
      setPreferredSize(new Dimension(400,400));
      setLayout(new BorderLayout());
      setBackground(Color.gray);
      
      // Initiate MaltsList and load Malts to list
      malts = new MaltsList();
      
      try  {
            malts = malts.load("MaltsList.txt");
         }
         catch(ClassNotFoundException e) {
            System.out.println("Did not load Malts");
            e.getStackTrace();
         }
      // Initiate objects used for JPanel
      list = malts.toString();
      container = new JPanel();
      bContainer = new JPanel();
      
      
      addButton = new JButton("Add");
      removeButton = new JButton("Remove");
      
      AddListener addListener = new AddListener();
      RemoveListener removeListener = new RemoveListener();
      
      addButton.addActionListener(addListener);
      removeButton.addActionListener(removeListener);
      
      maltsList = new JLabel(list);
      container.add(maltsList);
      bContainer.add(addButton);
      bContainer.add(removeButton);
      
      jsp = new JScrollPane(container);
      
      add(jsp, BorderLayout.CENTER);
      add(bContainer, BorderLayout.SOUTH);
      
   }
   /* Removes item from list with given name then saves list to MaltsList.txt
   *  and updates String with list
   */
   public class RemoveListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         
         String name;
         name = JOptionPane.showInputDialog("Name of the malt you'd like to remove: ");
         malts.removeMalt(malts.find(name));
         list = malts.toString();
         maltsList.setText(list);
         try {
            malts.save("MaltsList.txt");
         }
         catch(IOException e) {
            e.printStackTrace();
         }

      }
   }
   /* Ask user for Malt characteristics, then uses values to add
   *  a new Malt to list
   */
   public class AddListener implements ActionListener {
      public void actionPerformed(ActionEvent event){
         String name, type, select = "";
         int love, ppg = 0;
         
         name = JOptionPane.showInputDialog("Malts Name: ");
         select = JOptionPane.showInputDialog("Malt Color: ");
         love = Integer.parseInt(select);
         select = JOptionPane.showInputDialog("Pounds per Gallon: ");
         ppg = Integer.parseInt(select);
         type = JOptionPane.showInputDialog("Malt Type: ");
         
         
         malts.addMalt(new Malt(name, love, ppg, type));
         
         try {
            malts.save("MaltsList.txt");
         }
         catch(IOException e) {
            e.printStackTrace();
         }
         list = malts.toString();
         maltsList.setText(list);
         
         try {
            malts.load("MaltsList.txt");
         }
         catch(ClassNotFoundException e) {
            e.printStackTrace();
         }
         catch(IOException e) {
            e.printStackTrace();
         }
         
         repaint();
         
      }
   }
}
