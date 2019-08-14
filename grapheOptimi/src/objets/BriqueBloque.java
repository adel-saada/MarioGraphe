package objets;

import javax.swing.ImageIcon;

public class BriqueBloque extends Objet {
	
	//** VARIABLES **//

	private String lettre= null;
	
	public BriqueBloque(int x, int y,String nomImage, String lettre) {
		super(x, y, 30, 30);
		super.setIco(new ImageIcon(getClass().getResource("/images/"+nomImage)));
		super.setImg(super.getIco().getImage());
		this.setLettre(lettre);
	}

	public String getLettre() {
		return lettre;
	}

	public void setLettre(String lettre) {
		this.lettre = lettre;
	}
	
	//** GETTERS **//
	
	public void changementImage(String nomImage) {
		super.setIco(new ImageIcon(getClass().getResource("/images/"+nomImage)));
		super.setImg(super.getIco().getImage());
	}
	
	//** SETTERS **//
	
	//** METHODES **//
	
}
