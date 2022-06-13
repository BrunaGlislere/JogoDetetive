package elementos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ferramentas.SpriteSheet;
import main.Game;

public class Cenario {
	public SpriteSheet sheet;
	public BufferedImage[] foto;
	public static int ponteiro = 5;

	public Cenario() {
		sheet = new SpriteSheet("/cenarios.jpg");
		foto = new BufferedImage[6];
		for (int i = 0; i < 6; i++) {
			foto[i] = sheet.getSprite(i * 1000, 0, 1000, 1000);
		}

	}

	public void render(Graphics g) {
		g.drawImage(foto[ponteiro], 0, 0, 1000, 1000, null);
	}
}
