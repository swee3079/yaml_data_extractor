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

        LoaderOptions loaderOptionsEnv = new LoaderOptions();
        String expandedEnvYaml = loadAndExpandEnvVariables("application.yaml");

        loaderOptionsEnv.setTagInspector(tag -> tag.getClassName().equals(EnvironmentConfigs.class.getName()));
        loaderOptionsEnv.setTagInspector(tag -> tag.getClassName().equals(Major.class.getName()));
        Yaml yamlForEnv = new Yaml(new Constructor(EnvironmentConfigs.class, loaderOptionsEnv));
        Yaml yamlForConfigs = new Yaml(new Constructor(Major.class, loaderOptionsEnv));

        EnvironmentConfigs environmentConfigs = yamlForEnv.load(expandedEnvYaml);

        String yamlFileName = switch (environmentConfigs.getEnvironment()) {
            case "dev" -> "application-local.yaml";
            case "stag" -> "application-stag.yaml";
            case "prod" -> "application-prod.yaml";
            default -> throw new IllegalArgumentException("Unsupported environment: " + environmentConfigs.getEnvironment());
        };

        String expandedYaml2 = loadAndExpandEnvVariables(yamlFileName);
        return yamlForConfigs.load(expandedYaml2);
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
