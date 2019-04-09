package edu.depauw.csc232;

public class Main {

	public static void main(String[] args) {
		Shape a = new Rectangle(30, 10);
		Shape b = new Triangle(20, 5);
		Shape c = new RowShape(a, b, new CenterAlignmentStrategy());
		Shape s = new FramedShape(c);
		s.render();
	}

}
