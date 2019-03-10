package mapfood.converter.client;

import mapfood.dto.client.ClientDto;
import mapfood.model.client.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientEntityToDto {

    private final Client client;

    public ClientEntityToDto(final Client client) {
        this.client = client;
    }

    public ClientDto build() {
        List<Double> location = new ArrayList<>();
        location.add(client.getLocation().getX());
        location.add(client.getLocation().getY());

        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setLocation(location);

        return clientDto;
    }
}
