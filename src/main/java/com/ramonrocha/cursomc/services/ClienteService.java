package com.ramonrocha.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramonrocha.cursomc.domain.Cliente;
import com.ramonrocha.cursomc.exception.ObjectNotFoundException;
import com.ramonrocha.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	public Cliente buscar(Integer id) {
		Optional<Cliente> Cliente = repository.findById(id);
		return Cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
	}
}