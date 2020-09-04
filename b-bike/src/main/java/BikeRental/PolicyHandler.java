package BikeRental;

import BikeRental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    BikeRepository bikeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRentalCancelled_RentalCancel(@Payload RentalCancelled rentalCancelled){

        if(rentalCancelled.isMe()){
            System.out.println("##### listener RentalCancel : " + rentalCancelled.toJson());

            Bike bike = new Bike();
            bike.setId(rentalCancelled.getBikeId());
            // bike.setStatus(rentalCancelled.getStatus());

            bike.setStatus("ouccpied");
            bikeRepository.save(bike);

            bike.setStatus("ouccupied");
            bikeRepository.save(bike);
        }
    }

}
