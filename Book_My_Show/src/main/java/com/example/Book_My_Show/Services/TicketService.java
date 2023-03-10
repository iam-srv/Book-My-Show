package com.example.Book_My_Show.Services;


import com.example.Book_My_Show.Converters.TicketConverters;
import com.example.Book_My_Show.Entites.ShowEntity;
import com.example.Book_My_Show.Entites.ShowSeatEntity;
import com.example.Book_My_Show.Entites.TicketEntity;
import com.example.Book_My_Show.Entites.UserEntity;
import com.example.Book_My_Show.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception{

        // setting values from Dto
        TicketEntity ticketEntity = TicketConverters.DtoToEntity(ticketEntryDto);

        // validation : checking the requested seats are available or not
        boolean isValidRequest = checkValidityOfRequestedSeats(ticketEntryDto);

        if(isValidRequest == false){
            throw new Exception("Seats are Unavailable");
        }

        // we assume that the requestedSeats are valid
        // calculate the total amount
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatEntity> seatEntityList = showEntity.getShowSeatEntityList();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int totalAmount = 0;
        for(ShowSeatEntity showSeatEntity : seatEntityList){

            if(requestedSeats.contains(showSeatEntity.getSeatNo())){
                totalAmount += showSeatEntity.getPrice();
                showSeatEntity.setBooked(false);
                showSeatEntity.setBookedAt(new Date());
            }
        }

        ticketEntity.setTotalAmount(totalAmount);

        // setting the fk attributes and others
         ticketEntity.setMovieName(showEntity.getMovieEntity().getMoveName());
         ticketEntity.setShowTime(showEntity.getShowTime());
         ticketEntity.setShowDate(showEntity.getShowDate());
         ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());

         // we need to set the string , requested seats
          String allottedSeats = getAllotedSeatsFromShowSeats(requestedSeats);
          ticketEntity.setBookedSeats(allottedSeats);

         // setting fk
        UserEntity  userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();

        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        // save the parent
        List<TicketEntity> ticketEntityList = showEntity.getTicketEntityList();
        ticketEntityList.add(ticketEntity);
        showEntity.setTicketEntityList(ticketEntityList);
        showRepository.save(showEntity);

        List<TicketEntity> ticketEntities = userEntity.getBookedTickets();
        ticketEntities.add(ticketEntity);
        userEntity.setBookedTickets(ticketEntities);
        userRepository.save(userEntity);


        return "Ticket has successfully been added";

    }

    private  boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){
        int showId = ticketEntryDto.getShowId();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        ShowEntity showEntity = showRepository.findById(showId).get();
        List<ShowSeatEntity> seatEntityList= showEntity.getShowSeatEntityList();


        for(ShowSeatEntity showSeatEntity : seatEntityList){

            String seatNo = showSeatEntity.getSeatNo();

            if(!requestedSeats.contains(seatNo) || showSeatEntity.isBooked() == true) return false;
        }

        return true;
    }

    private String getAllotedSeatsFromShowSeats(List<String>  requestedSeats){
        String result = "";

        for(String s : requestedSeats){
            result+= s + ", ";
        }
        return result;
    }
}
