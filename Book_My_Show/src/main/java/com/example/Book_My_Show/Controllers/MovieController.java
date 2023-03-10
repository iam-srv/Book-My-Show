package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Entites.MovieEntity;
import com.example.Book_My_Show.EntryDtos.MovieEntryDto;
import com.example.Book_My_Show.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){

        try {
            String response = movieService.addMove(movieEntryDto);
            return new ResponseEntity<>(response , HttpStatus.CREATED);
        }catch (Exception e){

            String response = "Movie not added";

            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public MovieEntity getMovieById(@PathVariable int id ) throws Exception {
        MovieEntity movieEntity = movieService.getMovieById(id);
        return movieEntity;
    }

    @GetMapping("/findAll")
    public List<MovieEntity> getAllMovies(){

        List<MovieEntity> movieEntityList = movieService.getAllMovies();
        return movieEntityList;
    }

}
