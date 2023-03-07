package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import static com.example.demo.connection.ConnectionConst.*;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class MemberRepositoryV1Test {
    MemberRepositoryV2 repository;

    @BeforeEach
    void beforeEach() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,Password);

        //Connection Pooling
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(Password);

        repository = new MemberRepositoryV2(dataSource);
    }
    @Test
    void crud() throws SQLException {
        Member member = new Member("data3", 10000);

        try {
            repository.save(member);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Member findMember = repository.findById(member.getId());
        log.info("findMember={}", findMember);
        Assertions.assertThat(findMember).isEqualTo(member);

        repository.update(member.getId(), 20000);
        Member updatedMember = repository.findById(member.getId());

        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(20000);

        repository.delete(member.getId());

        Assertions.assertThatThrownBy(() -> repository.findById(member.getId()))
                .isInstanceOf(NoSuchElementException.class);

    }
}