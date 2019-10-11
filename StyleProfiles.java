/* Author: Brady Gordish         StyleProfiles.java
*  
*  Description:
*  -------------------------
*  Class used to define beer profiles
*
*  List of functions:
*  StyleProfiles()
*  getName()
*  getMaxL()
*  getMinL()
*  getMaxI()
*  getMinI()
*  getMaxA()
*  getMinA()
*  getMaxO()
*  getMinO()
*  getMaxF()
*  getMinF()
*  compartTo()
*  toString()
*/
import java.text.DecimalFormat;


public class StyleProfiles implements Comparable<StyleProfiles> {
   //Declare variables
    private String style;
    private int maxColor, minColor, maxIBU, minIBU;
    private double maxABV, minABV, maxOG, minOG, maxFG, minFG;
    private DecimalFormat df = new DecimalFormat("#.###");
    
    //Defines a StyleProfiles
    public StyleProfiles(String profile, int maxLove, int minLove,
            int maxBitterness, int minBitterness, double maxAlcoPerc, 
            double minAlcoPerc, double maxOGravity, double minOGravity,
            double maxFGravity, double minFGravity) {
        style = profile;
        maxColor = maxLove;
        minColor = minLove;
        maxIBU = maxBitterness;
        minIBU = minBitterness;
        maxABV = maxAlcoPerc;
        minABV = minAlcoPerc;
        maxOG = maxOGravity;
        minOG = minOGravity;
        maxFG = maxFGravity;
        minFG = minFGravity;
    }
    
    //Get profile name
    public String getName() {
      return style;
    }
    
    //Get profiles maximum color(in Lovibond units)
    public int getMaxL() {
      return maxColor;
    }
    
    //Get profiles minimum color
    public int getMinL() {
      return minColor;
    }
    
    // get profiles maximum IBUs
    public int getMaxI() {
      return maxIBU;
    }
    
    // Get profiles minimum IBUs
    public int getMinI() {
      return minIBU;
    }
    
    // Get profiles maximum Alcohol%
    public double getMaxA() {
      return maxABV;
    }
    
    // Get profiles minimum Alcohol%
    public double getMinA() {
      return minABV;
    }
    
    // Get maximum Original Gravity
    public double getMaxO() {
      return maxOG;
    }
    
    // Get minimum Original gravity
    public double getMinO() {
      return minOG;
    }
    
    // get maximum Final gravity
    public double getMaxF() {
      return maxFG;
    }
    
    // get minimum Final gravity
    public double getMinF() {
      return minFG;
    }
    
    // compare one style with another
    public int compareTo(StyleProfiles other) {
      int result = 0;
      result = style.compareTo(other.getName());
      return result;
    }
    
    // convert to string
    public String toString() {
        String description;
        description = style + "<br>------------<br>    Color: " + minColor + "L-" + maxColor + "L    IBUs: " + minIBU + "-" + maxIBU +
               "    ABV: " + minABV + "%-" + maxABV + "%<br>   Original Gravity: " + df.format(minOG) + "-"+ df.format(maxOG) + "<br>    Final Gravity: " + df.format(minFG) + "-" + df.format(maxFG);
        
        return description;
    }
    
}
