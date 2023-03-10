package com.example.Book_My_Show.Entites;


import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;

    private int age;

    @Column(unique = true , nullable = false)
    private String email;

    @NonNull
    @Column(unique = true)
    private String mobNo;

    private String address;

   @OneToMany(mappedBy = "userEntity" , cascade = CascadeType.ALL)
   List<TicketEntity> bookedTickets  = new ArrayList<>();


}
