package org.example;

import org.example.configs.ConfigJacksonExtractor;
import org.example.configs.ConfigSnakeYAML;
import org.example.models.Major;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //SnakeYAML --------------------------------------------------------------------------
        ConfigSnakeYAML configSnakeYAML = new ConfigSnakeYAML();
        Major majorObj = configSnakeYAML.dataExtractor();
        System.out.println("Extracted From SnakeYAML "+majorObj.getCatalog().getDb());

        //Jackson dataformat -----------------------------------------------------------------
        ConfigJacksonExtractor configJacksonExtractor = new ConfigJacksonExtractor();
        Major majorObj2 = configJacksonExtractor.dataExtractor();
        System.out.println("Extracted From Jackson Dataformat"+majorObj2.getCatalog().getDb());
    }
}