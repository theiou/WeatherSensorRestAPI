package by.theiou.WeatherSensorServer.dto;

import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

public class WeatherDTO {
    @Range(min = -100, max = 100, message = "Value should be in range [-100; 100]")
    private double value;
    private String raining;
    private LocalDateTime createdAt;
    private SensorDTO sensor;

    public WeatherDTO() {
    }

    public WeatherDTO(double value, String raining, SensorDTO sensor, LocalDateTime createdAt) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
