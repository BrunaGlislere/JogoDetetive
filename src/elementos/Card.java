package elementos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;

import ferramentas.SpriteSheet;
import main.Game;

public class Card {
	public SpriteSheet sheet;
	public BufferedImage foto;
	

	public Card() {
		sheet = new SpriteSheet("/cards.jpg");
		foto = sheet.getSprite(0, 0, 500, 500);

	}

	public void render(Graphics g) {
		g.drawImage(foto, 250, 50, 500, 500, null);
	}
	
	}
