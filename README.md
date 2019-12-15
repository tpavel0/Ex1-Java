# Ex1-Java
--------------------------Pavel--------------------------------


About the **Monom** class:
The form of Monom is c\*x^n where **c** is the coefficient (double) and **n** is the power (integer).
You can perform various actions on Monoms, such as: 
* create from string
* toString
* add two monoms
* multiply two monoms
* check if two monoms are equal
* clone a monom

About the **Polynom** class:
We represent Polynom as a list (ArrayList) of Monoms.
The form of Polynom is c0+c1\*x+c2\*x^2+...+cn\*x^n.
You can perform various actions on Polynoms, such as: 
* create from string
* toString
* add two polynoms
* substract two polynoms
* multiply two polynoms
* calculate the derivative
* calculate area under the graph of the polynom
* check if two polynoms are equal
* clone a polynom

About the **ComplexFunction** class:
We represent ComplexFunction as a tuple of three: Operation (plus, div, ...), Left side function (ComplexFunction) and a Right side function (ComplexFunction).
So ComplexFunction is a recursive type.
You can perform various actions on ComplexFunction f, such as: 
* create from string
* toString
* calculate the value of f(x)
* Add,Multiply,Max,Min,Compose,Divide with another function.

We parse the complex function as we first extract the operation, and then find the middle comma in the inner string (in the parenthesis).
The cut the inner string to two parts, left and right, and converts them to ComplexFunctions.

We Created a Functions_GUI class to hold a collection of functions.
Use this class to read functions from file, write function to file, draw functions on screen (with option to read configuration from json file).
