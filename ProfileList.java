/* Author: Brady Gordish         ProfileList.java
 * 
 * Description:
 * --------------------
 * This class is used to create an array to hold beer profiles
 *
 * List of functions:
 * -------------------
 * ProfileList()
 * addProfile()
 * find()
 * guageRecipe()
 * removeProfile()
 * toString()
 
 */

import java.io.*;
import java.util.*;
import jsjf.*;


public class ProfileList implements Iterable<StyleProfiles> {
   //Initiates ArrayOrderedList for StylesProfiles
   private ArrayOrderedList<StyleProfiles> profileList;
    
       
   public ProfileList() {
   profileList = new ArrayOrderedList<StyleProfiles>();
   }
   
   //Used to add a StyleProfile to the list
   public void addProfile(StyleProfiles profile) {
      if (profile != null)
         profileList.add(profile);
   }
   
   //Used to find a StylesProfile in the list by name
   public StyleProfiles find(String name) {
      for (StyleProfiles profile : profileList)
         if (name.equals(profile.getName()))
            return profile;        
      return null;
   }
   
   //Used to determine which StylesProfile best describes the beer recipe
   public String guageRecipe(double og, double fg, double love, double abv, double ibu) {
      int listCheck = 0;
      int count;
      String profileFit = "";
      
      for (StyleProfiles profile : profileList) {
         
         count = 0;
         // If recipe's calculated characteristics are within the maximum or mimimum
         // characterists of the beer profile, count++
         if ((og >= profile.getMinO()) && (og <= profile.getMaxO())) 
            count++;
         if ((fg >= profile.getMinF()) && (fg <= profile.getMaxF()))
            count++;
         if ((love >= profile.getMinL()) && (love <= profile.getMaxL()))
            count++;
         if ((abv >= profile.getMinA()) && (abv <= profile.getMaxA()))
            count++;
         if ((ibu >= profile.getMinI()) && (ibu <= profile.getMaxI()))
            count++;
            
            
            //if profile's count is greater than the last compared profile, 
            //add the name of that profile to profileFit
         if (listCheck < count) {
             listCheck = count;
             profileFit = profile.getName(); 
         }
      }
      return profileFit;  
      
   }
                     
   // remove the targeted StyleProfile from the list
   public void removeProfile(StyleProfiles target) {
      if (target == null)
         return;
      else
         profileList.remove(target);
   }
   
   
   // Convert list of profiles to a String.
   // Note: This string will be added to a JLabel, which does not read '/n'. 
   // To remedy this, I've convered the format to HTML. <br> = /n
   public String toString() {
      String result = "<html>";
      for (StyleProfiles profile : profileList)
         result += profile + "<br><br>";
      result += "</html>";
      return result;
   }
   
   
   public Iterator<StyleProfiles> iterator() {
      return profileList.iterator();
   }
   
   
   // saves the list to a .txt file
   public void save(String fileName) throws IOException {
      FileWriter fw = new FileWriter(fileName);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      
      for(StyleProfiles profile : profileList)
         pw.println(profile);
      
      pw.flush();
      pw.close();
   }
   
   // loads the profile to a .txt file
   public static ProfileList load(String fileName) throws IOException, ClassNotFoundException {
      ProfileList list = new ProfileList();
      File file;
      Scanner fileScan = new Scanner(file = new File(fileName));
      Scanner stringScan;
      
      String record = "";
      String name = "";
      int maxC = 0;
      int minC = 0;
      int maxB = 0;
      int minB = 0;
      double maxA = 0.0;
      double minA = 0.0;
      double maxO = 0.0;
      double minO = 0.0;
      double maxF = 0.0;
      double minF = 0.0;
      while(fileScan.hasNext()) {
         
         record = fileScan.nextLine();
         stringScan = new Scanner(record);
         // ignores all 'extra' content added to the .txt file that isn't a profile characteristic
         stringScan.useDelimiter("<br>------------<br>    Color: |L-|L    IBUs: |-|    ABV: |%-" +
            "|%<br>   Original Gravity: |<br>    Final Gravity: ");
         name = stringScan.next();
         minC = stringScan.nextInt();
         maxC = stringScan.nextInt();
         minB = stringScan.nextInt();
         maxB = stringScan.nextInt();
         minA = stringScan.nextDouble();
         maxA = stringScan.nextDouble();
         minO = stringScan.nextDouble();
         maxO = stringScan.nextDouble();
         minF = stringScan.nextDouble();
         maxF = stringScan.nextDouble();
         
         list.addProfile(new StyleProfiles(name, maxC, minC, maxB, minB,
            maxA, minA, maxO, minO, maxF, minF));
      }
      return list;
   }
}     

