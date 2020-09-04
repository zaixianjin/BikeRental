package BikeRental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Deliver_table")
public class Deliver {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long rentalId;
    private Long bikeId;
    private Long userId;
    private String status;

    @PostPersist
    public void onPostPersist(){


        //for Circuit Breaker testing part start:
        try {
            Thread.currentThread().sleep((long) (3000 + Math.random() * 1000));
            System.out.println("[Circuit Breaker STRESS TESTING] 임의 부하 처리 부분 ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //end

        System.out.println("deliver onPostPersist");
        Delivered delivered = new Delivered();
        BeanUtils.copyProperties(this, delivered);
        delivered.publishAfterCommit();


    }

    @PreRemove
    public void onPreRemove(){
        DeliverCancelled deliverCancelled = new DeliverCancelled();
        BeanUtils.copyProperties(this, deliverCancelled);
        deliverCancelled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
