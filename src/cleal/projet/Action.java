package cleal.projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Action {

	private int id;
	private Livre livre;
	private Utilisateur utilisateur;

	private static int currentId = 0;
	private static HashMap<Integer, Action> reserv = new HashMap<>();
	private static HashMap<Integer, Action> emprunt = new HashMap<>();

	public Action(Livre livre, Utilisateur utilisateur) {
		this.id = nextId();
		this.livre = livre;
		this.utilisateur = utilisateur;
	}

	private int nextId() {
		return currentId++;
	}

	public int getId() {
		System.out.println("ID:"+id);
		System.out.println(emprunt);
		return id;
	}

	public Livre getLivre() {
		return livre;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public static boolean reservation(Utilisateur utilisateur, Livre livre) {
		Action action = new Action(livre, utilisateur);
		if (Repertoire.getLivre(livre.getId()).emprunter()) {
			reserv.put(action.getId(), action);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean emprunt(Utilisateur utilisateur, Livre livre) {
		Action action = new Action(livre, utilisateur);
		if (Repertoire.getLivre(livre.getId()).emprunter()) {
			emprunt.put(action.getId(), action);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean cancelReservation(int actionID) {
		Action action = reserv.get(actionID);
		action.getLivre().rendre();
		
		return reserv.remove(action.getId(), action);
	}
	
	public static boolean cancelEmprunt(int actionID) {
		Action action = emprunt.get(actionID);
		action.getLivre().rendre();
		
		return emprunt.remove(action.getId(), action);
	}
	
	public static void deleteLivre(int id) {
		Repertoire.deleteLivre(id);
		
		for (Map.Entry<Integer, Action> entry : emprunt.entrySet()) {
			Action action = entry.getValue();
			int key = entry.getKey();
			if(action.getLivre().getId() == id) {
				emprunt.remove(key);
			}
		}
		
		for (Map.Entry<Integer, Action> entry : reserv.entrySet()) {
			Action action = entry.getValue();
			int key = entry.getKey();
			if(action.getLivre().getId() == id) {
				reserv.remove(key);
			}
		}
	}
	
	public static ArrayList<Action> getReservForUser(String uid) {
		ArrayList<Action> reservations = new ArrayList<>();
		for (Map.Entry<Integer, Action> entry : reserv.entrySet()) {
			Action action = entry.getValue();
			if (action.getUtilisateur().getLogin().equals(uid)) {
				reservations.add(action);
			}
		}
		return reservations;
	}
	
	public static ArrayList<Action> getEmpruntsForUser(String uid) {
		ArrayList<Action> emprunts = new ArrayList<>();
		for (Map.Entry<Integer, Action> entry : emprunt.entrySet()) {
			Action action = entry.getValue();
			if (action.getUtilisateur().getLogin().equals(uid)) {
				emprunts.add(action);
			}
		}
		return emprunts;
	}
}
