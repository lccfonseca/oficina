/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.engcomp.oficina.controller;

import br.uema.engcomp.oficina.model.Cliente;
import br.uema.engcomp.oficina.repository.CidadeRepository;
import br.uema.engcomp.oficina.repository.ClienteRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("clientes", repository.findAll());
        return "/clientes/index";
    }
    
    @GetMapping("/add")
    public String add(Cliente cliente, Model model) {
        cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        model.addAttribute("estado", "");
        model.addAttribute("ufs", cidadeRepository.findDistinctUf());
        return "/clientes/add";
    }
    
    @PostMapping("/create")
    public String create(@Valid Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/clientes/add";
        }

        repository.save(cliente);
        return "redirect:/clientes/";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("cliente", cliente);
        model.addAttribute("estado", cidadeRepository.findById(cliente.getCidades_id()).get().getUf());
        model.addAttribute("ufs", cidadeRepository.findDistinctUf());
        return "/clientes/update";
    }
    
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Cliente cliente,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            cliente.setId(id);
            return "/clientes/update";
        }

        repository.save(cliente);
        return "redirect:/clientes/";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        repository.delete(cliente);
        return "redirect:/users/";
    }
    
}
