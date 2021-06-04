package abstraction.eq4Transformateur2;

import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.Chocolat;

//Tout le monde

public class Transformateur2Valeurs {
	
	public Transformateur2Valeurs() {
		var_stock_feve_basse = new Variable("stock feve basse", (IActeur) this, quantite_init_feve_basse);
		var_stock_feve_moyenne = new Variable("stock feve moyenne", (IActeur) this, quantite_init_feve_moyenne);
		var_stock_tablette_basse = new Variable("stock tablette basse", (IActeur) this, quantite_init_tablette_basse);
		var_stock_tablette_moyenne = new Variable("stock tablette moyenne", (IActeur) this, quantite_init_tablette_moyenne);
		var_stock_confiserie_basse = new Variable("stock confiserie basse", (IActeur) this, quantite_init_confiserie_basse);
		var_stock_confiserie_moyenne = new Variable("stock confiserie moyenne", (IActeur) this, quantite_init_confiserie_moyenne);
	}
	
	//Echéancier
	
	protected LinkedList<Double> echeancier_basse;
	protected LinkedList<Double> echeancier_moyenne ;
	protected LinkedList<Double> echeancier_total ; 
	

	
	protected static double Prix_max_achat;
	
	protected LinkedList<ExemplaireContratCadre> contrats;
	protected int cryptogramme;
	protected Journal journal;
	
	
	//Variables utilisées pour le stockage

	
	protected HashMap<Feve,Double> stock_feve;
	protected HashMap<Chocolat,Double> stock_chocolat;
	protected double mini_stock_tablette_basse = 15000000.0;
	protected double mini_stock_tablette_moyenne = 15000000.0;
	protected double mini_stock_confiserie_basse = 15000000.0;
	protected double mini_stock_confiserie_moyenne = 15000000.0;
	protected double mini_stock_feve_basse = 10000000.0;
	protected double mini_stock_feve_moyenne = 10000000.0;
	
	//Variables de coûts

	protected double quantite_totale_demandee_basse = 0;
	protected double quantite_totale_demandee_moyenne = 0;
	protected double nombre_step_total_basse = 1;
	protected double nombre_step_total_moyenne = 1;
	
	//Coûts fixes
	
	protected static double cout_stockage_unite_feve = 0.006;
	protected static double cout_stockage_unite_choco = 0.006 ;
	
	protected static double cout_fixe_entrepot_feve = 1000;
	protected static double cout_fixe_entrepot_choco = 1000;
	
	//Achat 
	
	protected static double cout_max_feve_basse = 2.5 ; 
	protected static double cout_max_feve_moyenne = 2.6;

	//Production
	
	protected static double charges_fixes = 0.0 ;
	protected static double capacite_production = 100000000000000000.0 ;
	protected static double cout_transformation = 0.5;
	
	//Vente
	
	protected double prix_min_tablette_basse = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_basse; 
	protected double prix_min_tablette_moyenne = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_moyenne; 
	protected double prix_min_confiserie_basse = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_basse; 
	protected double prix_min_confiserie_moyenne = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_moyenne; 

	//Variables
	
	protected Variable var_stock_feve_basse;
	protected Variable var_stock_feve_moyenne;
	
	protected Variable var_stock_tablette_basse;
	protected Variable var_stock_tablette_moyenne;
	protected Variable var_stock_confiserie_basse;
	protected Variable var_stock_confiserie_moyenne;
	
	//Quantité intiale dans les entrepôts
	
	protected static double quantite_init_feve_basse = (1627200000.0+4197600000.0)/48.0;
	protected static double quantite_init_feve_moyenne = (1627200000.0+4197600000.0)/48.0;
	protected static double quantite_init_tablette_basse = 4197600000.0/48;
	protected static double quantite_init_tablette_moyenne = 4197600000.0/48;
	protected static double quantite_init_confiserie_basse = 1627200000.0/48;
	protected static double quantite_init_confiserie_moyenne = 1627200000.0/48;
	
	protected Journal journal_achat;
	protected Journal journal_transformation;
	protected Journal journal_stock;
	protected Journal journal_vente;
	
	// Test

}
	