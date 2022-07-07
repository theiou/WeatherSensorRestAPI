package by.theiou.WeatherSensorServer.dto;

import org.hibernate.validator.constraints.Range;

public class WeatherDTO {
    @Range(min = -100, max = 100, message = "Value should be in range [-100; 100]")
    private double value;
    private String raining;
    private SensorDTO sensor;

    public WeatherDTO() {
    }

    public WeatherDTO(double value, String raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(String raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
