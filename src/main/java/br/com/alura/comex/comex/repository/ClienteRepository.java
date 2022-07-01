package br.com.alura.comex.comex.repository;

import br.com.alura.comex.comex.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  Cliente findByNome(String nomeDoCliente);
}
