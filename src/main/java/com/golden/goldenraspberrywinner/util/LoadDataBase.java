package com.golden.goldenraspberrywinner.util;

import com.golden.goldenraspberrywinner.bean.Movie;
import com.golden.goldenraspberrywinner.bean.Producer;
import com.golden.goldenraspberrywinner.bean.Studio;
import com.golden.goldenraspberrywinner.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadDataBase {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private ProducerDao producerDao;

    @Autowired
    private StudioDao studioDao;

    private boolean baseIsLoaded = false;

    public void loadDataBase() {
        if (!baseIsLoaded) {
            try {
                List<String[]> movies = loadCSV();

                movies.forEach(x -> {
                    String[] data = x;
                    Integer year = Integer.parseInt(data[0]);
                    String name = data[1];

                    Studio studio = studioDao.findByName(data[2]);
                    if (studio == null) {
                        studio = new Studio(data[2]);
                        studioDao.save(studio);
                    }

                    Producer producer = producerDao.findByName(data[3]);
                    if (producer == null) {
                        producer = new Producer(data[3]);
                        producerDao.save(producer);
                    }
                    boolean winner = false;
                    if (data.length == 5 && data[4].equals("yes")) {
                        winner = true;
                    }
                    Movie movie = new Movie(name, year, winner, producer, studio);
                    movieDao.save(movie);

                });

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                baseIsLoaded = true;
            }
        }


    }

    private List loadCSV() {
        boolean firstLine = true;
        List<String[]> movies = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\movielist.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                if (!firstLine) {
                    String[] values = line.split(";");
                    movies.add(values);
                } else {
                    firstLine = false;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;


    }
}
