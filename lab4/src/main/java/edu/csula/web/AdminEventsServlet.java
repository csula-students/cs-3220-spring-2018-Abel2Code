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
	public void init(){
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		dao.add(new Event(0, "Make me new toys! Write some code!", "You are doing great. Keep at it.", 10));
		dao.add(new Event(1, "Your code is easy to read! Keep it up!", "This is good for your line count!", 100));
		dao.add(new Event(2, "The new iPhone just came out. Be the first to build an app for it.", "Use your entrepreneurial spirit to write some code.", 500));
	}

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		System.out.println(events);
		out.println("<h1>Incremental Game Framework</h1>");
		out.println("<nav> <a href=\"admin-info.html\">Game Information</a> | <a href=\"admin-generators.html\">Generators</a> | <a href=\"./events\"><strong>Events</strong></a> </nav>");
		out.println("<main>");
		out.println("<div class=\"container\"> <div class=\"fill\"> <form method='POST'> <p> Event Name <br /> <input type=\"text\" name='name'></input> </p> <p> Event Description <br /> <textarea name='description'></textarea> </p> <p> Trigger At <br /> <input type=\"text\" name='triggerAt'></input> </p> <button> {Add|Edit} </button> </form> </div>");
		out.println("<div><table> <tr id=\"tableHead\"> <th> Name </th> <th> Description </th> <th> Trigger At </th><th> Edit|Delete </th> </tr>");
		Object[] tempArr = events.toArray();
		for(int i = 0; i < tempArr.length; i++) {
			Event event = (Event) tempArr[i];
				out.println("<tr> <td>" + event.getName() + "</td> <td>" + event.getDescription() + "</td> <td>" + event.getTriggerAt() + "</td><td> <a href='../admin/events/edit?id=" + event.getId() + "'>Edit</a>|<a href='../admin/events/delete?id=" + event.getId() + "'>Delete</a></td> </tr>");
		}
		out.println("</table> </div> </div></div></main>");
		out.println("<script>this.document.head.innerHTML = '<link rel=\"stylesheet\" type=\"text/css\" href=\"../app.css\">';</script>");
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String triggerAtIn = request.getParameter("triggerAt");
		//Int triggerAt = Integer.parseInt(triggerAtIn);

		Collection<Event> events = new EventsDAOImpl(getServletContext()).getAll();
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

		new EventsDAOImpl(getServletContext()).add(new Event(id,name,description,Integer.parseInt(triggerAtIn)));


		response.sendRedirect("../admin/events");
	}
}
