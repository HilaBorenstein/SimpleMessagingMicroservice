package com.example.demo;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//i followed this tutorial https://spring.io/guides/tutorials/rest/


@RestController
public class MessageController {


	  private final MessageRepository repository;
	  
	//  private final UserRepository user_repository;
	  
	  private final MessageModelAssembler assembler;

	  MessageController(MessageRepository repository, MessageModelAssembler assembler) {
	    this.repository = repository;
	    this.assembler = assembler;
	  }

	  
	  @GetMapping("/messages")
	  ResponseEntity<CollectionModel<EntityModel<Message>>> findAll() {
		  
			List<EntityModel<Message>> messages = StreamSupport.stream(repository.findAll().spliterator(), false)
					.map(message -> EntityModel.of(message, //
							linkTo(methodOn(MessageController.class).findOne(message.getId())).withSelfRel(), //
							linkTo(methodOn(MessageController.class).findAll()).withRel("messages"))) //
					.collect(Collectors.toList());

			return ResponseEntity.ok( //
					CollectionModel.of(messages, //
							linkTo(methodOn(MessageController.class).findAll()).withSelfRel()));
		  
	  }
	  /*
	  // Aggregate root
	  // tag::get-aggregate-root[]
	  @GetMapping("/messages")
	  List<Message> all() {
	    return repository.findAll();
	  }
	  // end::get-aggregate-root[]  
	  */

	  @PostMapping("/messages")
	  Message newMessage(@RequestBody Message newMessage) {
	    return repository.save(newMessage);
	  }
	  /*
	  /*send message parameters: sender receiver message
	  @PostMapping("/messages")
	  Message newMessage(@PathVariable  User sender,@PathVariable User receiver,@PathVariable String content) {
	    return repository.save(new Message(content,sender,receiver,"1/1/2022 00:00:00"));
	  }*/
	  
	  @GetMapping(path="/")
	  public String sayHello() {
	   return "Messaging App";
	  }

	  
	  @GetMapping("/messages/msg/{id}")
	  EntityModel<Message> findOne(@PathVariable Long id) {

		  Message message = repository.findById(id) //
	        .orElseThrow(() -> new MessageNotFoundException(id));

		  return assembler.toModel(message);
	  }
	  
	  /**********MY BALAGAN**********/
	 
	  @GetMapping("/messages/usr/{id}")
	  ResponseEntity<CollectionModel<EntityModel<Message>>>  getAllMessagesPerUser(/*@PathVariable Message message,*/@PathVariable Long id/*,@PathVariable User user*/) {
	//List<Message>  getAllMessagesPerUser(/*@PathVariable Message message,*/@PathVariable Long id/*,@PathVariable User user*/) {

		  List<EntityModel<Message>> messages=new ArrayList<EntityModel<Message>>() ;	  
		  List<Message> all=repository.findAll();
		  List<Message> part=new ArrayList<Message>();
		  for(Message temp: all)
			{
				if(temp.getReceiver().getId()==id)//user.getId())
					part.add(temp);				
			}
			
			for(Message temp: part)
			{
				  EntityModel<Message> messageModel = EntityModel.of(temp,
						    linkTo(methodOn(MessageController.class).findOne(temp.getId())).withSelfRel(),
						    linkTo(methodOn(MessageController.class).findAll()).withRel("messages"));	
				  messages.add(messageModel);
			}
			//return all;
			if(part.size()==0) throw new MessageNotFoundException();
			
			return
		 	ResponseEntity.ok( //
				CollectionModel.of(messages, //
						linkTo(methodOn(MessageController.class).findAll()).withSelfRel()));
			
}
	/*
	  // Single item  
	  @GetMapping("/messages/{id}")
	  Message one(@PathVariable Long id) {
	    
	    return repository.findById(id)
	      .orElseThrow(() -> new MessageNotFoundException(id));
	  }*/

	  @PutMapping("/messages/{id}")
	  ResponseEntity<?> replaceMessage(@RequestBody Message newMessage, @PathVariable Long id, @PathVariable User s, @PathVariable User r) {

		  Message updatedMessage = repository.findById(id) //
	        .map(message -> {
		    	  message.setContent(newMessage.getContent());
		    	  message.setDate(newMessage.getDate());
		    	  message.setSender(newMessage.getSender());
		    	  message.setReceiver(newMessage.getReceiver());
	          return repository.save(message);
	        }) //
	        .orElseGet(() -> {
	        	newMessage.setId(id);
	          return repository.save(newMessage);
	        });

	    EntityModel<Message> entityModel = assembler.toModel(updatedMessage);

	    return ResponseEntity //
	        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	        .body(entityModel);
	  }
	  
	  /*
	  @PutMapping("/messages/{id}")
	  Message replaceMessage(@RequestBody Message newMessage, @PathVariable Long id) {
	    
	    return repository.findById(id)
	      .map(message -> {
	    	  message.setContent(newMessage.getContent());
	    	  message.setDate(newMessage.getDate());
	    	  message.setSender(newMessage.getSender());
	    	  message.setReceiver(newMessage.getReceiver());
	        return repository.save(message);
	      })
	      .orElseGet(() -> {
	        newMessage.setId(id);
	        return repository.save(newMessage);
	      });
	  }
	  */
	  @DeleteMapping("/messages/{id}")
	  ResponseEntity<?> deleteMessage(@PathVariable Long id) {

	    repository.deleteById(id);

	    return ResponseEntity.noContent().build();
	  }
	  /*
	  @DeleteMapping("/messages/{id}")
	  void deleteMessage(@PathVariable Long id) {
	    repository.deleteById(id);
	  }*/
}
