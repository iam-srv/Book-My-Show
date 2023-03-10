package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.Entites.ShowEntity;
import com.example.Book_My_Show.Entites.TicketEntity;
import com.example.Book_My_Show.EntryDtos.TicketEntryDto;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketConverters {

    public static TicketEntity DtoToEntity(TicketEntryDto ticketEntryDto){

        TicketEntity ticketEntity = new TicketEntity();
        return ticketEntity;

    }
}
