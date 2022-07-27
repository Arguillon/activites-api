package com.activites.api.cucumber.config;

import com.activites.api.ActivitesApiApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ActivitesApiApplication.class, loader = SpringBootContextLoader.class)
@AutoConfigureMockMvc
@CucumberContextConfiguration
@TestPropertySource(locations = "classpath:application-test.properties")
public class CucumberSpringConfig {
}
