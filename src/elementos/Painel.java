package elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;

import ferramentas.SpriteSheet;
import main.Game;

public class Painel {
	public SpriteSheet sheet;
	public BufferedImage foto;
	public int L, A, X, Y;

	public Painel() {
		L = 1000;
		A = 138;
		Y = Game.HIGHT - A;
		X = 0;
		sheet = new SpriteSheet("/painel.jpg");
		foto = sheet.getSprite(0, 0, L, A);

	}

	public void render(Graphics g) {
		g.drawImage(foto, X, Y, L, A, null);
	}
}