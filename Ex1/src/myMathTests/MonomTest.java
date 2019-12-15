package myMathTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.Monom;

class MonomTest {
	Monom monom;
	@BeforeEach
	void setUp() throws Exception {
		monom=new Monom(-3,2);
	}
	@Test
	void testMonomDoubleInt() {
		double expected=-3,actual=monom.get_coefficient();
		int expected2=2, actual2=monom.get_power();
		assertEquals(expected, actual);
		assertEquals(expected2, actual2);

	}
	@Test
	void testMonomMonom() {
		Monom monom1=new Monom(this.monom);
		double expected=monom1.get_coefficient(),actual=monom.get_coefficient();
		int expected2=monom1.get_power(), actual2=monom.get_power();
		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}

	@Test
	void testMultyply() {
		Monom monomp = new Monom(-6, 1);
		monom.multipy(monomp);
		double expected=18,actual=monom.get_coefficient();
		int expected2=3, actual2=monom.get_power();
		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}
	@Test
	void testF() {
		double expected=-3;
		double actual=monom.f(1);
		assertEquals(expected, actual);

	}
	@Test
	void testDerivative() {
		Monom monomtag = new Monom(-6, 1);
		Monom test = monom.derivative();
		double expected=monomtag.get_coefficient(),actual=test.get_coefficient();
		int expected2=monomtag.get_power(), actual2=test.get_power();
		assertEquals(expected, actual);
		assertEquals(expected2, actual2);

	}
	@Test
	void testAdd() {
		Monom monomadd = new Monom(-6,2);
		monom.add(monomadd);
		double expected=-9;
		double actual =monom.get_coefficient();
		assertEquals(expected, actual);
	}
	@Test
	void testToString() {
		String monomStr =new String(monom.toString());
		String str="-3.0x^2";
		boolean x=monomStr.equals(str);
		assertTrue(x);
	}
	@Test
	void testMonomString() {
		Monom monomStr = new Monom(-1,1);
		double expected=-1,actual=monomStr.get_coefficient();
		int expected2=1, actual2=monomStr.get_power();
		assertEquals(expected, actual);
		assertEquals(expected2, actual2);
	}

}
