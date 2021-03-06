package br.com.zup.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zup.casadocodigo.autor.Autor;
import br.com.zup.casadocodigo.categoria.Categoria;
import br.com.zup.casadocodigo.validacao.IdExiste;
import br.com.zup.casadocodigo.validacao.ValorUnico;

//5
public class LivroDTO {

	// 1
	// 1
	@NotBlank
	@ValorUnico(classeDominio = Livro.class, nomeCampo = "titulo")
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String resumo;

	@NotBlank
	private String sumario;

	@NotNull
	@Min(20)
	private BigDecimal preco;

	@Min(100)
	private int numeroPaginas;

	@NotBlank
	@ValorUnico(classeDominio = Livro.class, nomeCampo = "isbn")
	private String isbn;

	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;

	// 1
	// 1
	@NotNull
	@IdExiste(domainClass = Categoria.class, fieldName = "idCategoria", message = "Categoria inexistente")
	private Integer idCategoria;

	// 1
	@NotNull
	@IdExiste(domainClass = Autor.class, fieldName = "idAutor", message = "Autor Inexistente")
	private Integer idAutor;

	public LivroDTO(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
			@NotNull @Min(20) BigDecimal preco, @Min(100) int numeroPaginas, @NotBlank String isbn,
			@NotNull Integer idCategoria, @NotNull Integer idAutor) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.idCategoria = idCategoria;
		this.idAutor = idAutor;

	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Livro geraNovoLivro(EntityManager bancoDados) {
		Autor buscaAutor = bancoDados.find(Autor.class, idAutor);
		Categoria buscaCategoria = bancoDados.find(Categoria.class, idCategoria);

		Livro novoLivro = new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, buscaAutor,
				buscaCategoria);

		return novoLivro;

	}

}
