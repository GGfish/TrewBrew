/* Author: Brady Gordish               Hops.java
*
*  Description: 
*  ----------------------
*  Object used to classify hops by their name, alpha acidity, and hops variety
*
*  Funtions:
*  Hops() - stores name, AA%, and variety of hops
*  toString() - prints hops as a string
*  getName() - returns the name of the hops
*  getAlpha() - returns the hops alpha acidity %
*  getType() - returns the hops variety type
*  compareTo() - used to make comparable
*/

public class Hops implements Comparable<Hops> {

    private String name, type;
    double alpha;
    
    public Hops(String label, double alphaPec, String variety) {
        name = label;
        alpha = alphaPec;
        type = variety;
    
    }
    
    /*   Returns a string of the Hops information
    *    Note: JLabels do not read \n, so String was written in HTML as a work-around
    */
    public String toString() {
        String report = name + "<br>----------<br>Alpha: " + alpha + "%   Type: " + type;
        return report;
    }
    
    /*   Return name of Hops
    */
    public String getName() {
        return name;
    }
    
    /*   Return Hops AA%
    */
    public double getAlpha() {
        return alpha;
    }
    
    /*   Return hops variety
    */
    public String getType() {
      return type;
    }
    
    /*   Used to make class comparable
    */
    public int compareTo(Hops other) {
      int result = 0;
      result = name.compareTo(other.getName());
      return result;
    }
}
