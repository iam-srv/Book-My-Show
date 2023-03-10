package com.example.Book_My_Show.Entites;

import com.example.Book_My_Show.Enums.ShowType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Shows")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalTime showTime;

    private LocalDate showDate; // customizable date

    @Enumerated(value = EnumType.STRING)
    private  ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

     // this is child wrt the movieEntity
     @ManyToOne
     @JoinColumn
    private MovieEntity movieEntity;

    // this is child wrt the theaterEntity
    @ManyToOne
    @JoinColumn
    private TheaterEntity  theaterEntity;

    @OneToMany(mappedBy = "showEntity" , cascade = CascadeType.ALL)
    private List<TicketEntity> ticketEntityList = new ArrayList<>();


    @OneToMany(mappedBy = "showEntity" , cascade = CascadeType.ALL)
    private List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

}

