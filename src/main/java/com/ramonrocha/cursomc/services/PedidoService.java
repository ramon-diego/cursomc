package com.ramonrocha.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramonrocha.cursomc.domain.Pedido;
import com.ramonrocha.cursomc.repositories.PedidoRepository;
import com.ramonrocha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repository;

	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado! Id: " + id + " Tipo: " + Pedido.class.getName()));
	}
}
