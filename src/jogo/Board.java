package jogo;

import java.awt.Color;
import java.lang.reflect.Array;

import javax.swing.JFrame;

public class Board extends JFrame { 
	
	public Board(){
		add(new Level());
		setTitle("Pac-Man V1.0 - by Thalysom");
		setLocationRelativeTo(null);
		setSize(500, 535);
		setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Board();
	}

}
