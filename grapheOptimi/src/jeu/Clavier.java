package jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import personnages.Position;

public class Clavier implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Main.scene.mario.setMarche(true);
			Main.scene.mario.setPosition(Position.DROITE);	
			Main.scene.setDx(1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Main.scene.mario.setMarche(true);
			Main.scene.mario.setPosition(Position.GAUCHE);
			Main.scene.setDx(-1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Main.scene.mario.setMarche(true);
			Main.scene.mario.setPosition(Position.BAS);
			Main.scene.setDy(1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			Main.scene.mario.setMarche(true);
			Main.scene.mario.setPosition(Position.HAUT);
			Main.scene.setDy(-1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(Main.scene.commencementGraphe == true) {
				Main.scene.listeObjets.remove(Main.scene.yoshi);
				Main.scene.preparationGraphe = true;
				Main.scene.commencementGraphe = false;
			}
			if(Main.scene.numeroEtoileSelection != -1) {
				Main.scene.gestionChemin = true;
			}	
		} else if(e.getKeyCode() == KeyEvent.VK_G && Main.scene.blocage) {
				Main.scene.demarrage();
				Main.scene.gestionChemin = false;
				Main.scene.blocage = false;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		Main.scene.mario.setMarche(false);
		Main.scene.setDx(0);
		Main.scene.setDy(0);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}



}
