package mapfood.service.client.impl;

import mapfood.converter.client.ClientEntityToDto;
import mapfood.dto.client.ClientDto;
import mapfood.model.client.Client;
import mapfood.repository.client.ClientRepository;
import mapfood.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientDto save(final ClientDto clientDto) {
        final Client client = new Client();
        client.setId(clientDto.getId());
        client.setLocation(new GeoJsonPoint(clientDto.getLocation().get(0), clientDto.getLocation().get(1)));
        return new ClientEntityToDto(clientRepository.save(client)).build();
    }

    @Override
    public ClientDto update(final int id, final ClientDto clientDto) {
        final Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            client.get().setLocation(new GeoJsonPoint(clientDto.getLocation().get(0), clientDto.getLocation().get(1)));
            return new ClientEntityToDto(clientRepository.save(client.get())).build();
        }

        return null;
    }

    @Override
    public ClientDto findById(final int id) {
        final Optional<Client> client = clientRepository.findById(id);

        return client.map(client1 -> new ClientEntityToDto(client1).build()).orElse(null);
    }

    // TODO adicionar paginação
    @Override
    public List<ClientDto> findAll() {
        final List<Client> clients = clientRepository.findAll();
        final List<ClientDto> clientDtos = new ArrayList<>();

        clients.forEach(client -> clientDtos.add(new ClientEntityToDto(client).build()));

        return clientDtos;
    }


    @Override
    public Boolean deleteById(final int id) {
        final Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            clientRepository.deleteById(id);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
