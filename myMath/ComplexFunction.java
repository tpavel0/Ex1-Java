package myMath;

import myMath.ComplexFunction;
import myMath.Monom;
import myMath.Polynom;
import myMath.Operation;
import myMath.function;

public class ComplexFunction implements complex_function {
	

	private function left;
	private function right;
	private Operation op;

	public ComplexFunction(String op, function left, function right) {
		if (left == null) {
			throw new RuntimeException("error: left is null");
		}
		this.left = left;
		this.right = right;
		this.op = fromStringToOp(op);
	}
	private  ComplexFunction(Operation o, function l, function r) {
		if(o == Operation.Error) {
			throw new RuntimeException("Error");
		}
		op = o;
		this.left = l;
		this.right = r;
	}
	
	public ComplexFunction(function f) {
		this.left = f;
		this.right = null;
		this.op = Operation.None;
	}
	
	/*
	 * Checks if an object is monom
	 */
	public boolean isMonom(Object o) {
		return o instanceof Monom;
	}
	/*
	 * Checks if object is polynom
	 */
	public boolean isPolynom(Object o) {
		return o instanceof Polynom;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;

		if (isPolynom(other) || isMonom(other)) {
			return this.right == null && this.left.equals(other);
		}
		if (getClass() != other.getClass()) {
			return false;
		}
		ComplexFunction otherComplex = (ComplexFunction) other;
		if (this.op == Operation.None && otherComplex.op != Operation.None) {
			return this.left.equals(otherComplex);
		}
		if (otherComplex.op == Operation.None && this.op != Operation.None) {
			return otherComplex.left.equals(this);
		}
		if (op != otherComplex.op) {
			return false;
		}
		if ((left == null && otherComplex.left != null) || !left.equals(otherComplex.left)) {
			return false;
		}
		if ((right == null && otherComplex.right != null) || (right != null && !right.equals(otherComplex.right))) {
			return false;
		}

		return true;
	}
	
	/*
	 * Converts string to Operation
	 */
	public Operation fromStringToOp(String operation) {
		operation = operation.toLowerCase();
		if (operation.equals("plus")) {
			return Operation.Plus;
		} else if (operation.equals("mul")) {
			return Operation.Times;
		} else if (operation.equals("div")) {
			return Operation.Divid;
		} else if (operation.equals("max")) {
			return Operation.Max;
		} else if (operation.equals("min")) {
			return Operation.Min;
		} else if (operation.equals("comp")) {
			return Operation.Comp;
		} else if (operation.equals("none")) {
			return Operation.None;
		}
		throw new RuntimeException("error: bad op");
	}
	
	/*
	 * Converts Operation to string
	 */
	public String fromOpToString(Operation op) {
		if(op == Operation.Plus) {
			return "plus";
		} else if(op == Operation.Times) {
			return "mul";
		} else if(op == Operation.Comp) {
			return "comp";
		} else if(op == Operation.Divid) {
			return "div";
		} else if(op == Operation.Max) {
			return "max";
		} else if(op == Operation.Min) {
			return "min";
		} else if(op == Operation.None) {
			return "none";
		}
		throw new RuntimeException("error: bad op");
	}
	
	/*
	 * Returns the index of the middle comma or -1 otherwise
	 */
	public int findWhereToSplit(String s) {
		int c = 0;
		int index = 0;
		while(index < s.length()) {
			char current = s.charAt(index);
			if (current == ',' && c == 0) {
				return index;
			}
			if (current == '(') {
				c++;
			} else if (current == ')') {
				c--;
			}
			index++;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		if (this.op == Operation.None) {
			return left.toString();
		}
		return fromOpToString(op) + "(" + left.toString() + "," + right.toString() + ")";
	}

	public ComplexFunction(String s) {
		if(s==null || s.isEmpty())
		{
			throw new RuntimeException("error: s is null");
		}
		
		// remove spaces from string
		s = s.replaceAll(" ", "");
		s = s.toLowerCase();
		Operation newOp = null;	
		
		if(s.startsWith(fromOpToString(Operation.Plus))) {
			newOp = Operation.Plus;
		}
		else if(s.startsWith(fromOpToString(Operation.Times))) {
			newOp = Operation.Times;
		}
		else if(s.startsWith(fromOpToString(Operation.Comp))) {
			newOp = Operation.Comp;
		}
		else if(s.startsWith(fromOpToString(Operation.Divid))) {
			newOp = Operation.Divid;
		}
		else if(s.startsWith(fromOpToString(Operation.Max))) {
			newOp = Operation.Max;
		}
		else if(s.startsWith(fromOpToString(Operation.Min))) {
			newOp = Operation.Min;
		} else {
			this.op = Operation.None;
			try {
				this.left = new Monom(s);
			} catch(Exception e) {
				try {
					this.left = new Polynom(s);
				} catch(Exception ee) {
					this.left = null;
				}
			}
			if(this.left == null) {
				throw new RuntimeException("error: cannot convert string to left");
			}
			this.right = null;
			return;
		}
		
		String opStr = fromOpToString(newOp);
		int openIndex = opStr.length();
		int endIndex = s.length()-1;
		if(s.charAt(openIndex) != '(' || s.charAt(endIndex) != ')') {
			throw new RuntimeException("error: bad string s - parenthesis");
		}
		
		String inner = s.substring(openIndex+1, endIndex);
		
		int middle = findWhereToSplit(inner);

		String newLeft = inner.substring(0, middle);
		String newRight = inner.substring(middle+1);
		
		this.op = newOp;
		this.left = new ComplexFunction(newLeft);
		this.right = new ComplexFunction(newRight);
	}
	
	@Override
	public double f(double x) {
		double sum = 0;
		
		switch(op) {
			case Plus:
				sum=  left.f(x) + right.f(x);
				break;
			case Times:
				sum = left.f(x) * right.f(x);
				break;
			case Divid:
				sum = left.f(x) / right.f(x);
				break;
			case Max:
				sum = Math.max(left.f(x),  right.f(x));
				break;
			case Min:
				sum = Math.min(left.f(x), right.f(x));
				break;
			case Comp:
				sum = left.f(right.f(x));
				break;
			case None:
				sum = left().f(x);
				break;
		}
		
		return sum;
	}
	


	@Override
	public function initFromString(String s) {
		return new ComplexFunction(s);
	}

	@Override
	public function copy() {
		if(this.right==null) {
			return new ComplexFunction(this.left.copy());
		}
		return new ComplexFunction(this.op, this.left.copy(),this.right.copy());		
	}
	
	/*
	 * Moves the current instance to the left and sets new operation and right side
	 */
	private void AddLevel(function functionToOperateWith, Operation operation) {
		this.left = copy();
		this.op = operation;
		this.right = functionToOperateWith;
	}

	@Override
	public void plus(function f1) {
		AddLevel(f1,Operation.Plus);
	}

	@Override
	public void mul(function f1) {
		AddLevel(f1,Operation.Times);
	}

	@Override
	public void div(function f1) {
		AddLevel(f1,Operation.Divid);
	}

	@Override
	public void max(function f1) {
		AddLevel(f1,Operation.Max);
	}

	@Override
	public void min(function f1) {
		AddLevel(f1,Operation.Min);
	}

	@Override
	public void comp(function f1) {
		AddLevel(f1,Operation.Comp);
	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}

}
