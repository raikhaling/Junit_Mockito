package com.example.testing.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTY_TABLE")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PROPERTY_TITLE", nullable = false)
    private String title;
    private String description;
    private String ownerName;
    @Column(name = "EMAIL")
    private String ownerEmail;
    private Double price;
    private String address;
}
