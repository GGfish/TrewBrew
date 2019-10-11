/* Author: Brady Gordish            MaltsList.java
*  
*  Descriptions: 
*  --------------
*  Class used to store malts in an ArrayOrderedList
*
*  Functions:
*  --------------
*  MaltList() - initiates ArrayOrderedList for Malt objects
*  addMalt() - add a new malt to list
*  find() - find a malt in list with given name
*  removeMalt() - remove target malt
*  toString() - returns string of list
*  iterator() - makes list iterable
*  save() - saves list to MaltsList.txt
*  load() - loads MaltsList.txt and adds malts in file to list
*/
import jsjf.*;
import java.io.*;
import java.util.*;

public class MaltsList implements Iterable<Malt>{

    private ArrayOrderedList<Malt> maltList;
    
    
    public MaltsList() {
        // Initiate ArrayOrderedList for malts
        maltList = new ArrayOrderedList<Malt>();
    }
    
    /*   Add malt to list
    */
    public void addMalt(Malt malt) {
        if(malt != null)
          maltList.add(malt);
    }
    
    /*   Find malt with given name
    */
    public Malt find(String name) {
      for(Malt malt : maltList)
         if(name.equals(malt.getName()))
            return malt;
            
      return null;
    }
    
    /*   Remove target malt
    */
    public void removeMalt(Malt target) {
      if (target == null)
         return;
      else
         maltList.remove(target);
    }
    
    /*   Return list as a string
    *    Note: In HTML format so that JLabel can read EOLs
    */
    public String toString() {
      String result = "<html>";
      for (Malt malt : maltList)
         result +=  malt + "<br><br>";
      result += "</html>";
      return result;
    }
    
    public Iterator<Malt> iterator() {
      return maltList.iterator();
    }
    
    /* Saves list to MaltsList.txt
    */
    public void save(String fileName) throws IOException {
      FileWriter fw = new FileWriter(fileName);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      
      for(Malt malt : maltList)
         pw.println(malt);
         
      pw.flush();
      pw.close();
    }
    
    /*   Loads MaltsList.txt and adds contents as Malts to list
    */
    public static MaltsList load(String fileName) throws IOException, ClassNotFoundException {
      MaltsList list = new MaltsList();
      File file;
      Scanner fileScan = new Scanner(file = new File(fileName));
      Scanner stringScan;
      
      String record = "";
      String name = "";
      String type = "";
      
      int love, ppg = 0;
      while(fileScan.hasNext()) {
         
         record = fileScan.nextLine();
         stringScan = new Scanner(record);
         stringScan.useDelimiter("<br>----------<br>Color: |L  <br>| ppg  <br>| malt");
         name = stringScan.next();
         love = stringScan.nextInt();
         ppg = stringScan.nextInt();
         type = stringScan.next();
         
         list.addMalt(new Malt(name, love, ppg, type));
      }
      
      return list;
   }
}   