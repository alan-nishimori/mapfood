package mapfood.controller;

import mapfood.entity.ClientEntity;
import mapfood.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public HttpStatus test() {
        ClientEntity client = new ClientEntity();
        client.setId(1);
        client.setLatitude(10D);
        client.setLongitude(20D);
        client.setType("Point");
        clientRepository.save(client);
        System.out.println(clientRepository.findById(1).orElseThrow(() -> new RuntimeException("erro")).toString());
        return HttpStatus.OK;
    }

}
