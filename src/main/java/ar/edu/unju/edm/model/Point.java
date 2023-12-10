package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


@Component
@Entity
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private String photo;
    @NotNull
    private String name;
    @Size(min = 0, max = 1000)
    private String description;
    @NotNull
    private String locate;
    @NotNull
    private String type;
    @Min(0)
    @Max(5)
    private Float rating = 0.0f;
    private Boolean state = true;

    public Point() {}

    public Point(Integer ID, String photo, String name, String description, String locate, String type, Float rating, Boolean state) {
        this.ID = ID;
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.locate = locate;
        this.type = type;
        this.rating = rating;
        this.state = state;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

}