package demo.dushyant.otelkafkaproduce;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
public class OtelkafkaproduceApplication {

	@Value("${spring.kafka.bootstrap-servers}")
	private String brokers;

	public static void main(String[] args) {
		SpringApplication.run(OtelkafkaproduceApplication.class, args);
	}

	@Bean
	public KafkaAdmin getAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        return new KafkaAdmin(configs);
    }
}
