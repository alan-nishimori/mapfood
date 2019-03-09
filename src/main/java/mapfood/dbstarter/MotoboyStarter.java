package mapfood.dbstarter;

import mapfood.model.Localization;
import mapfood.model.Motoboy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MotoboyStarter {

    public static List<Motoboy> motoboyStart() {

        String csvFile = "motoboys.csv";
        String line;
        String csvSplitter = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        List<Motoboy> clients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] buffer = line.split(csvSplitter);

                Motoboy motoboy = new Motoboy();
                motoboy.setId(Integer.valueOf(buffer[0]));
                motoboy.setLocalization(new Localization(Double.valueOf(buffer[1]), Double.valueOf(buffer[2])));

                clients.add(motoboy);
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar arquivo.");
            e.printStackTrace();
        }

        return clients;
    }
}
