package br.com.jonyfs;

import static org.springframework.util.Assert.notNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.jonyfs.spring.boot.mongodb.thymeleaf.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

	@Resource
	ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		notNull(applicationContext);
	}

}
