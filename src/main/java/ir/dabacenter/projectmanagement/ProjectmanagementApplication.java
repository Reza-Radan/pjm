package ir.dabacenter.projectmanagement;

import io.jsonwebtoken.Clock;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class ProjectmanagementApplication extends SpringBootServletInitializer {


	@Autowired
	Environment environment;
	private static Clock clock = DefaultClock.INSTANCE;


	public static void main(String[] args) {


		SpringApplication.run(ProjectmanagementApplication.class, args);
		System.out.print(" time second :  "+clock.now().getTime()+" after " );
	}


	public String getCatalinaBasePath(){
		return environment.getProperty("catalina.base");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {return application.sources(ProjectmanagementApplication.class);	}

}
