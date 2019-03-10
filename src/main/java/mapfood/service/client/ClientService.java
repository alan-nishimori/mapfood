package mapfood.service.client;

import mapfood.dto.client.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(final ClientDto clientDto);

    ClientDto update(final int id, final ClientDto clientDto);

    ClientDto findById(final int id);

    List<ClientDto> findAll();

    Boolean deleteById(final int id);
}
