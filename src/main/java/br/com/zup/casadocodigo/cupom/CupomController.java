package br.com.zup.casadocodigo.cupom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//3
@RestController
public class CupomController {

	@PersistenceContext
	private EntityManager bancoDados;

	@PostMapping(value = "/cupom", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	// 1
	// 1
	public ResponseEntity<Cupom> descontoCupom(@RequestBody @Valid CupomDTO dadosCupom) {

		Cupom novoCupom = dadosCupom.gerarNovoCupom();
		bancoDados.persist(novoCupom);

		return new ResponseEntity<Cupom>(novoCupom, HttpStatus.CREATED);
	}

	@PutMapping(value = "/cupom/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<Cupom> atualizarCupom(@RequestBody CupomDTO dadosCupomAtualizar,
			@PathVariable("id") Integer idCupom) {

		Cupom procurarId = bancoDados.find(Cupom.class, idCupom);
		// 1
		if (procurarId == null) {
			return ResponseEntity.notFound().build();
		}

		Cupom novoCupom = dadosCupomAtualizar.gerarCupomAtualizado();
		novoCupom.setId(idCupom);
		bancoDados.merge(novoCupom);

		return new ResponseEntity<Cupom>(novoCupom, HttpStatus.OK);

	}

}
