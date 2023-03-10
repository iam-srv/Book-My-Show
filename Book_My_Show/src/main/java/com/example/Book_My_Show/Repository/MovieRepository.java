package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entites.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity , Integer> {

    //MovieEntity findMovieByName(String MovieName);
}
