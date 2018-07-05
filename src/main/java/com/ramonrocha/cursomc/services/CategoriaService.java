package com.ramonrocha.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ramonrocha.cursomc.domain.Categoria;
import com.ramonrocha.cursomc.repositories.CategoriaRepository;
import com.ramonrocha.cursomc.services.exceptions.DataIntegrityException;
import com.ramonrocha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repository;

	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria objeto) {
		objeto.setId(null);
		return repository.save(objeto);
	}

	public Categoria update(Categoria objeto) {
		find(objeto.getId());
		return repository.save(objeto);
	}

	public void delete(Integer id) {
		find(id);
		
		try {	
			repository.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
		
	}
	
	
}
