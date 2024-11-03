package tn.esprit.spring.kaddem.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.services.IDetailEquipeService;

import java.text.ParseException;

@SpringBootTest
@Slf4j
public class DetailEquipeRestControllerTest {
    @Autowired
    IDetailEquipeService detailEquipeService;

    @Test
    public void testGetDetailEquipes() throws ParseException {

    }
}
