package com.example.Book_My_Show.EntryDtos;

import lombok.Data;

@Data
public class TheaterEntryDto {

    private String name;
    private String location;

    private int classicSeatsCount;
    private int premiumSeatsCount;

}
