package mapfood.dbstarter;

import mapfood.model.Client;
import mapfood.model.Localization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientStarter {

    public static List<Client> clientStart() {

        String csvFile = "clientes.csv";
        String line;
        String csvSplitter = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        List<Client> clients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] buffer = line.split(csvSplitter);

                Client client = new Client();
                client.setId(Integer.valueOf(buffer[0]));
                client.setLocalization(new Localization(Double.valueOf(buffer[1]), Double.valueOf(buffer[2])));

                clients.add(client);
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar arquivo.");
            e.printStackTrace();
        }

        return clients;
    }
}
