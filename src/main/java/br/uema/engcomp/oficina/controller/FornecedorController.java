/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.engcomp.oficina.controller;

import br.uema.engcomp.oficina.model.Fornecedor;
import br.uema.engcomp.oficina.repository.FornecedorRepository;
import br.uema.engcomp.oficina.repository.CidadeRepository;
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
 * @author Joilson
 */

@Controller
@RequestMapping({"/fornecedores"})
public class FornecedorController {
    
    @Autowired
    private FornecedorRepository repository;
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("fornecedores", repository.findAll());
        return "/fornecedores/index";
    }
    
    @GetMapping("/add")
    public String add(Fornecedor fornecedor, Model model) {
        fornecedor = new Fornecedor();
        model.addAttribute("fornecedor", fornecedor);
        model.addAttribute("estado", "");
        model.addAttribute("ufs", cidadeRepository.findDistinctUf());
        return "/fornecedores/add";
    }
    
    @PostMapping("/create")
    public String create(@Valid Fornecedor fornecedor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/fornecedores/add";
        }

        repository.save(fornecedor);
        return "redirect:/fornecedores/";
    }
    
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Fornecedor fornecedor = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("fornecedor", fornecedor);
        model.addAttribute("estado", cidadeRepository.findById(fornecedor.getCidades_id()).get().getUf());
        model.addAttribute("ufs", cidadeRepository.findDistinctUf());
        return "/fornecedores/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Fornecedor fornecedor,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            fornecedor.setId(id);
            return "/fornecedores/update";
        }

        repository.save(fornecedor);
        return "redirect:/fornecedores/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Fornecedor fornecedor = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        repository.delete(fornecedor);
        return "redirect:/fornecedores/";
    }
}
