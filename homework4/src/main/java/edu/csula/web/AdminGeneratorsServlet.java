package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.mysql.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

import edu.csula.storage.mysql.Database;


@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UsersDAO userDao = new UsersDAOImpl(request.getSession());
		if(!userDao.getAuthenticatedUser().isPresent()){
			response.sendRedirect("../admin/auth");
			return;
		}
		// TODO: render the events page HTML
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		Collection<Generator> generators = dao.getAll();
		request.setAttribute("generators", generators);
		request.getRequestDispatcher("../WEB-INF/admin-generators.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		UsersDAO userDao = new UsersDAOImpl(request.getSession());
		if(!userDao.getAuthenticatedUser().isPresent()){
			response.sendRedirect("../admin/auth");
			return;
		}
		String name = request.getParameter("name");
		String rate = request.getParameter("rate");
		String cost = request.getParameter("baseCost");
		String unlockAt = request.getParameter("unlockAt");
		String description = request.getParameter("description");


		Collection<Generator> generators = new GeneratorsDAOImpl(new Database()).getAll();
		Object[] tempArr = generators.toArray();
		int id = -1;
		for(int i = 0; i < tempArr.length; i++) {
			Generator generator = (Generator) tempArr[i];
			if(generator.getId() != i){
				id = i;
				break;
			}
		}
		if(id == -1){
			id = generators.size();
		}

		new GeneratorsDAOImpl(new Database()).add(new Generator(id, name, description, Integer.parseInt(rate),Integer.parseInt(cost),Integer.parseInt(unlockAt)));

		response.sendRedirect("../admin/generators");
	}
}
