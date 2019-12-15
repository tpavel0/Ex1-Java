package myMathTests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import myMath.ComplexFunction;
import myMath.Monom;
import myMath.Polynom;

public class ComplexFunctionTest {

	@Test
	public void test1() {
		Monom m = new Monom("16x^5");
		assertEquals(new ComplexFunction("mul", m, m).f(2), 262144);
	}

	@Test
	public void test2() {
		Polynom p = new Polynom("8x^2+12x^3+123");
		assertEquals(new ComplexFunction("plus", p, p).f(1), 286);
	}

	@Test
	public void test3() {
		assertEquals(new ComplexFunction("div", 
				new Polynom("5555x^6"), 
				new Polynom("2x^5")).f(2), 5555);
	}

	@Test
	public void test4() {
		assertEquals(new ComplexFunction("comp", 
				new Polynom("3x^2"), 
				new Polynom("x^5")).f(2), 3072);
	}

	@Test
	public void test5() {
		ComplexFunction c = new ComplexFunction("div(2,4)");
		assertEquals(c, c.initFromString("div(2,4)"));		
	}

	@Test 
	public void test6() {
		String expected = "div(mul(plus(x^3+3.0,x^7-2.0),8.0),6.0)";
		ComplexFunction c = new ComplexFunction(expected);
		assertEquals(expected, c.toString());
	}

	@Test 
	public void test7() {
		String expected = "mul(plus(x^3+3.0,x^7-2.0),6.0)";
		ComplexFunction c = new ComplexFunction(expected);
		assertEquals(expected, c.toString());
	}
	

	@Test 
	public void test8() {
		String expected = "plus(x^3+3.0,x^7-2.0)";
		ComplexFunction c = new ComplexFunction(expected);
		assertEquals(expected, c.toString());
	}
}
