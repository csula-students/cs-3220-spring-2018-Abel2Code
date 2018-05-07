package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

import edu.csula.storage.servlet.GameNameDaoImpl;
import edu.csula.storage.GameNameDao;

import edu.csula.storage.mysql.Database;

@WebServlet("/admin/information")
public class AdminInformationServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		UsersDAO userDao = new UsersDAOImpl(request.getSession());
		if(!userDao.getAuthenticatedUser().isPresent()){
			response.sendRedirect("../admin/auth");
			return;
		}

		GameNameDaoImpl gameNameDao = GameNameDaoImpl.getInstance();

		request.setAttribute("name", gameNameDao.getName());
		request.getRequestDispatcher("../WEB-INF/admin-information.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		UsersDAO userDao = new UsersDAOImpl(request.getSession());
		if(!userDao.getAuthenticatedUser().isPresent()){
			response.sendRedirect("../admin/information");
			return;
		}

		String name = request.getParameter("name");
		GameNameDaoImpl gameNameDao = GameNameDaoImpl.getInstance();
		gameNameDao.setName(name);
		System.out.println(name);

		response.sendRedirect("../admin/information");
	}
}
