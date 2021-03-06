package com.ramonrocha.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ramonrocha.cursomc.domain.Categoria;
import com.ramonrocha.cursomc.domain.Produto;
import com.ramonrocha.cursomc.repositories.CategoriaRepository;
import com.ramonrocha.cursomc.repositories.ProdutoRepository;
import com.ramonrocha.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	

	@Autowired
	CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrado! Id: " + id + " Tipo: " + Produto.class.getName()));
	}
	
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest );
	}
}
