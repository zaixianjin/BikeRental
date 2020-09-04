package BikeRental;

import BikeRental.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageViewHandler {


    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserRegistered_then_CREATE_1 (@Payload UserRegistered userRegistered) {
        try {
            if (userRegistered.isMe()) {
                // view 객체 생성
                MyPage myPage = new MyPage();
                // view 객체에 이벤트의 Value 를 set 함
                myPage.setUserId(userRegistered.getId());
                myPage.setUserName(userRegistered.getUserName());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserUpdated_then_UPDATE_1(@Payload UserUpdated userUpdated) {
        try {
            if (userUpdated.isMe()) {
                // view 객체 조회
                Optional<MyPage> myPageOptional = myPageRepository.findById(userUpdated.getId());
                if( myPageOptional.isPresent()) {
                    MyPage myPage = myPageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setUserName(userUpdated.getUserName());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenVoucherBought_then_UPDATE_2(@Payload VoucherBought voucherBought) {
        try {
            if (voucherBought.isMe()) {
                // view 객체 조회
                Optional<MyPage> myPageOptional = myPageRepository.findById(voucherBought.getUserId());
                if( myPageOptional.isPresent()) {
                    MyPage myPage = myPageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setVoucherId(voucherBought.getId());
                    myPage.setVoucherCnt(voucherBought.getVoucherCnt());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenVoucherMinus_then_UPDATE_(@Payload VoucherMinus voucherMinus) {
        try {
            if (voucherMinus.isMe()) {
                // view 객체 조회
                Optional<MyPage> myPageOptional = myPageRepository.findById(voucherMinus.getUserId());
                if( myPageOptional.isPresent()) {
                    MyPage myPage = myPageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setVoucherCnt(voucherMinus.getVoucherCnt());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenVoucherUpdated_then_UPDATE_4(@Payload VoucherUpdated voucherUpdated) {
        try {
            if (voucherUpdated.isMe()) {
                // view 객체 조회
                Optional<MyPage> myPageOptional = myPageRepository.findById(voucherUpdated.getUserId());
                if( myPageOptional.isPresent()) {
                    MyPage myPage = myPageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setVoucherCnt(voucherUpdated.getVoucherCnt());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenRented_then_UPDATE_5(@Payload Rented rented) {
        try {
            if (rented.isMe()) {
                // view 객체 조회
                Optional<MyPage> myPageOptional = myPageRepository.findById(rented.getUserId());
                if( myPageOptional.isPresent()) {
                    MyPage myPage = myPageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setBikeId(rented.getBikeId());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenRentalCancelled_then_UPDATE_6(@Payload RentalCancelled rentalCancelled) {
        try {
            if (rentalCancelled.isMe()) {
                // view 객체 조회
                Optional<MyPage> myPageOptional = myPageRepository.findById(rentalCancelled.getUserId());
                if( myPageOptional.isPresent()) {
                    MyPage myPage = myPageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setBikeId(rentalCancelled.getBikeId());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserDeleted_then_DELETE_1(@Payload UserDeleted userDeleted) {
        try {
            if (userDeleted.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                myPageRepository.deleteById(userDeleted.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
