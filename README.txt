
The REST command in my microservice is:


For Users( I did not use HATEOUS in this controller-sorry I have not enough time):
# get all usersGET http://localhost:8080/users/Example:
 

# get all messages per a single user using an id parameter-THIS IS WHAT I ASKEDGET http://localhost:8080/users/{id}Example:
 
# get all messages per a single user using an name parameter-GET http://localhost:8080/users/name/{name}Example: 

# create an userPOST http://localhost:8080/users/
# update a userPUT http://localhost:8080/users/
# delete an user using an id parameterDELETE http://localhost:8080/users/{id}

for Messages:
# get all messagesGET http://localhost:8080/messages/Example: 

# get all messages per a single user using an id parameter -THIS IS WHAT I ASKEDGET http://localhost:8080/message/usr/{id}Example: 

# get a single message using an id parameterGET http://localhost:8080/message/msg/{id}
Example: 

# create an message POST http://localhost:8080/message/

# create a message with sender user and receiver user -THIS IS WHAT I ASKEDPUT http://localhost:8080/message/
# delete an message using an id parameterDELETE http://localhost:8080/message/{id}
