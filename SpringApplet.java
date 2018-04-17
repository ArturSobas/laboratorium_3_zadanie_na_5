import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;

import javax.swing.JApplet;

public class SpringApplet extends JApplet {
	
	@Override
	public void init() {
		
	}

	@Override
	public void paint(Graphics g) {
		
		setSize(1366, 662);
		
		Vector2D.setCount();
		
		int xo = getWidth();
		int yo = getHeight();
		
		//czyszczenie okna
		g.clearRect(0, 0, getWidth(), getHeight());
		
		g.drawString("czerwony, zielony - wektory na ktorych wykonujemy dzialania", 30, yo - yo/3 - 40);
		g.drawString("niebieski - wektor wynikowy", 30, yo - yo/3 -20);
		
		//tworzenie obiektow klasy Vector2D
		Vector2D vector1 = new Vector2D(50, 70);
		Vector2D vector2 = new Vector2D(30, -20);
		
		//Operacje na wektorach
		vector1.dodaj(vector2, g, xo, yo);
		vector1.odejmij(vector2, g, xo, yo);
		vector1.mnozPrzezStala(2, g, xo, yo);
	}
}
	
	
	
