package BikeRental;

import BikeRental.config.kafka.KafkaProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class RentalApplication {
    protected static ApplicationContext applicationContextBike;

    public static void main(String[] args) {
        applicationContextBike = SpringApplication.run(RentalApplication.class, args);
    }


    @Bean 
 	RestTemplate restTemplate() { 
 		RestTemplate restTemplate = new RestTemplate(); 
 		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(); 
 		converter.setObjectMapper(new ObjectMapper()); 
 		restTemplate.getMessageConverters().add(converter); 
 		return restTemplate; 
 	} 



}


