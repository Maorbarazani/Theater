package theater.service;

import java.util.Date;
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

	public Actor createActor(Actor actor) throws Exception {
		if (actor.getName() != null && actorRepo.findByName(actor.getName()) == null) {
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

	public Set<Date> getActorNaDates(Actor actor) throws Exception {
		actor = actorRepo.findByName(actor.getName());
		if (actor != null) {
			try {
				Set<Date> naDates = actor.getNaDates();
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

	public void addNaDate(Actor actor, Date date) throws Exception {
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

	public void removeNaDate(Actor actor, Date date) throws Exception {
		actor = actorRepo.findByName(actor.getName());
		if (actor != null || date != null) {
			try {
				Set<Date> naDates = actor.getNaDates();
				if (!naDates.contains(date)) {
					naDates.add(date);
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
