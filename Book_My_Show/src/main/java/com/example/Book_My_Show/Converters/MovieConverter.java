package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.Entites.MovieEntity;
import com.example.Book_My_Show.EntryDtos.MovieEntryDto;

public class MovieConverter {


    public static MovieEntity convertEntryDtoToEntity(MovieEntryDto movieEntryDto){

        MovieEntity movieEntity = MovieEntity.builder().moveName(movieEntryDto.getMoveName()).
        duration(movieEntryDto.getDuration()).genre(movieEntryDto.getGenre()).language(movieEntryDto.getLanguage()).
        ratings(movieEntryDto.getRatings()).build();

        return movieEntity;
    }
}
