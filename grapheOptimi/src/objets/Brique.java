package objets;

import javax.swing.ImageIcon;

public class Brique extends Objet {
	
	//** VARIABLES **//

	
	public Brique(int x, int y) {
		super(x, y, 15, 15);
		super.setIco(new ImageIcon(getClass().getResource("/images/brique.png")));
		super.setImg(super.getIco().getImage());
	}


	
	//** GETTERS **//
	
	
	//** SETTERS **//
	
	//** METHODES **//
	
}
