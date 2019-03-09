package mapfood.dbstarter;

import mapfood.model.Establishment;
import mapfood.model.Localization;
import mapfood.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EstablishmentStarter {

    public static List<Establishment> establishmentsStart() {

        String csvFile = "estabelecimento-por-municipio.csv";
        String line;
        String csvSplitter = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        List<Establishment> establishments = new ArrayList<>();

        // Start restaurants
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] buffer = line.split(csvSplitter);

                // 0                1           2           3          4        5
                //restaurant_id,restaurant,address_city,longitude,latitude,dish_description
                Establishment establishment = new Establishment();
                establishment.setId(buffer[0]);
                establishment.setName(buffer[1]);
                establishment.setCity(buffer[2]);
                establishment.setDescription(buffer[5]);
                if (buffer[3].equals("SAO PAULO")) {
                    System.out.println("teste");
                }
                establishment.setLocalization(new Localization(Double.valueOf(buffer[3]), Double.valueOf(buffer[4])));

                establishments.add(establishment);
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar arquivo.");
            e.printStackTrace();
        }


        csvFile = "produtos-por-estabelecimento.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] buffer = line.split(csvSplitter);

                //      0               1       2           3              4           5           6
                //item_description,item_id,restaurant_id,restaurant,classification,unit_price,address_city
                Product product = new Product();
                product.setId(buffer[1]);
                product.setDescription(buffer[0]);
                product.setPrice(Double.valueOf(buffer[5]));
                product.setClassification(buffer[4]);

                establishments.stream()
                        .filter(e -> e.getName().equals(buffer[3]))
                        .findFirst()
                        .ifPresent(establishment -> establishment.addProduct(product));
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar arquivo.");
            e.printStackTrace();
        }

        return establishments;
    }
}
