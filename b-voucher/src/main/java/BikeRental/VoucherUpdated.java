package BikeRental;

public class VoucherUpdated extends AbstractEvent {

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
        //호출됨
        System.out.println("### [VoucherUpdated] setVoucherCnt(), voucherCnt =>" +voucherCnt);

        boolean bOK = true;
        //NUll check
        if(this.userId == null)
            bOK = false;
        if(this.id == null)
            bOK = false;

        //voucher 수량 업데이트 시행
        if(bOK){
            this.voucherCnt = voucherCnt;
        }
    }
}