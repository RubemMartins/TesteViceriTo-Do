package br.com.testeviceritodo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testeviceritodo.dto.TarefaSumaryModel;
import br.com.testeviceritodo.entities.Tarefa;
import br.com.testeviceritodo.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins="*",allowedHeaders="*")
public class TarefaController {

	@Autowired
	private TarefaRepository tarefarepository;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<TarefaSumaryModel> listarTodos(){
		return tarefarepository.findAll()
				.stream()
				.map(this::toTarefaSumaryModel)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> GetById(@PathVariable long id){
		return tarefarepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	private TarefaSumaryModel toTarefaSumaryModel(Tarefa tarefa) {
		return modelMapper.map(tarefa, TarefaSumaryModel.class);
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tarefa>> GetByTarefa(@PathVariable String descricao){
		return ResponseEntity.ok(tarefarepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Tarefa> post (@RequestBody Tarefa tarefa){
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefarepository.save(tarefa));
	}
	
}
