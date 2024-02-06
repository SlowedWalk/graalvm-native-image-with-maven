package tech.hidetora.graalvmnativeimage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import tech.hidetora.graalvmnativeimage.entity.Customer;
import tech.hidetora.graalvmnativeimage.repository.CustomerRepository;

import java.util.List;

@SpringBootApplication
@Slf4j
public class GraalvmNativeImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraalvmNativeImageApplication.class, args);
	}
	@Bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		log.info("================= Initialization ================");
		return args -> {
			List<Customer> customers = List.of(
					Customer.builder()
							.firstName("Hidetora").lastName("Tojo").email("hidetoratojo@gmail.com").build(),
					Customer.builder()
							.firstName("Aomine").lastName("Diaki").email("diaki.aomine@gmail.com").build(),
					Customer.builder()
							.firstName("Hanane").lastName("yamal").email("hanane@gmail.com").build()
			);
			customerRepository.saveAll(customers);
		};
	}
}
