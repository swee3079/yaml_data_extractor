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
        String expandedYaml = loadAndExpandEnvVariables("application.yaml");

        LoaderOptions loaderOptionsEnv = new LoaderOptions();
        loaderOptionsEnv.setTagInspector(tag -> tag.getClassName().equals(EnvironmentConfigs.class.getName()));
        Yaml yamlForEnv = new Yaml(new Constructor(EnvironmentConfigs.class, loaderOptionsEnv));
        EnvironmentConfigs environmentConfigs = yamlForEnv.load(expandedYaml);

        String yamlFileName = switch (environmentConfigs.getEnvironment()) {
            case "dev" -> "application-local.yaml";
            case "stag" -> "application-stag.yaml";
            case "prod" -> "application-prod.yaml";
            default -> throw new IllegalArgumentException("Unsupported environment: " + environmentConfigs.getEnvironment());
        };

        return extractOperationalConfigurations(yamlFileName);
    }


    public Major extractOperationalConfigurations(String yamlFile) throws IOException {
        String expandedYaml = loadAndExpandEnvVariables(yamlFile);

        LoaderOptions loaderOptions = new LoaderOptions();
        TagInspector tagInspector = tag -> tag.getClassName().equals(Major.class.getName());
        loaderOptions.setTagInspector(tagInspector);
        Yaml yamlForConfigs = new Yaml(new Constructor(Major.class, loaderOptions));
        return yamlForConfigs.load(expandedYaml);
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
