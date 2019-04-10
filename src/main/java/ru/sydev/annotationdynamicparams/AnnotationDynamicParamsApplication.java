package ru.sydev.annotationdynamicparams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class AnnotationDynamicParamsApplication {
	public static void main(String[] args) {
		final ConfigurableApplicationContext app = SpringApplication.run(AnnotationDynamicParamsApplication.class, args);
		final MyService service = (MyService) app.getBean("myService");
		service.check();
	}
}
