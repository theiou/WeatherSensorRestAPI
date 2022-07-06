package by.theiou.WeatherSensorServer.models;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Sensor")
public class Sensor implements Serializable{
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "sensor")
    @Transient
    private List<Weather> values;

    public Sensor(String name, LocalDateTime createdAt, List<Weather> values) {
        this.name = name;
        this.createdAt = createdAt;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Weather> getValues() {
        return values;
    }

    public void setValues(List<Weather> values) {
        this.values = values;
    }

    public Sensor() {
    }
}
