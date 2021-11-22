package com.uncue_core.uncue;

import com.uncue_core.uncue.filleUpload.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
@EnableScheduling
public class UncueApplication {

	public static void main(String[] args) {
		SpringApplication.run(UncueApplication.class, args);
	}

}
