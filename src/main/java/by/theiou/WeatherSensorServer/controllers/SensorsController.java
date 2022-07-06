package by.theiou.WeatherSensorServer.controllers;

import by.theiou.WeatherSensorServer.dto.SensorDTO;
import by.theiou.WeatherSensorServer.models.Sensor;
import by.theiou.WeatherSensorServer.services.SensorsService;
import by.theiou.WeatherSensorServer.util.SensorBadNameException;
import by.theiou.WeatherSensorServer.util.SensorErrorResponse;
import by.theiou.WeatherSensorServer.util.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    @Autowired
    SensorsService sensorsService;
    @Autowired
    SensorValidator sensorValidator;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> sensorRegistration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);
        if (bindingResult.hasErrors())
            returnAllErrors(bindingResult);

        sensorsService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<Sensor> getSensors(){
        return sensorsService.getSensors();
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
        return sensor;
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorBadNameException e){
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private void returnAllErrors(BindingResult bindingResult){
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();

        for (FieldError error : errors){
            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");}
        throw new SensorBadNameException(errorMessage.toString());
    }


}
