package com.example.zakladmechanicznyspringboot;
import com.example.zakladmechanicznyspringboot.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SmokeTest {
    @Autowired
    private IndexController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}








