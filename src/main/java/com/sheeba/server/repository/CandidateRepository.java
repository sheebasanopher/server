package com.sheeba.server.repository;

import com.sheeba.server.domain.VoteCandidatePojo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CandidateRepository extends CrudRepository<VoteCandidatePojo,Integer> {
    Optional<VoteCandidatePojo> findByName(String name);
}
