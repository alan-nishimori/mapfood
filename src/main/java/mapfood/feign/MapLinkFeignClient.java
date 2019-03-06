package mapfood.feign;

import mapfood.model.maplink.AuthorizationToken;
import mapfood.model.maplink.ClientAuthorizationData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mapLink", url = "${maplink.url}")
public interface MapLinkFeignClient {

    @PostMapping("/oauth/client_credential/accesstoken?grant_type=client_credentials")
    AuthorizationToken applicationLogin(@RequestBody ClientAuthorizationData clientAuthorizationData);
}
