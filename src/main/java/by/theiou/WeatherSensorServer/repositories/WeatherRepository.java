package by.theiou.WeatherSensorServer.repositories;

import by.theiou.WeatherSensorServer.models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
