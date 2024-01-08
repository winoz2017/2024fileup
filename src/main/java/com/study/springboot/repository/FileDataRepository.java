package com.study.springboot.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.springboot.entity.FileData;


public interface FileDataRepository extends JpaRepository<FileData, Long> {
	Optional<FileData> findByName(String FileName);
}
