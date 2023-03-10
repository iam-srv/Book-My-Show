package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.Entites.TheaterEntity;
import com.example.Book_My_Show.EntryDtos.TheaterEntryDto;

public class TheaterConverters {

    public static TheaterEntity convertDtoToEntity(TheaterEntryDto theaterEntryDto ){

        return TheaterEntity.builder().location(theaterEntryDto.getLocation()).name(theaterEntryDto.getName()).build();

    }
}
