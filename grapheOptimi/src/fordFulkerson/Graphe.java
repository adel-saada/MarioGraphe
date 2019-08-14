package fordFulkerson;


import java.util.List;

import jeu.Main;

import java.util.ArrayList;
 
	
public class Graphe {
	private static final char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
    private int nbrSommets;
    private int[][] adj;
    
    private int[][] flots;
    
    private int maxFlow = 0;
 
    /**
     * Constructeur de graphe
     * 	- Indique le nombre de sommet du graphe
     * 	- Met à 0 le tableau adjacents
     * @param nbrSommets
     */
    public Graphe(int nbrSommets) {
    	this.nbrSommets = nbrSommets;
    	// initilisation du tableau adjacents
    	adj = new int[nbrSommets][nbrSommets];
    	for (int i = 0; i < nbrSommets; i++) {
    		for (int j = 0; j < nbrSommets; j++) {
    			adj[i][j] = 0;
    		}
    	}
    	// initilisation du tableau flots [uniquement pour animation]
    	flots = new int[nbrSommets][nbrSommets];
    	for (int i = 0; i < nbrSommets; i++) {
    		for (int j = 0; j < nbrSommets; j++) {
    			flots[i][j] = 0;
    		}
    	}
    }
    
    /**
     * 
     * @return le nombre de sommets du graphe
     */
    public int getNbrSommets() {
        return nbrSommets;
    }
 
    /**
     * 
     * @return le tableau des adjacences
     */
    public int[][] getAdj() {
        return adj;
    }
    
    /**
     * 
     * @return le tableau des flots
     */
    public int[][] getFlots() {
        return flots;
    }
    
    
    /* Sert uniquement pour l'animation */
    public int getCapacite(int i, int j) {
    	if(i < adj.length && j<adj[1].length) {
    		return this.adj[i][j];
    	}
    	return -1;
    }
    
    /* Sert uniquement pour l'animation */
    public int getFlots(int i, int j) {
    	if(i< flots.length && j<flots[i].length) {
    		return this.flots[i][j];
    	}
    	return -1;
    }
    
//    public void setFlots(int i, int j, int delta) throws GrapheException {
//    	if(i< flots.length && j<flots[i].length) {
//    		if(this.adj[i][j] > 0) {
//    			if(this.flots[i][j]+delta <= this.adj[i][j]) {
//    				this.flots[i][j] += delta;
//    			} else {
//    				throw new GrapheException("dépassement par rapport à la capacité");
//    			}
//    		} else {
//    			throw new GrapheException("Il n'y a pas d'arrête");
//    		}
//    	} else {
//    		throw new GrapheException("Depassement tableau");
//    	}
//    }
   

    /**
     * Ajoute une arête en indiquant la valeur de capacité entre 2 sommets
     * dans le tableau d'adjacence.
     * @param i (le numéro du sommet de départ de l'arête)
     * @param j (le numéro du sommet de fin de l'arête)
     * @param capacite (la capacité de l'arête)
     */
    public void ajouterArete(int i, int j, int capacite) {
        adj[i][j] = capacite;
    }
 
    /**
     * Supprime une arête en indiquant la valeur 0 au tableau 
     * [ligne sommet D Arete] [ligne sommet A Arete]
     * @param i (le numéro du sommet de départ de l'arête)
     * @param j (le numéro du sommet de fin de l'arête)
     * 
     * détail : i-----(0)------j  
     * signification : pas d'existence d'une arête entre sommet i et j
     */
    public void supprimerArete(int i, int j) {
        adj[i][j] = 0;
    }
 
    /**
     * Vérifie l'existence d'une arête entre un sommet i et un sommet j
     * @param i (le numéro du sommet de départ de l'arête)
     * @param j (le numéro du sommet de fin de l'arête)
     * 
     * détail : SI  i--------(0)---------j 
     * ALORS il n'y a pas d'arête entre sommet i et j
     * @return un booléen qui vérifie l'existence.
     */
    public boolean aUneArete(int i, int j) {
        if (adj[i][j] != 0) {
            return true;
        }
        return false;
    }
 
    /**
     * Retourne la liste des sommets voisins du sommet en paramètre.
     * @param sommet (pour lequel on cherche les voisins)
     * @return une liste d'entiers
     */
    public List<Integer> voisins(int sommet) {
        List<Integer> listeArretes = new ArrayList<Integer>();
        for (int i = 0; i < nbrSommets; i++)
            if (aUneArete(sommet, i))
                listeArretes.add(i);
        return listeArretes;
    }
 
    /**
     * Affiche les voisins de chaque sommet
     */
    public void afficherGraphe() {
        for (int i = 0; i < nbrSommets; i++) {
            List<Integer> listeArretes = voisins(i);
            System.out.print(ALPHABET[i] + ": ");
            for (int j = 0; j < listeArretes.size(); j++) {
                System.out.print(ALPHABET[listeArretes.get(j)] + " ");
            }
            System.out.println();
        }
    }

	public int getMaxFlow() {
		return maxFlow;
	}

	public void setMaxFlow(int maxFlow) {
		this.maxFlow = maxFlow;
	}
    

}