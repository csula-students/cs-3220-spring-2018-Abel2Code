package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json
 * {
 *   "events": [
 *     { "id": 0, "name": "event-1", "description": "..." }
 *   ],
 *   "generators": [
 *     { "id": 0, "name": "generator-1", "description": "..." }
 *   ]
 * }
 * ```
 */
public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "generators";

	public GeneratorsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		// TODO: get a list of generators from the context
		Object data = context.getAttribute(CONTEXT_NAME);
		if(data == null){
			return new ArrayList<Generator>();
		}
		return (List<Generator>) data;
	}

	@Override
	public Optional<Generator> getById(int id) {
		List<Generator> list = getAll();

		for(int i = 0; i < list.size(); i++){
				if(list.get(i).getId() == id){
					return Optional.of(list.get(i));
				};
		}

		return Optional.empty();
	}

	@Override
	public void set(int id, Generator generator) {
		// TODO: change a certain generator from context
		List<Generator> list = getAll();

		for(int i = 0; i < list.size(); i++){
				if(list.get(i).getId() == id){
					list.set(i, generator);
				};
		}

		context.setAttribute(CONTEXT_NAME, list);
	}

	@Override
	public void add(Generator generator) {
		// TODO: add a new generator to the context

				List<Generator> list = getAll();
				list.add(generator);

				context.setAttribute(CONTEXT_NAME, list);
	}

	@Override
	public void remove(int id) {
		// TODO: remove a single generator from the context
		List<Generator> list = getAll();

		for(int i = 0; i < list.size(); i++){
				if(list.get(i).getId() == id){
					list.remove(i);
				};
		}

		context.setAttribute(CONTEXT_NAME, list);
	}
}
