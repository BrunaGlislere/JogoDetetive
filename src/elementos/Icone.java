package elementos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ferramentas.SpriteSheet;
import main.Game;

public class Icone {
	public SpriteSheet sheet;
	public BufferedImage foto[];
	public boolean visivel = true, alerta = true, cardvisivel = false;
	public int L, A, X, Y, ponteiro = 0;
	public String pistas[] = { "" };
	public Card card;
	public int nivel = 0;
	public int locais[] = { 0 };
	public String tipo;

	public Icone(String caminho, int largura, int altura, int quantidade, int x, int y, String tipo) {
		sheet = new SpriteSheet(caminho);
		foto = new BufferedImage[quantidade];
		this.tipo = tipo;
		lendo();
		L = largura;
		A = altura;
		X = x;
		Y = y;
		for (int i = 0; i < quantidade; i++) {
			foto[i] = sheet.getSprite(i * largura, 0, largura, altura);
		}
		card = new Card();

	}

	public void tick() {
		if (alerta) {
			ponteiro++;
			if (ponteiro > 2) {
				ponteiro = 1;
			}
		} else {
			ponteiro = 0;
		}

	}

	public void render(Graphics g) {
		if (visivel ) {

			g.drawImage(foto[ponteiro], X, Y, L, A, null);
			

		}
		if (cardvisivel) {
			card.render(g);
			g.setColor(new Color(255,255,255));
			String[] texto=pistas[nivel].split(";");
			for(int i =0;i<texto.length;i++) {
				g.drawString(texto[i], 250, 200+(i*18));
			}
			}
	}

	public void clicado(int x, int y) {
		alerta = false;
		cardvisivel = !cardvisivel;
	}

	public void lendo() {
		int[] sherlock = { 0, 3, 1,2,1 };
		int[] watson = { 3,2,3 };
		int[] policia = { 3,1,2,1,2,1,2,1 };
		if (tipo == "sherlock") {
			
			String pistas[] = { "Ol�, eu sou o Sherlock Holms ; Minhas dedu��es s�o 100% assertivas ; Fui designado para solucionar o desaparecimento; da princesa Maud.; Temos uma lista de susupeitos e voc� pode acusar em qualquer momento,; mas cuidado pois � o meu nome que esta em jogo!; Se acusar o suspeito errado iniciamos do zero.; Voc� poder� andar pelos locais do mapa e quando tiver uma nova pista; os icones ficar�o piscando; se a dica for quente ir� para o resumo como inferencia. " ,"Um homem viu uma carroagem sendo pilotada; no sentido do palacio.; A testemunha jura ter sido Muriarte.; Precisamos ver onde tudo come�ou, na entrada do pal�cio","Irene esteve na entrada e foi detida.; Apreenderam um carimbo com a data de hoje."," Um copeiro havia visto um panfleto com a informa��o de que o Ilusionista; iria se apresentar no jardim hoje. Por�m ele estava na R�ssia segundo o jornal,; ent�o foi at� o jardim para ver quem estaria se passando; pelo ilusionista. ; Chegando l� encontrou uma carroagem,; Muriarte estava instalando sinalizadores em um caminho para a carroagem.; Um tumulto se instalou na entrada do palacio e acabei me ; distraindo e sai do jardim.; O panfleto estava na cozinha.","Dredger foi encontrato e disse que havia perdido o panfleto com a data falsa ;na cozinha, ent�o foi at�; a entrada do palacio, para pegar o carimbo da Irene e carimbar um outro panfleto que havia ; encontrado no pal�cio.; Voltou � cozinha e convenceu a princesa Maud a encontrar o ilusionista; no jardim.","A carroagem j� havia sa�do e precisa passar pela entrada para conseguir sair."};
			locais = sherlock;
			this.pistas =  pistas;
		}
		if (tipo == "watson") {
			
			String pistas[] = { "Ol�, eu sou o Watson.; Minhas dedu��es s�o 90% assertivas,;"," Uma carroagem preta entrou no local e foi para o jardim"," Um copeiro viu um outro copeiro desajeitado com um panfleto na m�o,; ele falava com a princesa, e ela; foi sentido ao jardim."," O porteiro viu a carroagem e muriarte estava nela."  };
			locais = watson;
			this.pistas = pistas;
		}
		if (tipo == "policia") {
			
			String pistas[] = { "Ol�, eu sou o detetive Lestrad; N�s acertamos 10% das dedu��es e sim, temos muito orgulho disto. "," Watson foi visto pela manh�, estava indo ver a Rainha, e ent�o deixou um panfleto; velho na cozinha."," Uma cartomante disse que iremos morar em Marte,; um tal Elon Musk nos colocar� em um l�pis gigante que pega fogo; e iremos direto para Marte. Achei in�til para o caso."," Encontramos uma f�brica de ch� nova a caminho daqui,; muito suspeito tendo visto que; todos n�s conhecemos todas as casas de ch� da cidade"," um homem foi visto com um cachorro e ele nao estava usando coleira."," Um m�sico tocou na hora do ch� de sua alteza real,; por�m suas m�os estavam tr�mulas,; muito susupeito n�o acha?;"," Mandamos um policial inspecionar o rio Tamisa em busca de alguma pista; ele encontrou um par de pneus novos.; Muito suspeito.","Hoje na troca da guarda o sol estava mais forte que o normal."," Um homem consegue contar at� 10 sem precisar de papel e caneta; pode usar os dedos.", " Um Louco que n�o diz nada com nada nos disse:;'Nada com nada.', talvez ajude na investiga��o."," Se estiver lendo isso � porque j� est� avacalhando, n�o ta mais jogando s�rio."};
			locais = policia;
			this.pistas = pistas;
		}

	}

	public void verificanivel() {
		System.out.println(tipo+" nivel:"+ Cenario.ponteiro+" local: "+locais[nivel]);
		if (locais[nivel] == Cenario.ponteiro) {
			if (nivel + 1 < locais.length) {
				nivel++;
			}

			alerta = true;
		}
		if(tipo=="sherlock") {
			if(nivel==1 && Cenario.ponteiro==0) {
				Resumo.pistas.add("Muriarte estava na carroagem, logo seguiu para o pal�cio.");
			}
			if(nivel==2 && Cenario.ponteiro==3) {
				Resumo.pistas.add("Irene esteve na entrada, por�m nao entrou no Pal�cio");
			}
			if(nivel==3 && Cenario.ponteiro==1) {
				Resumo.pistas.add("Muriarte enteve no jardim.");
			}
			if(nivel==4 && Cenario.ponteiro==2) {
				Resumo.pistas.add("Dredger esteve com a Princesa na cozinha.");
				Resumo.pistas.add("Dredger induziu a Princesa a ir para o Jardim.");
				Resumo.pistas.add("Se a Princesa foi ao Jardim ela encontrou Muriarte e entrou na carro�a");
			}
			if(nivel==5 && Cenario.ponteiro==1) {
				Resumo.pistas.add("O sequestro ocorreu no jardim.");
				Resumo.pistas.add("A princesa foi ao jardim.");
			}
			
		}
		if(tipo=="watson") {
			if(nivel==1 && Cenario.ponteiro==3) {
				Resumo.pistas.add("Dredger esteve com a Princesa na cozinha.");
			}
			if(nivel==2 && Cenario.ponteiro==2) {
				Resumo.pistas.add("Dredger esteve com a Princesa na cozinha.");
				Resumo.pistas.add("A princesa foi ao jardim.");
			}
			if(nivel==3 && Cenario.ponteiro==3) {
				Resumo.pistas.add("A carroagem saiu do pal�cio.");
			}
			
		}
		if(tipo=="policia") {
			if(nivel==1 && Cenario.ponteiro==1) {
				Resumo.pistas.add("Watson esteve no palacio pela manh� e deixou um panfleto");
			}
		}

	}
}
