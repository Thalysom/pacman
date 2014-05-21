package jogo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Pacman implements Desenho, TecladoListener {

	private int x, y, lastMoveX, lastMoveY;
	private int dx, dy;
	private int altura, largura;
	private Image currentPacman;
	private Image pacman1;
	private Image pacman2up;
	private Image pacman3up;
	private Image pacman4up;
	private Image pacman2down;
	private Image pacman3down;
	private Image pacman4down;
	private Image pacman2left;
	private Image pacman3left;
	private Image pacman4left;
	private Image pacman2right;
	private Image pacman3right;
	private Image pacman4right;
	private int positionLeft = 0;
	private int positionRigth = 0;
	private int positionUp = 0;
	private int positionDown = 0;

	public Pacman(int x, int y) {
		loadImages();
		this.x = x * 15;
		this.y = y * 15;		
		doAnimationPacman();
		this.largura = this.currentPacman.getWidth(null);
		this.altura = this.currentPacman.getHeight(null);
	}

	private void loadImages() {
		ImageIcon referencia = new ImageIcon("images\\pacman.jpg");
		pacman1 = referencia.getImage();
		pacman2up = new ImageIcon("images\\pacmanUp.jpg").getImage();
		pacman3up = new ImageIcon("images\\pacmanUp2.jpg").getImage();
		pacman4up = new ImageIcon("images\\pacmanUp3.jpg").getImage();
		pacman2down = new ImageIcon("images\\pacman.jpg").getImage();
		pacman3down = new ImageIcon("images\\pacmanDown2.jpg").getImage();
		pacman4down = new ImageIcon("images\\pacmanDown3.jpg").getImage();
		pacman2left = new ImageIcon("images\\pacman.jpg").getImage();
		pacman3left = new ImageIcon("images\\pacmanLeft2.jpg").getImage();
		pacman4left = new ImageIcon("images\\pacmanLeft3.jpg").getImage();
		pacman2right = new ImageIcon("images\\pacman.jpg").getImage();
		pacman3right = new ImageIcon("images\\pacmanRigth2.jpg").getImage();
		pacman4right = new ImageIcon("images\\pacmanRigth3.jpg").getImage();
	}

	public Image getCurrentPacman() {		
		switch (getLastMoveX()) {
		//Esquerda
		case -1:{
			if (positionLeft == 3) {
				currentPacman = pacman4left;
				positionLeft = 4;
			}			
			if (positionLeft == 2) {
				currentPacman = pacman3left;
				positionLeft = 3;
			}			
			if (positionLeft == 3) {
				currentPacman = pacman4left;
				positionLeft = 4;
			}	
			if (positionLeft == 4) {
				currentPacman = pacman3left;
				positionLeft = 5;
			}
			if (positionLeft == 5) {
				currentPacman = pacman3left;
				positionLeft = 2;
			}
			break;
		}
		//Direita
		case 1:{
			currentPacman = pacman3right;
			break;
		}	
		default:
			break;
		}		
		
		switch (getLastMoveY()) {
		//Cima
		case -1:{
			currentPacman = pacman3up;
			break;
		}
		//Baixo
		case 1:{
			currentPacman = pacman3down;
			break;
		}
		default:
			break;
		}
		
		return this.currentPacman;		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLastMoveX() {
		return lastMoveX;
	}

	public int getLastMoveY() {
		return lastMoveY;
	}

	private void doAnimationPacman() {
		currentPacman = pacman1;
	}

	public void move() {
		x += dx; // 1 e 481
		y += dy; // 1 e 499
		
		lastMoveX = dx;
		lastMoveY = dy;

		if (this.x < 1) {
			x = 1;
		}

		if (this.x > 481) {
			x = 481;
		}

		if (this.y < 1) {
			y = 1;
		}

		if (this.y > 499) {
			y = 499;
		}
		
		//Teletransporte direita para esquerda
		if ((x > 467) && (y >= 222) && (y <= 228)) {
			x = 16;
			y = 225;
		}
		
		//Teletransporte esquerda para direita
		if ((x < 13) && (y >= 222) && (y <= 228)) {
			x = 466;
			y = 225;
		}		
	}
	
	public void stop(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public void repaint(Graphics g) {
		g.drawImage(getCurrentPacman(), getX(), getY(), null);
	}

	@Override
	public void keyUp(KeyType keyType) {
		if (keyType == keyType.PRESSED) {
			dy = -1;
			dx = 0;
		}
	}

	@Override
	public void keyDown(KeyType keyType) {
		if (keyType == keyType.PRESSED) {
			dy = 1;
			dx = 0;
		}
	}

	@Override
	public void keyLeft(KeyType keyType) {
		if (keyType == keyType.PRESSED) {
			dx = -1;
			dy = 0;
		}
	}

	@Override
	public void keyRight(KeyType keyType) {
		if (keyType == keyType.PRESSED) {
			dx = 1;
			dy = 0;
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

}
