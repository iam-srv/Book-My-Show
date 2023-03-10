package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.Entites.ShowEntity;
import com.example.Book_My_Show.EntryDtos.ShowEntryDtos;
import lombok.Data;

@Data
public class ShowConverters {

    public static ShowEntity convertEntityToEntity(ShowEntryDtos showEntryDtos){

        ShowEntity showEntity  = ShowEntity.builder()
                .showDate(showEntryDtos.getLocalDate())
                .showTime(showEntryDtos.getLocalTime())
                .showType(showEntryDtos.getShowType()).build();

        return showEntity;

    }
}
