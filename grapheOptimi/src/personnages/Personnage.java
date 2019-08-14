package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import jeu.Main;
import objets.Objet;

public class Personnage {

	
	//** VARIABLES **//
	private int largeur, hauteur; // dimension personnage
	private int x, y; // position personnage
	private boolean marche; // vrai quand pers marche
//	private boolean versDroite; // vrai quand pers tourné vers la droite
//	private boolean versBas; // vrai quand pers tourné vers le bas
	private Position position;
	public int compteur; // compteur pas du personnage
	 
	
	//** CONSTRUCTEUR **//
	
	public Personnage(int x, int y, int largeur, int hauteur) {
		
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.compteur = 0;
		this.marche = false;
//		this.versDroite = false;
//		this.versBas = false;
		this.setPosition(Position.DROITE);
	}

	//**GETTERS **//
	public int getLargeur() {return largeur;}
	public int getHauteur() {return hauteur;}
	public int getX() {return x;}
	public int getY() {return y;}
	public boolean isMarche() {return marche;}
//	public boolean isVersDroite() {return versDroite;}
//	public boolean isVersBas() {return versBas;}
	public int getCompteur() {return compteur;}
	
	public Position getPosition() {return position;}


	//**SETTERS **//
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setMarche(boolean marche) {this.marche = marche;}
//	public void setVersDroite(boolean versDroite) {this.versDroite = versDroite;}
//	public void setVersBas(boolean versBas) {this.versBas = versBas;}
	public void setPosition(Position position) {this.position = position;}
	public void setCompteur(int compteur) {this.compteur = compteur;}
	
	//** METHODES **//
	public Image marche(String nom, int frequence) {
		
		String str;
		ImageIcon ico;
		Image img;
		
		if(this.marche == false) {  // ( || Main.scene.getxPos() <= 0 || Main.scene.getyPos() <= 0
			if(position == Position.DROITE) {
				str = "/images/" + nom + "ArretDroite.png";
			} else if(position == Position.GAUCHE) {
				str = "/images/" + nom + "ArretGauche.png";
			} else if(position == Position.BAS) {
				str = "/images/" + nom + "ArretBas.png";
			} else {
				str = "/images/" + nom + "ArretHaut.png";
			}
		} else {
			this.compteur++;
			if(this.compteur / frequence == 0) {
				if(position == Position.DROITE) {
					str = "/images/" + nom + "ArretDroite.png";
				} else if(position == Position.GAUCHE) {
					str = "/images/" + nom + "ArretGauche.png";
				} else if(position == Position.BAS) {
					str = "/images/" + nom + "ArretBas.png";
				} else {
					str = "/images/" + nom + "ArretHaut.png";
				}
			} else {
				if(position == Position.DROITE) {
					str = "/images/" + nom + "MarcheDroite.png";
				} else if(position == Position.GAUCHE) {
					str = "/images/" + nom + "MarcheGauche.png";
				} else if(position == Position.BAS) {
					str = "/images/" + nom + "MarcheBas.png";
				} else {
					str = "/images/" + nom + "MarcheHaut.png";
				}
				
				if(this.compteur == 2* frequence) {
					this.compteur = 0;
				}
			}
		}
		// affichage de l'image du personnage
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}

	
	// Détection contact à droite
	public boolean contactAvant(Objet objet) {
		if(this.x + this.largeur + 10 < objet.getX()|| this.x + this.largeur > objet.getX() + 10
				|| this.y + this.hauteur <= objet.getY() || this.y + this.hauteur >= objet.getY() + objet.getHauteur()) {
			return false;
		}
		return true;			
	}
	
	// Détection contact à gauche
	public boolean contactArriere(Objet objet) {
		if(this.x >= objet.getX() + objet.getLargeur() + 5 || this.x + this.largeur < objet.getX() + objet.getLargeur()+ 5||
				this.y + this.hauteur <= objet.getY() || this.y >= objet.getY() + objet.getHauteur()) {
			return false;
		}
		return true;			
	}	
	
	// Détection contact en dessous
	public boolean contactDessous(Objet objet) {
		if(this.x + this.largeur < objet.getX()  || this.x > objet.getX() + objet.getLargeur() 
				|| this.y + this.hauteur < objet.getY() || this.y + this.hauteur > objet.getY()  ) {
			return false;
		}
		return true;
	}
	
	// Détection contact au-dessus
	public boolean contactDessus(Objet objet) {
		if(this.x + this.largeur < objet.getX() || this.x  > objet.getX() + objet.getLargeur() 
				|| this.y < objet.getY() + objet.getHauteur()-8|| this.y > objet.getY() + objet.getHauteur()) {
			return false;
		}
		return true;			
	}	
	
	public boolean proche(Objet objet) {
		if( (this.x > objet.getX() - 10 && this.x < objet.getX() + objet.getLargeur() + 10)
			|| (this.x + this.largeur > objet.getX() - 10 && this.x + this.largeur < objet.getX() + objet.getLargeur()+10)) {
			
			return true;
		}
		return false;
	}
	
	
}
