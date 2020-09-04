
package BikeRental.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="deliver", url="${api.url.deliver}")
public interface DeliverService {

    @RequestMapping(method= RequestMethod.POST, path="/delivers")
    public void deliverRequest(@RequestBody Deliver deliver);

}