/* Author: Brady Gordish         RecipePane
*  
*  Description:
*  -------------------
*  This class is used to create the panel used to create beer recipes, calculate results, and
*  save results to a .txt file
*
*  Logic:
*  ---------------
*  RecipePane()
*  ---------------
*  Declare variables
*  create lists and load ingredients to list
*  create JPanel
*  declare ActionListeners
*
*  MaltComboListener()/YeastComboListener()/HopsComboListener()
*  -------------------------------------------------------------
*  cMalt/yeast/cHops = selected item from malt/hops/yeast JComboBox
*
*
*  AddMaltListener()
*  -----------------------------------------------------
*  if malt add button is clicked, add selected JComboBox item to stack
*  ask how much in lb user would like to add to to the recipe
*  add amount of lbs to maltsWeight stack
*  refresh malts label to add malt to list
*  
*  AddYeastListener()
*  -----------------------------------------------------
*  if yeast add button is clicked, add JComboBox item to yeastStack
*  if yeastStack is not empty
*     pop current item off of stack
*     add selected item to stack
*  refresh yeast label
*
*  AddHopsListener()
*  -----------------------------------------------------
*  if hops add button is clicked, add JComboBox item to hopsStack
*  ask for how much in oz user would like to add to the recipe
*  ask for when the user would be adding hops to the 60 minute boil
*  add amount of oz to hopsWeight stack and time to hopsTime stack
*
*  RemoveMaltListener()/RemoveYeastListener()/RemoveHopsListener()
*  ------------------------------------------------------------
*  pop top item from all stacks
*  refresh label to show updated lists
*
*  CalculateListener()
*  --------------------------
*  declare variables
*  while malt stacks are not empty
*     calculate OG and color of top malt
*     pop malt stacks
*  calculate FG, and ABV
*  while hops stacks are not empty
*     calculate IBU from to hops
*     pop hops stacks
*  find beer profile that relates to recipe
*  print results
*  ask if user would like to save recipe
*  if yes save recipe
*/
import java.text.DecimalFormat;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import jsjf.*;

public class RecipePane extends JPanel {
   // Call ingredients classes used to reference ingredients selected in JComboBoxes
   private Malt cMalt;
   private Yeast cYeast;
   private Hops cHops;
   
   //creat decimal formats used for standard decimal forms for each value
   private DecimalFormat gravityForm, loveForm, decForm;
         
   private String beer, profileGuage;
   
   // Create Stacks
   private LinkedStack<Malt> maltBatch;
   
   private LinkedStack<Yeast> yeastBatch;
   
   private LinkedStack<Hops> hopsBatch;
   
   private LinkedStack<String> maltsString, hopsString,  yeastString, savedMalt, savedYeast, savedHops;
   
   private LinkedStack<Double> hopsOunce, maltPound, savedMaltWeight, 
      savedHopsWeight;
   private LinkedStack<Integer> hopsTime, savedHopsTime;
      
   
   // create doubles used in caculations and for saving recipe   
   double gallons, color, abv, fg, og, ibu;
   // Create Recipe lists that will be shown in JComboBoxes
   private MaltsList maltList; 
   private YeastList yeastList;
   private HopsList hopsList;
   
   // Create objects for the malt section of JPanel
   private JButton addMaltButton, removeMaltButton;
   private JComboBox<String> maltsBox;
   private JPanel maltsPanel;
   private JLabel maltsLabel;
   private JPanel mcontainer;
   private JScrollPane mscroll;
   private String malts;
   
   // Create objects for the hops section of JPanel
   private JButton addHopsButton, removeHopsButton;
   private JComboBox<String> hopsBox;
   private JPanel hopsPanel;
   private JLabel hopsLabel;
   private JPanel hcontainer;
   private JScrollPane hscroll;
   private String hops;
   
   // Create ProfileList used to reference various beer profiles
   private ProfileList profileList;
   
   // Create objects for the yeast section of JPanel
   private JButton addYeastButton, removeYeastButton;
   private JComboBox<String> yeastBox;
   private JPanel yeastPanel;
   private JLabel yeastLabel;
   private JPanel ycontainer;
   private JScrollPane yscroll;
   private String yeast;
   
   // Create Panel used for JComboBoxes and other buttons
   private JPanel selectionPanel;
   private JPanel listContainer;
   private JButton calcButton;
   private Calculations calc;
   
   
   public RecipePane() throws IOException {
      
      
      //Initiate main JPanel and set dementions as well as initiate various objects used in JPanel
      profileList = new ProfileList();
      setLayout(new BorderLayout());
      setBackground(Color.gray);
      //setPreferredSize(new Dimension(1200,500));
      
      maltBatch = new LinkedStack<Malt>();
      savedMalt = new LinkedStack<String>();
      maltPound = new LinkedStack<>();
      savedMaltWeight = new LinkedStack<>();
      yeastBatch = new LinkedStack<Yeast>();
      savedYeast = new LinkedStack<String>();
      hopsBatch = new LinkedStack<Hops>();
      savedHops = new LinkedStack<String>();
      savedHopsWeight = new LinkedStack<>();
      hopsOunce = new LinkedStack<>();
      maltsString = new LinkedStack<String>();
      hopsString = new LinkedStack<String>();
      yeastString = new LinkedStack<String>();
      hopsTime = new LinkedStack<>();
      savedHopsTime = new LinkedStack<>();
      maltList = new MaltsList();
      
  /*    
      
      // Load lists used
      try {
         profileList = profileList.load("ProfileList.txt");
      }
      catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      try {
         maltList = maltList.load("MaltsList.txt");
      }
      catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      
      yeastList = new YeastList();
      
      try {
         yeastList = yeastList.load("YeastList.txt");
         System.out.println("Yeast is loaded");
      }
      catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
*/      
      hopsList = new HopsList();
/*     
      try {
         hopsList = hopsList.load("HopsList.txt");
      }
      catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
      
 */    
      maltsBox = new JComboBox<String>(new DefaultComboBoxModel<String>());
/*
      //Add names of malts in maltsList to JComboBox
      for (Malt malt : maltList) {
         maltsBox.addItem(malt.getName());
      }
*/
      // Note: JLables use HTML, so Strings used for JLabels are written with HTML
      malts =  "<html>Malts:<br><br>";
      
      // Creates the panel used for malt selection
      addMaltButton = new JButton("Add");
      removeMaltButton = new JButton("Remove");
      maltsPanel = new JPanel();     
      mcontainer = new JPanel();
      mcontainer.add(maltsBox);
      mcontainer.add(addMaltButton);
      mcontainer.add(removeMaltButton);
      
      //Initiate JLabel to print malts used(html format)
      maltsLabel = new JLabel(malts + "</html>");
      maltsPanel = new JPanel();
      maltsPanel.add(maltsLabel);
      mscroll = new JScrollPane(maltsPanel);
      mscroll.setPreferredSize(new Dimension(300,500));
      
         
      //Add yeast names to JComboBox
      yeastBox = new JComboBox<String>(new DefaultComboBoxModel<String>());
/*
      for(Yeast yeast : yeastList) {
         yeastBox.addItem(yeast.getName());
      }
 */     
      //Initiates objects used for Yeast selection
      addYeastButton = new JButton("Add");
      removeYeastButton = new JButton("Remove");
      ycontainer = new JPanel();
      ycontainer.add(yeastBox);
      ycontainer.add(addYeastButton);
      ycontainer.add(removeYeastButton);
      
      //set yeast String value(HTML format)
      yeast = "<html>Yeast:<br></html>";
      //initiate objects used for viewing selected yeast
      yeastLabel = new JLabel(yeast);
      yeastPanel = new JPanel();
      yeastPanel.add(yeastLabel);
      yscroll= new JScrollPane(yeastPanel);
      yscroll.setPreferredSize(new Dimension(300,500));
      addHopsButton = new JButton("Add");
      removeHopsButton = new JButton("Remove");
     
      //Add names of hops to JComboBox
      hopsBox = new JComboBox<String>(new DefaultComboBoxModel<String>());

/*
      for(Hops hops : hopsList) {
         hopsBox.addItem(hops.getName());
      }
 */
      //Initiate objects used for hops selection
      hcontainer = new JPanel();
      hcontainer.add(hopsBox);
      hcontainer.add(addHopsButton);
      hcontainer.add(removeHopsButton);
      
      //Initiate objects used for viewing selected hops
      hops = "<html>Hops:<br></html>";
      hopsLabel = new JLabel(hops);
      hopsPanel = new JPanel();
      hopsPanel.add(hopsLabel);
      hscroll = new JScrollPane(hopsPanel);
      hscroll.setPreferredSize(new Dimension(300,500));
      
      //Initiate ingredients selection panel
      listContainer = new JPanel();    
      listContainer.setLayout(new BorderLayout()); 
      listContainer.add(yscroll, BorderLayout.EAST);
      listContainer.add(hscroll, BorderLayout.WEST);
      listContainer.add(mscroll);
      add(listContainer);
      
      //Initiate Panel used for ingredient options
      selectionPanel = new JPanel();
      selectionPanel.setLayout(new BorderLayout());
      selectionPanel.add(hcontainer, BorderLayout.WEST);
      selectionPanel.add(mcontainer, BorderLayout.CENTER);
      selectionPanel.add(ycontainer, BorderLayout.EAST);
      add(selectionPanel, BorderLayout.NORTH);
      
      
      //initiate Caluclation button and add to the bottom of panel
      calcButton = new JButton("Calculate Recipe!");
      add(calcButton, BorderLayout.SOUTH);
      
      //Set the size of JComboBoxes
      maltsBox.setPrototypeDisplayValue("-------------------------------");
      yeastBox.setPrototypeDisplayValue("-----------------------------------------------------");
      hopsBox.setPrototypeDisplayValue("--------------------------------------");
      
      //Initiate ActionListeners used for various objects
      addMaltButton.addActionListener(new AddMaltListener());
      removeMaltButton.addActionListener(new RemoveMaltListener());
      addYeastButton.addActionListener(new AddYeastListener());
      removeYeastButton.addActionListener(new RemoveYeastListener());
      addHopsButton.addActionListener(new AddHopsListener());
      removeHopsButton.addActionListener(new RemoveHopsListener());
      maltsBox.addActionListener(new MaltComboListener());
      yeastBox.addActionListener(new YeastComboListener());
      hopsBox.addActionListener(new HopsComboListener());
      calcButton.addActionListener(new CalculationListener());
      
      
      //Initiates the first items selected in the JComboBoxes
      
      
   }
   // targets the malt selected in JComboBox
   private class MaltComboListener implements ActionListener {
      
      public void actionPerformed(ActionEvent event) {
         if (maltsBox.getItemCount()!=0) {
            cMalt = new Malt((maltList.find((String)maltsBox.getSelectedItem()).getName()), (maltList.find((String)maltsBox.getSelectedItem()).getColor()), (maltList.find((String)maltsBox.getSelectedItem()).getPPG()), (maltList.find((String)maltsBox.getSelectedItem()).getType()));
         }
      }
   }
   // targets the yeast selected in JComboBox
   private class YeastComboListener implements ActionListener {
      
      public void actionPerformed(ActionEvent event) {
         if (yeastBox.getItemCount()!=0)
            cYeast = new Yeast(yeastList.find((String)yeastBox.getSelectedItem()).getName());
      }
   }
   // targets the hops selected in JComboBox
   private class HopsComboListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         if (hopsBox.getItemCount()!=0)
            cHops = new Hops((hopsList.find((String)hopsBox.getSelectedItem()).getName()), (hopsList.find((String)hopsBox.getSelectedItem()).getAlpha()), (hopsList.find((String)hopsBox.getSelectedItem()).getType()));
      }     
   }
   
   //Adds malt selected to the malt stack and malt weight stack as well as to all
   //related stacks
   private class AddMaltListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         
         String poundStr = JOptionPane.showInputDialog("Pounds: ");
         double pounds = 0.0;
         pounds = Double.parseDouble(poundStr);
         String ozStr = JOptionPane.showInputDialog("Ounces: ");
         double oz = Double.parseDouble(ozStr);
         //convert oz to lb and add to pounds
         oz = oz * .0625;
         pounds += oz;
         
         malts = pounds + "lb " + cMalt.getName() + "<br>";
         maltsString.push(malts);
         maltsLabel.setText("<html>Malts:<br>" + maltsString.toString() + "</html>");
         savedMalt.push(cMalt.getName());
         maltBatch.push(cMalt);
         savedMaltWeight.push(pounds);
         maltPound.push(pounds);
         
      }
   }
   
   //removes the top from all malt related stacks
   private class RemoveMaltListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         
         maltsString.pop();
         maltBatch.pop();
         savedMalt.pop();
         savedMaltWeight.pop();
         maltPound.pop();
         maltsLabel.setText("<html>Malts:<br>" + maltsString.toString() + "</html>");
      }
   }
   
   //adds the selected yeast to the yeast stack and allows
   private class AddYeastListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
      
         //allow for only one yeast to be used in recipe by poping the yeast in stack 
         //before pushing the the new yeast to the stack
         if (!yeastString.isEmpty()) {
            yeastString.pop();
            savedYeast.pop();
         }
         yeast = cYeast.getName() + "<br>";
         yeastString.push(yeast);
         savedYeast.push(cYeast.getName());
         yeastLabel.setText("<html>Yeast:<br>" + yeastString.toString() + "</html>");
         
      }
   }
   //removes the yeast from the stack
   private class RemoveYeastListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         yeastString.pop();
         savedYeast.pop();
         yeastLabel.setText("<html>Yeast:<br>" + yeastString.toString() + "</html>");
      }
   }
   
   // Adds the selected hops to the hops stack as well as the weight to the weight stack
   // and the time when added to the hops time stack
   private class AddHopsListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         String ozStr = JOptionPane.showInputDialog("Ounces: ");
         double oz = 0.0;
         oz = Double.parseDouble(ozStr);
         String timeStr = JOptionPane.showInputDialog("Add at what time in boil?: ");
         int time = 0;
         time = Integer.parseInt(timeStr);
         hops = oz + "oz " + cHops.getName() +" (" + time + ")<br>";
         hopsString.push(hops);
         hopsLabel.setText("<html>hops:<br>" + hopsString.toString() + "</html>");
         savedHops.push(cHops.getName());
         hopsBatch.push(cHops);
         savedHopsWeight.push(oz);
         hopsOunce.push(oz);
         hopsTime.push(time);
         savedHopsTime.push(time);
      }
   }
   
   //removes the top of the timeStack, hops stack, and weight stack
   private class RemoveHopsListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         hopsString.pop();
         hopsBatch.pop();
         savedHops.pop();
         savedHopsWeight.pop();
         hopsOunce.pop();
         hopsTime.pop();
         savedHopsTime.pop();
         hopsLabel.setText("<html>Hops:<br>" + hopsString.toString() + "</html>");
         
      }
   }
   // takes the ingredients and variables from each stack and calculates the
   // original gravity, final gravity, alcohol by volume, imperial bitter units, and
   // color of beer in standard lovibond units
   private class CalculationListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         //initiate string which will hold the beer profile that closest defines the recipe
         String profileGuage = "";
         
         //set decimal formats used for standard decimal forms for each value
         gravityForm = new DecimalFormat("#.###");
         loveForm = new DecimalFormat("#.0");
         decForm = new DecimalFormat("#.#");
         
         //Initiates the Caluclations class used to calculate beer characteristics
         Calculations calculations = new Calculations();
         
         //initiate variables used in for calculations and results
         gallons = 0.0;
         color = 0;
         abv = 0.0;
         fg = 0.0;
         og = 0.0;
         ibu = 0.0;
         
         //Initiate new Malt and Hops objects to select current ingredient being calculated
         Malt maltCheck;
         Hops hopsCheck;
         
         //Ask user how many gallons of water used for recipe
         String gallonsStr = JOptionPane.showInputDialog("Batch size(Gallons): ");
         gallons = Double.parseDouble(gallonsStr);
         
         // calculate Color and original gravity of each ingredient and add them together
         while (!maltsString.isEmpty()) {
            double weight = (double)maltPound.pop();
            maltCheck = (Malt)maltBatch.pop();
            color += calculations.getColor(((double)maltCheck.getColor()), weight, gallons);
            og += calculations.standardGravity(weight, maltCheck.getPPG(), gallons);
            maltsString.pop();
         }
         
         
         //convert color from Standard Reference Method units to Lovibond units
         // simply because I prefer lovibond units 
         color = calculations.srmConverter(color);
         
         // change format of original gravity to standard calculation by dividing by 1000. Adds the
         // value of the water. Water has a gravity of 1.000 which is why we add 1.
         og = (og/1000) + 1;
         
         //calculate the final gravity
         fg = calculations.finalGravity(og);
         
         //calculate the ABV %
         abv = calculations.getABV(og, fg);
         
         // Calculate the utility rate of each hops(needed to calculate bitterness), and the imperial
         // bittering units of each, then add them together
         while(!hopsString.isEmpty()) {
            hopsString.pop();
            hopsCheck = (Hops)hopsBatch.pop();
            double utilRate = calculations.hopsUtilRate((int)hopsTime.pop(), og); 
            ibu += calculations.getIBU(hopsCheck.getAlpha(), utilRate, (double)hopsOunce.pop(), gallons);   
         }
         
         // Removes the yeast from the stack in order to clear it from the yeast list.
         // Note: I am not including calculations for yeast because I use dry yeast, which can
         // not be consistantly calculated.
         yeastString.pop();
         
         // Ask user what they would like to name their calculated beer
         beer = JOptionPane.showInputDialog(null, "Name your beer: ");
         
         // find the beer profile that most closely defines the beer
         profileGuage = profileList.guageRecipe(og, fg, color, abv, ibu);
         
         // print results
         JOptionPane.showMessageDialog(null, (beer + "\n\nOG: " + gravityForm.format(og) + 
            "   FG: " + gravityForm.format(fg) + "\nColor: " + loveForm.format(color) + "L   " + 
            "IBUs: " + decForm.format(ibu) + "   ABV: " + decForm.format(abv) + 
            "\n\nThis recipe conforms to: " + profileGuage));
         
         //Reset labels to with blank stacks
         hopsLabel.setText("<html>Hops:<br>" + hopsString.toString() + "</html>");
         maltsLabel.setText("<html>Malts:<br>" + maltsString.toString() + "</html>");
         yeastLabel.setText("<html>Yeast:<br>" + yeastString.toString() + "</html>");
         
         // Ask if user would like to save their results
         int saveRecipe = JOptionPane.showConfirmDialog(null, "Would you like to save this recipe?");
         if (saveRecipe == JOptionPane.YES_OPTION) {
            
            //Save recipe
            String beerFile = beer + ".txt";
            try {
               save(beerFile, profileGuage);
               System.out.println("Saved");
                  
            }
            catch(IOException e) {
               e.printStackTrace();
               System.out.println("Did not print");
            }
         } 
      }  
      
      // Used to save Beer recipe results to a .txt file and includes a Notes section for
      // the brewer to use in the .txt file. Also empties the stacks so that another recipe
      // can be created 
      private void save(String fileName, String guage) throws IOException {
        
          FileWriter fw = new FileWriter(fileName);
          BufferedWriter bw = new BufferedWriter(fw);
          PrintWriter pw = new PrintWriter(bw);
          String recipeMalt = "";
          String recipeHops = "";
          
          
          
          pw.println(beer + " -- " + guage);
          pw.println();
          pw.println("Original Gravity: " + gravityForm.format(og));
          pw.println("Final Gravity: " + gravityForm.format(fg));
          pw.println("Color: " + loveForm.format(color));
          pw.println("ABV: " + decForm.format(abv));
          pw.println("IBU: " + decForm.format(ibu));
          pw.println();
          pw.println("Fermentables:");
          pw.println("-------------");
          pw.println();
          while (!savedMalt.isEmpty()) {
            pw.println(savedMaltWeight.peek() + "lbs - " + savedMalt.peek());
            savedMaltWeight.pop();
            savedMalt.pop();
          }
          pw.println();
          pw.println("Hops:");
          pw.println("-------------");
          pw.println();
          while (!savedHops.isEmpty()) {
            pw.println(savedHopsWeight.peek() + "oz - " + savedHops.peek() + " (" + savedHopsTime.peek() +
               " minutes)");
               
            savedHopsTime.pop();
            savedHops.pop();
            savedHopsWeight.pop();
          }
          pw.println();
          pw.println("Yeast:");
          pw.println("-------------");
          pw.println();
          pw.println(savedYeast.peek());
          savedYeast.pop();
          pw.println();
          pw.println();
          pw.println();
          pw.println("Notes:");
          pw.println("----------------------------------------------");
          
          
          pw.flush();
          pw.close();
      } 
      
   } 
   
   
   /* Used refresh JComboBoxes, to show changes made to lists
   */
   public void reload() throws IOException {
      try {
         profileList = profileList.load("ProfileList.txt");
      }
      catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      try {
         maltList = maltList.load("MaltsList.txt");
      }
      catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      
      
      
      try {
         yeastList = yeastList.load("YeastList.txt");
      }
      catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      
      
      try {
         hopsList = hopsList.load("HopsList.txt");
      }
      catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
      maltsBox.removeAllItems();
      for (Malt malts : maltList) {
         maltsBox.addItem(malts.getName());
      }
      yeastBox.removeAllItems();
      for (Yeast yeast : yeastList) {
         yeastBox.addItem(yeast.getName());
      }
      hopsBox.removeAllItems();
      for (Hops hops : hopsList) {
         hopsBox.addItem(hops.getName());
      }
      
      cMalt = new Malt((maltList.find((String)maltsBox.getSelectedItem()).getName()), (maltList.find((String)maltsBox.getSelectedItem()).getColor()), (maltList.find((String)maltsBox.getSelectedItem()).getPPG()), (maltList.find((String)maltsBox.getSelectedItem()).getType()));
      cYeast = new Yeast(yeastList.find((String)yeastBox.getSelectedItem()).getName());  
      cHops = new Hops((hopsList.find((String)hopsBox.getSelectedItem()).getName()), (hopsList.find((String)hopsBox.getSelectedItem()).getAlpha()), (hopsList.find((String)hopsBox.getSelectedItem()).getType()));
      
   }
      

}