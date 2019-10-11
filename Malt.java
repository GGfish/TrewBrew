/* Author: Brady Gordish            Malt.java
*
*  Description:
*  ------------------
*  Object class used to characterize Malts with a name, color of malt
*  in Lovibond units, the malt's estimated pounds per gallon ratio,
*  and malt type.
*
*  Functions:
*  ------------------
*  Malt() - characterizes malt
*  getName() - return malt name
*  getColor() - returns malt color in Lovibond units
*  getPPG() - returns malt's estimated pounds per gallon ratio
*  getType() - returns the type of malt
*  compareTo() - used to make object comparable
*  toString() - returns a string of malt characteristics
*/

public class Malt implements Comparable<Malt> {
   private String name, variety;
   private int color, ppg;
   
   public Malt(String title, int love, int pounds, String type) {
      name = title;
      color = love;
      ppg = pounds;
      variety = type;
   }
   
   
   /* Return malt name
   */
   public String getName() {
      return name;
   }
   
   /* Return color of malt in Lovibond units
   */
   public int getColor() {
      return color;
   }
   
   /* Return Pounds per gallon ratio
   */
   public int getPPG() {
      return ppg;
   }
   
   /* Return malt type
   */
   public String getType() {
      return variety;
   }
   
   /* used to make object comparable
   */
   public int compareTo(Malt other) {
      int result = 0;
      result = name.compareTo(other.getName());
      return result;
   }
   
   /* Return string of malt characteristics
   *  Note: HTML format so that JLabel can see EOL
   */
   public String toString() {
      String result = name + "<br>----------<br>Color: " + color + "L  <br>" + ppg + " ppg  <br>" + variety + " malt";
      return result;
   }
}