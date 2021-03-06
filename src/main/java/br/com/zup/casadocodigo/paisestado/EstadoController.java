package br.com.zup.casadocodigo.paisestado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//2
@RestController
public class EstadoController {

	@PersistenceContext
	private EntityManager bancoDados;

	@PostMapping(value = "/estado")
	@Transactional
	public ResponseEntity<Estado> cadastrarEstado(@RequestBody @Valid EstadoDTO dadosEstado) {
		Estado novoEstado = dadosEstado.gerarNovoEstado(bancoDados);
		bancoDados.persist(novoEstado);
		return new ResponseEntity<Estado>(novoEstado, HttpStatus.CREATED);

	}
}
