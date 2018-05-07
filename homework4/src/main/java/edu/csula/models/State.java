package edu.csula.models;

import java.util.Collection;

public class State {
  String example = "Hello custom element";
  int counter = 0;
  Collection<Generator> generators;
  Collection<Event> story;

	public State(Collection<Generator> generators, Collection<Event> events) {
		this.generators = generators;
    this.story = events;
	}
}
