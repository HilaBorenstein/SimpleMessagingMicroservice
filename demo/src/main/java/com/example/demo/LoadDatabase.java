package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  
  User Ross= new User("Ross", "Geller");
  User Rachel=new User("Rachel","Greene");
  User Phoebe=new User("Phoebe","Buffay");
  User Emily=new User("Emily","Waltham");
  User Joey=new User("Joey","Tribiani");
  User Chandler=new User("Chandler","Bing");
  User Monica=new User("Monica","Geller");
  User Amy=new User("Amy","Greene");
  

  @Bean
  CommandLineRunner initDatabase(UserRepository repository,MessageRepository repository2) {

    return args -> {
      log.info("Preloading " + repository.save(Ross));
      log.info("Preloading " + repository.save(Rachel));
      log.info("Preloading " + repository.save(Joey));
      log.info("Preloading " + repository.save(Phoebe));
      log.info("Preloading " + repository.save(Chandler));
      log.info("Preloading " + repository.save(Monica));
      log.info("Preloading " + repository.save(Emily));
      log.info("Preloading " + repository.save(Amy));
  	  log.info("Preloading " + repository2.save(new Message("WE WHERE ON A BREAK!",Ross ,Rachel,"1/1/2015 20:00:01")));
  	  log.info("Preloading " + repository2.save(new Message("OH! MY EYES! MY  EYES!",Phoebe,Rachel,"1/1/2016 18:30:01")));
  	  log.info("Preloading " + repository2.save(new Message("I Ross take thee Rachel",Ross,Emily,"14/1/2016 18:30:01")));
  	  log.info("Preloading " + repository2.save(new Message("Joey Doesn't share food!",Joey,Phoebe,"14/1/2016 18:30:01")));
  	  log.info("Preloading " + repository2.save(new Message("How you doin’?",Joey,Monica,"14/11/2015 18:30:01")));
  	  log.info("Preloading " + repository2.save(new Message("You can’t just give up. Is that what a dinosaur would do??",Joey,Ross,"14/10/2012 17:30:01")));
  	  log.info("Preloading " + repository2.save(new Message("I’m Chandler. I make jokes when I’m uncomfortable.",Chandler,Monica,"14/10/2019 18:30:01")));
  	  log.info("Preloading " + repository2.save(new Message("Unagi!",Ross,Phoebe,"14/5/2009 18:30:01")));
  	  log.info("Preloading " + repository2.save(new Message("No more falafel for you!",Ross,Amy,"14/5/2009 18:30:01")));
  	  log.info("Preloading " + repository2.save(new Message("Something is wrong with the left phalange!",Phoebe,Rachel,"14/5/2019 18:30:01")));
  	  log.info("Preloading " + repository2.save(new Message("I KNOW!",Monica,Rachel,"14/4/2019 18:30:01")));
  	log.info("Preloading " + repository2.save(new Message("SOMEONE ATE MY SANDWITCH!",Ross,Monica,"15/4/2019 18:30:01")));
	
    };
  }


  
  
}

