package objets;

import javax.swing.ImageIcon;

public class Peach extends Objet {
	
	//** VARIABLES **//

	
	
	public Peach(int x, int y) {
		super(x, y, 27, 50);
		super.setIco(new ImageIcon(getClass().getResource("/images/peach1.png")));
		super.setImg(super.getIco().getImage());
	}
	
	//** GETTERS **//
	
	//** SETTERS **//
	
	//** METHODES **//
	
}
