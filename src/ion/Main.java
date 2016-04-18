package ion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JApplet;

/**
 * 
 * @author hadrianba
 *
 */

@SuppressWarnings("serial")
public class Main extends JApplet implements ActionListener, ItemListener {
	Choice type;
	Color color_implantation, ion_color;
	Button boton;
	String text1, text2;
	Graphics gBuffer;
	Image image;
	boolean flag;
	static Ion[] iones;
	static Ion_move[] iones2;
	static Ion_silicio[] iones3;

	/**
	 * Metodo que inicia toda la applet
	 */
	public void init() {
		color_implantation = new Color(0, 128, 128);
		this.setLayout(null);
		boton = new Button("iniciar");
		boton.setBounds(300, 370, 80, 25);
		boton.setBackground(new Color(200, 50, 50, 200));
		boton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		boton.addActionListener(this);
		type = new Choice();
		type.setBounds(300, 10, 80, 25);
		type.add("Select");
		type.add("tipo N");
		type.add("tipo P");
		type.addItemListener(this);
		add(type);
		add(boton);
		set_animation();
		new Thread() {
			public void run() {
				while (true) {
					repaint();
					delayAnimation();
				}
			}
		}.start();
	}

	/**
	 * Metodo que se encarga de iniciar ciertos parametros de los atomos como:
	 * posiciones, color, etc.
	 * @param ion_color 
	 */
	private void set_animation() {
		// TODO Auto-generated method stub
		int[] posiciones = { 52, 102, 153, 204, 255, 306, 357 };
		/*
		 * ciclo que ordena y posiciona la matriz del lado derecho
		 */
		iones3 = new Ion_silicio[42];
		for (int i = 0; i < iones3.length; i++) {
			iones3[i] = new Ion_silicio();
		}
		int cont = 0;
		for (int i = 1; i < 8; i++) {
			for (int j = 1; j < 7; j++) {
				iones3[cont].setPosicion(new Point((400 + (j * 40)), i * 50));
				cont++;
			}
		}
		/*
		 * Ciclo que posiciona por primera vez a los atomos en movimiento
		 * vertical
		 */
		iones2 = new Ion_move[7];
		for (int i = 0; i < iones2.length; i++)
			iones2[i] = new Ion_move();

		for (int i = 0; i < iones2.length; i++) {
			iones2[i].setRadio(20);
			iones2[i].setColor( null);
			iones2[i].setPosicion(new Point(-30, posiciones[i]));
		}
		/*
		 * ciclo que posiciona los atomos en movimiento en la primera franja
		 */
		iones = new Ion[42];
		// create array ion statement
		for (int i = 0; i < iones.length; i++)
			iones[i] = new Ion();
		// create random ion's position
		for (int i = 0; i < iones.length; i++) {
			iones[i].setAnchoContenedor(80);
			iones[i].setAltoContenedor(400);
			iones[i].setRadio(20);
			iones[i].setAlturaRebote((int) (Math.random() * 400 - iones[i].getRadio()));
			iones[i].setColor(null);
			iones[i].setPosicion(
					new Point((int) ((Math.random() * 40 - iones[i].getRadio()) + iones[i].getRadio()), 0));
			iones[i].setAngulo((int) (Math.random() * 180));
			iones[i].setFactorMovimiento((int) (Math.random() * 10) - 5);
		}
		text1 = text2 = "";
	}

	/**
	 * Metodo que se encarga de llevar el control de los tiempos y control de
	 * ejecucion
	 */
	protected void delayAnimation() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(15);
			if (boton.getLabel() == "iniciar") {
				for (int i = 0; i < iones.length; i++)
					iones[i].mueve();
			}
			if (iones2[6].getPosicion().x >= 560) {
				boton.setVisible(true);
			}
			if (boton.getLabel() == "next") {
				for (int i = 0; i < iones.length; i++)
					iones[i].mueve();
				lanza();
			}

			// repaint();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Metodo principal que se encarga de dibujar todo lo que está en la applet
	 */
	public void paint(Graphics g) {
		setSize(700, 400);
		if (gBuffer == null) {
			image = createImage(700, 400);
			gBuffer = image.getGraphics();
		}
		gBuffer.setColor(Color.darkGray);
		gBuffer.fillRect(0, 0, 100, getSize().height - 1);

		gBuffer.setColor(Color.GRAY);
		gBuffer.fillRect(100, 0, getSize().width - 400, getSize().height - 1);

		gBuffer.setColor(color_implantation);
		gBuffer.fillRect(getSize().width - 300, 0, 300, getSize().height - 1);

		Graphics2D g2d = (Graphics2D) gBuffer;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		dibuja(g2d);
		g.drawImage(image, 0, 0, null);
	}

	/**
	 * Este metodo se ejecuta en el momento que se presiona el boton iniciar y
	 * termina de ejecutarse cuando los atomos en movimiento alcanzan nuestra
	 * zona de implantacion
	 * 
	 * @param g
	 * @param g2
	 */
	public void dibuja(Graphics2D g2) {
		if (!flag)
			for (int i = 0; i < iones.length; i++) {
				g2.setColor(iones[i].getColor());
				g2.fillOval(iones[i].getPosicion().x, (iones[i].getAltoContenedor()) - (iones[i].getPosicion().y),
						iones[i].getRadio(), iones[i].getRadio());
			}

		for (int i = 0; i < iones3.length; i++) {
			g2.setColor(iones3[i].getColor());
			g2.fillOval(iones3[i].getPosicion().x, iones3[i].getPosicion().y, iones3[i].getRadius(),
					iones3[i].getRadius());
		}
		for (int i = 0; i < iones2.length; i++) {
			g2.setColor(iones2[i].getColor());
			g2.fillOval(iones2[i].getPosicion().x, iones2[i].getPosicion().y, iones2[i].getRadio(),
					iones2[i].getRadio());
		}
		drawString(g2, text1, 120, 50);
	}

	/**
	 * Acciones posibles cuando presionamos el unico boton que que se tiene en
	 * la applet.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "iniciar") {
			boton.setLabel("next");
			boton.setVisible(false);
			valueString();
		}
		if (e.getActionCommand() == "next") {
			flag = true;
			color_implantation = new Color(255, 102, 0);
			ordena();
			boton.setVisible(false);
			boton.setVisible(true);
			boton.setLabel("finish");
			text1 = text2;
		}
		if (e.getActionCommand() == "finish") {
			boton.setLabel("restart");
		}
		if (e.getActionCommand() == "restart") {
			this.getAppletContext().showDocument(this.getDocumentBase());
		}
	}
	 public void itemStateChanged(ItemEvent e) {
		 if (e.getItem()== "tipo N") {
			 ion_changeColor(new Color(70, 120, 230), iones, 25);
			 type.setEnabled(false);
		}else{
			ion_changeColor(new Color(128, 0, 0), iones, 15);
			type.setEnabled(false);
		}
	 }

	private void ion_changeColor(Color color, Ion[] iones, int tamaño) {
		// TODO Auto-generated method stub
		for(int i = 0; i<iones.length; i++){
			iones[i].setColor(color);
			iones[i].setRadio(tamaño);
		}
		for(int i = 0;i<iones2.length;i++){
			iones2[i].setColor(color);
			iones2[i].setRadio(tamaño);
		}
	}

	/**
	 * Despues de quedar desordenado la implantacion se recurre al ordenarlo
	 */

	private void ordena() {
		// TODO Auto-generated method stub
		int cont = 0;
		int posiciones[] = {560,520,440,480,520,480,440};
		for (int i = 1; i < 8; i++)
			for (int j = 1; j < 7; j++) {
				iones3[cont].setPosicion(new Point((400 + (j * 40)), i * 50));
				if(iones3[cont].getPosicion().x == posiciones[i-1])
					iones3[cont].setPosicion(new Point(-20,-20));
				cont++;
			}
		for(int i = 0;i<posiciones.length;i++){
			iones2[i].setPosicion(new Point(posiciones[i],50 + i*50));
		}
	}

	private void lanza() {
		// int [] vertical_position = { 520, 480, 440,440,560,480,560}
		if (iones2[6].getPosicion().x <= 560) {
			if (iones2[1].getPosicion().x <= 480) {
				iones2[1].mueve();
				if (iones2[1].getPosicion().x == 480) {
					iones2[1].setPosicion(new Point(500, 120));
					iones3[7].setPosicion(new Point(460, 80));
				}

			}
			if (iones2[1].getPosicion().x > 250 && iones2[5].getPosicion().x <= 480) {
				iones2[5].mueve();
				if (iones2[5].getPosicion().x == 480) {
					iones2[5].setPosicion(new Point(500, 320));
					iones3[31].setPosicion(new Point(485, 290));
				}
			}
			if (iones2[5].getPosicion().x > 470 && iones2[0].getPosicion().x <= 520) {
				iones2[0].mueve();
				if (iones2[0].getPosicion().x == 520) {
					iones2[0].setPosicion(new Point(540, 70));
					iones3[2].setPosicion(new Point(500, 30));
				}
			}
			if (iones2[0].getPosicion().x > 470 && iones2[2].getPosicion().x <= 440) {
				iones2[2].mueve();
				if (iones2[2].getPosicion().x == 440) {
					iones2[2].setPosicion(new Point(460, 130));
					iones3[12].setPosicion(new Point(420, 170));
				}
			}
			if (iones2[2].getPosicion().x > 300 && iones2[3].getPosicion().x <= 440) {
				iones2[3].mueve();
				if (iones2[3].getPosicion().x == 440) {
					iones2[3].setPosicion(new Point(460, 180));
					iones3[18].setPosicion(new Point(420, 220));
				}
			}
			if (iones2[3].getPosicion().x > 400 && iones2[6].getPosicion().x <= 560) {
				iones2[4].mueve();
				iones2[6].mueve();
				if (iones2[6].getPosicion().x == 560) {
					iones2[6].setPosicion(new Point(580, 370));
					iones2[4].setPosicion(new Point(580, 270));
					iones3[38].setPosicion(new Point(590, 330));
					iones3[27].setPosicion(new Point(590, 230));
				}
			}
		}
	}

	/**
	 * Dubuja los textos que se muestran en el applet
	 * 
	 * @param g
	 * @param text
	 * @param x
	 * @param y
	 */
	private void drawString(Graphics g, String text, int x, int y) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 12));
		g.setColor(Color.WHITE);
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	private void valueString() {
		text1 = "Los iones que chocan  sobre la\n" + "superficie pierden  su energía\n"
				+ "cinética a través de colisiones\n" + "inelásticas  con  átomos  en el\n" + "sustrato.";
		text2 = "La implantación iónica siempre\n" + "es seguido por una temperatura \n"
				+ "elevada necesaria para restablecer\n" + "el orden en la red cristalográfica.\n";
	}
}