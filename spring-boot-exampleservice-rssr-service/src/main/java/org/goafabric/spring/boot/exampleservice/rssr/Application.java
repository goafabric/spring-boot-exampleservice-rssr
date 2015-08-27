package org.goafabric.spring.boot.exampleservice.rssr;

import org.goafabric.spring.boot.exampleservice.rssr.service.configuration.BaseConfiguration;
import org.springframework.boot.SpringApplication;

/**
 * Created by amautsch on 26.06.2015.
 */
public final class Application {
    private Application() {
    }

    public static void main(String[] args){
        SpringApplication.run(BaseConfiguration.class, args);
    }
}
