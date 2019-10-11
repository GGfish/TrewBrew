/* Author: Brady Gordish         ProfilePane.java
*  
*  Description: 
*  --------------------------
*  JPanel used to view, add, and/or remove Beer profiles from the ProfileList
*
*  Logic:
*  -----------------
*
*  ProfilePane
*  ------------------
*  Declare variables
*  load ProfileList
*  create JPanel and objects used in panel
*  print list in JLabel
*
*  RemoveListener
*  -----------------
*  Ask user for name of profile
*  remove profile from list
*  save profilelist to ProfileList.txt
*  update string and print new string to JLabel
*
*  AddListener
*  -----------------
*  declare variables
*  ask user for name and profile characteristics of new profile
*  create new profile with collected data and add to list
*  save profilelist to ProfileList.txt
*  update string and print new string to JLabel
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ProfilePane extends JPanel {
      // Declare objects and variables
      private JLabel profileList;
      
      private JButton addButton;
      private JButton removeButton;
      
      private ProfileList list;
      private String profiles;
      private JScrollPane jsp;
      private JPanel container;
      private JPanel bContainer;
      
      public ProfilePane() throws IOException {
         // Initiate ProfileList list.
         list = new ProfileList();
         
         //Load the list with items found in ProfileList.txt
         try  {
            list = list.load("ProfileList.txt");
         }
         catch(ClassNotFoundException e) {
            e.getStackTrace();
         }
         
         // String of all beer profiles that will be shown on JLabel
         profiles = list.toString();
         
         //Create JPanel characteristics and add objects
         setLayout(new BorderLayout());
         setBackground(Color.gray);
         setPreferredSize(new Dimension(400,400));
         
         AddListener addListener = new AddListener();
         RemoveListener removeListener = new RemoveListener();
         addButton = new JButton("Add a Profile");
         removeButton = new JButton("Remove a Profile");
         
         addButton.addActionListener(addListener);
         removeButton.addActionListener(removeListener);
         
         profileList= new JLabel(profiles);
         container = new JPanel();
         bContainer = new JPanel();
         
         bContainer.add(addButton);
         bContainer.add(removeButton);
         
         container.add(profileList);
         jsp = new JScrollPane(container);
         add(bContainer, BorderLayout.SOUTH);
         add(jsp, BorderLayout.CENTER);
         
      }
      
      
      /* Used to remove a beer profile from the list, then saves the updated list to ProfileList.txt
      */
      public class RemoveListener implements ActionListener {
         public void actionPerformed(ActionEvent event) {
            
            String name;
            //Ask user for name of the profile they wish to delete.
            name = JOptionPane.showInputDialog("Name of the beer profile you would like to delete: ");
            list.removeProfile(list.find(name));
            
            //Update string with list
            profiles = list.toString();
            profileList.setText(profiles);
            
            // Save the updated list
            try {
               list.save("ProfileList.txt");
               System.out.println("Saved");
                  
            }
            catch(IOException e) {
               e.printStackTrace();
               System.out.println("Did not print");
            }
         }
      }
            
      /* Used to add a new beer profile to the list, update the list with added profile, and 
      *  save to Profile.txt
      */
      public class AddListener implements ActionListener {
         public void actionPerformed(ActionEvent event){
            String name, select = "";
            int maxC, minC, maxB, minB = 0;
            double maxA, minA, maxO, minO, maxF, minF = 0.0;
            //Ask user for new profile name and characteristics
            name = JOptionPane.showInputDialog("Style Name: ");
            select = JOptionPane.showInputDialog("Max Color: ");
            maxC = Integer.parseInt(select);
            select = JOptionPane.showInputDialog("Min Color: ");
            minC = Integer.parseInt(select);
            select = JOptionPane.showInputDialog("Max IBUs: ");
            maxB = Integer.parseInt(select);
            select = JOptionPane.showInputDialog("Min IBUs: ");
            minB = Integer.parseInt(select);
            select = JOptionPane.showInputDialog("Max ABV: ");
            maxA = Double.parseDouble(select);
            select = JOptionPane.showInputDialog("Min ABV: ");
            minA = Double.parseDouble(select);
            select = JOptionPane.showInputDialog("Max Original Gravity: ");
            maxO = Double.parseDouble(select);
            select = JOptionPane.showInputDialog("Min Original Gravity: ");
            minO = Double.parseDouble(select);
            select = JOptionPane.showInputDialog("Max Final Gravity: ");
            maxF = Double.parseDouble(select);
            select = JOptionPane.showInputDialog("Min Final Gravity: ");
            minF = Double.parseDouble(select);
            
            //Take collected information to create a new profile to list
            list.addProfile(new StyleProfiles(name, maxC, minC, maxB, minB,
               maxA, minA, maxO, minO, maxF, minF));
               //Save
               try {
                  list.save("ProfileList.txt");
                  System.out.println("Saved");
                  
               }
               catch(IOException e) {
                  e.printStackTrace();
                  System.out.println("Did not print");
               }
               
               // Update the string
               System.out.println(list.toString());
               profiles = list.toString();
               profileList.setText(profiles);
               
                       
      }
   }
}  