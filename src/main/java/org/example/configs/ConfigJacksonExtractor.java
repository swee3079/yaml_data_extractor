package org.example.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.models.EnvironmentConfigs;
import org.example.models.Major;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ConfigJacksonExtractor {

    public Major dataExtractor() throws IOException {
        String resolvedEnvYaml = extractYAMLForString("application.yaml");
        ObjectMapper mapperEnv = new ObjectMapper(new YAMLFactory());

        EnvironmentConfigs environmentConfigs = mapperEnv.readValue(resolvedEnvYaml, EnvironmentConfigs.class);
        String yamlFileName = switch (environmentConfigs.getEnvironment()) {
            case "dev" -> "application-local.yaml";
            case "stag" -> "application-stag.yaml";
            case "prod" -> "application-prod.yaml";
            default -> throw new IllegalArgumentException("Unsupported environment: " + environmentConfigs.getEnvironment());
        };

        String resolvedYaml = extractYAMLForString(yamlFileName);
        Major majorObj = mapperEnv.readValue(resolvedYaml, Major.class);
        return majorObj;
    }



    private static String extractYAMLForString(String yamlFile) throws IOException {
        InputStream yamlInputStream = ConfigJacksonExtractor.class.getClassLoader().getResourceAsStream(yamlFile);
        if (yamlInputStream == null) {
            throw new IllegalArgumentException("Resource not found: " + yamlFile);
        }
        String rawYaml = new String(yamlInputStream.readAllBytes(), StandardCharsets.UTF_8);
        for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
            rawYaml = rawYaml.replace("${" + entry.getKey() + "}", entry.getValue());
        }

        return rawYaml;
    }
}
