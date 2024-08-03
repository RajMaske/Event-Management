package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.entity.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {}

