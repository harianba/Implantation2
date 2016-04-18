package ion;

import java.awt.Color;
import java.awt.Point;

public class Ion {
	private Point posicion;
	private int factorMovimiento, alturaRebote, radio, angulo, anchoContenedor, altoContenedor;
	private Color color;

	/**
	 * Constructor
	 */
	public Ion() {
		posicion = new Point(0, 0);
		this.setFactorMovimiento(1);
		this.setRadio(20);
		this.setAlturaRebote(100);
		this.angulo = 0;
	}

	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	public int getFactorMovimiento() {
		return factorMovimiento;
	}

	public void setFactorMovimiento(int factorMovimiento) {
		this.factorMovimiento = factorMovimiento;
	}

	public int getAlturaRebote() {
		return alturaRebote;
	}

	public void setAlturaRebote(int alturaRebote) {
		this.alturaRebote = alturaRebote;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	public int getAngulo() {
		return angulo;
	}

	public void setAngulo(int angulo) {
		this.angulo = angulo;
	}

	public int getAnchoContenedor() {
		return anchoContenedor;
	}

	public void setAnchoContenedor(int anchoContenedor) {
		this.anchoContenedor = anchoContenedor;
	}

	public int getAltoContenedor() {
		return altoContenedor;
	}

	public void setAltoContenedor(int altoContenedor) {
		this.altoContenedor = altoContenedor;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void mueve() {
		mueve(this.factorMovimiento);
	}

	private void mueve(int factorMovimiento2) {
		// TODO Auto-generated method stub

		double anguloEnRadianes;

		this.angulo++;
		this.angulo = this.angulo > 180 ? 0 : this.angulo;
		/*
		 * Convertimos los grados sexagesimales en radianes
		 */
		anguloEnRadianes = (this.angulo * 2 * Math.PI / 360);
		/*
		 * Incrementamos o decremetamos la x según la variables factorMovimiento
		 */
		this.posicion.x += this.factorMovimiento;
		/*
		 * Controlamos que la x se encuentre siempre entre los límites de la
		 * primera franja
		 */
		if ((this.posicion.x + this.radio) >= this.anchoContenedor) {
			this.factorMovimiento = -1 * this.factorMovimiento;
		}
		if ((this.posicion.x + this.radio) - this.radio <= 0) {
			this.factorMovimiento = -1 * this.factorMovimiento;
		}
		/*
		 * Calcula la posicion y de la pelota
		 */
		this.posicion.y = this.radio + (int) (this.alturaRebote * Math.sin(anguloEnRadianes));

	}
}