/* Author: Brady Gordish               YeastList.java
*  
*  Description:   
*  -------------------
*  class that holds ArrayOrderedList for yeast.
*  
*  Functions:
*  -------------------
*
*  YeastList()- initicates ArrayOrderedList
*  addYeast() - add a new yeast to list
*  find() - locate a yeast in list by name
*  removeYeast() - removes a target yeast from list
*  toString() - returns a string of list of yeast
*  iterator() - makes class iterable
*  save() - saves list to YeastList.txt
*  load() - collects yeast from YeastList.txt and adds them to list
*/
import jsjf.*;
import java.io.*;
import java.util.*;



public class YeastList implements Iterable<Yeast> {
    
    private ArrayOrderedList<Yeast> yeastList;
    
    public YeastList() {
        yeastList = new ArrayOrderedList<Yeast>();
    }
    //Add a yeast to list
    public void addYeast(Yeast yeast) {
        if(yeast != null) 
          yeastList.add(yeast);
         
    }
    //Find a yeast in list by it's name
    public Yeast find(String name) {
       for(Yeast yeast : yeastList)
          if(name.equals(yeast.getName()))
             return yeast;
       
       return null;
    }
    //Remove a targeted yeast from list
    public void removeYeast(Yeast target) {
       if(target == null)
          return;
       else
          yeastList.remove(target);
    }
    
    //Returns of string of yeastlist
    //Note: in HTML in because JLabels do not read '\n'. <br> = \n in HTML
    public String toString() {
        String result = "<html>";
        for(Yeast yeast : yeastList)
           result += yeast + "<br><br>";
        result += "</html>";
        return result;   
    }
    
    //Makes the class iterable
    public Iterator<Yeast> iterator() {
       return yeastList.iterator();
    }
    //Saves list to .txt file
    public void save(String fileName) throws IOException {
       FileWriter fw = new FileWriter(fileName);
       BufferedWriter bw = new BufferedWriter(fw);
       PrintWriter pw = new PrintWriter(bw);
       
       for(Yeast yeast : yeastList)
          pw.println(yeast);
       
       pw.flush();
       pw.close();
    }
    
    //Loads .txt file and adds yeasts in file to list
    public static YeastList load(String fileName) throws IOException, ClassNotFoundException {
       YeastList list = new YeastList();
       File file;
       Scanner fileScan = new Scanner(file = new File(fileName));
       Scanner stringScan;
       
       String record = "";
       String name = "";
       
       while (fileScan.hasNext()) {
          record = fileScan.nextLine();
          stringScan = new Scanner(record);
          stringScan.useDelimiter("<br>");
          name = stringScan.next();
          
          list.addYeast(new Yeast(name));
       }
       return list;
   }
    
    
}
