package br.com.alura.comex.comex.repository;

import br.com.alura.comex.comex.models.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
