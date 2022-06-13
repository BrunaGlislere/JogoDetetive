package elementos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import ferramentas.SpriteSheet;

public class Entrada extends Card {
	public boolean alerta=false;
	public int temp=0;
	

	public Entrada() {
		sheet = new SpriteSheet("/arte.jpg");
		foto = sheet.getSprite(0, 0, 757, 634);
	}
	public void tick() {
		temp++;
		if(temp>2) {
			temp=0;
			alerta=!alerta;
		}
	}
	public void render(Graphics g) {
		g.drawImage(foto, 0, 0, 1000, 1000,null) ;
		g.setColor(new Color(255,255,255));
		g.setFont(new Font( "Serif", Font.BOLD, 30 ) );
		if(alerta) {
			g.drawString("Pressione Enter", 400, 30);
		}
		g.setFont(new Font( "Serif", Font.BOLD, 16 ) );
	}
}
