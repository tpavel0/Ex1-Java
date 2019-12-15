package myMathTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.Polynom;
import myMath.Polynom_able;

class PolynomTest {
    Polynom poly;
	@BeforeEach
	void setUp() throws Exception {
		poly=new Polynom("3x^2+4x^3-x+4");
	}
    @Test
	void testPolynomString() {
		Polynom newpoly = new Polynom();
		newpoly.add(new Monom(3,2));
		newpoly.add(new Monom(4,3));
		newpoly.add(new Monom (-1,1));
		newpoly.add(new Monom(4,0));
		assertTrue(poly.equals(newpoly));

	}
   @Test
	void testCopy() {
		Polynom copy = new Polynom();
		copy = (Polynom)poly.copy();
		assertTrue(poly.equals(copy));
	}
    @Test
	void testAddPolynom_able() {
    	Polynom_able newpoly = new Polynom("4x^3+6x^2-x+4");
    	Polynom_able newpoly1 = new Polynom("8x^3+12x^2-2x+8");
    	poly.add(new Monom(3,2));
    	assertTrue(poly.equals(newpoly));
        poly.add(newpoly);
    	assertTrue(poly.equals(newpoly1));

	    
	    
	}
    @Test
	void testAddMonom() {
    	Polynom_able newpoly = new Polynom("4x^3+6x^2-x+4");
    	poly.add(new Monom(3,2));
    	assertTrue(poly.equals(newpoly));
	}
    @Test
	void testSubstract() {
    	Polynom_able newpoly = new Polynom("+2x^2-6x-13+x^5");
    	Polynom_able newpoly1 = new Polynom("+4x^3+x^2+5x+17-x^5");
    	poly.substract(newpoly);
    	assertTrue(poly.equals(newpoly1));
	}
 @Test
	void testMultiply() {
		Polynom ppoly = new Polynom("x");
		Polynom rpoly = new Polynom("3x^3 +4x^4-x^2+4x");
        poly.multiply(ppoly);
    	assertTrue(poly.equals(rpoly));

	}
    @Test
	void testEqualsPolynom_able() {
		Polynom notgood=new Polynom("4x^2");
    	Polynom pol=new Polynom();
		Polynom_able p2 = new Polynom("x^2-4");
		if(p2.equals(notgood))
			fail("Polynoms not equal");
		pol.add(new Monom(3,2));
		pol.add(new Polynom("4x^3-x+4"));
		assertTrue(poly.equals(pol));
		assertFalse(poly.equals(notgood));
	}

   @Test
	void testIsZero() {
		Polynom pol=new Polynom();
		boolean isZiro=pol.isZero();
		boolean polyZ=poly.isZero();
		assertTrue(isZiro);
		assertFalse(polyZ);

	}

	@Test
	void testRoot() {
		Polynom pol = new Polynom("x^2-x-2");
		double expected= pol.root(1, 6,0.001);
		double actual=1.999755859375;
		if(expected == 1)
			fail("wrong root");
        assertEquals(expected, actual);

	}

	 @Test
	void testDerivative() {
		 	Polynom pTest = new Polynom();
			Polynom polyy=new Polynom("3x^2+4x^3-x");
	        Polynom pol = new Polynom("6x+12x^2-1");
	        pTest = (Polynom)polyy.derivative();
    		assertTrue(pTest.equals(pol));
}
@Test
	void testArea() {
	Polynom pol = new Polynom("-x+5");
	double expected= pol.area(0,5, 0.01);
	double actual=12.525000000000091; 
	double expected2= pol.area(0,10, 0.01);
	assertEquals(expected, actual);
	assertEquals(expected2, actual);

		
	
	}

	@Test
	void testToString() {
		String polynomStr =new String(poly.toString());
		String str = "4.0x^3+3.0x^2-x+4.0";
		boolean x= polynomStr.equals(str);
		assertTrue(x);
	}
	@Test
	void testF() {
		double expected =poly.f(1);
		double actual=10;
		assertEquals(expected, actual);

	}
	

}
