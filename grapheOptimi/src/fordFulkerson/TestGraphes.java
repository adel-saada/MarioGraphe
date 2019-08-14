package fordFulkerson;

import java.util.LinkedList;

public class TestGraphes {
 
	private static final char[] ALPHABET = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};


 
    public static float FordFulkerson(Graphe g, int source, int puit) {
    	
        if (source == puit) {
            return 0;
        }
        
        int sommets = g.getNbrSommets();
 
        // creation graphe résiduel
        Graphe rg = new Graphe(sommets);
        for (int i = 0; i < sommets; i++) {
            for (int j = 0; j < sommets; j++) {
                rg.getAdj()[i][j] = g.getAdj()[i][j];
            }
        }
 
        // rempli par BFS pour stocker le chemin
        int parent[] = new int[sommets];
 
        float maxFlow = 0; // valeur max flow 
 
        // Tant qu'un chemin existe de la source vers le puit
        while (bfs(rg, source, puit, parent)) {
            // pour stocker le flux du chemin
            float fluxChemin = Float.MAX_VALUE;
 
            // trouver flow max de chemin rempli par BFS
            for (int i = puit; i != source; i = parent[i]) {
                int j = parent[i];
                fluxChemin = Math.min(fluxChemin, rg.getAdj()[j][i]);
            }
 
            // mettre à jour les capacités du graphe residuel
            // reverse edges along the path  
            for (int i = puit; i != source; i = parent[i]) {
                int j = parent[i];
                rg.getAdj()[j][i] -= fluxChemin;
                rg.getAdj()[i][j] += fluxChemin;
                
            }
 
            // Add path flow to max flow
            maxFlow += fluxChemin;
        }
 
        return maxFlow;
    }
 
    /* BFS : Breadth First Search ou Algorithme de parcours en largeur */
    public static boolean bfs(Graphe rg, int source, int dest, int parent[]) {
        // tableau pour stocker les sommets visités.
        boolean[] sommetsVisites = new boolean[rg.getNbrSommets()];
        for (int i = 0; i < rg.getNbrSommets(); i++)
            sommetsVisites[i] = false;
 
        LinkedList<Integer> q = new LinkedList<Integer>(); // variante d'une file
 
        // visit source
        q.add(source);
        sommetsVisites[source] = true;
        parent[source] = -1;
 
        // boucle sur tous les sommets
        while (!q.isEmpty()) {
            int i = q.poll();
            // vérifier les voisins du sommet i
            for (Integer j : rg.voisins(i)) {
                // Si pas visité et valeur positive alors visité
                if ((sommetsVisites[j] == false) && (rg.getAdj()[i][j] > 0)) {
                    q.add(j);
                    sommetsVisites[j] = true;
                    parent[j] = i;
                }
            }
        }
 
        // retourne un booleen qui nous dit si nous sommes arrivé à destination
        return sommetsVisites[dest];
    }
 
}
