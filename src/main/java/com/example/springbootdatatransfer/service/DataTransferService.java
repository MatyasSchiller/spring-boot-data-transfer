package com.example.springbootdatatransfer.service;

import com.example.springbootdatatransfer.model.User;
import com.example.springbootdatatransfer.repository.ExamOpportunityRepository;
import com.example.springbootdatatransfer.repository.ResultRepository;
import com.example.springbootdatatransfer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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


    private static final Logger logger = LoggerFactory.getLogger(DataTransferService.class);

    public void transferData() {
        try {
            // Fetch data from source database (example SQL query)
            String query = "SELECT * FROM source_table"; // Replace with actual table name
            List<Map<String, Object>> rows = sourceJdbcTemplate.queryForList(query);

            for (Map<String, Object> row : rows) {
                // Parse JSON data and save it to target database
                // Example for parsing and saving User entity
                User user = new User();
                user.setName(row.get("name").toString());
                user.setEmail(row.get("email").toString());
                user.setDateOfBirth(Date.valueOf(row.get("date_of_birth").toString()));
                user.setRole(row.get("role").toString());
                userRepository.save(user);

                // Repeat for ExamOpportunity and Result
            }
        } catch (Exception e) {
            logger.error("Error occurred while transferring data: ", e);
            // Handle exception
        }
    }
}

