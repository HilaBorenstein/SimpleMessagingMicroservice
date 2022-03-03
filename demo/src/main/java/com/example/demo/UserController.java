package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {

  private final UserRepository repository;
  
  private final MessageRepository m_repository;

  UserController(UserRepository repository, MessageRepository m_repository) {
    this.repository = repository;
    this.m_repository=m_repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/users")
  List<User> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]


  @GetMapping("/users/{id}")
  List<Message> one(@PathVariable Long id) {	  
  List<Message> all=m_repository.findAll();
  List<Message> part=new ArrayList<Message>();
  for(Message temp: all)
	{
		if(temp.getReceiver().getId()==id)//user.getId())
			part.add(temp);				
	}
  if(part.size()==0) throw new MessageNotFoundException();
	return part;
  }
	
 /* 
  @GetMapping("/users/{id}")
  User one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
   
  }*/
  
  @GetMapping("/users/name/{name}")
  List<Message> search_one(@PathVariable String name) {	
	  
  List<Message> all=m_repository.findAll();
  List<Message> part=new ArrayList<Message>();
  for(Message temp: all)
	{
		if(temp.getReceiver().getName().toLowerCase().equals(name.toLowerCase()))       
			part.add(temp);				
	}
  	if(part.size()==0) throw new MessageNotFoundException();
	return part;
  }
  
  @PostMapping("/users")
  User newUser(@RequestBody User newUser) {
    return repository.save(newUser);
  }

  // Single item
  


  @PutMapping("/users/{id}")
  User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(user -> {
    	  user.setName(newUser.getName());
        user.setLastName(newUser.getLastName());
        return repository.save(user);
      })
      .orElseGet(() -> {
    	  newUser.setId(id);
        return repository.save(newUser);
      });
  }

  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Long id) {
    repository.deleteById(id);
  }
}