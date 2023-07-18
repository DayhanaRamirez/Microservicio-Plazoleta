package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_cliente")
    private Long idClient;

    @Column(name = "fecha")
    private LocalDate date;

    @Column(name = "estado")
    private String status;

    @Column(name = "id_chef")
    private Long idChef;

    @Column(name = "id_restaurante")
    private Long idRestaurant;
}
