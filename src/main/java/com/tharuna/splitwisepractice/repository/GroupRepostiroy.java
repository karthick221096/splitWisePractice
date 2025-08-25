package com.tharuna.splitwisepractice.repository;

import com.tharuna.splitwisepractice.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepostiroy extends JpaRepository<Group,Long> {
}
