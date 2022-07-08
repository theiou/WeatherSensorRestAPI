package by.theiou.WeatherSensorServer.controllers;

import by.theiou.WeatherSensorServer.dto.SensorDTO;
import by.theiou.WeatherSensorServer.dto.WeatherDTO;
import by.theiou.WeatherSensorServer.models.Sensor;
import by.theiou.WeatherSensorServer.models.Weather;
import by.theiou.WeatherSensorServer.services.WeatherService;
import by.theiou.WeatherSensorServer.util.WeatherErrorResponse;
import by.theiou.WeatherSensorServer.util.WeatherNoSensorException;
import by.theiou.WeatherSensorServer.util.WeatherValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    @Autowired
    ModelMapper modelMapper;


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
        return modelMapper.map(weather, WeatherDTO.class);
    }

    private Weather convertToWeather(WeatherDTO weatherDTO){
        return modelMapper.map(weatherDTO, Weather.class);
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

    private void returnErrorsToClient(BindingResult bindingResult){
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors){
            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");}

        throw new WeatherNoSensorException(errorMessage.toString());
    }




}
