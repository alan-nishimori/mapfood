package mapfood;

import mapfood.entity.Motoboy;
import mapfood.entity.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class ImportDataMotoBoy {
    private String cvsSplitBy = ",";

    @Autowired
    private MotoboyRepository repository;

    //Importa os dados dos clientes e salva na collection
    public void importData(){

        String csvFile = "motoboys.csv";
        String line;
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null){
                String[] arquivo = line.split(cvsSplitBy);
                Motoboy newMotoboy = new Motoboy();

                if(!arquivo[0].equals("ID Motoboy")){
                    newMotoboy.setId(Integer.parseInt(arquivo[0]));
                    newMotoboy.setLongitude(new BigDecimal(arquivo[1]));
                    newMotoboy.setLatitude(new BigDecimal(arquivo[2]));

                    repository.save(newMotoboy);
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
