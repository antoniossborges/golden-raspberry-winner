package com.golden.goldenraspberrywinner.dao;

import com.golden.goldenraspberrywinner.bean.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioDao extends JpaRepository<Studio,Long> {

    Studio findByName(String name);
}
