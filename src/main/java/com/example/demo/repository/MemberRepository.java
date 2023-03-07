package com.example.demo.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Member;


public interface MemberRepository {
    Member save(Member member) throws SQLException;
    Member findById(String memberId) throws SQLException;

    void update(String memberId, int money) throws SQLException;
}
