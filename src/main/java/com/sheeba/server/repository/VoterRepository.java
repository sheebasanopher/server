package com.sheeba.server.repository;

import com.sheeba.server.domain.VoteVoterPojo;
import org.springframework.data.repository.CrudRepository;

public interface VoterRepository extends CrudRepository<VoteVoterPojo, String> {
}
