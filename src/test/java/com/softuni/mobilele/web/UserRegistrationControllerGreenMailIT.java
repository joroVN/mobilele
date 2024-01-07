package com.softuni.mobilele.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerGreenMailIT {

//    @Value("${mail.host}")
//    private String mailHost;
//
//    @Value("${mail.port}")
//    private Integer mailPort;
//
//    @Value("${mail.username}")
//    private String username;
//
//    @Value("${mail.password}")
//    private String password;

    private GreenMail greenMail;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(new ServerSetup(1025, "localhost", "smtp"));
        greenMail.start();
        greenMail.setUser("mobilele@mobilele.com", "topsecret");
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", "anna@example.com")
                        .param("firstName", "Anna")
                        .param("lastName", "Petrova")
                        .param("password", "topsecret")
                        .param("confirmPassword", "topsecret")
                        .with(csrf())
                        .cookie(new Cookie("lang", Locale.GERMAN.getLanguage())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

//        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
//        Assertions.assertEquals(1, receivedMessages.length);
    }


}
