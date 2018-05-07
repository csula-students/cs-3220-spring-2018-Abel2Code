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

@WebServlet("/admin/generators/delete")
public class AdminDeleteGeneratorServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersDAO userDao = new UsersDAOImpl(request.getSession());
		if(!userDao.getAuthenticatedUser().isPresent()){
			response.sendRedirect("../auth");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		dao.remove(id);
		response.sendRedirect("../generators");
	}
}
