package doctintuc.com.websitedoctintuc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import doctintuc.com.websitedoctintuc.adapter.web.controller.CategoryController;

@SpringBootApplication
public class WebsiteDocTinTucApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteDocTinTucApplication.class, args);
	}

}
