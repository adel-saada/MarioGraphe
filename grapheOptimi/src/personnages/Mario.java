package personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import fordFulkerson.Graphe;
import fordFulkerson.TestGraphes;
import jeu.Main;
import jeu.Scene;
import objets.Etoile;
import objets.Objet;
import objets.Peach;

public class Mario extends Personnage {

	
	//** VARIABLES **//
	private Image imgMario;
	private ImageIcon icoMario;
	
	//** CONSTRUCTEUR **//
	public Mario(int x, int y) {
		super(x, y, 13, 16);
		icoMario = new ImageIcon(getClass().getResource("/images/marioArretDroite.png"));
		this.imgMario = this.icoMario.getImage();
	}
	
	//** GETTERS **//
	public Image getImgMario() {return imgMario; }
	
	//** SETTERS **//
	
	//** METHODES **//
	
	public void contact(Objet objet) {
		// contact horizontal
		
		if( super.contactAvant(objet)&& this.getPosition() == Position.DROITE 
				|| super.contactArriere(objet)&& this.getPosition() == Position.GAUCHE
					|| super.contactDessus(objet) && this.getPosition() == Position.HAUT 
						|| super.contactDessous(objet) && this.getPosition() == Position.BAS)
					 {
			Main.scene.setDx(0);
			Main.scene.setDy(0);
			this.setMarche(false);
			if(objet.getClass().getName().equals("objets.Yoshi")) {
				
				int maxFlow = Main.scene.graphe.getMaxFlow();
				
				if(maxFlow == Main.scene.maxFlowOptimise) {
					Main.scene.message = "Bravo";
					Main.scene.victoire = true;
					Main.scene.victoireMessage = true;
				} else {
					Main.scene.message = "Appuie sur Entree pour lancer le graphe";
					Main.scene.commencementGraphe = true;					
				}
				
				
				
			}else if (objet.getClass().getName().equals("objets.Etoile")) {
				Etoile etoile = (Etoile)objet;
				int numeroEtoile = etoile.getNumero();
				Main.scene.message = "Je suis l'étoile "+ Scene.ALPHABET[numeroEtoile] ;
				Main.scene.numeroEtoileSelection = numeroEtoile;
			} else if(objet.getClass().getName().equals("objets.Peach")) {
				

				int maxFlow = Main.scene.graphe.getMaxFlow();
				String messagePeach = "Flow max actuel : "+maxFlow;
				if(maxFlow == 0) {
					messagePeach += " TU ES NUL !";
				} else if(maxFlow == Main.scene.maxFlowOptimise) {
					messagePeach += ", FELICITATION! MON HEROS";		
				} else {
					messagePeach += ", CONTINUE COMME CA !";					
				}
				Main.scene.message = messagePeach;
				
	

			}
			
		}
	}
	
}
