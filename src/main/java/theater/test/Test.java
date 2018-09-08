package theater.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Test implements ApplicationContextAware {

	private ApplicationContext appContext;
	TestThread testThread;

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.appContext = appContext;
		System.out.println();
		System.out.println("appContext@Test = " + appContext);
		System.out.println();
		System.out.println("## appContext's beans:");
		String[] beans = appContext.getBeanDefinitionNames();
		for (int i = 0; i < beans.length; i++) {
			String bean = beans[i];
			System.out.println(bean);
		}
	}

	@PostConstruct
	private void TheaterInit() {

		testThread = (TestThread) appContext.getBean("testThread");
		testThread.setName("Test Thread");
		testThread.start();

	}

}
