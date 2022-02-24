package br.com.testeviceritodo.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.testeviceritodo.dto.TarefaSumaryModel;
import br.com.testeviceritodo.entities.Tarefa;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Tarefa.class, TarefaSumaryModel.class)
			.<String>addMapping(src -> src.getUsuario().getNome(),
					(dest, value) -> dest.setDono(value));
		
		return modelMapper;
			
	}

}
