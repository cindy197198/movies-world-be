package com.movies.service;

import com.movies.entity.dao.ShowTimeFilm;

import java.util.Date;
import java.util.List;

public interface ShowTimeService {
    List<Date> getDateShow(Integer filmId);

    List<Date> getTimeShow(Integer filmId, Date date);

    List<ShowTimeFilm> getAll();
}