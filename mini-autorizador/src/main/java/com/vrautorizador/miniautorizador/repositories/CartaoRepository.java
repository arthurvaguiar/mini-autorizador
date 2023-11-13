package com.vrautorizador.miniautorizador.repositories;

import com.vrautorizador.miniautorizador.models.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    boolean existsByNumeroCartao(String numeroCartao);

}
