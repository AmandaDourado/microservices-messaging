package course.msavaliadorcredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // scannear onde tem feignclients em outros services
public class MsavaliadorcreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsavaliadorcreditoApplication.class, args);
	}

}
