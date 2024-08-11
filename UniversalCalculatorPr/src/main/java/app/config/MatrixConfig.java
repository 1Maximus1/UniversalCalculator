package app.config;

import app.services.matrixActions.impl.MatrixActions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatrixConfig {
	@Bean
	public MatrixActions matrixActions() {
		return new MatrixActions();
	}


}