package mapfood.service.client;

import mapfood.dto.client.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto update(int id, ClientDto clientDto);

    ClientDto findById(int id);

    List<ClientDto> findAll();

    Boolean deleteById(int id);
}
