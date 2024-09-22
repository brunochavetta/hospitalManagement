package com.example.laboratory4.test;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface TestRepositoryCustom {
    void modifyTest(Test test) throws Exception;

    List<Test> listTests(int option, String search) throws Exception;
}
