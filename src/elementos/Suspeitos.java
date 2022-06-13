package elementos;

import java.awt.Graphics;

import ferramentas.SpriteSheet;

public class Suspeitos extends Card {

	public Suspeitos() {

		sheet = new SpriteSheet("/suspeitos.png");
		foto = sheet.getSprite(0, 0, 500, 500);
	}

	public void render(Graphics g) {
		g.drawImage(foto, 250, 50, 500, 500, null);
	}

}
