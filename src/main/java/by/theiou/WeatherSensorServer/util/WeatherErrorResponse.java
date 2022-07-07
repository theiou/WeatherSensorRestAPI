package by.theiou.WeatherSensorServer.util;

public class WeatherErrorResponse {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WeatherErrorResponse(String msg) {
        this.msg = msg;
    }
}
