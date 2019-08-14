package objets;

import javax.swing.ImageIcon;

public class Yoshi extends Objet {
	
	//** VARIABLES **//

	
	
	public Yoshi(int x, int y) {
		super(x, y, 27, 30);
		super.setIco(new ImageIcon(getClass().getResource("/images/yoshiArretGauche.png")));
		super.setImg(super.getIco().getImage());
	}
	
	//** GETTERS **//
	
	//** SETTERS **//
	
	//** METHODES **//
	
}
