/* Author: Brady Gordish         HopsList.java
*
*  Description: Class that stores Hops objects in an array ordered list.
*  
*  Functions:
*  ---------------
*  HopsList() - initiates ArrayOrderedList for Hops
*  addHops() - adds a new Hops to the list
*  find() - locates and returns a Hops with given name
*  removeHops() - removes Hops from list with given name
*  toString() - returns a string of all Hops objects and characteristics
*  iterator() - used to make class iterable
*  save() - save hopslist to HopsList.txt
*  load() - loads contents from HopsList.txt into a hopslist
*/

import jsjf.*;
import java.io.*;
import java.util.*;



public class HopsList implements Iterable<Hops> {
    
    private ArrayOrderedList<Hops> hopsList;
    
    public HopsList() {
        //Initiate an ArrayOrderList for Hops
        hopsList = new ArrayOrderedList<Hops>();
    }
    
    /*   Used to add a new Hops variety to the list
    */
    public void addHops(Hops hops) {
        if(hops != null)
            hopsList.add(hops);
    }
    /*   Used to find a Hops variety in the list by name
    */
    public Hops find(String name) {
        for(Hops hops : hopsList) 
            if(name.equals(hops.getName()))
               return hops;
        
        return null;
    }
    
    /*Remove a hops variety from the list
    */
    public void removeHops(Hops target) {
      if(target == null)
         return;
      else
         hopsList.remove(target);
    }
    
    /*Returns a string of all hops in list
    * Note: This is used for JLabels, which do not read "\n". As a work-around,
    * the String is writen in HTML format. <br> = \n
    */
    public String toString() {
      String result = "<html>";
      for (Hops hops : hopsList)
         result += hops + "<br><br>";
      result += "</html>";
      return result;
    }
    
    public Iterator<Hops> iterator() {
      return hopsList.iterator();
    }
    
    /*   Saves list to HopsList.txt
    */
    public void save(String fileName) throws IOException {
      FileWriter fw = new FileWriter(fileName);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      
      for(Hops hops : hopsList)
         pw.println(hops);
      
      pw.flush();
      pw.close();
    }
    
    /*   Loads HopsList.txt and adds contents to list
    */
    public static HopsList load(String fileName) throws IOException, ClassNotFoundException {
      HopsList list = new HopsList();
      File file;
      Scanner fileScan = new Scanner(file = new File(fileName));
      Scanner stringScan;
    
      String record, name, type = "";
      double alpha = 0.0;
      
      while(fileScan.hasNext()) {
         
         record = fileScan.nextLine();
         stringScan = new Scanner(record);
         stringScan.useDelimiter("<br>----------<br>Alpha: |%   Type: ");
         name = stringScan.next();
         alpha = stringScan.nextDouble();
         type = stringScan.next();
         
         list.addHops(new Hops(name, alpha, type));
      }
      return list;
   }
}
