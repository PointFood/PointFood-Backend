package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"registered_at"}, allowGetters = true)
@Builder
@Table(name="orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="client_id", nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Client client;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> dishes;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "registered_at", nullable = false)
    private Date registeredAt;

    //@NotNull(message = "La fecha de entrega no puede ser vacío")
    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "delivered_at", nullable = false)
    //private Date deliveredAt;

    @JoinColumn(name="restaurant_id", nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Restaurant restaurant;

    @Column(name="delivery", nullable = false)
    private boolean delivery;

    @Column(name="address", length = 50)
    private String address;

    @Column(name = "total", precision = 6, scale =  2, nullable = false)
    private double total;

    @JoinColumn(name="status_id", nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private State state;
}
