package com.lorenzolobrutto.messageapi.repository;

import com.lorenzolobrutto.messageapi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {}
