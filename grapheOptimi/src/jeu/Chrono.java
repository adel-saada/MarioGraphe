package jeu;

public class Chrono implements Runnable {

	private final int PAUSE = 8; // temps d'attente entre 2 tours de boucle
	
	
	@Override
	public void run() {
		
		while(true) {
			Main.scene.repaint();
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
	}

}
