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
        System.out.println("### eventString : "+eventString);
    }
    @Autowired
    VoucherRepository voucherRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRentalCancelled_RentalCancel(@Payload RentalCancelled rentalCancelled){

        if(rentalCancelled.isMe()){
            //voucherCnt 개수 조정 (+1)
            //SAGA Pattern
            //voucher에 rental cancel에 대한 처리
            System.out.println("#####[rentalCancelled] ::SAGA:: voucher =============");
            Voucher voucher = new Voucher();
            voucher.setId(rentalCancelled.getVoucherId());
            voucher.setUserId(rentalCancelled.getUserId());

            boolean bOK = false;
            if(rentalCancelled.getUserId() == null)
                bOK = true;

            if(rentalCancelled.getId() == null)
                bOK = true;

            //Voucher 에 대한 null 처리
            if(voucher.getVoucherCnt()==null)
                voucher.setVoucherCnt(0L);

            if (bOK){
                voucher.setVoucherCnt(voucher.getVoucherCnt() + 1);//1증가
                voucherRepository.save(voucher);
                System.out.println("##### voucherRepository.save() ### : "+voucher);
            }

            System.out.println("##### listener RentalCancel : " + rentalCancelled.toJson());
        }
    }
}