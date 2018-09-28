package theater.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import theater.entities.Actor;
import theater.entities.Show;
import theater.service.ActorService;
import theater.service.ShowService;

@RestController
@RequestMapping("admin")
// @Scope("session") //TODO work on this
@CrossOrigin(origins = "http://localhost:4200") // TODO for testing purposes only while building client-side
public class AdminController {

	@Autowired
	private ShowService ss;
	@Autowired
	private ActorService as;

	@RequestMapping(value = "test", method = RequestMethod.POST)
	public ResponseEntity<String> test(@RequestBody Integer num, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("TEST INVOKED. num=" + num);
		try {
			return new ResponseEntity<String>("Test has worked, biatch!", HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getAllShows", method = RequestMethod.GET)
	public ResponseEntity<?> getAllShows(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Show> shows = ss.getAllShows();
			return new ResponseEntity<List<Show>>(shows, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getShow/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getShow(@PathVariable String name, HttpServletRequest req, HttpServletResponse resp) {
		try {
			Show s = ss.getShow(name);
			return new ResponseEntity<Show>(s, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "createShow", method = RequestMethod.POST)
	public ResponseEntity<String> createShow(@RequestBody Show show, HttpServletRequest req, HttpServletResponse resp) {
		try {
			ss.createShow(show);
			String successMsg = ("Successfully created show *" + show.getName() + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "removeShow", method = RequestMethod.POST)
	public ResponseEntity<String> removeShow(@RequestBody Show show, HttpServletRequest req, HttpServletResponse resp) {
		try {
			ss.removeShow(show);
			String successMsg = ("Successfully removed show *" + show.getName() + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getAllActors", method = RequestMethod.GET)
	public ResponseEntity<?> getAllActors(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Actor> list = as.getAllActors();
			return new ResponseEntity<List<Actor>>(list, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getActor/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getActor(@PathVariable String name, HttpServletRequest req, HttpServletResponse resp) {
		try {
			Actor a = as.getActor(name);
			return new ResponseEntity<Actor>(a, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "createActor", method = RequestMethod.POST)
	public ResponseEntity<String> createActor(@RequestBody Actor actor, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			as.createActor(actor);
			String successMsg = ("Successfully created actor *" + actor.getName() + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "removeActor", method = RequestMethod.POST)
	public ResponseEntity<String> removeActor(@RequestBody Actor actor, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			as.removeActor(actor);
			String successMsg = ("Successfully removed actor *" + actor.getName() + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "updateActorPassword", method = RequestMethod.PUT)
	public ResponseEntity<String> updateActorPassword(@RequestBody Actor actor, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			as.updateActorPassword(actor, actor.getPassword());
			String successMsg = ("Successfully updated actor *" + actor.getName() + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "addActorToShow/{showName}", method = RequestMethod.POST)
	public ResponseEntity<String> addActorToShow(@PathVariable String showName, @RequestBody Actor actor,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			Show s = ss.getShow(showName);
			Actor a = as.getActor(actor.getName());
			ss.addActorToShow(s, a);
			String successMsg = ("Successfully added *" + a.getName() + "* to *" + s.getName() + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "removeActorFromShow/{showName}", method = RequestMethod.POST)
	public ResponseEntity<String> removeActorFromShow(@PathVariable String showName, @RequestBody Actor actor,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			Show s = ss.getShow(showName);
			Actor a = as.getActor(actor.getName());
			ss.removeActorFromShow(s, a);
			String successMsg = ("Successfully removed *" + a.getName() + "* from *" + s.getName() + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getShowActors/{showName}", method = RequestMethod.GET)
	public ResponseEntity<?> getShowActors(@PathVariable String showName, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			Show s = ss.getShow(showName);
			Set<Actor> actors = ss.getShowActors(s);
			return new ResponseEntity<Set<Actor>>(actors, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getShowNaDates/{showName}", method = RequestMethod.GET)
	public ResponseEntity<?> getShowNaDates(@PathVariable String showName, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			Show s = ss.getShow(showName);
			Set<LocalDate> naDates = ss.getShowNaDates(s);
			return new ResponseEntity<Set<LocalDate>>(naDates, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getShowNaMap/{showName}", method = RequestMethod.GET)
	public ResponseEntity<?> getShowNaMap(@PathVariable String showName, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			Show s = ss.getShow(showName);
			Map<LocalDate, Set<Actor>> naMap = ss.getShowNaMap(s);
			return new ResponseEntity<Map<LocalDate, Set<Actor>>>(naMap, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getActorNaDates/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getActorNaDates(@PathVariable String name, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			Actor a = as.getActor(name);
			Set<LocalDate> naDates = as.getActorNaDates(a);
			return new ResponseEntity<Set<LocalDate>>(naDates, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getActorShows/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getActorShows(@PathVariable String name, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			Actor a = as.getActor(name);
			Set<Show> shows = as.getActorShows(a);
			return new ResponseEntity<Set<Show>>(shows, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
