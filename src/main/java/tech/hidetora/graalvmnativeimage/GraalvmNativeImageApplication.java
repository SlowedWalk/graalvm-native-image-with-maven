package tech.hidetora.graalvmnativeimage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@Slf4j
public class GraalvmNativeImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraalvmNativeImageApplication.class, args);
	}

}

@RestController
class Controller {
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return List.of(
				new Customer(1L, "John", "Doe", "johndoe@gmail.com"),
				new Customer(2L, "Jane", "Doe", "janedoe@gmail.com"),
				new Customer(3L, "Jack", "Doe", "jack@gmail.com")
		);
	}

	@GetMapping("/")
	public String welcome() {
		return "Welcome to GraalVM Native Image";
	}
}

record Customer(
		Long id,
		String firstName,
		String lastName,
		String email
) { }

