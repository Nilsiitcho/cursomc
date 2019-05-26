package br.com.estudo.estudodecaso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudo.estudodecaso.domain.Pedido;
import br.com.estudo.estudodecaso.repositories.PedidoRepository;
import br.com.estudo.estudodecaso.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) {
		Pedido pedido = pedidoRepository.findOne(id);
		if(pedido == null) {
			throw new ObjectNotFoundException("Pedido não encontrado. Id: " + id);
		}
		return pedido;
	}
	
}
