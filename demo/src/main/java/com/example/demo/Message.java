package com.example.demo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/* for Myself
 * 
 * this is simple message class i created
 * it have content date sender and receiver
 * 
 */


/* for Myself
 * 
 * @Entity is a JPA annotation to make this object ready for storage in a JPA-based data store.
 * 
 */
@Entity
public class Message {
	
	private @Id @GeneratedValue Long id;
	private String content;
	private String date;
	@OneToOne
	@JoinColumn(name="sender_id")
	private User sender;
	@OneToOne
	@JoinColumn(name="receiver_id")
	private User receiver;

	
	Message() {}

	Message(String content, User sender,User receiver,String date) {

	    this.content = content;
	    this.sender = sender;
	    this.receiver = receiver;
	    this.date=date;
	  }

	  public Long getId() {
	    return this.id;
	  }

	  public String getContent() {
	    return this.content;
	  }

	  public User getSender() {
	    return this.sender;
	  }
	  
	  public User getReceiver() {
		    return this.receiver;
		  }
	  
	  public String getDate() {
		    return this.date;
		  }

	  public void setId(Long id) {
	    this.id = id;
	  }
	  
	  public void setContent(String content) {
		    this.content = content;
		  }

	  public void setSender(User sender) {
	    this.sender = sender;
	  }

	  public void setReceiver(User receiver) {
		    this.receiver = receiver;
		  }
	  public void setDate(String date) {
		    this.date = date;
		  }
	  @Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Message))
	      return false;
	    Message message = (Message) o;
	    return Objects.equals(this.id, message.id) && Objects.equals(this.content, message.content)
	        && Objects.equals(this.sender, message.sender) && Objects.equals(this.receiver, message.receiver);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.content, this.sender,this.receiver);
	  }

	  @Override
	  public String toString() {
	    return "Message{" + "id=" + this.id + ", Sender='" + this.sender  + "', Receiver='"+ this.receiver + "', Date: '" +this.date +"', Content: '" +this.content+ "'}'";
	  }
	
}
