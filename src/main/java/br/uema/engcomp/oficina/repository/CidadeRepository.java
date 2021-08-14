/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.engcomp.oficina.repository;

import br.uema.engcomp.oficina.model.Cidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lccf
 */
@Repository
@Transactional
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    
    @Query("SELECT DISTINCT c.uf FROM Cidade c ORDER BY c.uf")
    List<String> findDistinctUf();
    
    List<Cidade> findByUf(String uf);
    
}
