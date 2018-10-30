package edu.depauw.csc232;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private ArrayList<Image> images;

	public Canvas() {
		images = new ArrayList<Image>();
	}

	/** add an image to be drawn to the canvas */
	public void addImage(Image s) {
		images.add(s);
	}

	@Override
	public void paint(Graphics g) {
		drawImages(g);
	}

	/* draw the specified image onto the specified graphics context */
	private void drawImage(Graphics g, Image image) {
		int height = this.getHeight();
		int width = this.getWidth();

		for (int i = 0; i < width; i++) {
			double x = (double) i / width;
			for (int j = 0; j < height; j++) {
				double y = (double) j / height;
				g.setColor(image.colorAt(x, y));
				g.drawLine(i, j, i, j); // draw a point
			}
		}
	}

	/* draw all of the shapes on the canvas in the specified Graphics context */
	private void drawImages(Graphics g) {
		for (Image image : images) {
			drawImage(g, image);
		}
	}
}
