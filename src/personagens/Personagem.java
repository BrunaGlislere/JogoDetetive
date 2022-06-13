package personagens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ferramentas.SpriteSheet;

public class Personagem {
	public boolean visible = false;
	public int x, y, w, l;
	public SpriteSheet sheet;
	public BufferedImage foto;

	public Personagem(String caminho) {
		sheet = new SpriteSheet(caminho);
		foto = sheet.getSprite(x, y, w, l);
	}

	public void render(Graphics g) {

	}

}
