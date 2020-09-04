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
    DeliverRepository deliverRepository;


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRentalCancelled_DeliverCancelled(@Payload RentalCancelled rentalCancelled){

        if(rentalCancelled.isMe()){
            System.out.println("##### listener DeliverCancelled : " + rentalCancelled.toJson());

            Deliver deliver = new Deliver();
            deliver.setRentalId(rentalCancelled.getId());
            deliver.setBikeId(rentalCancelled.getBikeId());
            deliver.setStatus("deliver cancelled");
            deliver.setUserId(rentalCancelled.getUserId());

            deliverRepository.save(deliver);
        }
    }

}
