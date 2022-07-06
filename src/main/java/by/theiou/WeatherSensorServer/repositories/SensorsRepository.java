package by.theiou.WeatherSensorServer.repositories;

import by.theiou.WeatherSensorServer.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, String> {
}
