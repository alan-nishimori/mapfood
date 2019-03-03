package mapfood;

import mapfood.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class ImportDataClientEntity {
    private String cvsSplitBy = ",";

    @Autowired
    private ClientEntityRepository repository;

    //Importa os dados dos clientes e salva na collection
    public void importData(){

        String csvFile = "clientes.csv";
        String line;
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null){
                String[] arquivo = line.split(cvsSplitBy);
                ClientEntity newClient = new ClientEntity();

                if(!arquivo[0].equals("ID Cliente")){
                    newClient.setId(Integer.parseInt(arquivo[0]));
                    newClient.setLongitude(new BigDecimal(arquivo[1]));
                    newClient.setLatitude(new BigDecimal(arquivo[2]));

                    repository.save(newClient);
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
}
