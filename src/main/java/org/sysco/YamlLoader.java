package org.sysco;

import org.sysco.configs.ConfigSnakeYAML;
import org.sysco.models.AppConfig;
import java.io.IOException;

public class YamlLoader {
    public static void main(String[] args) throws IOException {
        ConfigSnakeYAML configSnakeYAML = new ConfigSnakeYAML();
        AppConfig appConfigObj = configSnakeYAML.dataExtractor();
        System.out.println(appConfigObj);
    }
}