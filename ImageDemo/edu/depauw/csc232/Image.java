package edu.depauw.csc232;

import java.awt.Color;

/**
 * An Image is completely determined by the color at any given point (x, y),
 * where 0.0 <= x <= 1.0 and 0.0 <= y <= 1.0.
 */
public interface Image {
	Color colorAt(double x, double y);
	
	default Image shifted(double dx, double dy) {
		Image self = this;
		return new Image() {
			public Color colorAt(double x, double y) {
				return self.colorAt(x - dx, y - dy);
			}
		};
	}
	
	default Image scaled(double sx, double sy) {
		Image self = this;
		return new Image() {
			public Color colorAt(double x, double y) {
				return self.colorAt(x / sx, y / sy);
			}
		};
	}
	
	static Image ellipse(double x, double y, double w, double h, Color color) {
		return new Circle(color).scaled(w, h).shifted(x, y);
	}
	
	static Image rectangle(double x, double y, double w, double h, Color color) {
		return new Square(color).scaled(w, h).shifted(x, y);
	}
	
	static final Color CLEAR = new Color(0, 0, 0, 0); 
}
