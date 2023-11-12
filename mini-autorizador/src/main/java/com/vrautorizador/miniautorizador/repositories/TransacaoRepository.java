package com.vrautorizador.miniautorizador.repositories;

import com.vrautorizador.miniautorizador.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository <Transacao, Long> {

}
