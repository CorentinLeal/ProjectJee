package cleal.projet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class ControlerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ControlerServlet() {
		super();
		Repertoire.addLivre(new Livre("Les raisins de la colère", "Steinbeck", 2));
		Repertoire.addLivre(new Livre("Sur la route", "Kerouac", 5));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
}
