package by.theiou.WeatherSensorServer.services;

import by.theiou.WeatherSensorServer.models.Weather;
import by.theiou.WeatherSensorServer.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    public void save(Weather weather){
        enrichWeather(weather);
        weatherRepository.save(weather);
    }

    private Weather enrichWeather(Weather weather){
        weather.setCreatedAt(LocalDateTime.now());
        return weather;
    }

    public int getRainyDaysCount(){
       return weatherRepository.countByRaining("true");
    }

    public List<Weather> getAllMeasurements(){
        return weatherRepository.findAll();
    }

}
