package org.example.configs;


import org.example.models.EnvironmentConfigs;
import org.example.models.Major;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ConfigSnakeYAML {


    public Major dataExtractor() throws IOException {

        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setTagInspector(tag -> tag.getClassName().equals(EnvironmentConfigs.class.getName()));
        loaderOptions.setTagInspector(tag -> tag.getClassName().equals(Major.class.getName()));
        Yaml yamlForEnv = new Yaml(new Constructor(EnvironmentConfigs.class, loaderOptions));
        Yaml yamlForConfigs = new Yaml(new Constructor(Major.class, loaderOptions));

        String extractedEnvYaml = loadAndExpandEnvVariables("application.yaml");
        EnvironmentConfigs environmentConfigs = yamlForEnv.load(extractedEnvYaml);

        String yamlFileName = switch (environmentConfigs.getEnvironment()) {
            case "dev" -> "application-local.yaml";
            case "stag" -> "application-stag.yaml";
            case "prod" -> "application-prod.yaml";
            default -> throw new IllegalArgumentException("Unsupported environment: " + environmentConfigs.getEnvironment());
        };

        String dataExtractedYaml = loadAndExpandEnvVariables(yamlFileName);
        Major extractedDataObj = yamlForConfigs.load(dataExtractedYaml);
        return extractedDataObj;
    }


    private String loadAndExpandEnvVariables(String yamlFile) throws IOException {
        InputStream yamlInputStream = ConfigSnakeYAML.class.getClassLoader().getResourceAsStream(yamlFile);
        String rawYaml = new String(yamlInputStream.readAllBytes(), StandardCharsets.UTF_8);
        for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
            rawYaml = rawYaml.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return rawYaml;
    }


}
