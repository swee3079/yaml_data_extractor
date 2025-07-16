package org.sysco.configs;


import org.sysco.models.AppConfig;
import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ConfigSnakeYAML {


    public AppConfig dataExtractor() throws IOException {

        Yaml yaml = new Yaml();
        return yaml.loadAs(
                loadAndExpandEnvVariables(
                        switch (System.getenv("ENVIRONMENT")) {
                            case "dev" -> "application-local.yaml";
                            case "stag" -> "application-stag.yaml";
                            case "prod" -> "application-prod.yaml";
                            default -> throw new IllegalArgumentException("Unsupported environment");
                        }
                ), AppConfig.class);
    }

    private String loadAndExpandEnvVariables(String yamlFile) throws IOException {
        String rawYaml = new String(ConfigSnakeYAML.class.getClassLoader().getResourceAsStream(yamlFile).readAllBytes(), StandardCharsets.UTF_8);
        for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
            rawYaml = rawYaml.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return rawYaml;
    }

}
