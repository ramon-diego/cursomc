package com.ramonrocha.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ramonrocha.cursomc.domain.Cliente;
import com.ramonrocha.cursomc.dto.ClienteDTO;
import com.ramonrocha.cursomc.repositories.ClienteRepository;
import com.ramonrocha.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate ann) {
		
	}
	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
				
		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
		
		if (cliente !=null && !cliente.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "E-mail já cadastrado"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
