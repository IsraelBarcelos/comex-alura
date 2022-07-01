package br.com.alura.comex.comex.builders;

import br.com.alura.comex.comex.models.Categoria;

public class CategoriaBuilder {

    private Categoria categoria;

    public CategoriaBuilder() {
        categoria = new Categoria();
    }

    public CategoriaBuilder comNome(String nome) {
        categoria.setNome(nome);
        return this;
    }

    public CategoriaBuilder ativo(boolean ativo) {
        categoria.setAtivo(ativo);
        return this;
    }

    public CategoriaBuilder ativaCategoria() {
        categoria.ativaCategoria();
        return this;
    }

    public Categoria build() {
        return categoria;
    }
}
