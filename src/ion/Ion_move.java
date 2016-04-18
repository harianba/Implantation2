package ion;

import java.awt.Color;
import java.awt.Point;

public class Ion_move {
	private Point posicion;
	private int radio;
	private Color color;

	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void mueve() {
		mueve2();
	}

	private void mueve2() {
		this.posicion.x++;
		if ((this.posicion.x + this.radio) >= 700 - this.getRadio())
			posicion.x = this.getRadio();

		if ((this.posicion.x + this.radio) - this.radio <= 0)
			posicion.x = this.getRadio();

	}
}
