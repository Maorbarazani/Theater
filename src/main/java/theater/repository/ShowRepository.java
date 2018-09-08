package theater.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import theater.entities.Actor;
import theater.entities.Show;
@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

	public Show findByName(String name);

	public Show findById(long id); // alternative to the generic findById() of the JpaRepository

	public Set<Show> findByActorsContaining(Actor actor);

	// @Modifying
	// @Transactional
	// @Query("update Company c set c.email =?2 where c.compName=?1")
	// public void updateCompanyEmail(String compName, String email);

}
