package elementos;

import java.awt.Graphics;

import ferramentas.SpriteSheet;

public class Jornal extends Card {

	public Jornal() {
		sheet = new SpriteSheet("/jornal.png");
		foto = sheet.getSprite(0, 0, 768, 1280);
	}
	public void render(Graphics g) {
		g.drawImage(foto, 250, 50, 450, 750, null);
	}
}
