package com.example.laboratory4.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    public Test findTestById(String id) {
        return testRepository.findById(id).orElse(null);
    }

    public List<Test> listTests(int option, String search) throws Exception {
        return testRepository.listTests(option, search);
    }

    public void deleteTest(String id) {
        testRepository.deleteById(id);
    }

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public void modifyTest(Test test) throws Exception {
        testRepository.modifyTest(test);
    }
}
