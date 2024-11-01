package tn.esprit.spring.kaddem.services;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)

@SpringBootTest

@Slf4j

class UniversiteServiceImplTest {

    @Autowired

    IUniversiteService universiteService;
    @Test

     void testAddUniversite() throws ParseException {




        Universite u = new Universite("ESPRIT");

        Universite universite = universiteService.addUniversite(u);

        log.info("Universite "+ universite);

        assertNotNull(universite.getIdUniv());

        assertNotNull(universite.getNomUniv());




    }}