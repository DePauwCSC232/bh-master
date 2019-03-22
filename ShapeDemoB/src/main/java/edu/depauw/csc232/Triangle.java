package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

public class Triangle implements Shape {
	private int width, height;

	public Triangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public List<String> render() {
		List<String> result = new ArrayList<>();
		for (int row = 0; row < height; row++) {
			int starWidth = width * row / height;
			int spaceWidth = width - starWidth;
			result.add(Util.repeat("*", starWidth) + Util.repeat(" ", spaceWidth));
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
