package elementos;

import java.awt.Graphics;

import ferramentas.SpriteSheet;

public class Locais extends Card{

	public Locais(){
		sheet = new SpriteSheet("/locais.png");
		foto = sheet.getSprite(0, 0, 500, 500);
	}
	public void render(Graphics g) {
		g.drawImage(foto, 150, 50, 700, 700, null);
	}
	
}
