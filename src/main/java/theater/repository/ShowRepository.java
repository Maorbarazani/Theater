package theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import theater.entities.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {

	public Show findByName(String name);

	public Show findById(long id); // alternative to the generic findById() of the JpaRepository

	// @Modifying
	// @Transactional
	// @Query("update Company c set c.email =?2 where c.compName=?1")
	// public void updateCompanyEmail(String compName, String email);

}
