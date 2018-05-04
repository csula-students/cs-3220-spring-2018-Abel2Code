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

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.models.State;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
	public void init(){
		EventsDAO eventsDao = new EventsDAOImpl(getServletContext());
		if(eventsDao.getAll().size() == 0){
			eventsDao.add(new Event(0, "Make me new toys! Write some code!", "You are doing great. Keep at it.", 10));
			eventsDao.add(new Event(1, "Your code is easy to read! Keep it up!", "This is good for your line count!", 100));
			eventsDao.add(new Event(2, "The new iPhone just came out. Be the first to build an app for it.", "Use your entrepreneurial spirit to write some code.", 500));
		}

		GeneratorsDAO generatorDao = new GeneratorsDAOImpl(getServletContext());
		if(generatorDao.getAll().size() == 0){
			GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
			generatorDao.add(new Generator(0, "Intern", "Interns generate code, but not quickly.", 5, 10, 10));
			generatorDao.add(new Generator(1, "Software Engineer", "Software Engineers can write code pretty quickly.", 10, 100, 100));
			generatorDao.add(new Generator(2, "Researcher", "They do Math?", 20, 500, 500));
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Collection<Generator> generators = new GeneratorsDAOImpl(getServletContext()).getAll();

		Collection<Event> events = new EventsDAOImpl(getServletContext()).getAll();

		GsonBuilder builder = new GsonBuilder();
  	Gson gson = builder.create();
    String state = gson.toJson(new State(generators, events));
		System.out.println(state);
		request.setAttribute("state", state);
		request.setAttribute("generatorLast", generators.size() - 1);
		request.getRequestDispatcher("./WEB-INF/game.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction

		response.sendRedirect("./game");
	}
}
