package theater.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import theater.entities.Actor;
import theater.entities.Show;
import theater.repository.ActorRepository;
import theater.repository.ShowRepository;

@Service
public class ShowService {

	@Autowired
	private ShowRepository showRepo;
	@Autowired
	private ActorRepository actorRepo;

	public List<Show> getAllShows() throws Exception {
		try {
			List<Show> shows = showRepo.findAll();
			if (!shows.isEmpty()) {
				return shows;
			} else {
				throw new Exception("No shows exist");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public Show getShow(String name) throws Exception {
		try {
			Show show = showRepo.findByName(name);
			if (show != null) {
				return show;
			} else {
				throw new Exception("Show *" + name + "* does not exist");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public Show createShow(Show show) throws Exception {
		if (showRepo.findByName(show.getName()) != null) {
			throw new Exception("Show with name *" + show.getName() + "* already exists");
		}
		if (show.getName() != null) {
			try {
				Show created = showRepo.save(show);
				return created;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Show name cannot be null");
		}
	}

	public void removeShow(Show show) throws Exception {
		if (show.getName() != null) {
			try {
				showRepo.delete(show);
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Show name cannot be null");
		}
	}

	public Show addActorToShow(Show show, Actor actor) throws Exception {
		show = showRepo.findByName(show.getName());
		actor = actorRepo.findByName(actor.getName());
		if (show != null && actor != null) {
			try {
				show.getActors().add(actor);
				Show updated = showRepo.save(show);
				return updated;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Show and/or actor name cannot be null");
		}
	}

	public Show removeActorFromShow(Show show, Actor actor) throws Exception {
		show = showRepo.findByName(show.getName());
		actor = actorRepo.findByName(actor.getName());
		if (show != null && actor != null) {
			try {
				if (show.getActors().contains(actor)) {
					show.getActors().remove(actor);
					Show updated = showRepo.save(show);
					return updated;
				} else {
					throw new Exception(
							"Actor *" + actor.getName() + "* does not exist in show *" + show.getName() + "*");
				}
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Show and/or actor name cannot be null");
		}
	}

	public Set<Actor> getShowActors(Show show) throws Exception {
		show = showRepo.findByName(show.getName());
		if (show != null) {
			try {
				Set<Actor> actors = show.getActors();
				if (!actors.isEmpty()) {
					return actors;
				} else {
					throw new Exception("Show *" + show.getName() + "* has no actors");
				}
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Show name cannot be null");
		}
	}

	public Set<LocalDate> getShowNaDates(Show show) throws Exception {
		show = showRepo.findByName(show.getName());
		if (show != null) {
			Set<LocalDate> naDates = new HashSet<LocalDate>();
			try {
				Set<Actor> actors = show.getActors();
				for (Actor actor : actors) {
					Set<LocalDate> dates = actor.getNaDates();
					for (LocalDate date : dates) {
						if (!naDates.contains(date)) {
							naDates.add(date);
						}
					}
				}

				if (!naDates.isEmpty()) {
					return naDates;
				} else {
					throw new Exception("No unavailable dates for show " + show.getName() + "* found");
				}
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Show name cannot be null");
		}
	}

	public Map<LocalDate, Set<Actor>> getShowNaMap(Show show) throws Exception {
		show = showRepo.findByName(show.getName());
		if (show != null) {
			Map<LocalDate, Set<Actor>> map = new HashMap<>();
			Set<Actor> actors = show.getActors();
			for (Actor actor : actors) {
				Set<LocalDate> dates = actor.getNaDates();
				for (LocalDate date : dates) {
					if (!map.containsKey(date)) {
						Set<Actor> absents = new HashSet<>();
						absents.add(actor);
						map.put(date, absents);
					} else {
						Set<Actor> absents = map.get(date);
						absents.add(actor);
						map.put(date, absents);
					}
				}
			}
			if (!map.isEmpty()) {
				return map;
			} else {
				throw new Exception("No unavailable dates specified for show *" + show.getName() + "*");
			}
		} else {
			throw new Exception("Show name cannot be null");
		}
	}

}
