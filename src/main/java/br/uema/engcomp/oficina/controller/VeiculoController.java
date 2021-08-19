/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.engcomp.oficina.controller;

import br.uema.engcomp.oficina.model.Veiculo;
import br.uema.engcomp.oficina.repository.VeiculoRepository;
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
@RequestMapping({"/veiculos"})
public class VeiculoController {
    
    @Autowired
    private VeiculoRepository repository;

    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("veiculos", repository.findAll());
        return "/veiculos/index";
    }
    
    @GetMapping("/add")
    public String add(Veiculo veiculo, Model model) {
        veiculo = new Veiculo();
        model.addAttribute("veiculo", veiculo);
        return "/veiculos/add";
    }
    
    @PostMapping("/create")
    public String create(@Valid Veiculo veiculo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/veiculos/add";
        }

        repository.save(veiculo);
        return "redirect:/veiculos/";
    }
    
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("veiculo", veiculo);
        return "/veiculos/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Veiculo veiculo,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            veiculo.setId(id);
            return "/veiculos/update";
        }

        repository.save(veiculo);
        return "redirect:/veiculos/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        repository.delete(veiculo);
        return "redirect:/veiculos/";
    }
}
