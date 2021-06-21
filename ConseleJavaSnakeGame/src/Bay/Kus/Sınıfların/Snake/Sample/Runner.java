
package Bay.Kus.Sýnýflarýn.Snake.Sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Runner {
	public static String[][] s = new String[20][20];

	private static int a;
	private static int b;
	private static int snakeLength;
	private static Random r;
	private static Locations[] snake;
	private static Locations[] snaketemp;
	private static boolean durum;

	public static void main(String[] args) {

		String[][] s = matrisOlustur();

		// ekranaYaz(s);
		snakeLength = 3;
		snake = new Locations[snakeLength];
		for (int i = 0; i < snake.length; i++) {
			snake[i] = new Locations();
			snake[i].setX(i);
			snake[i].setY(0);
		}

		yilaniMatriseEkle(s, snake);

		r = new Random();
		Locations locations = new Locations();
		a = r.nextInt(9);
		b = r.nextInt(9);

		if (durum) {
			s[a][b] = " @";

		} else {
			a = r.nextInt(9);
			b = r.nextInt(9);
			s[a][b] = " @";

		}

		ekranaYaz(s);
		boolean durum = true;
		while (durum) {

			Scanner scan = new Scanner(System.in);
			System.out.println("saða d sola a yukarý w aþaðý s");
			String secim = scan.nextLine();

			switch (secim) {

			case "s": {
				Locations temp = null;
				for (int i = snake.length - 1; i >= 0; i--) {
					if (i == snake.length - 1) {
						temp = new Locations();
						temp.setX(snake[i].getX()); // yilanin baþýný temp e yedekledik
						temp.setY(snake[i].getY());

						snake[i].setY(snake[i].getY() + 1);

					} else {
						Locations temp1 = new Locations();
						temp1.setX(snake[i].getX()); // yilanin ortasini temp1 e yedekledik
						temp1.setY(snake[i].getY());
						snake[i] = new Locations();
						snake[i].setX(temp.getX());
						snake[i].setY(temp.getY());
						temp = temp1;

					}

				}

				yilaniMatriseEkle(s, snake);
				SnakeGrowing(s);

				ekranaYaz(s);
				break;
			}
			case "d": {
				Locations temp = null;
				for (int i = snake.length - 1; i >= 0; i--) {
					if (i == snake.length - 1) {
						temp = new Locations();
						temp.setX(snake[i].getX());
						temp.setY(snake[i].getY());
						snake[i].setX(snake[i].getX() + 1);
					} else {
						Locations temp1 = new Locations();
						temp1.setX(snake[i].getX());
						temp1.setY(snake[i].getY());
						snake[i] = new Locations();
						snake[i].setX(temp.getX());
						snake[i].setY(temp.getY());
						temp = temp1;

					}

				}

				yilaniMatriseEkle(s, snake);
				SnakeGrowing(s);
				ekranaYaz(s);
				break;
			}
			case "w": {
				Locations temp = null;
				for (int i = snake.length - 1; i >= 0; i--) {
					if (i == snake.length - 1) {
						temp = new Locations();
						temp.setX(snake[i].getX());
						temp.setY(snake[i].getY());
						snake[i].setY(snake[i].getY() - 1);
					} else {
						Locations temp1 = new Locations();
						temp1.setX(snake[i].getX());
						temp1.setY(snake[i].getY());
						snake[i] = new Locations();
						snake[i].setX(temp.getX());
						snake[i].setY(temp.getY());
						temp = temp1;

					}

				}

				yilaniMatriseEkle(s, snake);
				SnakeGrowing(s);
				ekranaYaz(s);
				break;
			}
			case "a": {
				Locations temp = null;
				for (int i = snake.length - 1; i >= 0; i--) {
					if (i == snake.length - 1) {
						temp = new Locations();
						temp.setX(snake[i].getX());
						temp.setY(snake[i].getY());
						snake[i].setX(snake[i].getX() - 1);
					} else {
						Locations temp1 = new Locations();
						temp1.setX(snake[i].getX());
						temp1.setY(snake[i].getY());
						snake[i] = new Locations();
						snake[i].setX(temp.getX());
						snake[i].setY(temp.getY());
						temp = temp1;

					}

				}

				yilaniMatriseEkle(s, snake);
				SnakeGrowing(s);
				ekranaYaz(s);
				break;
			}
			default:
				break;
			}

		}

	}

	private static void SnakeGrowing(String[][] s) {
		if (yemkontrol(s, snake)) {

			snakeLength++;
			snaketemp = new Locations[snakeLength];

			for (int i = 0; i < snakeLength; i++) {
				if (i < snakeLength - 1) {

					snaketemp[i] = snake[i];

				} else if (i == snakeLength - 1) {
					snaketemp[i]=snake[i - 1];
					

				}
			}
			snake = new Locations[snakeLength];
			for (int i = 0; i < snake.length; i++) {
				snake[i] = snaketemp[i];
			}
			yilaniMatriseEkle(s, snake);

		}
	}

	private static String[][] matrisOlustur() {

		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				s[i][j] = " .";
			}
		}
		return s;
	}

	private static void yilaniMatriseEkle(String[][] s, Locations[] snake) {
		s = matrisOlustur();

		for (int i = 0; i < snakeLength; i++) {
			int x = snake[i].getX();
			int y = snake[i].getY();
			s[y][x] = " x";

		}

	}

	private static void ekranaYaz(String[][] s) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				System.out.print(s[i][j]);
			}
			System.out.println();
		}
	}

	public static boolean yemkontrol(String[][] s, Locations[] snake) {

		durum = false;
		int x;
		int y;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length; j++) {

				if (s[i][j].equals(" x")) {
					x = j;
					y = i;

					if (b == x && a == y) {

						durum = true;
						break;

					} else {
						durum = false;
						break;
					}
				}

			}
		}

		if (durum) {
			a = r.nextInt(9);
			b = r.nextInt(9);
			s[a][b] = " @";

		} else {

			s[a][b] = " @";

		}
		return durum;

	}

}
