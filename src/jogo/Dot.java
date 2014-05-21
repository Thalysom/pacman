package jogo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Dot {
	
	private int x, y;
	private Image dotPicture;
	private int largura, altura;
	
	
	public Dot(int x, int y){
		ImageIcon referencia = new ImageIcon("images\\dot.jpg");
		dotPicture = referencia.getImage();
		this.x = (x * 15) + 4;
		this.y = (y * 15) + 4;
		
		this.largura = dotPicture.getWidth(null);
		this.altura = dotPicture.getHeight(null);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Image getDotPicture() {
		return dotPicture;
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(dotPicture, getX(), getY(), null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

}
