package com.example.Book_My_Show.Controllers;

import com.example.Book_My_Show.Converters.ShowConverters;
import com.example.Book_My_Show.Entites.ShowEntity;
import com.example.Book_My_Show.EntryDtos.ShowEntryDtos;
import com.example.Book_My_Show.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String>  addShow(@RequestBody ShowEntryDtos showEntryDtos){

        return new ResponseEntity<>(showService.addShow(showEntryDtos), HttpStatus.CREATED);
    }

}
