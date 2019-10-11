/* Author: Brady Gordish         Calculations.java
* 
*  Description:
*  ---------------
*  This class is used for all necessary beer calculation.
*
*  List of Functions:
*  ------------------
*  standardGravity() - calculates OG
*  finalGravity() - calculates FG
*  getABV() - calculates ABV%
*  getColor() - calculates color into SRM
*  srmConverter() - changes SRM to Lovibond units
*  hopsUtilRate() - calculates Hops utility rate
*  getIBU() - calculates IBUs
*/



public class Calculations {
   /* Calculates standard gravity(more commonly known as original gravity(OG).
   *  OG is the unit used to tell you the volume of sugar found in your unfermented
   *  beer.
   */
   public double standardGravity(double lb, int ppg, double gallons) {
   
      // OG = (pounds of malt * malt pounds per gallon * .75) / gallons of water.
      double og = (lb*ppg*.75)/gallons;
  
      return og;
   }
   
   /* Final Gravity(FG) is the unit used to tell you the volume of sugar found in your
   * beer AFTER it has been fermented. FG and OG are useful in order to calculate
   * the alcohol content later.
   */
   public double finalGravity(double og) {
      double gravity = og*1000;
      double fg = ((gravity -1000)-((gravity-1000)*.75)+1000)/1000;
      return fg;
   }
   /* Calculates alcohol by volume(ABV).
   *  ABV is the alcohol percentage found in your beer.
   */
   public double getABV(double og, double fg) {
   
      // ABV = ((1.05 *(og-fg))/fg)/.79*100
      double abv = ((1.05*(og-fg))/fg)/.79*100;
      return abv;
   }
   /* calculates Standard Reference Method(SRM).
   *  SRM is the unit that discribes the color of your beer.
   *  The bigger the number, the darker the beer.
   */
   public double getColor(double love, double pounds, double gallons) {
      // srm = (pounds of malt * malt color(lovibonds)/gallons
      double srm = (pounds*love/gallons);
      return srm;
   }
   /* Calculation to convert SRM to Lovibonds.
   *  Lovibonds are another unit used to describe the color of beer.
   *  I prefer Lovibonds over SRM, which is why I convert SRM to Lovibonds
   */
   public double srmConverter(double srm) {
      // lovibonds = 1.4922 * SRM^.6859
      double love = 1.4922*Math.pow(srm, .6859);
      return love;
   }
   
   /* Calculates Hops Utilitization Rate. 
   * This unit is necessary to later calculate the bitterness that your hops 
   * have given to your beer. This particular formula is one great reason why
   * my program is so useful, because no one like doing this calculation!
   */
   public double hopsUtilRate(int mins, double og) {
      // Boil time factor = (1 - e^(-.04*minutes))/4.15
      // minutes is the referencing when you add your
      // hops to your boiling pot
      double bf = (1-Math.exp(-0.04*mins))/4.15;
      
      //Bitterness Factor = 1.65 * .000125^(OG - 1));
      double bt = 1.65 * Math.pow(0.000125, (og - 1));
      
      // Hops Utilization rate = Boil time factor * bitterness factor
      double utilization = bf * bt;
      return utilization;
   }
   
   /* Calculates the imperial bitterness units(IBU)
   *  IBUs tell you how much bitterness has been added
   *  to your beer from your hops.
   */
   public double getIBU(double alpha, double util, double ounces, double gallons) {
      // IBU = ((alpha acidity of hops/100)* utilization rate * ounces of hops * 7489)/ Gallons of water
      double ibu = ((alpha/100)*util*ounces*7489)/gallons;
      return ibu;
   }
}