package com.example;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
/**
 * Created by achalise on 12/1/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class DefaultUserDetailsAggregatorTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(9990));

    @Autowired
    private UserDetailsAggregator userDetailsAggregator;

    @Test
    public void testGetCurrentActiveUsers() {
        List<String> users = userDetailsAggregator.currentAciveUsers();
        Assert.assertEquals(1, users.size());
        Assert.assertTrue(users.contains("johnc"));
    }

    @Test
    public void testGetUserNames() {
        List<String> users = userDetailsAggregator.userNames();
        Assert.assertEquals(1, users.size());
        Assert.assertTrue(users.contains("username"));
    }
}
