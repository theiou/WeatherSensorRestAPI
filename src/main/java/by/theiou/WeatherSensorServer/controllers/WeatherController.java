package by.theiou.WeatherSensorServer.controllers;

import by.theiou.WeatherSensorServer.dto.WeatherDTO;
import by.theiou.WeatherSensorServer.models.Weather;
import by.theiou.WeatherSensorServer.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody WeatherDTO weatherDTO){
        weatherService.save(convertToWeather(weatherDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<Weather> getAllMeasurements(){
        return weatherService.getAllMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount(){
        return weatherService.getRainyDaysCount();
    }

    private Weather convertToWeather(WeatherDTO weatherDTO){
        Weather weather = new Weather();
        weather.setValue(weatherDTO.getValue());
        weather.setRaining(weatherDTO.getRaining());
        weather.setSensor(weatherDTO.getSensor());
        return weather;
    }

    private WeatherDTO convertToWeatherDTO(Weather weather){
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setValue(weather.getValue());
        weatherDTO.setRaining(weather.getRaining());
        weatherDTO.setSensor(weather.getSensor());
        return weatherDTO;
    }
}
