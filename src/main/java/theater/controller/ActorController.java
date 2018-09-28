package theater.controller;

import java.time.LocalDate;
import java.util.Set;

import javax.annotation.PostConstruct;
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
import theater.service.ActorService;

@RestController
@RequestMapping("actor")
// @Scope("session") //TODO work on this
@CrossOrigin(origins = "http://localhost:4200") // TODO for testing purposes only while building client-side
public class ActorController {

	@Autowired
	private ActorService as;

	private Actor currentActor;

	@PostConstruct
	public void setCurrentActor() {
		// String name = (String) this.session.getAttribute("name");
		// TODO find a way to populate this per session, after successful login
		// ALREADY TESTED: @Scope("session") means this bean/class is constructed only
		// AFTER a specific request was made to one of the methods HERE, /actor/*
		String name = "actor1";
		try {
			this.currentActor = as.getActor(name);
			System.out.println("## setCurrentActor @ ActorController: currentActor= " + currentActor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "getNaDates", method = RequestMethod.GET)
	public ResponseEntity<?> getNaDates(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Set<LocalDate> naDates = as.getActorNaDates(currentActor);
			return new ResponseEntity<Set<LocalDate>>(naDates, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "addNaDate/{year}/{month}/{day}", method = RequestMethod.POST)
	public ResponseEntity<String> addNaDate(@PathVariable int year, @PathVariable int month, @PathVariable int day,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			LocalDate date = LocalDate.of(year, month, day);
			as.addNaDate(currentActor, date);
			String successMsg = ("Successfully added N/A date *" + date + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "removeNaDate/{year}/{month}/{day}", method = RequestMethod.POST)
	public ResponseEntity<String> removeNaDate(@PathVariable int year, @PathVariable int month, @PathVariable int day,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			LocalDate date = LocalDate.of(year, month, day);
			as.removeNaDate(currentActor, date);
			String successMsg = ("Successfully removed N/A date *" + date + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "updatePassword", method = RequestMethod.PUT)
	public ResponseEntity<String> updatePassword(@RequestBody Actor actor, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			as.updateActorPassword(currentActor, actor.getPassword());
			String successMsg = ("Successfully updated actor *" + currentActor.getName() + "*");
			return new ResponseEntity<String>(successMsg, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("## EXCEPTION: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
