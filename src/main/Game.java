package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import elementos.Card;
import elementos.Cenario;
import elementos.Entrada;
import elementos.Icone;
import elementos.Jornal;
import elementos.Locais;
import elementos.Painel;
import elementos.Resumo;
import elementos.Suspeitos;

public class Game extends Canvas implements Runnable, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	public static JFrame frame;// gera a plataforma que o jogo ira rodar
	private Thread thread;// cria o timer do jogo ou tick porem nao instancia, apenas reserva a memoria
	private BufferedImage image; // para uso geral
	private boolean isRunning = true;// flag de jogo rodando
	public static final int WIDTH = 1000;// largura da plataforma
	public static final int HIGHT = 1000;// altura da plataforma
	public static final int SCALE = 1;// escala da plataforma, forma mais eficar de redimencionar o tamanho da tela
	public static Cenario cena;
	public static int cont = 0;
	public static Painel painel;
	public static Icone policia;
	public static Icone sherlock;
	public static Icone watson;
	public static ArrayList<Icone> lista;
	public static Resumo resumo;
	public static Locais locais;
	public static Suspeitos suspeitos;
	public static Jornal jornal;
	public static Entrada entrada;
	public static boolean verResumo = false, verLocais = false, verSuspeitos = false, verJornal = false, fim=false, win=false, verEntrada=true;
	public static String mensagem;
	public Game() {
		addMouseListener(this);// para que eventos de mouse funcionem
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HIGHT * SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HIGHT, BufferedImage.TYPE_INT_RGB);
	}

	public static void main(String[] args) {
		Game game = new Game();// instancia o jogo pelo contrutor
		lista = new ArrayList<Icone>();
		cena = new Cenario();
		painel = new Painel();
		policia = new Icone("/guarda.png", 75, 222, 3, WIDTH - 80,60, "policia") ;
		sherlock = new Icone("/sherlock.png", 75, 90, 3, WIDTH - 80, 60 + 232, "sherlock");
		watson = new Icone("/watson.png", 75, 111, 3, WIDTH - 80, 60 + 100 + 233, "watson");
		lista.add(policia);
		lista.add(sherlock);
		lista.add(watson);
		resumo = new Resumo();
		locais = new Locais();
		suspeitos = new Suspeitos();
		jornal=new Jornal();
		entrada=new Entrada();
		

		game.start();// inicia o jogo

	}

	public void tick() {
		cont++;
		entrada.tick();
		if (cont >= 20) {
			cont = 0;
			policia.tick();
			sherlock.tick();
			watson.tick();
		}
		if (fim){
			Resumo.pistas.clear();
			sherlock.ponteiro=0;
			watson.ponteiro=0;
			policia.ponteiro=0;				
				
			}
		}

	

	public void render() {
		BufferStrategy bs = getBufferStrategy();// conjunto de buffer para otimizar a renderização
		if (bs == null) {// caso esteja vazio
			this.createBufferStrategy(3);// criamos um com 3 espaços podem ser 2 ou 3
			return;
		}
		Graphics g = image.getGraphics();
		
		g = bs.getDrawGraphics();
		// tela de limpeza, foi criado um retângulo com o tamanho da tela em preto.
		g.setFont(new Font( "Serif", Font.BOLD, 16 ) );
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HIGHT);
		// aqui vai os métodos render() de cada objeto ou lista de objetos
		cena.render(g);
		painel.render(g);
		policia.render(g);
		sherlock.render(g);
		watson.render(g);
		if (verResumo) {
			resumo.render(g);
		}
		if (verJornal) {
			jornal.render(g);
		}
		if (verLocais) {
			locais.render(g);
		}
		if (verSuspeitos) {
			suspeitos.render(g);
		}
		if(fim) {
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, HIGHT/2, WIDTH, 300);
			g.setColor(new Color(255, 255, 255));
			g.drawString(mensagem, 20, 600);
		}
		if(verEntrada) {
			entrada.render(g);
		}

		bs.show();
	}

	public synchronized void start() {

		thread = new Thread(this);// estancia o time do game
		isRunning = true;// liga a flag para que o jogo possa fluir
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;// abaixa a flag de jogo rodando
		try {
			thread.join();// nao sei
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void initFrame() {// aqui se constroe a plataforma com as preferencias
		frame = new JFrame("Game #1");// cria o game... "game #1 "é o titulo do game
		frame.add(this);// nao entendi esta linha de comando
		frame.setResizable(false);// impede o redirecionamento da janela
		frame.pack();// mostrar a janela
		frame.setLocationRelativeTo(null);// coloca a janela no centro da tela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// garante que ao clicar em fechar ele encerre o aplicativo
		frame.setVisible(true);// ao inicializar fica visivel imediatamente
	}

	@Override
	public void run() {// verifica a flag de jogo rodando
		long lastTime = System.nanoTime();// pega a hora do sisitema em nanosegundos
		double amountOfTicks = 30.0;// ainda nao sei
		double ns = 1000000000 / amountOfTicks;// nao sei
		double delta = 0;// flag de estouro de timer
		double timer = System.currentTimeMillis();
		requestFocus();
		while (isRunning) {// condicional para verificação do game rodando
			long now = System.nanoTime();// pega a hora do sisitema em nano time
			delta += (now - lastTime) / ns;// soma as diferenças de tempo entre now w lastTime e divide por nano
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				delta--;// baixa o flag do delta porem mantem o erro acumulando e corrigindo o
						// syncronismo
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				// System.out.println("fps: "+frames);
				timer += 1000;
			}
		}
		stop();// caso ocorra algum problema e o programa saia do loop necessario desligar a
				// threds
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			verEntrada=false;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) { 
		verEntrada=false;
		if (fim) {
			fim = false;
			cena.ponteiro = 5;
			verSuspeitos = false;
			verEntrada = true;
		}
		
		Rectangle rec = new Rectangle(e.getX(), e.getY(), 20, 20);
		for (int i = 0; i < lista.size(); i++) {
			if (rec.intersects(new Rectangle(lista.get(i).X, lista.get(i).Y, lista.get(i).L, lista.get(i).A))) {
				lista.get(i).clicado(e.getX(), e.getY());
			}
		}
		if (rec.intersects(new Rectangle(80, 870, 100, 100))) {
			// pista
			verResumo = !verResumo;
			verLocais = false;
			verSuspeitos = false;
			verJornal = false;
		}
		if (rec.intersects(new Rectangle(320, 870, 100, 100))) {
			// deslocar
			verLocais = !verLocais;
			verResumo = false;
			verSuspeitos = false;
			verJornal = false;
		}
		if (rec.intersects(new Rectangle(560, 870, 100, 100))) {
			// acusar
			verSuspeitos = !verSuspeitos;
			verResumo = false;
			verLocais = false;
			verJornal = false;

		}
		if (rec.intersects(new Rectangle(800, 870, 100, 100))) {
			// acusar
			verJornal = !verJornal;
			verSuspeitos = false;
			verResumo = false;
			verLocais = false;

		}

		if (verSuspeitos) {
			
			if (rec.intersects(new Rectangle(300, 130, 100, 100))) {
				// irene
				System.out.println("irene");
				mensagem="Não, Irene estava envolvida, porém nunca conseguiu entrar no Palácio.";
				fim=true;
			}
			if (rec.intersects(new Rectangle(410, 180, 120, 150))) {
				// eisen
				System.out.println("eisen");
				mensagem="Não, ele estava na Rússia neste dia.";
				fim=true;
			}
			if (rec.intersects(new Rectangle(580, 100, 120, 150))) {
				System.out.println("lestrad");
				mensagem="Não, Embora seja muito burro!!";
				fim=true;
				// lestrad
			}
			if (rec.intersects(new Rectangle(600, 250, 120, 150))) {
				System.out.println("muriarte");
				// muriarte
				mensagem="Elementar meu caro Watson, Muriarte esta com a garota!";
				fim=true;
				win=true;
			}
			if (rec.intersects(new Rectangle(330, 350, 120, 150))) {
				System.out.println("draeger");
				// Dredger
				mensagem="Não, Ele estava envolvido, porém o prendemos e ele não estava com a Princesa.";
				fim=true;
			}
			if (rec.intersects(new Rectangle(500, 350, 120, 150))) {
				System.out.println("watson");
				mensagem="Não, Whatson colaborou com o sequestro, mas foi por acidente, o panfleto estava preso em sua cartola e acabou deixando na cozinha.";
				// watson
				fim=true;
			}
		}
		if (verLocais) {
			if (rec.intersects(new Rectangle(230, 150, 150, 130))) {
				System.out.println("cozinha");
				cena.ponteiro = 2;
				atualizaicones();
				verLocais = false;

			}
			if (rec.intersects(new Rectangle(680, 260, 100, 100))) {
				System.out.println("beco");
				cena.ponteiro = 0;
				atualizaicones();
				verLocais = false;
			}
			if (rec.intersects(new Rectangle(680, 150, 110, 100))) {
				System.out.println("Ponte");
				cena.ponteiro = 4;
				atualizaicones();
				verLocais = false;
			}
			if (rec.intersects(new Rectangle(550, 420, 100, 100))) {
				System.out.println("Entrada");
				cena.ponteiro = 3;
				atualizaicones();
				verLocais = false;
			}
			if (rec.intersects(new Rectangle(200, 500, 170, 150))) {
				System.out.println("Jardim");
				cena.ponteiro = 1;
				atualizaicones();
				verLocais = false;

			}
		}

	}

	public void atualizaicones() {
		policia.verificanivel();
		sherlock.verificanivel();
		watson.verificanivel();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
