package BikeRental;

public class VoucherBought extends AbstractEvent {

    private Long id;
    private Long userId;
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
    public Long getVoucherCnt() {
        return voucherCnt;
    }

    public void setVoucherCnt(Long voucherCnt) {
        //voucherCnt 처리
        // null인 경우 0,
        // 그외에는 1증가
        voucherCnt = (voucherCnt == null)  ? 0 : voucherCnt +1;
        this.voucherCnt = voucherCnt;
    }
}