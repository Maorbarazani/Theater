package theater;

import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class Config {

/*
	@Bean
	public FilterRegistrationBean<SessionFilter> sessionFilter() {
		FilterRegistrationBean<SessionFilter> sessionFilterBean = new FilterRegistrationBean<>();

		sessionFilterBean.setFilter(new SessionFilter());
		sessionFilterBean.addUrlPatterns("/rest/*");

		return sessionFilterBean;
	}

	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter() {
		FilterRegistrationBean<LoginFilter> loginFilterBean = new FilterRegistrationBean<>();

		loginFilterBean.setFilter(new LoginFilter());
		loginFilterBean.addUrlPatterns("/index.html");

		return loginFilterBean;
	}
*/
	@PreDestroy
	public void systemShutdown() {
	}

}
