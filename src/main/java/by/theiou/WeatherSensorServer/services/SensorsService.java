package by.theiou.WeatherSensorServer.services;

import by.theiou.WeatherSensorServer.models.Sensor;
import by.theiou.WeatherSensorServer.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SensorsService {
    @Autowired
    SensorsRepository sensorsRepository;

    public void save(Sensor sensor){
        enrichSensor(sensor);
        sensorsRepository.save(sensor);
    }

    public List<Sensor> getSensors(){
        System.out.println(sensorsRepository.findAll());
        return sensorsRepository.findAll();
    }

    private Sensor enrichSensor(Sensor sensor){
        sensor.setCreatedAt(LocalDateTime.now());
        return sensor;
    }

}
