package by.theiou.WeatherSensorServer.util;

public class WeatherNoSensorException extends RuntimeException{
    public WeatherNoSensorException(String msg){
        super(msg);
    }
}
