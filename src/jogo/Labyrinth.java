package jogo;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
	
	
	private int totalLinhaArray = 34;
	private int totalColunaArray = 33;
	
	//Array é linha x coluna
	private int[][] labyrinth = new int [totalLinhaArray][totalColunaArray];
	
	private Wall wall;
	private List<Dot> dots;
	private Dot dot;
	private List<Wall> labyrinthWall;
	private int limiteHorizontal = 32;
	private int limiteVertical = 32;

	public Labyrinth(int level){
		initArrayLabyrinth();
		
		labyrinthWall = new ArrayList<Wall>();
		dots = new ArrayList<Dot>();
		
		switch (level) {
		case 1:
			loadLabyrinthOne();
			loadDots();
			break;
		default:
			break;
		}
	}
	
	public List<Wall> getLabyrinthWall() {
		return labyrinthWall;
	}
	
	public List<Dot> getDots() {
		return dots;
	}
	
	private void loadDots(){
		for (int y = 0; y < totalLinhaArray; y++) {
			for (int x = 0; x < totalColunaArray; x++) {
				//Não coloca pontos na primeira e última linha
				if ((labyrinth[y][x] == 0) && (y > 0) && (y < totalLinhaArray - 1)) {
					labyrinth[y][x] = 2;
					dots.add(new Dot(x,y));
				}
			}
		}
	}

	private void loadLabyrinthOne(){
		//Bordas
		makeLine(Position.Vertical, 0, 0, 1, limiteVertical);
		makeLine(Position.Vertical, limiteHorizontal, limiteHorizontal, 1, limiteVertical);
		makeLine(Position.Horizontal, 1, limiteHorizontal, 1, limiteHorizontal - 1);
		makeLine(Position.Horizontal, 1, limiteHorizontal, limiteVertical, limiteHorizontal - 1);
		
		makeLine(Position.Vertical, 16, 16, 1, 5);
		
		//Linha 1 de quadrados
		makeRectangle(2, 6, 3, 3);
		makeRectangle(8, 14, 3, 3);
		makeRectangle(18, 24, 3, 3);
		makeRectangle(26, 30, 3, 3);
		
		//Linha 2 de quadrados
		makeRectangle(2, 6, 7, 2);		
		makeRectangle(8, 9, 7, 8);
		makeRectangle(10, 14, 10, 2);
		//T
		makeRectangle(11, 21, 7, 2);
		makeLine(Position.Vertical, 16, 16, 9, 11);
		//
		makeRectangle(18, 22, 10, 2);
		makeRectangle(23, 24, 7, 8);
		makeRectangle(26, 30, 7, 2);		
		
		//Quadrados laterais e meio
		makeRectangle(1, 6, 10, 5);
		makeRectangle(26, 31, 10, 5);
		makeLine(Position.Horizontal, 11, 21, 13, 13);
		makeLine(Position.Vertical, 11, 11, 14, 17);
		makeLine(Position.Vertical, 12, 12, 14, 17);
		makeLine(Position.Horizontal, 11, 21, 17, 17);
		makeLine(Position.Vertical, 21, 21, 14, 17);
		makeLine(Position.Vertical, 20, 20, 14, 17);
		
		makeRectangle(1, 6, 16, 5);
		makeRectangle(26, 31, 16, 5);
		
		makeRectangle(8, 9, 16, 5);
		makeRectangle(23, 24, 16, 5);
		
		//T baixo
		makeRectangle(11, 21, 19, 2);
		makeLine(Position.Vertical, 16, 16, 21, 23);
		//T baixo baixo
		makeRectangle(11, 21, 25, 2);
		makeLine(Position.Vertical, 16, 16, 27, 30);
		
		makeLine(Position.Horizontal, 2, 6, 22, 22);
		makeLine(Position.Vertical, 6, 6, 23, 26);
		
		makeRectangle(8, 14, 22, 2);
		makeRectangle(18, 24, 22, 2);
		
		makeLine(Position.Horizontal, 26, 30, 22, 22);
		makeLine(Position.Vertical, 26, 26, 23, 26);
		
		makeRectangle(1, 4, 24, 3);
		makeRectangle(2, 14, 28, 3);
		makeRectangle(8, 9, 25, 3);
		
		makeRectangle(28, 31, 24, 3);
		makeRectangle(18, 30, 28, 3);
		makeRectangle(23, 24, 25, 3);
		
	}
	
	private void makeLine(Position position, int colIni, int colFim, int linIni, int linFim){
		if (position == Position.Horizontal){
			for (int i = colIni; i <= colFim; i++) {
				labyrinthWall.add(new Wall(i,linIni));
				labyrinth[linIni][i] = 1;
			}
		} else if(position == Position.Vertical){
			for (int i = linIni; i <= linFim; i++) {
				labyrinthWall.add(new Wall(colIni,i));
				labyrinth[i][colIni] = 1;
			}
		}
	}
	
	private void makeRectangle(int colIni, int colFim, int linIni, int largura){
		for (int z = 0; z < largura; z++) {	
			for (int x = colIni; x <= colFim; x++) {
				labyrinthWall.add(new Wall(x,linIni + z));
				labyrinth[linIni + z][x] = 1;
			}
		}
	}
	
	private void initArrayLabyrinth(){
		for (int y = 0; y < totalLinhaArray; y++) {
			for (int x = 0; x < totalColunaArray; x++) {
				labyrinth[y][x] = 0;
			}			
		} 
	}
	
}
