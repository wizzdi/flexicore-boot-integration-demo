package com.example.pet.config;

import com.example.pet.model.Pet;
import com.flexicore.converters.JsonConverter;
import com.flexicore.model.Baseclass;
import com.flexicore.model.SecurityUser;
import com.flexicore.model.User;
import com.wizzdi.flexicore.boot.dynamic.invokers.model.DynamicExecution;
import com.wizzdi.flexicore.boot.jpa.service.EntitiesHolder;
import com.wizzdi.flexicore.file.model.FileResource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class ManualEntityProvider {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public EntitiesHolder manualEntityHolder(){
		return new EntitiesHolder(new HashSet<>(Arrays.asList(Baseclass.class, User.class, SecurityUser.class, Pet.class, JsonConverter.class, FileResource.class,DynamicExecution.class)));
	}
}
