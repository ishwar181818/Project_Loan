package com.man.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.man.model.Creditlimit;
@Repository
public interface Repo extends JpaRepository<Creditlimit, Integer> {

}
