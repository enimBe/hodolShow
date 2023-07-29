package com.blog.repository;

import com.blog.domain.Member;
import com.blog.domain.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<Session, Long> {
}
