package co.simplon;

import co.simplon.dao.HotelRepository;
import co.simplon.dao.UserRepository;
import co.simplon.dao.VilleRepository;
import co.simplon.entities.Hotel;
import co.simplon.entities.User;
import co.simplon.entities.Ville;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;


@SpringBootApplication
public class LightLocationV1Application implements CommandLineRunner  {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RepositoryRestConfiguration restConfiguration;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VilleRepository villeRepository;
	public static void main(String[] args) {
		SpringApplication.run(LightLocationV1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Hotel.class,Ville.class,User.class);
		villeRepository.save( new Ville(null,"Malibu",null,null,null ));
		villeRepository.save( new Ville(null,"papete",null,null,null ));
		villeRepository.save(new Ville(null,"Saintgilles",null,null,null ));
		Random rnd= new Random();
		villeRepository.findAll().forEach(v-> {
			for(int i=0;i<10;i++) {
				Hotel h = new Hotel();
				h.setName(RandomString.make(18));
				h.setPrice(100 + rnd.nextInt(10000));
				h.setAvailable(rnd.nextBoolean());
				h.setNumberChambre(rnd.nextInt());
				h.setPhone(1+rnd.nextInt(10));
				h.setAdresse(rnd.toString());
				h.setNumberPersonne(1+rnd.nextInt(10));
				h.setNumberEtoile(1+rnd.nextInt(5));
				h.setSelected(rnd.nextBoolean());
				h.setVille(v);
				h.setPhotoName("wordpress-categories-640x400 (1).png");
				hotelRepository.save(h);

			}
		});
		userRepository.save(new User(null,"hotelier","azerty","hotelier"));
		userRepository.save(new User(null,"julien","chedotal","user"));
		userRepository.save(new User(null,"franck","mbajoumbe","user"));
		userRepository.save((new User(null,"timothe","laude","user")));

	}
}

