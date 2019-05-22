package br.com.estudo.estudodecaso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudo.estudodecaso.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
