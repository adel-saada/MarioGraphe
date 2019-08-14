package objets;

import javax.swing.ImageIcon;

public class Etoile extends Objet {
	
	//** VARIABLES **//

	private int numero;
	
	public Etoile(int x, int y) {
		super(x, y, 30, 30);
		super.setIco(new ImageIcon(getClass().getResource("/images/etoile.png")));
		super.setImg(super.getIco().getImage());
	}
	
	public Etoile(int x, int y, int numero) {
		this(x, y);
//		super.setIco(new ImageIcon(getClass().getResource("/images/etoile"+numero+".png")));
//		super.setImg(super.getIco().getImage());
		this.numero = numero;
	}

	
	//** GETTERS **//
	public int getNumero() {
		return numero;
	}



	
	//** SETTERS **//
	
	//** METHODES **//
	
}
