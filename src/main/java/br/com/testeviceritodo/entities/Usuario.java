package br.com.testeviceritodo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Schema(example = "email@email.com.br")
	@Email(message = "O seu email deve ser fornecido em um formato válido!")
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "senha", nullable = false)
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "usuario")
	private List<Tarefa> tarefa;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Tarefa> getTarefa() {
		return tarefa;
	}

	public void setTarefa(List<Tarefa> tarefa) {
		this.tarefa = tarefa;
	}

}
