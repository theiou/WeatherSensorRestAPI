package by.theiou.WeatherSensorServer.controllers;

import by.theiou.WeatherSensorServer.dto.SensorDTO;
import by.theiou.WeatherSensorServer.dto.WeatherDTO;
import by.theiou.WeatherSensorServer.models.Sensor;
import by.theiou.WeatherSensorServer.models.Weather;
import by.theiou.WeatherSensorServer.services.WeatherService;
import by.theiou.WeatherSensorServer.util.WeatherErrorResponse;
import by.theiou.WeatherSensorServer.util.WeatherNoSensorException;
import by.theiou.WeatherSensorServer.util.WeatherValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherValidator weatherValidator;


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid WeatherDTO weatherDTO, BindingResult bindingResult){
        Weather weatherToAdd = convertToWeather(weatherDTO);
        weatherValidator.validate(weatherToAdd, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        weatherService.save(weatherToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<WeatherDTO> getAllMeasurements(){
        List<Weather> listWeather = weatherService.getAllMeasurements();
        List<WeatherDTO> listWeatherDTO = new ArrayList<>();
        for (Weather weather : listWeather) {
            listWeatherDTO.add(convertToWeatherDTO(weather));
        }
        return listWeatherDTO;
    }

    private WeatherDTO convertToWeatherDTO(Weather weather){
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setValue(weather.getValue());
        weatherDTO.setRaining(weather.getRaining());
        weatherDTO.setSensor(convertToSensorDTO(weather.getSensor()));
        return weatherDTO;
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName(sensor.getName());
        return sensorDTO;
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount(){
        return weatherService.getRainyDaysCount();
    }

    @ExceptionHandler
    private ResponseEntity<WeatherErrorResponse> handleException(WeatherNoSensorException e){
        WeatherErrorResponse response = new WeatherErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Weather convertToWeather(WeatherDTO weatherDTO){
        Weather weather = new Weather();
        weather.setValue(weatherDTO.getValue());
        weather.setRaining(weatherDTO.getRaining());
        weather.setSensor(convertToSensor(weatherDTO.getSensor()));
        return weather;
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
        return sensor;
    }

    private void returnErrorsToClient(BindingResult bindingResult){
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors){
            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");}

        throw new WeatherNoSensorException(errorMessage.toString());
    }




}
