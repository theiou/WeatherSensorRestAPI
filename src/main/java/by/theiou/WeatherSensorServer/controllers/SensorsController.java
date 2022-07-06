package by.theiou.WeatherSensorServer.controllers;

import by.theiou.WeatherSensorServer.dto.SensorDTO;
import by.theiou.WeatherSensorServer.models.Sensor;
import by.theiou.WeatherSensorServer.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    @Autowired
    SensorsService sensorsService;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> sensorRegistration(@RequestBody SensorDTO sensorDTO){
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



}
