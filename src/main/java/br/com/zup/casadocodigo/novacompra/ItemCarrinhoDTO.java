package br.com.zup.casadocodigo.novacompra;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.casadocodigo.livro.Livro;
import br.com.zup.casadocodigo.validacao.IdExiste;

//3
public class ItemCarrinhoDTO {

	// 1
	// 1
	@NotNull
	@IdExiste(domainClass = Livro.class, fieldName = "idLivro")
	private Integer idLivro;

	@Positive
	private int quantidade;

	public ItemCarrinhoDTO(@NotNull Integer idLivro, @Positive int quantidade) {
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	// 1
	public ItemCarrinho gerarNovoItemCarrinho(EntityManager bancoDados) {
		Livro livroEncontrado = bancoDados.find(Livro.class, idLivro);
		ItemCarrinho novoItemCarrinho = new ItemCarrinho(livroEncontrado, quantidade);
		return novoItemCarrinho;
	}

}
