package com.example;

import java.util.List;

/**
 * Created by achalise on 12/1/17.
 */
public interface UserDetailsAggregator {
    public List<String> currentAciveUsers();
    public List<String> userNames();
}
