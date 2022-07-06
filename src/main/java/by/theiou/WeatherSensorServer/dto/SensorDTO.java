package by.theiou.WeatherSensorServer.dto;

import javax.validation.constraints.Size;

public class SensorDTO {
    @Size(min = 3, max = 20, message = "Name of the sensor should be between 1 and 20 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
