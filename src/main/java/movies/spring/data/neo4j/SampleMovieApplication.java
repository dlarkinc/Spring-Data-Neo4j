package movies.spring.data.neo4j;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.domain.Person;
import movies.spring.data.neo4j.domain.Role;
import movies.spring.data.neo4j.repositories.MovieRepository;
import movies.spring.data.neo4j.repositories.PersonRepository;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 */
@SpringBootApplication
@EntityScan("movies.spring.data.neo4j.domain")
public class SampleMovieApplication implements CommandLineRunner{

	@Autowired
	private Session session;

	@Autowired
	private MovieRepository instance;

	@Autowired
	private PersonRepository personRepository;
	
	public static void main(String[] args) {
				SpringApplication.run(SampleMovieApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

			instance.deleteAll();
		
			Movie spiderMan = new Movie("Spider-Man", 2002);
			instance.save(spiderMan);

			Movie matrix = new Movie("The Matrix", 1999);
			instance.save(matrix);
			
			Movie johnWick = new Movie("John Wick", 2014);
			instance.save(johnWick);
			
			Person keanu = new Person("Keanu Reeves");
			personRepository.save(keanu);

			Role neo = new Role(matrix, keanu);
			neo.addRoleName("Neo");
			matrix.addRole(neo);
			instance.save(matrix);

			Role john = new Role(johnWick, keanu);
			neo.addRoleName("John Wick");
			johnWick.addRole(john);

			Person willem = new Person("Willem Dafoe");
			personRepository.save(willem);

			Role greenGoblin = new Role(spiderMan, willem);
			greenGoblin.addRoleName("Green Goblin");
			spiderMan.addRole(greenGoblin);
			instance.save(spiderMan);
			
			Role marcus = new Role(johnWick, willem);
			marcus.addRoleName("Marcus");
			johnWick.addRole(marcus);
			instance.save(johnWick);
	}
}
