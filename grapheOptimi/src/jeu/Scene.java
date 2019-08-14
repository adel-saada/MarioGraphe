package jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import audio.Audio;
import fordFulkerson.Graphe;
import fordFulkerson.TestGraphes;
import objets.Bowser;
import objets.Brique;
import objets.BriqueBloque;
import objets.Etoile;
import objets.Objet;
import objets.Peach;
import objets.Yoshi;
import personnages.Mario;


//** Accessible par toutes les autres classes, contient le moteur de l'application. **//
//** Gère la partie graphique **//


@SuppressWarnings("serial")
public class Scene extends JPanel { 

	public static final char[] ALPHABET = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	public Graphe graphe;
	public Graphe graphe2;
	public Etoile etoileA;
	public Etoile etoileB;
	public Etoile etoileC;
	public Etoile etoileD;
	public Etoile etoileE;
	public Etoile etoileF;
	public Etoile etoileG;
	
	public String texteArreteAC ="";
	public String texteArreteAB ="";
	public String texteArreteCF ="";
	public String texteArreteBF ="";
	public String texteArreteBD ="";
	public String texteArreteDE ="";
	public String texteArreteDG ="";
	public String texteArreteFE ="";
	public String texteArreteFG ="";
	public String texteArreteEG = "";
	
	public String texteNumeroEtoileA = "";
	public String texteNumeroEtoileB = "";
	public String texteNumeroEtoileC = "";
	public String texteNumeroEtoileD = "";
	public String texteNumeroEtoileE = "";
	public String texteNumeroEtoileF = "";
	public String texteNumeroEtoileG = "";

	
	public List<String> listeChemin = new ArrayList<>();

	public static  String message = "";
	public static String messageExplication ="";
	private ImageIcon icoFond;
	private Image imgFond1;
	
	private int xFond1; // abscisse du coin supérieur gauche img fond.
	private int yFond1; // ordonne du coin supérieur gauche img fond
	
	private int dx; // deplacement fond horizontal
	private int dy; // deplacement fond vertical
	
	private int xPos;
	private int yPos;
	
	public boolean commencementGraphe = false;
	public boolean preparationGraphe = false;
	public boolean gestionChemin = false;
	public boolean blocage = false;
	public boolean victoire = false;
	public boolean victoireMessage = false;

	
	public int numeroEtoileSelection = -1;

	public Mario mario;
	
	public Bowser bowser = new Bowser(500,0);
	
	public Yoshi yoshi;
	
	public List<Objet> listeObjets;
	
	public BriqueBloque briqueAC;
	public BriqueBloque briqueAB;
	public BriqueBloque briqueCF;
	public BriqueBloque briqueBF;
	public BriqueBloque briqueBD;
	public BriqueBloque briqueDE;
	public BriqueBloque briqueDG;
	public BriqueBloque briqueFE;
	public BriqueBloque briqueFG;
	public BriqueBloque briqueEG;
	
	public int maxFlowOptimise = 0;
		
	Audio musiqueFond;
	
	//** CONSTRUCTEUR **//
	public Scene() {
		super(); 	

				
		graphe = new Graphe(7); // création d'un graphe à 7 sommets
		graphe2 = new Graphe(7); // deuxième graphe identique
		
		
		System.out.println("Voulez vous jouez à la partie EXEMPLE ou la partie ALEATOIRE ? ");
		System.out.println("Saisir 0 pour la partie EXEMPLE");
		System.out.println("Saisir 1 pour la partie ALEATOIRE");
		
		
		Saisie sc = new Saisie();
		
		int saisieChoixOption = sc.readInt();
		
		while(saisieChoixOption != 0 && saisieChoixOption !=1) {
			System.out.println("Veuillez saisir la valeur 1 ou 0 :");
			saisieChoixOption = sc.readInt();
		}
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println("-------------DEMARRAGE JEU MARIO GRAPHE FORD FULKERSON--------------");
		System.out.println("_________________________>By Adel et Rabah<_________________________");
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
		System.out.println("--------------------------SCENARIO :--------------------------------");
		System.out.println("-------BOWSER A CAPTURé PEACH ET YOSHI VOUS DEMANDE DE L'AIDE-------");
		System.out.println();
		System.out.println("----TROUVER L'OPTIMISATION IDEALE POUR LA SAUVER ET BATTRE BOWSER----");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("\n");
		
		if(saisieChoixOption == 0) {
			/*---------------------------------EXEMPLE GRAPHE-------------------------------*/
		// il faudra ajouter une valeur aléatoire pour la capacité.
			graphe.ajouterArete(0, 1, 4);
			graphe.ajouterArete(0, 2, 9);
			graphe.ajouterArete(1, 3, 2);
			graphe.ajouterArete(1, 5, 3);
			graphe.ajouterArete(2, 5, 8);
			graphe.ajouterArete(3, 6, 10);
			graphe.ajouterArete(3, 4, 4);
			graphe.ajouterArete(4, 6, 1);
			graphe.ajouterArete(5, 4, 1);
			graphe.ajouterArete(5, 6, 3);
		// le deuxième graphe servira à obtenir le max flow optimisé. (il est directement généré par l'algo)
			graphe2.ajouterArete(0, 1, 4);
			graphe2.ajouterArete(0, 2, 9);
			graphe2.ajouterArete(1, 3, 2);
			graphe2.ajouterArete(1, 5, 3);
			graphe2.ajouterArete(2, 5, 8);
			graphe2.ajouterArete(3, 6, 10);
			graphe2.ajouterArete(3, 4, 4);
			graphe2.ajouterArete(4, 6, 1);
			graphe2.ajouterArete(5, 4, 1);
			graphe2.ajouterArete(5, 6, 3);	
		} else if (saisieChoixOption == 1) {
			/*---------------------------------ALEATOIRE GRAPHE-------------------------------*/			
			graphe.ajouterArete(0, 1, genererEntierAleatoire(1, 15) );
			graphe.ajouterArete(0, 2, genererEntierAleatoire(1, 15) );
			graphe.ajouterArete(1, 3, genererEntierAleatoire(1, 15) );
			graphe.ajouterArete(1, 5, genererEntierAleatoire(1, 15) );
			graphe.ajouterArete(2, 5, genererEntierAleatoire(1, 15) );
			graphe.ajouterArete(3, 6, genererEntierAleatoire(1, 15) );
			graphe.ajouterArete(3, 4, genererEntierAleatoire(1, 15) );
			graphe.ajouterArete(4, 6, genererEntierAleatoire(1, 15) );
			graphe.ajouterArete(5, 4, genererEntierAleatoire(1, 15) );
			graphe.ajouterArete(5, 6, genererEntierAleatoire(1, 15) );
			
	        for (int i = 0; i < 7; i++) {
	            for (int j = 0; j < 7	; j++) {
	                graphe2.getAdj()[i][j] = graphe.getAdj()[i][j];
	            }
	        }
	        
		}
		
		this.maxFlowOptimise = 0;
		TestGraphes testGraphe = new TestGraphes();
		this.maxFlowOptimise = (int) testGraphe.FordFulkerson(graphe2, 0, 6);
		
		/* thème de Mario Bros qui se lance au démarrage du jeu */
		musiqueFond = new Audio("/audio/themeMarioBros.wav");
		
		musiqueFond.playContinue(); // joue la musique 100 fois

		demarrage();
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Clavier());
		
		Thread chronoEcran = new Thread(new Chrono());
		chronoEcran.start();
		
	}
	
	//** GETTERS **//
	
	public int getDx() {return dx;}
	public int getDy() {return dy;}
	public int getxPos() {return xPos;}
	public int getyPos() {return yPos;}

	//** SETTERS **//
	public void setDx(int dx) {this.dx = dx;}
	public void setDy(int dy) {this.dy = dy;}
	public void setxPos(int xPos) {this.xPos = xPos;}
	public void setyPos(int yPos) {this.yPos = yPos;}
	
	//** METHODES **//
	
	public void deplacementFondHorizontal() {
		this.xPos = this.xPos + this.dx;
		this.xFond1 = this.xFond1 - this.dx;			
	}

	public void deplacementFondVertical() {
		this.yPos = this.yPos + this.dy;
		this.yFond1 = this.yFond1 - this.dy;			
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics g2 = (Graphics2D) g;
		
	
		message="";
		
		
		if(preparationGraphe) {
			afficherGraphe();
			preparationGraphe = false;
		}
		
		if(gestionChemin) {
			if(numeroEtoileSelection == graphe.getNbrSommets()-1) {
				// action à faire lorsqu'on arrive à la sortie.
				demarrage();
			} else {
				gererChemin();				
			}
			gestionChemin = false;
		}
		
		numeroEtoileSelection = -1;
		
		for(Objet obj : listeObjets) {
			this.mario.contact(obj);
		}

		this.deplacementFondHorizontal();
		this.deplacementFondVertical();
		
		for(Objet obj : listeObjets) {
			obj.deplacement();
		}
		
		g2.drawImage(imgFond1,xFond1,yFond1,null); // dessin img fond
		g2.drawImage(mario.marche("mario", 25),this.mario.getX(),this.mario.getY(),null); // code provisoire
		
		for(Objet obj : listeObjets) {
			g2.drawImage(obj.getImg(), obj.getX(), obj.getY(), null);
		}
		
		if(!victoire) {
			bowser = new Bowser(400, 14);
			g2.drawImage(bowser.getImg(), bowser.getX()+xFond1, bowser.getY()+yFond1, null);			
		} else {
			Main.scene.messageExplication = "BRAVO, OPTIMISATION PARFAITE";
			if(victoireMessage) {
				System.out.println("----------------------------------------------------------");
				System.out.println("--------------------VOUS AVEZ GAGNE !---------------------");
				System.out.println("--------------------BOWSER EST MORT !---------------------");
				System.out.println("--PEACH VOUS INVITE A BOIRE UN VERRE DANS SON CHATEAU !!--");
				victoireMessage = false;
				listeObjets.remove(yoshi);
			}
		}

		
		g2.setColor(Color.WHITE);
		g2.drawString(message, mario.getX(), mario.getY()+60);
		
		g2.setColor(Color.GREEN);
		g2.drawString(texteArreteAC,120+xFond1+185, 305+yFond1+20);
		g2.drawString(texteArreteAB,560+xFond1+185, 305+yFond1+20);
		g2.drawString(texteArreteCF,120+xFond1+185, 398+yFond1+20);
		g2.drawString(texteArreteBF,440+xFond1+185, 385+yFond1+20);
		g2.drawString(texteArreteBD,560+xFond1+185, 445+yFond1+20);
		g2.drawString(texteArreteDE,440+xFond1+185, 505+yFond1+20);					
		g2.drawString(texteArreteDG,560+xFond1+180, 595+yFond1+20);
		g2.drawString(texteArreteFE,230+xFond1+185, 505+yFond1+20);
		g2.drawString(texteArreteFG,120+xFond1+185, 595+yFond1+20);
		g2.drawString(texteArreteEG,340+xFond1+185, 555+yFond1+20);
		
		g2.setColor(Color.BLACK);
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		g2.setFont(font);
		g2.drawString(texteNumeroEtoileA, 340+xFond1+197, 300+yFond1+10);
		g2.drawString(texteNumeroEtoileB, 560+xFond1+197, 375+yFond1+10);
		g2.drawString(texteNumeroEtoileC, 120+xFond1+197, 350+yFond1+10);
		g2.drawString(texteNumeroEtoileD, 560+xFond1+197, 500+yFond1+10);
		g2.drawString(texteNumeroEtoileE, 340+xFond1+197, 510+yFond1+10);
		g2.drawString(texteNumeroEtoileF, 120+xFond1+197, 435+yFond1+10);
		g2.drawString(texteNumeroEtoileG, 340+xFond1+197, 600+yFond1+10);


		g2.setColor(Color.ORANGE);
		font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		g2.setFont(font);
		g2.drawString(messageExplication, mario.getX()-220, mario.getY()-200);
	}
	
	
	public void gererChemin() {
		List<Integer> voisins = graphe.voisins(numeroEtoileSelection);
		
		int numeroEtoile = numeroEtoileSelection;

		
		String texte ="";
		int i=1;
		List<Integer> listeEntiersValide = new ArrayList<>();
		
		for (Integer voisin : voisins) {
			if(graphe.getFlots(numeroEtoile, voisin) < graphe.getCapacite(numeroEtoile, voisin) ) {
				texte += i+" : "+ALPHABET[numeroEtoile]+"-"+ALPHABET[voisin]+"[ "+graphe.getFlots(numeroEtoile, voisin)+"/"+graphe.getCapacite(numeroEtoile,voisin)+" ]   ";							
				listeEntiersValide.add(i);
			}
			++i;
		}
		
		Etoile etoileD = getEtoile(numeroEtoile);
		Saisie saisie = new Saisie();
		System.out.println("----------------------------------------------------------");
		System.out.println(texte);
		if(texte == "" || listeEntiersValide.isEmpty()) {	
			System.out.println("il n'y a plus de chemin possible pour cette étoile, saisir touche 'G' pour revenir");
			blocage = true;
			return; 
		} 
		System.out.println("Choisir chemin : ");
		int numero = saisie.readInt();
		while(!listeEntiersValide.contains(numero)) {
			System.out.println("Veuillez resaisir le bon chemin proposé : ");
			numero = saisie.readInt();
		}
		
		if(numero <= voisins.size()) {
			int numeroVoisin = voisins.get(numero-1);
			System.out.println("Vous avez choisi le chemin de "+ALPHABET[numeroEtoile]+" vers "+ALPHABET[numeroVoisin]);	
			
			// suppression de l'étoile
			listeObjets.remove(getEtoile(numeroEtoile));
			supprimerTexteNumeroEtoile(numeroEtoile);
			
			listeChemin.add(numeroEtoile+"-"+numeroVoisin);
			

			Etoile etoileVoisin = getEtoile( voisins.get(numero-1));
			
			// suppression de la brique bloquant la route
			BriqueBloque briqueBloque=null;
			for (Objet objet : listeObjets) {
				if(objet.getClass().getName().equals("objets.BriqueBloque")) {
					briqueBloque = (BriqueBloque) objet;
					if(briqueBloque.getLettre() != null) {
						if(briqueBloque.getLettre().equals(""+ALPHABET[numeroEtoile]+ALPHABET[voisins.get(numero-1)])) {
							break;
						}						
					}
				}
				briqueBloque = null;
			}
			
			if(briqueBloque != null) {
				listeObjets.remove(briqueBloque);
			}
			
			
		
		} else {
			System.out.println("numéro invalide !");
		}

	}
	

	public Etoile getEtoile(int numero) {
		Etoile etoile = null;
		switch(numero) {
		case 0 :
			etoile = etoileA;
			break;
		case 1 :
			etoile = etoileB;
			break;
		case 2 :
			etoile = etoileC;
			break;
		case 3 :
			etoile = etoileD;
			break;		
		case 4 :
			etoile = etoileE;
			break;
		case 5 :
			etoile = etoileF;
			break;	
		case 6 :
			etoile = etoileG;
			break;			
		}
		return etoile;
	}
	
	public void supprimerTexteNumeroEtoile(int numero) {
		switch(numero) {
		case 0 :
			texteNumeroEtoileA = "";
			break;
		case 1 :
			texteNumeroEtoileB = "";
			break;
		case 2 :
			texteNumeroEtoileC = "";
			break;
		case 3 :
			texteNumeroEtoileD = "";
			break;		
		case 4 :
			texteNumeroEtoileE = "";
			break;
		case 5 :
			texteNumeroEtoileF = "";
			break;	
		case 6 :
			texteNumeroEtoileG = "";
			break;			
		}
	}
	
	/**
	 * Méthode qui charge le décor 
	 * Sert aussi à remettre à zero la position du jeu pour que Mario
	 * puisse recommencer son déplacement.
	 */
	public void demarrage() {


		if(!listeChemin.isEmpty() && blocage==false) {

			String[] sommets = listeChemin.get(0).split("[-]");
			int sommetDebut0 = Integer.parseInt(sommets[0]);
			int sommetFin0 = Integer.parseInt(sommets[1]);
			
			
			int min = graphe.getAdj()[sommetDebut0][sommetFin0] - graphe.getFlots(sommetDebut0, sommetFin0);
			// boucle pour récupérer le flot minimum du chemin parcouru.
			for (String chaine : listeChemin) {
				
				sommets = chaine.split("[-]");
				
				int sommetDebut = Integer.parseInt(sommets[0]);
				int sommetFin = Integer.parseInt(sommets[1]);

				int capacite = graphe.getAdj()[sommetDebut][sommetFin] - graphe.getFlots(sommetDebut, sommetFin);
				if(capacite < min) {
					min = capacite;
				}
			}
			// boucle pour modifier le flot des arêtes du chemin parcouru.
			for (String chaine : listeChemin) {
				sommets = chaine.split("[-]");
				int sommetDebut = Integer.parseInt(sommets[0]);
				int sommetFin = Integer.parseInt(sommets[1]);
								
				graphe.getFlots()[sommetDebut][sommetFin] = graphe.getFlots()[sommetDebut][sommetFin] + min;
				
				
			}
			graphe.setMaxFlow(graphe.getMaxFlow()+min);
			listeChemin = new ArrayList<>();
			
		}
		
		texteArreteAC ="";
		texteArreteAB ="";
		texteArreteCF ="";
		texteArreteBF ="";
		texteArreteBD ="";
		texteArreteDE ="";
		texteArreteDG ="";
		texteArreteFE ="";
		texteArreteFG ="";
		texteArreteEG = "";
		
		texteNumeroEtoileA = "";
		texteNumeroEtoileB = "";
		texteNumeroEtoileC = "";
		texteNumeroEtoileD = "";
		texteNumeroEtoileE = "";
		texteNumeroEtoileF = "";
		texteNumeroEtoileG = "";
		
		
		
		this.xFond1 = -50;
		this.yFond1 = 0;
		this.dx = 0;
		this.dy = 0;
		
		this.xPos = 0;
		this.yPos = 0;
		
		
		icoFond = new ImageIcon(getClass().getResource("/images/mapMario2.png"));
		this.imgFond1 = this.icoFond.getImage();
		
	
		
		
		mario = new Mario(280, 255);
		yoshi = new Yoshi(430,255);
		bowser = new Bowser(600,0);

		listeObjets = new ArrayList<>();
		
		listeObjets.add(yoshi);
				
		listeObjets.add(new Peach(720,230));
		
		// colonne de brique
		for(int i=0; i< 35; i++) {
			/* colonnes briques contours */
			listeObjets.add(new Brique(220+i*15,625)); // colonne basse
			listeObjets.add(new Brique(220+i*15,635)); // colonne basse  (2eme couche)	
			if(i<33) {
				listeObjets.add(new Brique(220+i*15,235)); // colonne haute
				listeObjets.add(new Brique(220+i*15,225)); // colonne haute  (2eme couche)							
			}
			if(i< 27) {
				listeObjets.add(new Brique(220,235+i*15)); // colonne gauche
				listeObjets.add(new Brique(230,235+i*15)); // colonne gauche (2eme couche) 	
				if(i<23) {
					listeObjets.add(new Brique(720,280+i*15)); // colonne droite							
					listeObjets.add(new Brique(730,280+i*15)); // colonne droite (2eme couche)
				}
			}
		}		
		
		for (int i = 0; i < 16; i++) {
			/* colonnes briques interieur */
			listeObjets.add(new Brique(220+i*15,280));	// 1ere colonne
			listeObjets.add(new Brique(220+i*15,290)); // 1ere colonne (2eme couche)

			listeObjets.add(new Brique(500+i*15,280));	// 1ere colonne
			listeObjets.add(new Brique(500+i*15,290)); // 1ere colonne (2eme couche)
		}
		
	
	}

	/**
	 *  Met en place le graphe
	 *  positionne les étoiles et les murs qui les séparent
	 */
	public void afficherGraphe() {
		etoileA = new Etoile(340,300,0); // sommet A
		etoileB = new Etoile(560,375,1); // sommet B
		etoileC = new Etoile(120,350,2); // sommet C
		etoileD = new Etoile(560,500,3); // sommet D
		etoileE = new Etoile(340,510,4); // sommet E
		etoileF = new Etoile(120,435,5); // sommet F
		etoileG = new Etoile(340,600,6); // sommet G
		

        // ajout des sommets dans la liste des objets
		listeObjets.add(etoileA);
		listeObjets.add(etoileB);
		listeObjets.add(etoileC);
		listeObjets.add(etoileD);
		listeObjets.add(etoileE);
		listeObjets.add(etoileF);
		listeObjets.add(etoileG);
		
		
		texteArreteAC = graphe.getFlots(etoileA.getNumero(),etoileC.getNumero() )+"/"+graphe.getCapacite(etoileA.getNumero(),etoileC.getNumero() );
		texteArreteAB = graphe.getFlots(etoileA.getNumero(),etoileB.getNumero() )+"/"+graphe.getCapacite(etoileA.getNumero(),etoileB.getNumero() );
		texteArreteCF = graphe.getFlots(etoileC.getNumero(),etoileF.getNumero() )+"/"+graphe.getCapacite(etoileC.getNumero(),etoileF.getNumero() );
		texteArreteBF = graphe.getFlots(etoileB.getNumero(),etoileF.getNumero() )+"/"+graphe.getCapacite(etoileB.getNumero(),etoileF.getNumero() );
		texteArreteBD = graphe.getFlots(etoileB.getNumero(),etoileD.getNumero() )+"/"+graphe.getCapacite(etoileB.getNumero(),etoileD.getNumero() );
		texteArreteDE = graphe.getFlots(etoileD.getNumero(),etoileE.getNumero() )+"/"+graphe.getCapacite(etoileD.getNumero(),etoileE.getNumero() );
		texteArreteDG = graphe.getFlots(etoileD.getNumero(),etoileG.getNumero() )+"/"+graphe.getCapacite(etoileD.getNumero(),etoileG.getNumero() );
		texteArreteFE = graphe.getFlots(etoileF.getNumero(),etoileE.getNumero() )+"/"+graphe.getCapacite(etoileF.getNumero(),etoileE.getNumero() );
		texteArreteFG = graphe.getFlots(etoileF.getNumero(),etoileG.getNumero() )+"/"+graphe.getCapacite(etoileF.getNumero(),etoileG.getNumero() );
		texteArreteEG = graphe.getFlots(etoileE.getNumero(),etoileG.getNumero() )+"/"+graphe.getCapacite(etoileE.getNumero(),etoileG.getNumero() );


		// création des murs séparant les étoiles
		for (int i = 0; i < 27; i++) {
			/* grands murs interieur */
			listeObjets.add(new Brique(150+i*15,330));	
			listeObjets.add(new Brique(150+i*15,340)); 
			listeObjets.add(new Brique(150+i*15,350)); 
			listeObjets.add(new Brique(150+i*15,360)); 
			listeObjets.add(new Brique(150+i*15,460)); 
			listeObjets.add(new Brique(150+i*15,475)); 
			listeObjets.add(new Brique(150+i*15,490)); 

			if(i< 12) {
				// petits murs intérieurs à gauche
				listeObjets.add(new Brique(150+i*15,370)); 
				listeObjets.add(new Brique(150+i*15,385)); 
				listeObjets.add(new Brique(150+i*15,400)); 
				listeObjets.add(new Brique(150+i*15,415)); 
				listeObjets.add(new Brique(150+i*15,530)); 
				listeObjets.add(new Brique(150+i*15,545)); 
				listeObjets.add(new Brique(150+i*15,560)); 
				listeObjets.add(new Brique(150+i*15,575)); 
				// petits murs intérieurs à droite
				listeObjets.add(new Brique(375+i*15,415)); 
				listeObjets.add(new Brique(375+i*15,430)); 
				listeObjets.add(new Brique(375+i*15,445)); 
				listeObjets.add(new Brique(375+i*15,530)); 
				listeObjets.add(new Brique(375+i*15,530)); 
				listeObjets.add(new Brique(375+i*15,545)); 
				listeObjets.add(new Brique(375+i*15,560)); 
				listeObjets.add(new Brique(375+i*15,575)); 
			}
		}
		

		texteNumeroEtoileA = "A";
		texteNumeroEtoileB = "B";
		texteNumeroEtoileC = "C";
		texteNumeroEtoileD = "D";
		texteNumeroEtoileE = "E";
		texteNumeroEtoileF = "F";
		texteNumeroEtoileG = "G";
		
		
		
		if(graphe.getFlots(0, 1) >= graphe.getCapacite(0, 1) ) {
			briqueAB = new BriqueBloque(560,305,"briqueRouge.png","AB");
		}else {
			briqueAB = new BriqueBloque(560,305,"briqueBleue.png","AB");
		}
		if(graphe.getFlots(0, 2) >= graphe.getCapacite(0, 2) ) {
			briqueAC = new BriqueBloque(120,305,"briqueRouge.png","AC");
		}else {
			briqueAC = new BriqueBloque(120,305,"briqueBleue.png","AC");			
		}		
		if(graphe.getFlots(1, 3) >= graphe.getCapacite(1, 3) ) {
			briqueBD = new BriqueBloque(560,445,"briqueRouge.png","BD");
		}else {
			briqueBD = new BriqueBloque(560,445,"briqueBleue.png","BD");
		}
		if(graphe.getFlots(1, 5) >= graphe.getCapacite(1, 5) ) {
			briqueBF = new BriqueBloque(440,385,"briqueRouge.png","BF");
		}else {
			briqueBF = new BriqueBloque(440,385,"briqueBleue.png","BF");
		}
		if(graphe.getFlots(2, 5) >= graphe.getCapacite(2, 5) ) {
			briqueCF = new BriqueBloque(120,398,"briqueRouge.png","CF");
		}else {
			briqueCF = new BriqueBloque(120,398,"briqueBleue.png","CF");
		}
		if(graphe.getFlots(3, 4) >= graphe.getCapacite(3, 4) ) {
			briqueDE = new BriqueBloque(440,505,"briqueRouge.png","DE");
		}else {
			briqueDE = new BriqueBloque(440,505,"briqueBleue.png","DE");
		}
		if(graphe.getFlots(3, 6) >= graphe.getCapacite(3, 6) ) {
			briqueDG = new BriqueBloque(560,595,"briqueRouge.png","DG");
		}else {
			briqueDG = new BriqueBloque(560,595,"briqueBleue.png","DG");
		}
		if(graphe.getFlots(4, 6) >= graphe.getCapacite(4, 6) ) {
			briqueEG = new BriqueBloque(340,555,"briqueRouge.png","EG");
		}else {
			briqueEG = new BriqueBloque(340,555,"briqueBleue.png","EG");
		}
		if(graphe.getFlots(5, 4) >= graphe.getCapacite(5, 4) ) {
			briqueFE = new BriqueBloque(230,505,"briqueRouge.png","FE");
		}else {
			briqueFE = new BriqueBloque(230,505,"briqueBleue.png","FE");
		}
		if(graphe.getFlots(5, 6) >= graphe.getCapacite(5, 6) ) {
			briqueFG = new BriqueBloque(120,595,"briqueRouge.png","FG");
		}else {
			briqueFG = new BriqueBloque(120,595,"briqueBleue.png","FG");
		}
		
	
		listeObjets.add(briqueAC);
		listeObjets.add(briqueAB);
		listeObjets.add(briqueCF);
		listeObjets.add(briqueBF);
		listeObjets.add(briqueBD);
		listeObjets.add(briqueDE);
		listeObjets.add(briqueDG);
		listeObjets.add(briqueFE);
		listeObjets.add(briqueFG);
		listeObjets.add(briqueEG);

	}
	
	/**
	 * Permet de générer un nombre aléatoire compris entre borneInf et borneSup
	 * @param borneInf
	 * @param borneSup
	 * @return
	 */
	public int genererEntierAleatoire(int borneInf, int borneSup){
		   Random random = new Random();
		   int nb;
		   nb = borneInf+random.nextInt(borneSup-borneInf);
		   return nb;
		}
}
