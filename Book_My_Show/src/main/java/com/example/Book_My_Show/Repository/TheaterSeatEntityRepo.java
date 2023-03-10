package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entites.TheaterSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatEntityRepo extends JpaRepository<TheaterSeatEntity, Integer> {
}
