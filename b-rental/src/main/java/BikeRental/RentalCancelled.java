package BikeRental;

public class RentalCancelled extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long voucherId;
    private Long bikeId;
    private String status;
    private Long voucherCnt;

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

    public Long getVoucherCnt() {
        return voucherCnt;
    }

    public void setVoucherCnt(Long voucherCnt) {
        this.voucherCnt = voucherCnt;
    }
}