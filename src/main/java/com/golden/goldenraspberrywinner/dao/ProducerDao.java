package com.golden.goldenraspberrywinner.dao;

import com.golden.goldenraspberrywinner.bean.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerDao extends JpaRepository<Producer, Long> {

    Producer findByName(String name);
}
