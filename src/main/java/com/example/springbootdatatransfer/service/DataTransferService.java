package com.example.springbootdatatransfer.service;

import com.example.springbootdatatransfer.model.ExamOpportunity;
import com.example.springbootdatatransfer.model.Result;
import com.example.springbootdatatransfer.model.User;
import com.example.springbootdatatransfer.repository.ExamOpportunityRepository;
import com.example.springbootdatatransfer.repository.ResultRepository;
import com.example.springbootdatatransfer.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataTransferService {

    @Autowired
    private JdbcTemplate sourceJdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamOpportunityRepository examOpportunityRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void transferData() {
        // Fetch data from source database (example SQL query)
        String query = "SELECT * FROM source_table"; // Replace with actual table name
        List<Map<String, Object>> rows = sourceJdbcTemplate.queryForList(query);

        for (Map<String, Object> row : rows) {
            // Parse JSON data and save it to target database
            try {
                // Example for parsing and saving User entity
                User user = new User();
                user.setName(row.get("name").toString());
                user.setEmail(row.get("email").toString());
                user.setDateOfBirth(Date.valueOf(row.get("date_of_birth").toString()));
                user.setRole(row.get("role").toString());
                userRepository.save(user);

                // Parse and save ExamOpportunity
                ExamOpportunity examOpportunity = new ExamOpportunity();
                examOpportunity.setDate(Date.valueOf(row.get("exam_date").toString()));
                examOpportunity.setSubject(row.get("subject").toString());
                examOpportunity.setInstructor(row.get("instructor").toString());
                examOpportunity.setStudent(row.get("student").toString());
                examOpportunity.setPassed(Boolean.parseBoolean(row.get("passed").toString()));
                examOpportunity.setComments(row.get("comments").toString());
                examOpportunityRepository.save(examOpportunity);

                // Parse and save Results
                List<Map<String, Object>> results = objectMapper.readValue(row.get("results").toString(), List.class);
                for (Map<String, Object> resultData : results) {
                    Result result = new Result();
                    result.setExamAspect(resultData.get("aspect").toString());
                    result.setGrade(Integer.parseInt(resultData.get("grade").toString()));
                    result.setExamOpportunity(examOpportunity);
                    resultRepository.save(result);
                }

            } catch (Exception e) {
                e.printStackTrace(); // Consider more sophisticated error handling
            }
        }
    }

    public Map<String, Map<String, Double>> calculateAverages() {
        List<Result> results = resultRepository.findAll();

        return results.stream()
                .collect(Collectors.groupingBy(
                        result -> result.getExamOpportunity().getStudent(),
                        Collectors.groupingBy(
                                Result::getExamAspect,
                                Collectors.averagingInt(Result::getGrade)
                        )
                ));
    }
}
