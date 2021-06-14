package com.example.pet;

import com.example.pet.data.PetRepository;
import com.flexicore.annotations.EnableFlexiCoreBaseServices;
import com.wizzdi.dynamic.properties.converter.EnableDynamicProperties;
import com.wizzdi.flexicore.boot.base.annotations.plugins.EnableFlexiCorePlugins;
import com.wizzdi.flexicore.boot.dynamic.invokers.annotations.EnableDynamicInvokersPlugins;
import com.wizzdi.flexicore.boot.jaxrs.annotations.EnableFlexiCoreJAXRSPlugins;
import com.wizzdi.flexicore.boot.jpa.annotations.EnableFlexiCoreJPAPlugins;
import com.wizzdi.flexicore.boot.rest.annotations.EnableFlexiCoreRESTPlugins;
import com.wizzdi.flexicore.boot.swagger.annotations.EnableFlexiCoreSwaggerPlugins;
import com.wizzdi.flexicore.security.annotations.EnableFlexiCoreSecurity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PetRepository.class)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableFlexiCorePlugins
@EnableFlexiCoreJPAPlugins
@EnableFlexiCoreRESTPlugins
@EnableFlexiCoreBaseServices
@EnableFlexiCoreSecurity
@EnableFlexiCoreSwaggerPlugins
@EnableDynamicInvokersPlugins
@EnableFlexiCoreJAXRSPlugins
@EnableDynamicProperties
public class Application {


    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ApplicationPidFileWriter());
        ConfigurableApplicationContext context=app.run(args);

    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            System.out.println("total of "+beanNames.length +" beans");



        };
    }
}
