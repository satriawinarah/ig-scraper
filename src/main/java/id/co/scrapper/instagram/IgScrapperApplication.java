package id.co.scrapper.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("id.co.scrapper.instagram.entity")
public class IgScrapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgScrapperApplication.class, args);
	}

}
