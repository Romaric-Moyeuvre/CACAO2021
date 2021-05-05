package abstraction.eq2Producteur2;

import java.util.LinkedList;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;

//ensemble fait par DIM

public abstract class Producteur2VeudeurFeveCC extends Producteur2VendeurFeveAO implements IVendeurContratCadre {
		protected LinkedList<ExemplaireContratCadre> mesContratsCC;

	/**
	 * @param mesContrats
	 */
	public Producteur2VeudeurFeveCC() {
		super();
		this.mesContratsCC = new LinkedList<ExemplaireContratCadre>();
	}

	@Override
	public boolean peutVendre(Object produit) {
		if (estFeve(produit) || estPoudre(produit)) {
			return true;
		}else {
		return false;
		}
	}

	@Override
	public boolean vend(Object produit) {
		double stock = qttTotale(produit).getValeur();
		return stock>0;
	}
	
	//Dim
	/**
	 * vérifie si le prix proposé pour la premiere reponse est acceptable 
	 */
	public static boolean estAcceptable(double i1, Object produit) {
		double i2 = prixEspere(produit);
		double dif = difAcceptee(produit);
		return (i1 > i2 - dif);
	}
	
	public static double prixEspere(Object produit) {
		if(estFeveHBE(produit)) {
			return PRIX_ESPERE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return PRIX_ESPERE_FEVE_HE;
		} else if(estFeveME(produit)) {
			return PRIX_ESPERE_FEVE_ME;
		} else if(estFeveM(produit)) {
			return PRIX_ESPERE_FEVE_M;
		} else if(estFeveB(produit)) {
			return PRIX_ESPERE_FEVE_B;
		} else if(estPoudreHE(produit)) {
			return PRIX_ESPERE_POUDRE_HE;
		} else if(estPoudreM(produit)) {
			return PRIX_ESPERE_POUDRE_M;
		}
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}
	public static double minAcceptee(Object produit) {
		if(estFeveHBE(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_HE;
		} else if(estFeveME(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_ME;
		} else if(estFeveM(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_M;
		} else if(estFeveB(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_B;
		} else if(estPoudreHE(produit)) {
			return PRIX_MIN_ACCEPTEE_POUDRE_HE;
		} else if(estPoudreM(produit)) {
			return PRIX_MIN_ACCEPTEE_POUDRE_M;
		}
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}
	public static double difAcceptee(Object produit) {
		if(estFeveHBE(produit)) {
			return DIF_ACCEPTEE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return DIF_ACCEPTEE_FEVE_HE;
		} else if(estFeveME(produit)) {
			return DIF_ACCEPTEE_FEVE_ME;
		} else if(estFeveM(produit)) {
			return DIF_ACCEPTEE_FEVE_M;
		} else if(estFeveB(produit)) {
			return DIF_ACCEPTEE_FEVE_B;
		} else if(estPoudreHE(produit)) {
			return DIF_ACCEPTEE_POUDRE_HE;
		} else if(estPoudreM(produit)) {
			return DIF_ACCEPTEE_POUDRE_M;
		}
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}
	
	public double aProduire(Object produit) {
		double prod = 0;
		for (ExemplaireContratCadre e: mesContratsCC) {
			if (e.getProduit() == produit) {
				prod += e.getQuantiteRestantALivrer();
			}
		}
		return prod;
	}
	
	public double aProduireAuStep(Object produit, int step) { // servira plus tard
		double prod = 0;
		for (ExemplaireContratCadre e: mesContratsCC) {
			if (e.getProduit() == produit) {
				prod += e.getEcheancier().getQuantite(step);
			}
		}
		return prod;
	}
	
	//DIM
	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		// cette partie est très basique et tend à être modifiée par la suite
		Object produit = contrat.getProduit();	
		double qttDemandee = contrat.getEcheancier().getQuantiteTotale();
		double qttDispo = qttTotale(produit).getValeur();
		double qttProduiteFutur = prodParStep(produit) * contrat.getEcheancier().getNbEcheances();
		double contratEnCours = aProduire(produit);
		double qtt = qttDispo + qttProduiteFutur - contratEnCours;
		boolean cond = qttDemandee < qtt;

		if(cond) { // on est daccord avec l'échéancier
			return contrat.getEcheancier();
		}else { // on propose une nouvelle valeur
			Echeancier e = contrat.getEcheancier();
			double qdm = qttDemandee;
			int i =0;
			while ( qdm < qtt && i< e.getStepFin()) { // on divise par 2 la qtt a fournir à chaque step jusqua pvr fournir
				e.set(e.getStepDebut()+i, e.getQuantite(e.getStepDebut()+i) / 2);
				i++;
				qdm = e.getQuantiteTotale();
			}		
			boolean cond2 = e.getQuantiteTotale() < qtt;
			if(cond2) {				
				return e; 
			} else { //on ne souhaite pas vendeur donc on retourne null
			return null; 
		}}

	}

	@Override
	//Dim
	public double propositionPrix(ExemplaireContratCadre contrat) {
		double prix = prixEspere(contrat.getProduit());
		return prix ; // on profite du fait qu'ils achetent quoi quil arrive pour devenir riche
	}

	@Override
	//Dim
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		// cette partie est très basique et tend à être modifiée par la suite
		Object produit = contrat.getProduit();		
		double prixPropose = contrat.getPrix();
		boolean cond = estAcceptable(prixPropose , produit);
		if(cond) { // on est daccord avec l'échéancier
			return prixPropose;
		}else { // on propose une nouvelle valeur
			// comparer au cout de production des ressources vendues ( ne pas vendre a perte + payer correctement les producteurs)
			// prendre en compte le nombre de step ou lon na pas vendu et notre tresorerie
			// prendre en compte le prix minima accepte
			double prix = contrat.getListePrix().get(contrat.getListePrix().size() - 2) - difAcceptee(produit); 
			double min = minAcceptee(produit);					
			boolean cond2 = min<prix;
			if(cond2) {				
				return prix;
			} else { //on retourne le minimum accepte au cas ou l'acheteur accepte ce prix
				return minAcceptee(produit);
		}}
	}
 
	@Override
	//Dim
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		// maj var mesContrats
		this.mesContratsCC.add(contrat);
		this.JournalVente.ajouter("nouvelle vente CC avec " + contrat.getAcheteur().getNom() + " qtt = " +
		Math.floor(contrat.getQuantiteTotale()) + contrat.getProduit()
		+ " pour " + contrat.getPrix() + "euro au kg, en " + contrat.getEcheancier().getNbEcheances()
		+" échéances" );
	}

	@Override
	// Dim
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// reflcechir pour transmettre lage de la feve a lachteur? vrmt utile?
		double stock = qttTotale(produit).getValeur();
		if (stock >= quantite ) {
			vente(quantite, produit);
			return quantite;
		}else {
			vente(stock, produit);
			return stock;
		}
	}

}
