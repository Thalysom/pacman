package jogo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Wall {
	
	private int x, y;
	private Image wallPicture;
	private int largura, altura;
	
	public Wall(int x, int y){
		ImageIcon referencia = new ImageIcon("images\\wall.jpg");
		wallPicture = referencia.getImage();
		this.x = x * 15;
		this.y = y * 15;
		
		this.largura = wallPicture.getWidth(null);
		this.altura = wallPicture.getHeight(null);
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(wallPicture, getX(), getY(), null);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getWallPicture() {
		return wallPicture;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

}
