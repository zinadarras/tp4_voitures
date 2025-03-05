package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IVoitureDao;
import dao.VoitureDaoImpl;
import metier.entities.Voiture;


@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	IVoitureDao metier;

	@Override
	public void init() throws ServletException {
		metier = new VoitureDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("voitures.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			VoitureModele model = new VoitureModele();
			model.setMotCle(motCle);
			List<Voiture> voits= metier.voituresParMC(motCle);
			model.setVoitures(voits);
			request.setAttribute("model", model);
			request.getRequestDispatcher("voitures.jsp").forward(request, response);
		}

		else if (path.equals("/saisie.do")) {
			request.getRequestDispatcher("saisieVoiture.jsp").forward(request, response);
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String marque = request.getParameter("marque");
			String modele = request.getParameter("modele");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Voiture v = metier.save(new Voiture(marque,modele, prix));
			request.setAttribute("voiture", v);
			response.sendRedirect("chercher.do?motCle=");

			//request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		} else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteVoiture(id);
			response.sendRedirect("chercher.do?motCle=");
		}

		else if (path.equals("/editer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Voiture v= metier.getVoiture(id);
			request.setAttribute("voiture", v);
			request.getRequestDispatcher("editerVoiture.jsp").forward(request, response);
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String marque = request.getParameter("marque");
			String modele = request.getParameter("modele");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Voiture v = new Voiture();
			v.setId(id);
			v.setMarque(marque);
			v.setModele(modele);
			v.setPrix(prix);
			metier.updateVoiture(v);
			request.setAttribute("voiture",v);
			//request.getRequestDispatcher("confirmation.jsp").forward(request, response);
			response.sendRedirect("chercher.do?motCle=");
		}

		else {
			response.sendError(Response.SC_NOT_FOUND);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}