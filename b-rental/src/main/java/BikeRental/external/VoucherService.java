
package BikeRental.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
// http://voucher:8080 ${api.url.voucher}
@FeignClient(name="voucher", url="${api.url.voucher}")
public interface VoucherService {

    // @RequestMapping(method= RequestMethod.PATCH, path="/vouchers/{voucherId}")
    // public void rent(@PathVariable("voucherId") Long voucherID,  @RequestBody Voucher voucher);

    @RequestMapping(method= RequestMethod.POST, path="/vouchers")
    public void rent(@RequestBody Voucher voucher);

}