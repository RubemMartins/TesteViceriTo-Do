package br.com.testeviceritodo.service;

import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import br.com.testeviceritodo.dto.TarefaSumaryModel;

public interface TarefaService {

	 Optional<TarefaSumaryModel> listar(TarefaSumaryModel tarefasumarymodel, String descricao, String dono);

	 TarefaSumaryModel cadastrar(TarefaSumaryModel form, String descricao, String dono);

	    Optional<TarefaSumaryModel> findById(Long id);

	    TarefaSumaryModel update(Long id, TarefaSumaryModel form) throws NotFoundException;

	    void deleteById(Long id);
}
