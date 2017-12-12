package cleal.projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Repertoire {

	private static HashMap<Integer, Livre> listeLivres = new HashMap<>();

	public Repertoire() {
		Repertoire.listeLivres = new HashMap<>();
	}

	public static void addLivre(Livre livre) {
		listeLivres.put(livre.getId(), livre);
	}

	public static Livre getLivre(int id) {
		return listeLivres.get(id);
	}

	public static void deleteLivre(int id) {
		listeLivres.remove(id);
	}

	public static ArrayList<Livre> getLivreParAuteur(String auteur) {
		ArrayList<Livre> result = new ArrayList<>();
		for (Entry<Integer, Livre> livre : listeLivres.entrySet()) {
			if (livre.getValue().getAuteur().equals(auteur)) {
				result.add(livre.getValue());
			}
		}
		return result;
	}

	public static ArrayList<Livre> getLivreParTitre(String titre) {
		ArrayList<Livre> result = new ArrayList<>();
		for (Entry<Integer, Livre> livre : listeLivres.entrySet()) {
			if (livre.getValue().getTitre().equals(titre)) {
				result.add(livre.getValue());
			}
		}
		return result;
	}

	public static ArrayList<Livre> getLivreParTitreAuteur(String titre, String auteur) {
		ArrayList<Livre> result = getLivreParTitre(titre);
		Iterator<Livre> iterator = result.iterator();
		while (iterator.hasNext()) {
			Livre livre = (Livre) iterator.next();
			if (!livre.getAuteur().equals(auteur)) {
				iterator.remove();
			}
		}
		return result;

	}

	public static ArrayList<Livre> rechercher(String titre, String auteur) {
		if (titre != null || auteur != null) {
			if (!titre.equals("") && titre != null) {
				if (!auteur.equals("") && auteur != null) {
					return getLivreParTitreAuteur(titre, auteur);
				} else {
					return getLivreParTitre(titre);
				}
			} else {
				return getLivreParAuteur(auteur);
			}
		}
		return null;
	}

}
