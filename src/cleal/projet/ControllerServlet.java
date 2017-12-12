package cleal.projet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/bibliotheque")
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final int NULL = -1;
	private static final int REFUSED = 0;
	private static final int ADMIN = 1;
	private static final int USER = 2;
	private HttpSession session;

	private int connexion = NULL;

	public ControllerServlet() {
		super();
		Utilisateur.addUtilisateurToListeUtilisateurs(new Utilisateur("root", "root", "root", true));
		Utilisateur.addUtilisateurToListeUtilisateurs(new Utilisateur("toto", "toto", "toto", false));
		Repertoire.addLivre(new Livre("Les raisins de la colère", "Steinbeck", 2));
		Repertoire.addLivre(new Livre("Sur la route", "Kerouac", 5));
		Repertoire.addLivre(new Livre("Des souris et des hommes", "Steinbeck", 3));

		Action.reservation(Utilisateur.getUtilisateurParIdentifiant("toto"), Repertoire.getLivre(0));
		Action.emprunt(Utilisateur.getUtilisateurParIdentifiant("toto"), Repertoire.getLivre(1));
		Action.emprunt(Utilisateur.getUtilisateurParIdentifiant("toto"), Repertoire.getLivre(2));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.session = req.getSession(false);

		String page = req.getParameter("elem");

		if (page != null) {
			switch (page) {
			case "recherche":
				page = "/Recherche.jsp";
				break;
			case "connexion":
				page = "/Connexion.jsp";
				break;
			case "consoleAdmin":
				page = "/ConsoleAdmin.jsp";
				break;
			case "pageUtilisateur":
				req.setAttribute("reserv", Action.getReservForUser((String) session.getAttribute("login")));
				req.setAttribute("emprunts", Action.getEmpruntsForUser((String) session.getAttribute("login")));
				page = "/PageUtilisateur.jsp";
				break;
			case "actions":
				req.setAttribute("actions-utilisateurs", Utilisateur.getArrayListUtilisateurs());
				page = "/Actions.jsp";
				break;
			default:
				break;
			}
		} else {
			page = "/bibliotheque?elem=recherche";
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session = req.getSession(false);

		switch (req.getParameter("form")) {
		case "recherche":
			req.setAttribute("recherche-results",
					Repertoire.rechercher(req.getParameter("titre"), req.getParameter("auteur")));
			break;
		case "recherche_reserv":
			Action.reservation(Utilisateur.getUtilisateurParIdentifiant((String) session.getAttribute("login")),
					Repertoire.getLivre(Integer.parseInt(req.getParameter("livre"))));
			break;
		case "console_recherche":
			req.setAttribute("console-results",
					Repertoire.rechercher(req.getParameter("titre"), req.getParameter("auteur")));
		case "connexion":
			connexion = connexion(req.getParameter("login"), req.getParameter("password"), req);
			break;
		case "console_add":
			Repertoire.addLivre(new Livre(req.getParameter("titre"), req.getParameter("auteur"),
					Integer.parseInt(req.getParameter("nombre"))));
			break;
		case "console_delete":
			Repertoire.deleteLivre(Integer.parseInt(req.getParameter("livre")));
			break;
		case "console_changeNumber":
			Repertoire.getLivre(Integer.parseInt(req.getParameter("livre")))
					.updateNbrExemplaires(Integer.parseInt(req.getParameter("newNombre")));
			break;
		case "pageUtilisateur_annuler":
			Action.cancelReservation(Integer.parseInt(req.getParameter("reserv")));
			break;
		case "actions_select":
			req.setAttribute("actions-emprunts", Action.getEmpruntsForUser(req.getParameter("utilisateur")));
			req.setAttribute("actions-reserv", Action.getReservForUser(req.getParameter("utilisateur")));
			req.setAttribute("selectedUser", req.getParameter("utilisateur"));
			break;
		case "actions_annulerReserv":
			Action.cancelReservation(Integer.parseInt(req.getParameter("emprunt")));
			break;
		case "actions_fin_emprunt":
			Action.cancelEmprunt(Integer.parseInt(req.getParameter("emprunt")));
			req.setAttribute("actions-emprunts", Action.getEmpruntsForUser(req.getParameter("utilisateur")));
			req.setAttribute("actions-reserv", Action.getReservForUser(req.getParameter("utilisateur")));
			req.setAttribute("selectedUser", req.getParameter("utilisateur"));
			break;
		case "actions_recherche":
			req.setAttribute("actions-results",
					Repertoire.rechercher(req.getParameter("titre"), req.getParameter("auteur")));
			break;
		case "actions_emprunter":
			Action.emprunt(Utilisateur.getUtilisateurParIdentifiant(req.getParameter("utilisateur")),
					Repertoire.getLivre(Integer.parseInt(req.getParameter("livre"))));
			break;
		default:
			break;
		}

		doGet(req, resp);
	}

	private int connexion(String login, String password, HttpServletRequest request) {
		Utilisateur utilisateur = Utilisateur.utilisateurExiste(login, password);
		if (utilisateur != null) {
			session = request.getSession();
			session.setAttribute("login", utilisateur.getLogin());
			session.setAttribute("admin", utilisateur.isIsAdmin());
			session.setAttribute("nom", utilisateur.getNom());
			session.setMaxInactiveInterval(10 * 60);

			if (utilisateur.isIsAdmin()) {
				return ADMIN;
			} else {
				return USER;
			}
		} else {
			return REFUSED;
		}
	}
}
