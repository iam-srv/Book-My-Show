package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Entites.MovieEntity;
import com.example.Book_My_Show.EntryDtos.MovieEntryDto;
import com.example.Book_My_Show.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PutMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto){

        try {
             String response = theaterService.addTheater(theaterEntryDto);
             return new ResponseEntity<>(response , HttpStatus.CREATED);

        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/MovieList/{theaterName}")
    public List<MovieEntity>  getMovieListByTheater(@PathVariable(value = "theaterName") String name){

        return theaterService.getMovieListByTheater(name);
    }
}
