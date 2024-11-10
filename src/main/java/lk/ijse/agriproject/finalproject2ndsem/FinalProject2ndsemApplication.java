package lk.ijse.agriproject.finalproject2ndsem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalProject2ndsemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProject2ndsemApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
