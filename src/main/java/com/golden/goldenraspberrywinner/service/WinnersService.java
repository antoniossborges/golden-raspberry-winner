package com.golden.goldenraspberrywinner.service;

import com.golden.goldenraspberrywinner.bean.Movie;
import com.golden.goldenraspberrywinner.bean.Winner;
import com.golden.goldenraspberrywinner.bean.WinnersList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.golden.goldenraspberrywinner.dao.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WinnersService {

    @Autowired
    private MovieDao movieDao;

    public WinnersList findWinnersList() {
        WinnersList winners = new WinnersList();
        HashMap<String, Winner> winnersList = buildWinnerList();

        List<Winner> winnerMultipleReward = createListOnlyMultipleWinner(winnersList);


        Integer maxValue = winnerMultipleReward.stream()
                .max(Comparator.comparing(Winner::getInterval)).get().getInterval();

        winners.getMax().addAll(filterIntervalByValue(winnerMultipleReward, maxValue));

        Integer minValue = winnerMultipleReward.stream()
                .min(Comparator.comparing(Winner::getInterval)).get().getInterval();

        winners.getMin().addAll(filterIntervalByValue(winnerMultipleReward, minValue));

        return winners;
    }

    private List<Winner> filterIntervalByValue(List<Winner> winnerMultipleReward, Integer minValue) {
        return winnerMultipleReward.stream().filter(x -> x.getInterval().equals(minValue)).collect(Collectors.toList());
    }

    private List<Winner> createListOnlyMultipleWinner(HashMap<String, Winner> winnersList) {
        return winnersList.entrySet().stream().filter(x -> x.getValue().getInterval() > 0).map(map -> map.getValue())
                .collect(Collectors.toList());
    }

    private HashMap<String, Winner> buildWinnerList() {
        List<Movie> movies = movieDao.findByWinner(true);

        HashMap<String, Winner> winnersList = new HashMap<>();

        movies.forEach(x -> {
            String[] producers = x.getProducer().getName().replace(",", ";").replace(" and ", ";").split(";");
            Arrays.stream(producers).forEach(y -> {
                String producer = y.trim();

                if (winnersList.get(producer) != null) {
                    System.out.println(producer);
                    Winner w = winnersList.get(producer);
                    if (x.getYear() < w.getPreviousWin()) {
                        w.setPreviousWin(x.getYear());
                    } else if (x.getYear() > w.getFollowingWin()) {
                        w.setFollowingWin(x.getYear());
                    }
                } else {
                    Winner w = new Winner();
                    w.setProducerName(producer);
                    w.setPreviousWin(x.getYear());
                    w.setFollowingWin(x.getYear());
                    winnersList.put(w.getProducerName(), w);
                }
            });
        });
        return winnersList;
    }
}
