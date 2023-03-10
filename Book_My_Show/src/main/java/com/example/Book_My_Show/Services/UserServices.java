package com.example.Book_My_Show.Services;


import com.example.Book_My_Show.Converters.UserConverter;
import com.example.Book_My_Show.Entites.UserEntity;
import com.example.Book_My_Show.EntryDtos.UserEntryDto;

import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws Exception{

        // from the converter we are changing the userEntry Dto to userEntity

      UserEntity userEntity = UserConverter.convertDtoToEntity(userEntryDto);
      userRepository.save(userEntity);

      return "User added Successfully";

    }
}
