package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

public class Rectangle implements Shape {
	private int width, height;

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public List<String> render() {
		List<String> result = new ArrayList<>();
		for (int row = 0; row < height; row++) {
			result.add(Util.repeat("*", width));
		}
		return result;
	}

   @Override
   public int width() {
      return width;
   }

   @Override
   public int height() {
      return height;
   }

}
