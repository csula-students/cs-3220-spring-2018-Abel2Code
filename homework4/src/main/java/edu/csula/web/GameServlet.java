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

import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.models.State;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.csula.storage.mysql.Database;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Collection<Generator> generators = new GeneratorsDAOImpl(new Database()).getAll();

		Collection<Event> events = new EventsDAOImpl(new Database()).getAll();

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
