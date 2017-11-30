package cleal.projet;

public class Livre {

	private int id;
	private String titre;
	private String auteur;
	private int nbrTotal;
	private int nbrEmprunte;

	private static int currentId = 0;

	public Livre(String titre, String auteur, int nbr) {
		this.id = nextId();
		this.titre = titre;
		this.auteur = auteur;
		this.nbrTotal = nbr;
		this.nbrEmprunte = 0;
	}

	private static int nextId() {
		return currentId++;
	}

	public boolean emprunter() {
		if (nbrEmprunte < nbrTotal) {
			this.nbrEmprunte++;
			return true;
		} else
			return false;
	}

	public boolean rendre() {
		if (nbrEmprunte < nbrTotal) {
			this.nbrEmprunte--;
			return true;
		} else
			return false;
	}

	public int getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public int getNbrTotal() {
		return nbrTotal;
	}

	public int getNbrEmprunte() {
		return nbrEmprunte;
	}
	
	

}
