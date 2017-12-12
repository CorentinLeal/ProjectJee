package cleal.projet;

import java.util.ArrayList;
import java.util.HashMap;

public class Utilisateur {
	
	private String nom;
	private String login;
	private String mdp;
	private boolean isAdmin;
	private ArrayList<Livre> listeLivre;
	private static HashMap<String, Utilisateur> listeUtilisateurs = new HashMap<String, Utilisateur>();

	public Utilisateur(String nom, String id, String mdp, boolean isAdmin) {
		this.nom = nom;
		this.login = id;
		this.mdp = mdp;
		this.isAdmin = isAdmin;
//		this.listeLivre = new ArrayList<>();
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp
	 *            the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/**
	 * @return the id
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.login = id;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the listeUtilisateurs
	 */
	public static HashMap<String, Utilisateur> getListeUtilisateurs() {
		return listeUtilisateurs;
	}
	
	public static ArrayList<Utilisateur> getArrayListUtilisateurs() {
		ArrayList<Utilisateur> values = new ArrayList<>(listeUtilisateurs.values());
		return values;
	}

	/**
	 * Récupérer un utilisateur avec l'identifiant
	 * 
	 * @param identifiant
	 *            l'identifiant de l'utilisateur
	 * @return l'utilisateur correspondant
	 */
	public static Utilisateur getUtilisateurParIdentifiant(String identifiant) {
		return getListeUtilisateurs().get(identifiant);
	}

	/**
	 * Ajouter un utilisateur
	 * 
	 * @param newUtilisateur
	 */
	public static void addUtilisateurToListeUtilisateurs(Utilisateur newUtilisateur) {
		getListeUtilisateurs().put(newUtilisateur.getLogin(), newUtilisateur);
	}

	/**
	 * Vérifie si l'utilisateur existe
	 * 
	 * @param login
	 * @param password
	 * @return l'utilisateur si présent false sinon
	 */
	public static Utilisateur utilisateurExiste(String login, String password) {
		Utilisateur user;
		if (login != null) {
			if (Utilisateur.getUtilisateurParIdentifiant(login) != null) {
				user = Utilisateur.getUtilisateurParIdentifiant(login);
				if (user.getMdp().equals(password)) {
					return user;
				}
			}
		}
		return null;
	}

	/**
	 * Ajoute un livre à la liste de livre de l'utilisateur
	 * 
	 * @param livre
	 *            livre emprunté
	 */
	public void ajouterLivre(Livre livre) {
		this.listeLivre.add(livre);
	}

	/**
	 * Supprime un livre de la liste de livre de l'utilisateur
	 * 
	 * @param livre
	 *            livre emprunté
	 */
	public void rendreLivre(Livre livre) {
		this.listeLivre.remove(livre);
	}

}