package org.sysco.models;

public class EnvironmentConfigs {
    private String environment;


    public EnvironmentConfigs() {
    }

    public EnvironmentConfigs(String environment) {
        this.environment = environment;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "EnvironmentConfigs{" +
                "environment='" + environment + '\'' +
                '}';
    }
}
