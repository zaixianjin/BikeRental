
package BikeRental.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
// http://bike:8080 ${api.url.bike}
@FeignClient(name="bike", url="${api.url.bike}")
public interface BikeService {

    @RequestMapping(method= RequestMethod.POST, path="/bikes")
    public void rent(@RequestBody Bike bike);
      

}