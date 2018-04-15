package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events/edit")
public class AdminEditEventServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Event event = null;
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();

		for(Event e: events){
				if(e.getId() == id){
						event = e;
				}
		}

		request.setAttribute("name", event.getName());
		request.setAttribute("description", event.getDescription());
		request.setAttribute("triggerAt", event.getTriggerAt());

		// TODO: render the events page HTML
		request.getRequestDispatcher("../../WEB-INF/admin-edit-events.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String triggerAtIn = request.getParameter("trigger");
		int id = Integer.parseInt(request.getParameter("id"));

		Collection<Event> events = new EventsDAOImpl(getServletContext()).getAll();

		Event e = new Event(id,name,description,Integer.parseInt(triggerAtIn));
		new EventsDAOImpl(getServletContext()).set(id, e);

		response.sendRedirect("../../admin/events");
	}
}
