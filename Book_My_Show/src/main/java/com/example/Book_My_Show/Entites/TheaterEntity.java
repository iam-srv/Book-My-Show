package com.example.Book_My_Show.Entites;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true , nullable = false)
    private String name;

    private String location;

    // this is the parent wrt to theaterSeats
    @OneToMany(mappedBy = "theaterEntity" , cascade = CascadeType.ALL)
    private List<TheaterSeatEntity> theaterSeatEntities = new ArrayList<>();

    @OneToMany(mappedBy = "theaterEntity" , cascade = CascadeType.ALL)
    List<ShowEntity> showEntityList = new ArrayList<>();


}
