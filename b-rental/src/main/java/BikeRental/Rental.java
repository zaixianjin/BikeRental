package BikeRental;

import javax.persistence.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.BeanUtils;
// import org.springframework.boot.json.JsonParser;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Entity
@Table(name="Rental_table")
public class Rental {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long voucherId;
    private Long bikeId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        Rented rented = new Rented();
        BeanUtils.copyProperties(this, rented);
        rented.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        // Bike Aggregate
        BikeRental.external.Bike bike = new BikeRental.external.Bike();
        bike.setUserId(this.getUserId());
        bike.setBikeId(this.getBikeId());
        bike.setId(this.getBikeId());
        bike.setStatus("occupied");
       
        System.out.println("bikeID :  " + bike.getBikeId() + " /// ID: "+ bike.getId() +"/// USERID : " + bike.getUserId());

        // mappings goes here
        RentalApplication.applicationContextBike.getBean(BikeRental.external.BikeService.class)
            .rent(bike);

        // Voucher Aggregate

        RestTemplate restTemplate = RentalApplication.applicationContextBike.getBean(RestTemplate.class);
        Environment env = RentalApplication.applicationContextBike.getEnvironment();

        String productUrl = env.getProperty("api.url.voucher") + "/vouchers/" + this.getVoucherId();
        ResponseEntity<String> productEntity = restTemplate.getForEntity(productUrl, String.class);
        System.out.print("productEntity : " + productEntity.getBody());


        JsonParser parser = new JsonParser(); 
        JsonObject jsonObject = parser.parse(productEntity.getBody()).getAsJsonObject();

        Long voucherCnt = 0L;
        voucherCnt = jsonObject.get("voucherCnt").getAsLong();

        System.out.print("voucherCnt : " + voucherCnt);
        System.out.println("voucherID :  " + this.getVoucherId());

        BikeRental.external.Voucher voucher = new BikeRental.external.Voucher();
        voucher.setUserId(this.getUserId());
        voucher.setId(this.getVoucherId());
        voucher.setVoucherCnt(voucherCnt-1L);
        
            
        RentalApplication.applicationContextBike.getBean(BikeRental.external.VoucherService.class)
                // .rent(this.getVoucherId(), voucher);
                .rent(voucher);

    }

    @PreUpdate
    public void onPreUpdate(){

        // RestTemplate restTemplate = RentalApplication.applicationContextBike.getBean(RestTemplate.class);
        // Environment env = RentalApplication.applicationContextBike.getEnvironment();

        // String productUrl = env.getProperty("api.url.voucher") + "/vouchers/" + this.getVoucherId();
        // ResponseEntity<String> productEntity = restTemplate.getForEntity(productUrl, String.class);
        // System.out.print("###onPreUpdate#### productEntity : " + productEntity.getBody());


        // JsonParser parser = new JsonParser(); 
        // JsonObject jsonObject = parser.parse(productEntity.getBody()).getAsJsonObject();


        // Long voucherCnt = 0L;
        // voucherCnt = jsonObject.get("voucherCnt").getAsLong();

        // System.out.println("voucherCnt : " + voucherCnt);
        // System.out.println("voucherID :  " + this.getVoucherId());

        

        // VoucherRepository voucherRepository = RentalApplication.applicationContext.getBean(voucherRepository.class); 


        RentalCancelled rentalCancelled = new RentalCancelled();
        // rentalCancelled.setVoucherCnt(voucherCnt+1L);
        BeanUtils.copyProperties(this, rentalCancelled);
        rentalCancelled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }
    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
