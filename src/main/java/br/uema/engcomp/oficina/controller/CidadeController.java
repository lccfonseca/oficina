/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.engcomp.oficina.controller;

import br.uema.engcomp.oficina.repository.CidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lccf
 */
@RestController
@RequestMapping({"/cidades"})
public class CidadeController {
    
    @Autowired
    private CidadeRepository repository;
   
    @GetMapping(path = {"/{uf}"})
    public List findById(@PathVariable String uf) {
        return repository.findByUf(uf);
    }

}
