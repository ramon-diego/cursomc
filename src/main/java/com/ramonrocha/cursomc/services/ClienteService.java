package com.ramonrocha.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ramonrocha.cursomc.domain.Cliente;
import com.ramonrocha.cursomc.dto.ClienteDTO;
import com.ramonrocha.cursomc.repositories.ClienteRepository;
import com.ramonrocha.cursomc.services.exceptions.DataIntegrityException;
import com.ramonrocha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente objeto) {
		Cliente newObj = find(objeto.getId());
		newObj.setNome(objeto.getNome());
		newObj.setEmail(objeto.getEmail());
		return repository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		
		try {	
			repository.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque existe entidades relacionadas");
		}
	}
		
	public List<Cliente> findAll() {
	
		return repository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		
		
		
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
}
