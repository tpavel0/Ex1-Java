package myMath;



import java.util.Comparator;

import myMath.Monom;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Pavel
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	
	/**
	 * Constructor: set a as coefficient and b as power
	 * @param a coefficient of monom
	 * @param b power of monom
	 * @throws RuntimeException if power is negative
	 */
	public Monom(double a, int b){
		if(b < 0)
			throw new RuntimeException("Power can't be negative");
		this.set_coefficient(a);
		this.set_power(b);
	}
	
	/**
	 * 
	 * Constructor copy a monom to new monom
	 * @param ot monom to copy
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	
	public double get_coefficient() {
		return this._coefficient;
	}
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	

	/**
	 * 
	 * @return power of monom
	 */
	public int get_power() {
		return this._power;
	}
	/**
	 * 
	 * @param p the power of the monom
	 */
	private void set_power(int p) {
		this._power = p;
	}
	
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/**
	 * return the result of given x to place as x in monom
	 * @param x variable to place in monom
	 * @return result of x placed in monom
	 */
	@Override
	public double f(double x) {
		return this.get_coefficient() * Math.pow(x, this.get_power());
	} 
	 
	public boolean isZero() {return this.get_coefficient() == 0;}
	
	
	public Monom(String s) {
		s = s.toLowerCase();
		try {
			double cof = 0;
			int pow = 0;
			
			if(!s.contains("x")) {
				cof = Double.parseDouble(s);
			}
			else {
				if(s.charAt(0) == 'x') {
					cof = 1;
					if(s.length() == 1) {
						pow = 1;
					}
					else if(s.charAt(1) == '^') {
						pow = Integer.parseInt(s.substring(2));
					}
					else {
						throw new RuntimeException("error: bad string monom");
					}
				}
				else {
					int i = s.indexOf('x');
					if(s.substring(0, i).equals("-")) {
						cof = -1;
					}else {
						cof = Double.parseDouble(s.substring(0, i));
					}
					if(s.contains("^")) {
						i = s.indexOf('^');
						pow = Integer.parseInt( s.substring(i+1, s.length()));
					}
					else {
						pow = 1;
					}
				}
			}

			this.set_coefficient(cof);
			this.set_power(pow);
		} catch (Exception e) {
			throw new RuntimeException("error: bad string monom");
		}
		
	}
	
	/**
	 * add the new monom to current monom
	 * @param m new monom to add to current monom
	 */
	public void add(Monom m) {
		set_coefficient(this._coefficient + m._coefficient);
	}
	
	public void multipy (Monom x) {
		this.set_coefficient(this._coefficient* x.get_coefficient());
		this.set_power(this._power + x.get_power());
		if(this.get_power()<0) {
			this.set_coefficient(0);
		}
	}
	
	/**
	 * String a representation of this Monom
	 * @return a String representation of this Monom
	 */
	@Override
	public String toString() {
		String str = "";
		
		if(_coefficient == 0)
			return "0";
		
		if(_power == 0)
			str += _coefficient;
		else if(_power == 1) {
			if(_coefficient == 1)
				str += "x";
			else if(_coefficient == -1)
				str += "-x";
			else
				str += _coefficient + "x";
		}
		else {
			if(_coefficient == 1)
				str += "x^" + _power;
			else if(_coefficient == -1)
				str += "-x^" + _power;
			else
				str += _coefficient + "x^" + _power;
		}
		
		return str;
	}
	
	public boolean monomEquals(Monom m) {
		if(Math.abs(this._coefficient) < EPSILON) {
			return Math.abs(this.get_coefficient() - m.get_coefficient()) < EPSILON;
		}
		return Math.abs(this.get_coefficient() - m.get_coefficient()) < EPSILON
				&& Math.abs(this.get_power() - m.get_power()) < EPSILON;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;

		if (!(other instanceof Monom)) {
			return other.equals(this);
		}
		return monomEquals((Monom) other);
	}
	
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	
	@Override
	public function initFromString(String s) {
		return new Monom(s);
	}

	@Override
	public function copy() {
		return new Monom(this);
	}

	
}
