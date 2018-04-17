import java.awt.Graphics;
import java.awt.Color;

public class Vector2D {
	public double x, y;
	private static int count = 0;

	// konstruktor domyœlny
	Vector2D() {
		this.x = 0;
		this.y = 0;
	}

	// konstruktor z parametrami
	Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
		
		count = 0;
	}
	
	//metoda sluzaca do rysowania wektora zaczepionego w srodku ukladu wspolrzednych
	public void rysujWektor(Graphics g, Color color, int xo, int yo) {
		
		xo = xo/6 + count * xo/3;
		yo = yo / 4;
		
		g.setColor(color);
		
		double a = Math.sqrt(this.x * this.x + this.y * this.y);

		double c = this.x / a;
		double s = -this.y / a;

		int xk = (int)(this.x + xo);
		int yk = (int)(-this.y + yo);
		
		g.drawLine(xo, yo, xk, yk);
		
		int x = -5, y = -3, yn = 3;
		g.drawLine(xk, yk, xk + (int) (x * c - y * s), yk + (int) (x * s + y * c));
		g.drawLine(xk, yk, xk + (int) (x * c - yn * s), yk + (int) (x * s + yn * c));
	
		g.drawString("(" + this.x + " , " + this.y + ")", xk, yk - 3);
	}
	
	//metoda sluzaca do rysowania wektora zaczepionego na koncu innego wektora
	public void rysujWektor(Graphics g, Color color, int xo, int yo, Vector2D vector) {
		
		xo = xo/6 + count * xo/3;
		yo = yo / 4;
		
		g.setColor(color);
		
		double a = Math.sqrt(this.x * this.x + this.y * this.y);

		double c = this.x / a;
		double s = -this.y / a;
		
		xo = xo + (int)vector.x;
		yo = yo - (int)vector.y;
		
		int xk = (int)(this.x + xo);
		int yk = (int)(-this.y + yo);
		
		g.drawLine(xo, yo, xk, yk);
		
		int x = -5, y = -3, yn = 3;
		g.drawLine(xk, yk, xk + (int) (x * c - y * s), yk + (int) (x * s + y * c));
		g.drawLine(xk, yk, xk + (int) (x * c - yn * s), yk + (int) (x * s + yn * c));
	}
	
	//metoda sluzaca do rysowania ukladu wspolrzednych
	public void rysujUkladWsp(Graphics g, int xo, int yo) {
		
		g.setColor(new Color(0,0,0));
		
		xo = xo/6 + count * xo/3;
		yo = yo / 4;
		
		count++;
		
		int wym = 150;
		//rysowanie osi x
		g.drawLine(xo - wym, yo, xo + wym, yo);
		g.drawLine(xo + wym, yo, xo + wym - 5, yo + 3);
		g.drawLine(xo + wym, yo, xo + wym - 5, yo - 3);
		//rysowanie osi y
		g.drawLine(xo, yo - wym, xo, yo + wym);
		g.drawLine(xo, yo - wym, xo + 3, yo - wym + 5);
		g.drawLine(xo, yo - wym, xo - 3, yo - wym + 5);
		//rysowanie podzia³ki na osiach
		for (int i = 1; i < 2*wym/10; i++) {
			g.drawLine(xo - wym + i * 10, yo - 2, xo - wym + i * 10, yo + 2);
			g.drawLine(xo - 2, yo - wym + i * 10, xo + 2, yo - wym + i * 10);
		}
		
		g.drawString("10", xo + 4 , yo + 13);
		g.drawString("10", xo -17 , yo - 5);
	}

	// metoda zwracajaca sume dwoch obiektow klasy Vector2D i przedstawiajaca ja graficznie
	public Vector2D dodaj(Vector2D vector, Graphics g, int xo, int yo) {
		Vector2D vectorWynikowy = new Vector2D();

		vectorWynikowy.x = this.x + vector.x;
		vectorWynikowy.y = this.y + vector.y;
		
		this.rysujWektor(g, new Color(255, 0, 0), xo, yo);
		vector.rysujWektor(g, new Color(0, 255, 0), xo, yo, this);
		vectorWynikowy.rysujWektor(g, new Color(0, 0, 255), xo, yo);

		g.drawString("DODAWANIE", xo/6 + count * xo/3 - 200, yo/2 - 50);
		
		rysujUkladWsp(g, xo, yo);
		
		return vectorWynikowy;
	}

	// metoda zwracajaca roznice dwoch obiektow klasy Vector2D i przedstawiajaca ja graficznie
	public Vector2D odejmij(Vector2D vector, Graphics g, int xo, int yo) {
		Vector2D vectorWynikowy = new Vector2D();

		vector.x = -vector.x;
		vector.y = -vector.y;
		
		vectorWynikowy.x = this.x + vector.x;
		vectorWynikowy.y = this.y + vector.y;

		this.rysujWektor(g, new Color(255, 0, 0), xo, yo);
		vector.rysujWektor(g, new Color(0, 255, 0), xo, yo, this);
		vectorWynikowy.rysujWektor(g, new Color(0, 0, 255), xo, yo);
		
		g.drawString("ODEJMOWANIE", xo/6 + count * xo/3 - 200, yo/2 - 50);
		
		rysujUkladWsp(g, xo, yo);
		
		vector.x = -vector.x;
		vector.y = -vector.y;
		
		return vectorWynikowy;
	}

	// metoda zwracajaca zwracajaca iloczn obiektu klasy Vector2D przez stala i przedstawiajaca ja graficznie
	public Vector2D mnozPrzezStala(double stala, Graphics g, int xo, int yo) {
		Vector2D vectorWynikowy = new Vector2D();

		vectorWynikowy.x = this.x * stala;
		vectorWynikowy.y = this.y * stala;
		
		this.rysujWektor(g, new Color(255, 0, 0), xo, yo);
		vectorWynikowy.rysujWektor(g, new Color(0, 0, 255), xo, yo);

		g.drawString("MNOZENIE PRZEZ STALA", xo/6 + count * xo/3 - 200, yo/2 - 50);
		
		rysujUkladWsp(g, xo, yo);

		return vectorWynikowy;
	}

	// metoda zwracajaca dlugosc wektora
	public double dlugoscWektora() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}

	// metoda zwracajaca wektor znormalizowany
	public Vector2D normalizuj() {
		Vector2D vectorWynikowy = new Vector2D();

		vectorWynikowy.x = this.x / this.dlugoscWektora();
		vectorWynikowy.y = this.y / this.dlugoscWektora();

		return vectorWynikowy;
	}
	
	public static void setCount() {
		count = 0;
	}
}
