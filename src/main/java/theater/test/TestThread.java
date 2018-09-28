package theater.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import theater.entities.Actor;
import theater.entities.Show;
import theater.service.ActorService;
import theater.service.ShowService;

@Component
public class TestThread extends Thread {

	@Autowired
	private ShowService ss;
	@Autowired
	private ActorService as;

	@Override
	public void run() {
		try {
			Thread.sleep(2500);
			System.out.println("Test thread has started. SS=" + ss);
			System.out.println();

			Show show1 = ss.getShow("show1");
			System.out.println(show1);
			Show show2 = ss.getShow("show2");
			System.out.println(show2);
			Actor actor1 = as.getActor("actor1");
			System.out.println(actor1);
			Actor actor2 = as.getActor("actor2");
			System.out.println(actor2);

			ss.addActorToShow(show2, actor1);
			System.out.println("SHOW2 ACTORS=" + ss.getShowActors(show2));
			ss.removeActorFromShow(show2, actor1);
			System.out.println("SHOW2 ACTORS=" + ss.getShowActors(show2));
			as.addNaDate(actor1, LocalDate.now());
			as.addNaDate(actor2, LocalDate.of(2015, 5, 15));

			System.out.println("SHOW2 NAs=" + ss.getShowNaDates(show2));
			System.out.println("SHOW2 NA MAP=" + ss.getShowNaMap(show2));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
