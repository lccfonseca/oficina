/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.engcomp.oficina.repository;

import br.uema.engcomp.oficina.model.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Italo Artley Silva Alcantara e Luan Thalisson Arantes de Oliveira
 */
@Repository
@Transactional
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {
    
}
