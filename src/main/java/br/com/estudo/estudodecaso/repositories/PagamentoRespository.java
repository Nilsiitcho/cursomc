package br.com.estudo.estudodecaso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estudo.estudodecaso.domain.Pagamento;

@Repository
public interface PagamentoRespository extends JpaRepository<Pagamento, Integer>{
	
}
