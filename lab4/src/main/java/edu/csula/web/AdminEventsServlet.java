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

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		System.out.println(events);
		out.println("<h1>Incremental Game Framework</h1>");
		out.println("<nav> <a href=\"admin-info.html\">Game Information</a> | <a href=\"admin-generators.html\">Generators</a> | <a href=\"admin-events.html\"><strong>Events</strong></a> </nav>");
		out.println("<main>");
		out.println("<div class=\"container\"> <div class=\"fill\"> <form> <p> Event Name <br /> <input type=\"text\"></input> </p> <p> Event Description <br /> <textarea></textarea> </p> <p> Trigger At <br /> <input type=\"text\"></input> </p> <button> {Add|Edit} </button> </form> </div> <div> <table> <tr id=\"tableHead\"> <th> Name </th> <th> Description </th> <th> Trigger At </th> </tr> <tr> <td> Grandma Shows Up </td> <td> Lorem... </td> <td> 10 </td> </tr> <tr> <td> You can construct a factory now! </td> <td> Lorem... </td> <td> 50 </td> </tr> <tr> <td> We've found cookies in deep mountain. </td> <td> Lorem... </td> <td> 200 </td> </tr> <tr> <td> </td> <td> </td> <td> </td> </tr> <tr> <td> </td> <td> </td> <td> </td> </tr> <tr> <td> </td> <td> </td> <td> </td> </tr> </table> </div> </div>");
		out.println("</main>");
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
	}
}
