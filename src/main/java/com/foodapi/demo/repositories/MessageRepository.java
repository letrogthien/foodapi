package com.foodapi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapi.demo.models.MessageData;

import java.util.List;
import java.util.Optional;

import com.foodapi.demo.models.User;


@Repository
public interface MessageRepository extends JpaRepository<MessageData,Integer> {
    Optional<MessageData> findByUser1IdAndUser2Id(Integer user1, Integer user2);
}
