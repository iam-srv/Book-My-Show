package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Converters.ShowConverters;
import com.example.Book_My_Show.Entites.*;
import com.example.Book_My_Show.EntryDtos.ShowEntryDtos;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.chrono.ThaiBuddhistChronology;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;
    public String addShow(ShowEntryDtos showEntryDtos) {

        //1. create an showEntity

        ShowEntity showEntity = ShowConverters.convertEntityToEntity(showEntryDtos);

        int movieId = showEntryDtos.getMovieId();
        int theaterId = showEntryDtos.getTheaterId();

        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        TheaterEntity theaterEntity = theaterRepository.findById(theaterId).get();


        // 2. setting the foreign Key
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theaterEntity);

        //  taking care of the showSeatEntity (List of show seats)

        List<ShowSeatEntity> showSeatEntityList = createShowSeatEntity(showEntryDtos, showEntity);

        showEntity.setShowSeatEntityList(showSeatEntityList);

        // updating the showEntity as the parent Entity in other class

        showEntity = showRepository.save(showEntity); //id attribute set
        // for movieEntity
        movieEntity.getShowEntityList().add(showEntity);
        movieRepository.save(movieEntity); // cascade all
        // for TheaterEntity
        theaterEntity.getShowEntityList().add(showEntity);
        theaterRepository.save(theaterEntity);// cascade all

        return "The show has been added Successfully";
    }

    private List<ShowSeatEntity> createShowSeatEntity(ShowEntryDtos showEntryDtos , ShowEntity showEntity){

        //setting attribute of seatEntity
        TheaterEntity theaterEntity =  showEntity.getTheaterEntity();
        List<TheaterSeatEntity> theaterSeatEntityList = theaterEntity.getTheaterSeatEntities();

        List<ShowSeatEntity> seatEntityList = new ArrayList<>();

        for(TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList){

            ShowSeatEntity showSeatEntity = new ShowSeatEntity();
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());
            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());

            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
                showSeatEntity.setPrice(showEntryDtos.getClassicSeatPrice());
            }else {
                showSeatEntity.setPrice(showEntryDtos.getPremiumSeatPrice());
            }

            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity); // fk for the showSeat Entity(parent)

            seatEntityList.add(showSeatEntity);
        }

        return seatEntityList;
    }
}

