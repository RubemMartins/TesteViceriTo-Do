package br.com.testeviceritodo.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.testeviceritodo.dto.UsuarioDto;
import br.com.testeviceritodo.entities.Usuario;
import br.com.testeviceritodo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			return Optional.empty();
		
		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(usuarioRepository.save(usuario));
	
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		
		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			
			return Optional.of(usuarioRepository.save(usuario));
			
		}
			
		return Optional.empty();

	}	

	public Optional<UsuarioDto> autenticarUsuario(Optional<UsuarioDto> usuarioDto) {

		Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioDto.get().getEmail());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioDto.get().getSenha(), usuario.get().getSenha())) {

				usuarioDto.get().setId(usuario.get().getId());
				usuarioDto.get().setNome(usuario.get().getNome());
				usuarioDto.get().setToken(gerarBasicToken(usuarioDto.get().getEmail(), usuarioDto.get().getSenha()));
				usuarioDto.get().setSenha(usuario.get().getSenha());
				return usuarioDto;

			}
		}	
		
		return Optional.empty();
		
	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);

	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);

	}

	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}

