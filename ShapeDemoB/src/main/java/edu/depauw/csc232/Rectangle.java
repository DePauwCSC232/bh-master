package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

public class Rectangle implements Shape {
	private int width, height;

	public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public List<String> render() {
		List<String> result = new ArrayList<>();
		
		for (int row = 0; row < height; row++) {
			String line = "";
			for (int col = 0; col < width; col++) {
				line = line + '*';
			}
			result.add(line);
		}
		return result;
	}

}
