package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Level extends JPanel implements Runnable {

	private boolean emJogo;
	private boolean ganhou = false;
	private int pontos = 0;
	private Pacman pacman;
	private Wall wall;
	private Dot dot;
	private Labyrinth labirinto;
	Thread thread;
	List<Desenho> desenhos = new ArrayList<Desenho>();
	List<TecladoListener> tecladoListeners = new ArrayList<TecladoListener>();

	public Level() {
		setDoubleBuffered(true);
		setFocusable(true);
		setBackground(Color.black);
		
		//Carrega o labirinto
		labirinto = new Labyrinth(1);		
		
		Thread thread = new Thread(this);
		pacman = new Pacman(18,24);
		desenhos.add(pacman);
		tecladoListeners.add(pacman);
		addKeyListener(new TecladoAdapter());

		thread.start();
		emJogo = true;
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D graficos = (Graphics2D) g;

		if (emJogo) {
			for (int i = 0; i < labirinto.getLabyrinthWall().size(); i++) {
				Wall wl = labirinto.getLabyrinthWall().get(i);
				graficos.drawImage(wl.getWallPicture(), wl.getX(), wl.getY(), this);
			}		
			
			for (int x = 0; x < labirinto.getDots().size(); x++) {
				Dot dt = labirinto.getDots().get(x);
				graficos.drawImage(dt.getDotPicture(), dt.getX(), dt.getY(), this);
			}
			
			//Escreve o placar na tela
			graficos.setColor(Color.white);
			graficos.setFont(new Font("SansSerif", Font.BOLD, 15));
			graficos.drawString("Pontos: " + pontos, 1, 13);
			
			for (Desenho desenho : desenhos) {
				desenho.repaint(graficos);
			}			
		} else if (ganhou == true) {
			graficos.setColor(Color.white);
			graficos.setFont(new Font("SansSerif", Font.BOLD, 40));
			graficos.drawString("Você Ganhou!", 1, 13);
		}
	}

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {

			sendKey(KeyType.PRESSED, e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			sendKey(KeyType.RELEASED, e);
		}

		private void sendKey(KeyType keyType, KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP: {
				for (TecladoListener listener : tecladoListeners) {
					listener.keyUp(keyType);
				}
				break;
			}
			case KeyEvent.VK_DOWN: {
				for (TecladoListener listener : tecladoListeners) {
					listener.keyDown(keyType);
				}
				break;
			}
			case KeyEvent.VK_LEFT: {
				for (TecladoListener listener : tecladoListeners) {
					listener.keyLeft(keyType);
				}
				break;
			}
			case KeyEvent.VK_RIGHT: {
				for (TecladoListener listener : tecladoListeners) {
					listener.keyRight(keyType);
				}
				break;
			}
			}
		}
	}

	@Override
	public void run() {
		while (emJogo) {
			checkColision();
			pacman.move();
			
			if (pontos == 351) {
				emJogo = false;
				ganhou = true;
			}
			
			repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {

			}
		}
	}
	
	public void checkColision(){
		Rectangle wallForm;
		Rectangle dotForm;
		Rectangle pacmanForm = pacman.getBounds();
		
		//Verifica para não atravessar a parede
		for (int i = 0; i < labirinto.getLabyrinthWall().size(); i++) {
			Wall tempWall = labirinto.getLabyrinthWall().get(i);
			wallForm = tempWall.getBounds();

			if (pacmanForm.intersects(wallForm)) {
				if (pacman.getLastMoveX() == 1){
					pacman.stop(tempWall.getX() - 13, pacman.getY());
				} else if (pacman.getLastMoveX() == -1){
					pacman.stop(tempWall.getX() + 13, pacman.getY());
				} else if (pacman.getLastMoveY() == 1){
					pacman.stop(pacman.getX(), tempWall.getY() - 13);
				} else if (pacman.getLastMoveY() == -1){
					pacman.stop(pacman.getX(), tempWall.getY() + 13);
				}
			}
		}
		
		//Faz desaparecer o dot da tela e marca pontuação
		for (int i = 0; i < labirinto.getDots().size(); i++) {
			Dot tempDot = labirinto.getDots().get(i);
			dotForm = tempDot.getBounds();
			
			if (pacmanForm.intersects(dotForm)) {
				labirinto.getDots().remove(tempDot);
				pontos ++;
			}
			
		}
		
	}

}
