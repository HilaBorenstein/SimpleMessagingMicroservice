package com.example.demo;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class MessageModelAssembler implements RepresentationModelAssembler<Message, EntityModel<Message>> {

  @Override
  public EntityModel<Message> toModel(Message message) {

    return EntityModel.of(message, //
        linkTo(methodOn(MessageController.class).findOne(message.getId())).withSelfRel(),
        linkTo(methodOn(MessageController.class).findAll()).withRel("messages"));
  }
  
  //@Override
  public EntityModel<Message> toModel(Message message,User user) {
	  
	// Unconditional links to single-item resource and aggregate root
	  EntityModel<Message> messageModel = EntityModel.of(message,
			    linkTo(methodOn(MessageController.class).findOne(message.getId())).withSelfRel(),
			    linkTo(methodOn(MessageController.class).findAll()).withRel("messages"));
	// Conditional links based on state of the order
	  if (message.getReceiver().getId() == user.getId()) {
		  messageModel.add(linkTo(methodOn(MessageController.class).findOne(message.getId())).withRel("complete"));
		  //messageModel.add(linkTo(methodOn(MessageController.class).complete(order.getId())).withRel("complete"));
		}

		return messageModel;
  }
}


