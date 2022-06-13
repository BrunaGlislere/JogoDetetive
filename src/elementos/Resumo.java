package elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ferramentas.SpriteSheet;

public class Resumo {
	public SpriteSheet sheet;
	public BufferedImage foto;
	public static ArrayList<String> pistas;

	public Resumo() {
		sheet = new SpriteSheet("/resumo.png");
		foto = sheet.getSprite(0, 0, 500, 500);
		pistas=new ArrayList<String>();

	}

	public void render(Graphics g) {
		g.drawImage(foto, 150, 50, 700, 700, null);
		for(int i=0;i<pistas.size();i++) {
			g.setColor(new Color(0,0,0));
			g.drawString(pistas.get(i), 260, 200+(16*i));
		}
	}
}
