package org.upc.ultimapracticaparaparcial;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UltimaPracticaParaParcialApplication {

    public static void main(String[] args) {
        SpringApplication.run(UltimaPracticaParaParcialApplication.class, args);
    }

    @Bean // <-- AÑADE ESTE MÉTODO
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
