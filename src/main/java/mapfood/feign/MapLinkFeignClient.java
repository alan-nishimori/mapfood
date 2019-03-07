package mapfood.feign;

import feign.Headers;
import mapfood.FeignClientConfiguration;
import mapfood.model.maplinkTeste.AuthorizationToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

//@FeignClient(name = "mapLink", url = "${maplinkTeste.url}")
@FeignClient(name = "mapLink", url = "https://lbslocal-prod.apigee.net", configuration = FeignClientConfiguration.class)
public interface MapLinkFeignClient {

    @PostMapping("/oauth/client_credential/accesstoken?grant_type=client_credentials")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    AuthorizationToken applicationLogin(@RequestBody Map<String, ?> params);
}
