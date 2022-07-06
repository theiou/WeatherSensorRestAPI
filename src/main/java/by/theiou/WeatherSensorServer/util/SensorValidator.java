package by.theiou.WeatherSensorServer.util;

import by.theiou.WeatherSensorServer.models.Sensor;
import by.theiou.WeatherSensorServer.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    @Autowired
    private SensorsService sensorsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorsService.findByName(sensor.getName()) != null)
            errors.rejectValue("name", "", "This sensor is already registered");
    }
}
