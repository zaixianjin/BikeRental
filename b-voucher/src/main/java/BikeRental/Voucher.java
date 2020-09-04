package BikeRental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Voucher_table")
public class Voucher {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long voucherCnt;

    @PostPersist
    public void onPostPersist(){
        VoucherBought voucherBought = new VoucherBought();
        BeanUtils.copyProperties(this, voucherBought);
        voucherBought.publishAfterCommit();



        //




    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("### onPostUpdate() ###");

        VoucherUpdated voucherUpdated = new VoucherUpdated();
        BeanUtils.copyProperties(this, voucherUpdated);
        voucherUpdated.publishAfterCommit();
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
    public Long getVoucherCnt() {
        return voucherCnt;
    }

    public void setVoucherCnt(Long voucherCnt) {

        //voucher 개수 조정
        this.voucherCnt = voucherCnt;
    }




}
