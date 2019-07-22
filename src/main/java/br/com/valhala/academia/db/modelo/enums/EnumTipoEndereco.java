package br.com.valhala.academia.db.modelo.enums;

public enum EnumTipoEndereco {

	RESIDENCIAL_COMERCIAL("Residencial ou Comercial"), RESIDENCIAL("Residencial"), COMERCIAL("Comercial"),
	ENDERECO_OFICIAL("Endereço Oficial"), NAO_ESPECIFICADO("Não especificado");

	private String descricao;

	private EnumTipoEndereco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
