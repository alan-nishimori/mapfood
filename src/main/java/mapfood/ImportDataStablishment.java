package mapfood;

import mapfood.entity.Establishment;
import mapfood.entity.EstablishmentRepository;
import mapfood.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ImportDataStablishment {
    private String cvsSplitBy = ",";

    @Autowired
    private EstablishmentRepository repository;

    //Importa os dados dos estabelecimentos e salva na collection
    public void importData(){

        String csvFile = "estabelecimento-por-municipio.csv";
        String line;
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null){
                String[] arquivo = line.split(cvsSplitBy);
                Establishment newStablishment = new Establishment();

                if(!arquivo[0].equals("restaurant_id")){
                    newStablishment.setId(arquivo[0]);
                    newStablishment.setName(arquivo[1]);
                    newStablishment.setCity(arquivo[2]);
                    newStablishment.setLongitude(new BigDecimal(arquivo[3]));
                    newStablishment.setLatitude(new BigDecimal(arquivo[4]));
                    newStablishment.setDescription(arquivo[5]);
                    newStablishment.setProducts(importDataProduct(arquivo[0]));

                    repository.save(newStablishment);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Product> importDataProduct(String establishmentId){
        String csvFile = "produtos-por-estabelecimento.csv";
        String line;
        BufferedReader br = null;
        List<Product> products = new ArrayList<>();

        try{
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null){
                String[] arquivo = line.split(cvsSplitBy);

                if(!arquivo[0].equals("item_description")){
                    if(arquivo[2].equals(establishmentId)){
                        Product newProduct = new Product();

                        newProduct.setId(arquivo[1]);
                        newProduct.setDescription(arquivo[0]);
                        newProduct.setClassification(arquivo[4]);
                        newProduct.setPrice(Double.parseDouble(arquivo[5]));

                        products.add(newProduct);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return products;
    }
}
