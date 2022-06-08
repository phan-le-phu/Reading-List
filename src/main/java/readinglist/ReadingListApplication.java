package readinglist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReadingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingListApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ReaderRepository repository) {
		return (args -> {
			repository.save(new Reader("phu", "phanlephu", "{noop}123456"));
		});
	}

}
