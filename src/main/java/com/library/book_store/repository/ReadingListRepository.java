package com.library.book_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.book_store.entity.ReadingList;

public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {

}
