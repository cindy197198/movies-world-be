package com.movies.controlller;

import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;
import com.movies.exception.BadRequestException;
import com.movies.service.SeatService;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @Autowired
    private ShowTimeFilmService showTimeFilmService;

    @GetMapping("/showTime")
    public List<List<Integer>> getSeatsByShowTimeFilm(@RequestParam("filmId") Integer filmId,
                                             @RequestParam("dateTime") String time) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        ShowTimeFilm showTimeFilm = showTimeFilmService.getOneByFilmAndTime(filmId, dateTime);
        if (showTimeFilm == null) {
            throw new BadRequestException("NOT FOUND SHOW TIME");
        }

        List<List<Integer>> rowSeatList = convertToRow(showTimeFilm.getSeats());
        return rowSeatList;
    }

    private List<List<Integer>> convertToRow(List<Seat> seats) {
        List<List<Integer>> rowSeatList = new ArrayList<>();
        for (char row = 'A'; row <='Z' ; row ++) {
            List<Integer> seatList = new ArrayList<>();
            for (Seat seat : seats) {
                if (seat.getName().charAt(0) == row) {
                    seatList.add(seat.getStatus());
                }
            }
            if (seatList.size() > 0) rowSeatList.add(seatList);
        }
        return rowSeatList;
    }


}
