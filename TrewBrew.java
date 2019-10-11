/** Brady Gordish             TrewBrew.java
*
**  Description:
*   A program to aid homebrewers with creating recipes by holding a collection of ingredients
*   and beer profiles, and calculating accurate results of Alcohol content, estimated color of beer,
*   and original and final gravity units. It also allows user to name their recipes and save them to
*   .txt files. It also has a GUI interface for a cleaner look and easier use.
*   
*  List of Classes:
*  -------------------
*  -TrewBrew - main
*  -IntroPanel - JPanel
*  -RecipePane - JPanel
*  -ProfilePane - JPanel
*  -IngredientsPane -JPanel
*     -HopsPane - JPanel
*     -YeastPane - JPanel
*     -MaltsPane - JPanel
*  -MaltsList - ArrayOrderedList
*     -Malts - Object
*  -HopsList - ArrayOrderedList
*     -Hops - Object
*  -YeastList - ArrayOrderedList
*     -Yeast - Object
*  -ProfileList - ArrayOrderedList
*     -StyleProfiles - Object
*  
*  TrewBrew.class Logic:
*  -----------------
*  declare variables
*  create GUI JFrame
*  create Tab menu to navigate to other JPanels
*  Pack JFrame
*  Make Frame visible
*
**/



import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;

public class TrewBrew {
   
   
   
   public static void main(String[] args) throws IOException {
      /**
      * GUI menu. Asks user if they would like to create a new recipe,
      * view beer profile list, or view ingredients.
      **/
      /*
      ProfileList list = new ProfileList();
      MaltsList maltlist = new MaltsList();
      HopsList hopsList = new HopsList();
      YeastList yeastList = new YeastList();
     */
      
      //Create JFrame used for profile
      JFrame frame = new JFrame("Trew Brew");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      RecipePane recipe = new RecipePane();
      //Create tab for each panel
      JTabbedPane tp = new JTabbedPane();
      tp.addTab("Intro", new IntroPanel());
      tp.addTab("Profiles", new ProfilePane());
      tp.addTab("Ingredients", new IngredientsPane());
      tp.addTab("Recipe",  recipe);
      
      //Add tab to JFrame
      frame.getContentPane().add(tp);
      
      tp.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e){ 
            
            Component c = tp.getSelectedComponent();
            if (c == recipe)
            try {
               recipe.reload();
            }
            catch(IOException ex) {
               ex.printStackTrace();
            }
              
         }
      });
     
      frame.pack();
      frame.setVisible(true);     
   }  
}

