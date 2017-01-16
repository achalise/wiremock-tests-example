package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by achalise on 12/1/17.
 */

@Component
public class DefaultUserDetailsAggregator implements UserDetailsAggregator {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<String> currentAciveUsers() {
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity("http://localhost:9990/users", String[].class);
        String[] users = responseEntity.getBody();
        return Arrays.asList(users);
    }

    @Override
    public List<String> userNames() {
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity("http://localhost:9990/user/names", String[].class);
        String[] users = responseEntity.getBody();
        return Arrays.asList(users);
    }

}
