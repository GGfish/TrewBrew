/* Author: Brady Gordish            Yeast.java
*  
*  Description:
*  ----------------
*  Class used to characterize yeasts
*  
*  Functions:
*  ----------------
*  
*  Yeast() - characterizes yeast
*  getName() - returns yeast name
*  compareTo - Makes object comparable
*  toString() - Return string with yeast name
*/
public class Yeast implements Comparable<Yeast> {
    private String title;
    
    public Yeast(String name) {
      title = name;
    }
    
    /*   Returns yeast name
    */
    public String getName() {
      return title;
    }
    
    /*   Makes yeast names comparable
    */
    public int compareTo(Yeast other) {
      int result = 0;
      result = title.compareTo(other.getName());
      return result;
    }
    
    /* Returns yeast as a string
    */
    public String toString() {
      String result = title + "<br>";
      return result;
    }
}
      