package theater.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(nullable = false)
	private String password;
	@ElementCollection(fetch = FetchType.EAGER, targetClass = LocalDate.class)
	private Set<LocalDate> naDates;

	public Actor() {
		super();
	}

	public Actor(long id, String name, String password, Set<LocalDate> naDates) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.naDates = naDates;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<LocalDate> getNaDates() {
		return naDates;
	}

	public void setNaDates(Set<LocalDate> naDates) {
		this.naDates = naDates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
