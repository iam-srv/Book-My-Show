package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Controllers.TheaterController;
import com.example.Book_My_Show.Converters.TheaterConverters;
import com.example.Book_My_Show.Entites.MovieEntity;
import com.example.Book_My_Show.Entites.ShowEntity;
import com.example.Book_My_Show.Entites.TheaterEntity;
import com.example.Book_My_Show.Entites.TheaterSeatEntity;
import com.example.Book_My_Show.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.Repository.TheaterSeatEntityRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService  {

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterSeatEntityRepo theaterSeatEntityRepo;

    @Autowired
    MovieRepository movieRepository1;
    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{

        // In case of any validation needed before setting the attributes like logical validations fo ex ;-
         // goood to have some validations
        if(theaterEntryDto.getName() == null || theaterEntryDto.getLocation() == null){
            throw new Exception("Theater Name and Location can't be Null");
        }

        TheaterEntity theaterEntity = TheaterConverters.convertDtoToEntity(theaterEntryDto);

        List<TheaterSeatEntity> theaterSeatEntityList = createTheaterSeats(theaterEntryDto, theaterEntity);

        theaterEntity.setTheaterSeatEntities(theaterSeatEntityList);

        theaterRepository.save(theaterEntity);
        return "Theater added Successfully";

    }

    private List<TheaterSeatEntity>  createTheaterSeats(TheaterEntryDto theaterEntryDto , TheaterEntity theaterEntity){

        int noClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int noPremiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();

        // created the classic seats
        for(int count = 1; count <= noClassicSeats; count++)
        {
            // we need to make a new TheaterSeatEntity
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
            seatType(SeatType.CLASSIC).seatNo(count+"c").theaterEntity(theaterEntity).build();
            theaterSeatEntityList.add(theaterSeatEntity);
        }

        //creating premium seats
        for(int count  = 1; count <= noPremiumSeats; count++)
        {
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
                    seatType(SeatType.PREMIUM).seatNo(count+"p").theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        //theaterSeatEntityRepo.saveAll(theaterSeatEntityList);
        // no need of saving the seatEntity cauze its child compared to theaterEntity and will automatically will be saved

        return theaterSeatEntityList;
    }

    public List<MovieEntity> getMovieListByTheater(String TheaterName){

        List<MovieEntity> movieEntityList = movieRepository1.findAll();
        TheaterEntity theaterEntity = theaterRepository.findTheaterByName(TheaterName);

        List<MovieEntity> movieEntityList1 = new ArrayList<>();
        for(MovieEntity movie : movieEntityList){

            List<ShowEntity> showEntityList = movie.getShowEntityList();

            for(ShowEntity show : showEntityList){
                if(show.getTheaterEntity().equals(theaterEntity)){
                    movieEntityList1.add(movie);
                }
            }
        }

        return movieEntityList1;
    }
}
