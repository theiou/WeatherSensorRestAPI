package by.theiou.WeatherSensorServer.dto;

import by.theiou.WeatherSensorServer.models.Sensor;

public class WeatherDTO {
    private double value;
    private String raining;
    private Sensor sensor;

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
