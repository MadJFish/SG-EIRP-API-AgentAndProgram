package com.sg.eirp.program.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties
public class AgentAndProgramProperties {

    private Jwt jwt;

    @Getter
    @Setter
    public static class Jwt {
        String signingKey;
    }


}
