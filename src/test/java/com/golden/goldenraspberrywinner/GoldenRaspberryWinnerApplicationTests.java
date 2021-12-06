package com.golden.goldenraspberrywinner;

import com.golden.goldenraspberrywinner.bean.Winner;
import com.golden.goldenraspberrywinner.bean.WinnersList;
import com.golden.goldenraspberrywinner.service.WinnersService;
import com.golden.goldenraspberrywinner.util.LoadDataBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GoldenRaspberryWinnerApplicationTests {

    @Autowired
    LoadDataBase loadDataBase;

    @Autowired
    private WinnersService winnersService;

    private WinnersList returnWinnersByPath(String path) {
        loadDataBase.setPath(path);
        loadDataBase.loadDataBase(true);
        return winnersService.findWinnersList();

    }

    private boolean verifyIntervalIsNotZero(Winner winner) {
        return winner.getInterval() > 0;
    }

    @Test
    void test1() {
        WinnersList winnersList = returnWinnersByPath("\\src\\test\\resources\\movielist_test1.csv");
        assertThat(winnersList.getMax().size()).isEqualTo(1);
        assertThat(winnersList.getMin().size()).isEqualTo(1);
        winnersList.getMax().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());
        winnersList.getMin().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());
    }

    @Test
    void test2() {
        WinnersList winnersList = returnWinnersByPath("\\src\\test\\resources\\movielist_test2.csv");
        assertThat(winnersList.getMax().size()).isEqualTo(2);
        assertThat(winnersList.getMin().size()).isEqualTo(1);
        winnersList.getMax().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());
        winnersList.getMin().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());


    }

    @Test
    void test3() {
        WinnersList winnersList = returnWinnersByPath("\\src\\test\\resources\\movielist_test3.csv");
        assertThat(winnersList.getMax().size()).isEqualTo(3);
        assertThat(winnersList.getMin().size()).isEqualTo(1);
        winnersList.getMax().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());
        winnersList.getMin().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());
    }


    @Test
    void test4() {

        WinnersList winnersList = returnWinnersByPath("\\src\\test\\resources\\movielist_test4.csv");
        assertThat(winnersList.getMax().size()).isEqualTo(3);
        assertThat(winnersList.getMin().size()).isEqualTo(2);
        winnersList.getMax().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());
        winnersList.getMin().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());
    }


    @Test
    void test5() {

        WinnersList winnersList = returnWinnersByPath("\\src\\test\\resources\\movielist_test_zero_result.csv");
        assertThat(winnersList.getMax().size()).isEqualTo(0);
        assertThat(winnersList.getMin().size()).isEqualTo(0);

    }

    @Test
    void test6() {
        WinnersList winnersList = returnWinnersByPath("\\src\\test\\resources\\movielist_test_only_max_winner.csv");
        assertThat(winnersList.getMax().size()).isEqualTo(1);
        assertThat(winnersList.getMin().size()).isEqualTo(1);
        assertThat(winnersList.getMin().get(0).getProducerName()).isEqualTo(winnersList.getMax().get(0).getProducerName());
        winnersList.getMax().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());
        winnersList.getMin().forEach(x -> assertThat(verifyIntervalIsNotZero(x)).isTrue());
    }

    //movielist_test_only_max_winner

}
