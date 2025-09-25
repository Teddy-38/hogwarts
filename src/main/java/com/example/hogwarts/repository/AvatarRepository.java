package com.example.hogwarts.repository;

import com.example.hogwarts.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}