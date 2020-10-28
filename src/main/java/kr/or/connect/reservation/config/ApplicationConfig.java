package kr.or.connect.reservation.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
//@Import({ PersistenceJPAConfig.class, WebMvcContextConfiguration.class })
@Configuration
@ComponentScan(basePackages = { "kr.or.connect.reservation.dao", "kr.or.connect.reservation.service", "kr.or.connect.reservation.exception" })
@EntityScan("kr.or.connect.reservation.model")
@EnableJpaRepositories("kr.or.connect.reservation.repository")
public class ApplicationConfig {

}
