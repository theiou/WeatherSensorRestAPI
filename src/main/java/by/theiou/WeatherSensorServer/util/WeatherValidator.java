package by.theiou.WeatherSensorServer.util;

import by.theiou.WeatherSensorServer.models.Weather;
import by.theiou.WeatherSensorServer.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WeatherValidator implements Validator {

    @Autowired
    SensorsService sensorsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Weather.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Weather weather = (Weather) target;
        if (sensorsService.findByName(weather.getSensor().getName()) == null){
            errors.rejectValue("sensor", "", "There is no such sensor");
        }

        if (weather.getRaining() != "true" || weather.getRaining() != "false")
            errors.rejectValue("raining", "", "There's only true or false value here");
    }
}
