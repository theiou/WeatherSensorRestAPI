package by.theiou.WeatherSensorServer.util;

public class SensorErrorResponse {
    private String message;

    public SensorErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
