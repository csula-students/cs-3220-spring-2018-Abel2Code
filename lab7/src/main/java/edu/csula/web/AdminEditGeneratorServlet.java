package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

@WebServlet("/admin/generators/edit")
public class AdminEditGeneratorServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		UsersDAO userDao = new UsersDAOImpl(request.getSession());
		if(!userDao.getAuthenticatedUser().isPresent()){
			response.sendRedirect("../auth");
			return;
		}

		Generator generator = null;
		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		Collection<Generator> generators = dao.getAll();

		for(Generator e: generators){
				if(e.getId() == id){
						generator = e;
				}
		}

		request.setAttribute("name", generator.getName());
		request.setAttribute("description", generator.getDescription());
		request.setAttribute("baseCost", generator.getBaseCost());
		request.setAttribute("unlockAt",generator.getUnlockAt());
		request.setAttribute("rate",generator.getRate());


		// TODO: render the events page HTML
		request.getRequestDispatcher("../../WEB-INF/admin-edit-generators.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersDAO userDao = new UsersDAOImpl(request.getSession());
		if(!userDao.getAuthenticatedUser().isPresent()){
			response.sendRedirect("../auth");
			return;
		}

		String name = request.getParameter("name");
		String rate = request.getParameter("rate");
		String cost = request.getParameter("baseCost");
		String unlockAt = request.getParameter("unlockAt");
		String description = request.getParameter("description");
		int id = Integer.parseInt(request.getParameter("id"));

		Collection<Generator> generator = new GeneratorsDAOImpl(getServletContext()).getAll();

		Generator e = new Generator(id, name, description, Integer.parseInt(rate), Integer.parseInt(cost), Integer.parseInt(unlockAt));

		new GeneratorsDAOImpl(getServletContext()).set(id, e);

		response.sendRedirect("../../admin/generators");
	}
}
