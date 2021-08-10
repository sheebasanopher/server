package com.sheeba.server.repository;

import com.sheeba.server.domain.LibraryBookPojo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<LibraryBookPojo, Integer> {

    List<LibraryBookPojo> findByIsbnNo(String isbnNo);

    Optional<LibraryBookPojo> findByIdAndIsbnNo(Integer id, String isbnNo);
}
