package com.example.demo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/* for Myself
 * 
 * @Entity is a JPA annotation to make this object ready for storage in a JPA-based data store.
 * 
 */
@Entity
public class User {
	
	private @Id @GeneratedValue Long id;
	private String name;
	private String last_name;

	
	User() {}

	User(String name, String last_name) {

	    this.name = name;
	    this.last_name = last_name;
	  }

	  public Long getId() {
	    return this.id;
	  }

	  public String getName() {
	    return this.name;
	  }

	  public String getLastName() {
	    return this.last_name;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public void setLastName(String last_name) {
	    this.last_name = last_name;
	  }

	  @Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof User))
	      return false;
	    User user = (User) o;
	    return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
	        && Objects.equals(this.last_name, user.last_name);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.name, this.last_name);
	  }

	  @Override
	  public String toString() {
	    return "User{" + "id=" + this.id + ", name='" + this.name + " "+ this.last_name + '\'' + '}';
	  }
	
}
