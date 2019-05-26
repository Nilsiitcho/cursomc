package br.com.estudo.estudodecaso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudo.estudodecaso.domain.Cliente;
import br.com.estudo.estudodecaso.repositories.ClienteRepository;
import br.com.estudo.estudodecaso.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {
		Cliente cliente = repository.findOne(id);
		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. Id: " + id + ", tipo: " + Cliente.class.getName());
		}

		return cliente;

	}

}
