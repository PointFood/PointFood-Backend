package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="restaurants")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @NotNull(message = "El número de celular no puede ser vacío")
    @Column(name = "phone", length = 9, nullable = false)
    private String phone;

    @JoinColumn(name="restaurant_owner_id")
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private RestaurantOwner restaurantOwner;

    @OneToMany
    @JoinColumn(name="restaurant_id")
    @JsonIgnore
    private List<Dish> dishes;

    @OneToMany
    @JoinColumn(name="restaurant_id")
    @JsonIgnore
    private List<Extra> extras;
}
