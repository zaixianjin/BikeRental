package BikeRental;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MyPage_table")
public class MyPage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long userId;
        private Long voucherId;
        private Long bikeId;
        private String userName;
        private Long voucherCnt;


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
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
        public Long getVoucherCnt() {
            return voucherCnt;
        }

        public void setVoucherCnt(Long voucherCnt) {
            this.voucherCnt = voucherCnt;
        }

}
