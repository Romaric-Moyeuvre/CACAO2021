package abstraction.eq3Transformateur1;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Variable;

public class Transformateur1 extends AcheteurFevesContratCadre {
	
	public Transformateur1() { 
		super();
	}
	
	public void next() {

		
		this.getStock().getFinancier().setIndicateurs();
		this.getStock().getFinancier().miseAJourContratVendeur();
		this.nosDemandesCC();
		this.getStock().getFinancier().miseAJourContratAcheteur();
		this.getStock().transformationFeveChocolat();
		this.getStock().coutStock();

		
		
	}
	
	public void initialiser() {
		this.setSupCCadre();
	}
}
