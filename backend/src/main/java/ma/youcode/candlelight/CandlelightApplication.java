package ma.youcode.candlelight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class CandlelightApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandlelightApplication.class, args);
	}

}
