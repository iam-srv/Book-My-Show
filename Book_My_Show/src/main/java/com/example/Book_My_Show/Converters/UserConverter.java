package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.Entites.UserEntity;
import com.example.Book_My_Show.EntryDtos.UserEntryDto;

public class UserConverter {

    public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){

        UserEntity userEntity =  UserEntity.builder().age(userEntryDto.getAge()).address(userEntryDto.getAddress()).email(userEntryDto.getEmail())
                .name(userEntryDto.getName()).mobNo(userEntryDto.getMobNo()).build();

        return userEntity;
    }
}
