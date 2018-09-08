package theater.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
			// ss.createShow(new Show(0, "testShow2", null));
			// System.out.println(ss.createShow(new Show(0, "testShow3", null)));
			System.out.println();
			System.out.println("AllShows=" + ss.getAllShows());
			// Show show3 = ss.getShow("testShow3");
			// System.out.println("Show3=" + show3);
			// ss.removeShow(show3);
			Show show2 = ss.getShow("testShow2");
			// System.out.println("show2 actors=" + ss.getShowActors(show2));

			// as.createActor(new Actor(0, "actor2", "1234", null));
			// Actor actor2 = as.getActor("actor2");
			// ss.addActorToShow(show2, actor2);
			//
			// as.createActor(new Actor(0, "actor3", "1234", null));
			// Actor actor3 = as.getActor("actor3");
			// ss.addActorToShow(show2, actor3);

			System.out.println("show2 actors=" + ss.getShowActors(show2));
			// ss.removeActorFromShow(show2, actor3);
			System.out.println("all acotrs= " + as.getAllActors());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
