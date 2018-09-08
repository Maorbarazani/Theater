package theater.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import theater.entities.Actor;
import theater.entities.Show;
import theater.service.ActorService;
import theater.service.ShowService;

@Component
@Scope("prototype")
public class TestThread extends Thread {

	@Autowired
	private ShowService ss;
	@Autowired
	private ActorService as;

	/**
	 * this method describes the thread actions: on every 24hour iteration the
	 * 'today' date is being updated, and then used in conjunction with 3 different
	 * DAO methods to find and remove expired coupons from wherever they appear.
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(2500);
			System.out.println("Test thread has started. SS=" + ss);
			System.out.println();

			// ss.createShow(new Show(0, "show2", null));
			// Actor actor1 = as.createActor(new Actor(0, "actor1", "1234", null));
			// Actor actor2 = as.createActor(new Actor(0, "actor2", "qwer", null));
			// Actor actor3 = as.createActor(new Actor(0, "actor3", "789", null));
			// ss.addActorToShow(ss.getShow("show1"), actor1);
			// ss.addActorToShow(ss.getShow("show1"), actor2);
			// ss.addActorToShow(ss.getShow("show1"), actor3);
			// System.out.println("SHOW1=" + ss.getShow("show1"));
			Show show1 = ss.getShow("show1");
			Actor actor3 = as.getActor("actor3");

			// as.addNaDate(actor3, LocalDate.now());
			// as.addNaDate(actor3, LocalDate.of(1999, 9, 9));

			System.out.println("SHOW1 ACTORS=" + ss.getShowActors(show1));
			System.out.println("SHOW1 NA/DATES=" + ss.getShowNaDates(show1));
			System.out.println("SHOW1 NA MAP= " + ss.getShowNaMap(show1));

			System.out.println("ACTOR3 NA DATES=" + as.getActorNaDates(actor3));
			System.out.println("ACTOR3 SHOWS=" + as.getActorShows(actor3));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
