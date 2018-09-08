package theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import theater.entities.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

	public Actor findByName(String name);

	public Actor findById(long id); // alternative to the generic findById() of the JpaRepository

	@Modifying
	@Transactional
	@Query("update Actor a set a.password =?2 where a.name=?1")
	public void updateActorPassword(String name, String password);
}
