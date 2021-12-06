package com.golden.goldenraspberrywinner.service;

import com.golden.goldenraspberrywinner.bean.Movie;
import com.golden.goldenraspberrywinner.bean.Winner;
import com.golden.goldenraspberrywinner.bean.WinnersList;
import com.golden.goldenraspberrywinner.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Integer maxValue = winnerMultipleReward.size() > 0 ? winnerMultipleReward.stream()
                .max(Comparator.comparing(Winner::getInterval)).get().getInterval() : 0;

        winners.getMax().addAll(filterIntervalByValue(winnerMultipleReward, maxValue));

        Integer minValue = winnerMultipleReward.size() > 0 ? winnerMultipleReward.stream()
                .min(Comparator.comparing(Winner::getInterval)).get().getInterval() : 0;

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
        HashMap<String, List<Integer>> winnerYear = new HashMap<>();

        movies.forEach(x -> {
            String[] producers = x.getProducer().getName().replace(",", ";").replace(" and ", ";").split(";");
            Arrays.stream(producers).forEach(y -> {
                String producer = y.trim();
                if (winnerYear.get(producer) != null) {
                    winnerYear.get(producer).add(x.getYear());
                } else {
                    winnerYear.put(producer, new ArrayList<>());
                    winnerYear.get(producer).add(x.getYear());
                }
            });
        });

        winnerYear.forEach((key, value) -> {
            Collections.sort(value);
            int count = 0;
            for (Integer year : value) {
                String keyTohashMap = null;
                keyTohashMap = getKeyWithCount(key, count);
                Winner winner = winnersList.get(keyTohashMap);
                //verificar com 4 premios
                if (winner != null && winner.getPreviousWin() != null && winner.getFollowingWin() == null) {
                    Winner w = winnersList.get(keyTohashMap);
                    w.setFollowingWin(year);
                    count++;
                } else if (winner != null && winner.getPreviousWin() != null && winner.getFollowingWin() != null) {
                    Winner w = new Winner();
                    w.setProducerName(key);
                    w.setPreviousWin(winner.getFollowingWin());
                    w.setFollowingWin(year);
                    winnersList.put(w.getProducerName() + String.valueOf(count), w);
                    count++;
                } else {
                    Winner w = new Winner();
                    w.setProducerName(key);
                    w.setPreviousWin(year);
                    winnersList.put(keyTohashMap, w);
                }
            }
        });

        return winnersList;
    }

    private String getKeyWithCount(String key, int count) {
        String keyTohashMap;
        if (count > 0) {
            keyTohashMap = key + String.valueOf(count - 1);
        } else {
            keyTohashMap = key + String.valueOf(count);
        }
        return keyTohashMap;
    }
}
