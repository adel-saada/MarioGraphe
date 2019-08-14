package jeu;

import javax.swing.JFrame;

public class Main { 

	public static Scene scene;
	
	public static void main(String[] args) {
		

		// Création de la fenetre de l'application
		JFrame fenetre = new JFrame("Mario graphe");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(600,700);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(false);
		fenetre.setAlwaysOnTop(true);
		
		// instanciation de l'objet scene
		scene = new Scene();
	
		fenetre.getContentPane().add(scene); // on associe la scène à la fenetre de l'appli
		fenetre.setVisible(true);

	}
}
