package com.vrautorizador.miniautorizador.repositories;

import com.vrautorizador.miniautorizador.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
