package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

import edu.csula.storage.mysql.Database;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
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

		EventsDAO dao = new EventsDAOImpl(new Database());
		Collection<Event> events = dao.getAll();
		request.setAttribute("events", events);
		request.getRequestDispatcher("../WEB-INF/admin-events.jsp").forward(request, response);
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
		String description = request.getParameter("description");
		String triggerAtIn = request.getParameter("triggerAt");

		Collection<Event> events = new EventsDAOImpl(new Database()).getAll();
		Object[] tempArr = events.toArray();
		int id = -1;
		for(int i = 0; i < tempArr.length; i++) {
			Event event = (Event) tempArr[i];
			if(event.getId() != i){
				id = i;
				break;
			}
		}
		if(id == -1){
			id = events.size();
		}

		new EventsDAOImpl(new Database()).add(new Event(id,name,description,Integer.parseInt(triggerAtIn)));

		response.sendRedirect("../admin/events");
	}
}
