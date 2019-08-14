package objets;

import java.awt.Image;

import javax.swing.ImageIcon;

import jeu.Main;

public class Bowser {
	
	//** VARIABLES **//
	private int largeur, hauteur; // dimensions de l'objet
	private int x, y;
	private ImageIcon ico;
	private Image img;
	
	
	public Bowser(int x, int y) {
		this.x = x;
		this.y = y;
		this.largeur = 280;
		this.hauteur = 210;
		this.setIco(new ImageIcon(getClass().getResource("/images/bowser4.gif")));
		this.setImg(this.getIco().getImage());
	}
	
	//** GETTERS **//
	public int getLargeur() {return largeur;}
	public int getHauteur() {return hauteur;}
	public int getX() {return x;}
	public int getY() {return y;}
	public ImageIcon getIco() {return ico;}
	public Image getImg() {return img;}

	

	//** SETTERS **//
	public void setLargeur(int largeur) {this.largeur = largeur;}
	public void setHauteur(int hauteur) {this.hauteur = hauteur;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setIco(ImageIcon ico) {this.ico = ico;}
	public void setImg(Image img) {this.img = img;}
	
	//** METHODES **//
	
	public void deplacement() {
		this.x = this.x - Main.scene.getDx();
		this.y = this.y - Main.scene.getDy();
	}
	
}
