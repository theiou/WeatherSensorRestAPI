package by.theiou.WeatherSensorServer;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherSensorServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherSensorServerApplication.class, args);
	}
	@Bean
	public ModelMapper ModelMapper(){
		return new ModelMapper();
	}

}
