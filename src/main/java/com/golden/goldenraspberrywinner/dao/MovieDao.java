package com.golden.goldenraspberrywinner.dao;

import com.golden.goldenraspberrywinner.bean.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieDao extends JpaRepository<Movie, Long> {

    List<Movie> findByWinner(boolean winner );
}
