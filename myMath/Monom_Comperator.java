package myMath;


import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	public Monom_Comperator() {;}
	/**public int compare(Monom o1, Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		return dp;
	}*/

	// ******** add your code below *********

	/**
	 * compare the power of 2 monoms
	 * @param arg0 first monon to compare power
	 * @param arg1 second monom to compare power
	 * @return 0 if power of monoms equals or positive number if arg0 power higher then arg1 or negative number if arg1 power higher than arg0 
	 */
	@Override
	public int compare(Monom arg0, Monom arg1) {
		return arg1.get_power() - arg0.get_power();
	}
}
