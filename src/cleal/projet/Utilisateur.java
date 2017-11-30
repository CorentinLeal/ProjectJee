/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author alequere
 */
public class Utilisateur {
    	private String nom;
	private String id;
	private String mdp;
	private boolean isAdmin;
        private ArrayList<Livre> listeLivre;
	private static HashMap<String,Utilisateur> listeUtilisateurs = new HashMap<String,Utilisateur>();
	

	public Utilisateur(String nom, String id, String mdp, boolean isAdmin) {
            this.nom = nom;	
            this.id = id;
            this.mdp = mdp;
            this.isAdmin = isAdmin;
            this.listeLivre = new ArrayList<>();
	}
	
            /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
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
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the listeUtilisateurs
     */
    public static HashMap<String,Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }
	/**
	 * Récupérer un utilisateur à partir de son identifiant
	 * @param identifiant l'identifiant de l'utilisateur à retourner
	 * @return l'utilisateur correspondant
	 */
	public static Utilisateur getUtilisateurParIdentifiant(String identifiant) {
		return getListeUtilisateurs().get(identifiant);
	}
	
	/**
	 * Ajouter un utilisateur
	 * @param newUtilisateur 
	 */
	public static void addUtilisateurToListeUtilisateurs(Utilisateur newUtilisateur) {
		getListeUtilisateurs().put(newUtilisateur.getId(), newUtilisateur);
	}
	
	/**
	 * Vérifie l'existence d'un utilsateur à partir de son identifiant et son mot de passe
	 * @param id l'identifiant testé
	 * @param mdp l'identifiant testé
	 * @return l'utilisateur trouvé s'il y en a un, false sinon
	 */
	public static Utilisateur utilisateurExiste(String id, String mdp) {
		Utilisateur user;
		if (id != null) {
			if(Utilisateur.getUtilisateurParIdentifiant(id) != null) {
				user = Utilisateur.getUtilisateurParIdentifiant(id);
				if(user.getMdp().equals(mdp)) {
					return user;
				}
			}
		}
		return null;
	}
        
        /**
	 * Ajoute un livre à la liste de livre de l'utilisateur
	 * @param livre livre emprunté
	 */
	public void ajouterLivre(Livre livre) {
		this.listeLivre.add(livre);
	}
        
        /**
	 * Supprime un livre de la liste de livre de l'utilisateur
	 * @param livre livre emprunté
	 */
        public void rendreLivre(Livre livre) {
		this.listeLivre.remove(livre);
	}




}