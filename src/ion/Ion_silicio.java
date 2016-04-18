package ion;

import java.awt.Color;
import java.awt.Point;

public class Ion_silicio {
	private Point posicion;
	private int radius;
	private Color color;

	Ion_silicio() {
		radius = 20;
		color = Color.black;
	}

	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
