package com.ad.teamnine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ad.teamnine.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Integer>{
	
}
