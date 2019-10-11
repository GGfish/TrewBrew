/* Author: Brady Gordish            HopsPane.java
*
*  Description: 
*  --------------
*  JPanel used to view list of hops and to add and remove hops from the list
*
*  Logic:
*  -------------
*
*  HopsPane()
*  --------------
*  Set JPanel layout as a BorderLayout
*  Set Background to gray
*  Set preferred demensions 500, 500
*  initiate HopsList()
*  create actionlisteners for buttons
*  load hops from HopsList.txt and add them to HopsList()
*  create JPanel's objects and add them to panel
*  
*  RemoveListener()
*  ----------------------
*  If user clickst the removeButton
*  Ask user for name of Hops they want to remove, and add name to String 'name'
*  find hops by name and remove
*  set list string with update list
*  save list to HopsList.txt
*
*  AddListener()
*  ---------------------
*  If addButton is clicked
*  Ask user for name of hops, AA%, and variety and set variables with values
*  add new Hops with said values
*  save to HopsList.txt
*  set list String with updated list
*/
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;


public class HopsPane extends JPanel {
   private JPanel container, bContainer;
   private JButton addButton;
   private JButton removeButton;
   private JLabel hopsList;
   private HopsList hops;
   private JScrollPane jsp;
   private String list;
   
   public HopsPane()throws IOException {
      // Set characteristics of JPanel
      setLayout(new BorderLayout());
      setBackground(Color.gray);
      setPreferredSize(new Dimension(400,400));
      
      //Initiate HopsList
      hops = new HopsList();
      
      
      //Initiate ActionListeners
      AddListener addListener = new AddListener();
      RemoveListener removeListener = new RemoveListener();
      
      //Load Hops to list
      try {
         hops = hops.load("HopsList.txt");
      }
      catch(ClassNotFoundException e) {
         e.getStackTrace();
      }
      
      // set hops to String
      list = hops.toString();
      
      //initiate objects for JPanel
      container = new JPanel();
      bContainer = new JPanel();
      addButton = new JButton("Add");
      removeButton = new JButton("Remove");
      
      //set ActionListeners for JButtons
      addButton.addActionListener(addListener);
      removeButton.addActionListener(removeListener);
      
      //Add Objects to JPanel
      hopsList = new JLabel(list);
      container.add(hopsList);
      
      bContainer.add(addButton);
      bContainer.add(removeButton);
      jsp = new JScrollPane(container);
      
      add(jsp, BorderLayout.CENTER);
      add(bContainer, BorderLayout.SOUTH);
   }
   
   /* Asks user for name of hops they'd like to remove, the finds the named hops
   *  and removes it from the list, then saves the list and updates the list's JLabel
   */
   public class RemoveListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         String name;
         name = JOptionPane.showInputDialog("Name of hops you'd like to remove: ");
         hops.removeHops(hops.find(name));
         list = hops.toString();
         hopsList.setText(list);
         try {
            hops.save("HopsList.txt");
         }
         catch(IOException e) {
            e.printStackTrace();
         }
      }
      
   }
   
   /* Asks user for new Hops characteristics then adds variables to create a new Hops
   *  to list. Then saves list to HopsList.txt and updates list's string
   */
   public class AddListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         String name, type, select = "";
         double alpha = 0.0;
         
         name = JOptionPane.showInputDialog("Name of Hops: ");
         select = JOptionPane.showInputDialog("Acidity: ");
         alpha = Double.parseDouble(select);
         type = JOptionPane.showInputDialog("Type of Hops(Bittering/Aroma/Both): ");
         
         hops.addHops(new Hops(name, alpha, type));
         try {
            hops.save("HopsList.txt");
         }
         catch(IOException e) {
            e.getStackTrace();
         }
         
         list = hops.toString();
         hopsList.setText(list);
      }
   }
}

