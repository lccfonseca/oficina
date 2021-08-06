/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.engcomp.oficina.controller;

import br.uema.engcomp.oficina.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lccf
 */
@Controller
@RequestMapping({"/clientes"})
public class ClienteController {
    
    @Autowired
    private ClienteRepository repository;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("clientes", repository.findAll());
        return "/clientes/index";
    }
    
}
