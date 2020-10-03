package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="restaurant_owners")
public class RestaurantOwner implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser vacío")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotNull(message = "El número de celular no puede ser vacío")
    @Column(name = "phone", length = 9, nullable = false)
    private String phone;

    @NotNull(message = "El email no puede ser vacío")
    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @NotNull(message = "El nombre de usuario no puede ser vacío")
    @Column(name = "username", unique = true, length = 20, nullable = false)
    private String username;

    @NotNull(message = "La contraseña no puede ser vacío")
    @Column(name = "password", length = 8, nullable = false)
    private String password;

    @OneToMany
    @JoinColumn(name="restaurant_owner_id")
    @JsonIgnore
    private List<Restaurant> restaurants;
}
