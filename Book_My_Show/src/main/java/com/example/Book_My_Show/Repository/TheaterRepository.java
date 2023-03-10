package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entites.TheaterEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository  extends JpaRepository<TheaterEntity , Integer> {

    TheaterEntity findTheaterByName(String name);

}
