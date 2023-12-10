package ar.edu.unju.edm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;


@Component
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @NotEmpty(message = "Ingresa tu Nombre")
    @Pattern(regexp = "^[^0-9]+$", message = "No se permiten números en el nombre.")
    private String name;
    @NotEmpty(message = "Ingresa tu Apellido")
    @Pattern(regexp = "^[^0-9]+$", message = "No se permiten números en el nombre.")
    private String lastName;
    @Email(message = "Correo electrónico no válido. Verifica tu entrada.")
    @NotEmpty(message = "Ingresa tu Correo electronico")
    private String email;
    @NotEmpty(message = "Ingresa una Contraseña")
    private String password;
    private String nationality;
    private String type;
    private Boolean state = true;

    public Usuario() {}

    public Usuario(Integer ID, String name, String lastName, String email, String password, String nationality, String type, Boolean state) {
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.nationality = nationality;
        this.type = type;
        this.state = state;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

}