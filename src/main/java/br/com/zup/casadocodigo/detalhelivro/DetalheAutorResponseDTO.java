package br.com.zup.casadocodigo.detalhelivro;

import java.time.format.DateTimeFormatter;

import br.com.zup.casadocodigo.autor.Autor;

//2
public class DetalheAutorResponseDTO {

	private Integer idAutor;

	private String nome;

	private String email;

	private String descricao;

	private String dataRegistro;

	// 1
	public DetalheAutorResponseDTO(Autor dadosAutor) {
		this.idAutor = dadosAutor.getIdAutor();
		this.nome = dadosAutor.getNome();
		this.email = dadosAutor.getEmail();
		this.descricao = dadosAutor.getEmail();
		// 1
		this.dataRegistro = dadosAutor.getDataRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	}

	public Integer getIdAutor() {
		return idAutor;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDataRegistro() {
		return dataRegistro;
	}

}
