package edu.depauw.csc232;

public class Main {

	public static void main(String[] args) {
		Shape a = new Rectangle(30, 10);
		Shape b = new Triangle(20, 5);
		Shape c = new StackShape(a, b, new CenterAlignStrategy());
		Shape s = new FramedShape(c);
		s.displayToConsole();
	}

}
