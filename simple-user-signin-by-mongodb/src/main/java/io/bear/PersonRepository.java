package io.bear;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface PersonRepository extends MongoRepository<Person,String> {
	   public Person findByName(String name);
}
