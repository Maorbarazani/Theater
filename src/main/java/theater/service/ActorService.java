package theater.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import theater.entities.Actor;
import theater.entities.Show;
import theater.repository.ActorRepository;
import theater.repository.ShowRepository;

@Service
public class ActorService {

	@Autowired
	private ShowRepository showRepo;
	@Autowired
	private ActorRepository actorRepo;

	public List<Actor> getAllActors() throws Exception {
		try {
			List<Actor> actors = actorRepo.findAll();
			if (!actors.isEmpty()) {
				return actors;
			} else {
				throw new Exception("No actors exist");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public Actor getActor(String name) throws Exception {
		try {
			Actor actor = actorRepo.findByName(name);
			if (actor != null) {
				return actor;
			} else {
				throw new Exception("This show does not exist");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public Actor createActor(Actor actor) throws Exception {
		if (actorRepo.findByName(actor.getName()) != null) {
			throw new Exception("Actor with name *" + actor.getName() + "* already exists");
		}
		if (actor.getName() != null) {
			try {
				Actor created = actorRepo.save(actor);
				return created;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Actor name cannot be null");
		}
	}

	public void removeActor(Actor actor) throws Exception {
		if (actor.getName() != null) {
			try {
				actorRepo.delete(actor);
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Actor name cannot be null");
		}
	}

	public void updateActorPassword(Actor actor, String newPass) throws Exception {
		actor = actorRepo.findByName(actor.getName());
		if (actor != null & newPass.trim() != null) {
			try {
				actorRepo.updateActorPassword(actor.getName(), newPass);
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Password and/or actor name cannot be null");
		}
	}

	public Set<LocalDate> getActorNaDates(Actor actor) throws Exception {
		actor = actorRepo.findByName(actor.getName());
		if (actor != null) {
			try {
				Set<LocalDate> naDates = actor.getNaDates();
				return naDates;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Actor name cannot be null");
		}
	}

	public Set<Show> getActorShows(Actor actor) throws Exception {
		actor = actorRepo.findByName(actor.getName());
		if (actor != null) {
			try {
				Set<Show> shows = showRepo.findByActorsContaining(actor); // TODO just trying that. might have to go
																			// plain java sorting
				return shows;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Actor name cannot be null");
		}
	}

	public void addNaDate(Actor actor, LocalDate date) throws Exception {
		actor = actorRepo.findByName(actor.getName());
		if (actor != null || date != null) {
			try {
				actor.getNaDates().add(date);
				actorRepo.save(actor);
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Actor name and/or date cannot be null");
		}
	}

	public void removeNaDate(Actor actor, LocalDate date) throws Exception {
		actor = actorRepo.findByName(actor.getName());
		if (actor != null || date != null) {
			try {
				Set<LocalDate> naDates = actor.getNaDates();
				if (naDates.contains(date)) {
					naDates.remove(date);
					actorRepo.save(actor);
				}
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("Actor name and/or date cannot be null");
		}
	}

}
