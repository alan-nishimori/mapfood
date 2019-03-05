package mapfood.controller;

import mapfood.model.Establishment;
import mapfood.model.Localization;
import mapfood.model.Product;
import mapfood.repository.ClientRepository;
import mapfood.repository.EstablishmentRepository;
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

    @Autowired
    EstablishmentRepository establishmentRepository;

    @GetMapping
    public HttpStatus test() {
//        Product product = new Product();
//        product.setClassification("Classificação");
//        product.setDescription("Descrição");
//        product.setId("Produto");
//        product.setPrice(200D);
//
//        Localization localization = new Localization();
//        localization.setLatitude(10D);
//        localization.setLongitude(20D);
//
//        Establishment establishment = new Establishment();
//        establishment.setCity("São Paulo");
//        establishment.setDescription("Descrição estabelimento");
//        establishment.setId("Estabelecimento");
//        establishment.setName("Nome");
//        establishment.addProduct(product);
//        establishment.setLocalization(localization);
//
//        establishmentRepository.save(establishment);

        Establishment establishment = establishmentRepository.findById("Estabelecimento").orElseThrow(() -> new RuntimeException("Erro"));
        System.out.println(establishment);

        return HttpStatus.OK;
    }

}
