/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.engcomp.oficina.controller;

import br.uema.engcomp.oficina.model.Funcionario;
import br.uema.engcomp.oficina.repository.CidadeRepository;
import br.uema.engcomp.oficina.repository.FuncionarioRepository;
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
 * @author Italo Artley Silva Alcantara e Luan Thalisson Arantes de Oliveira
 */
@Controller
@RequestMapping({"/funcionarios"})
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("funcionarios", repository.findAll());
        return "/funcionarios/index";
    }

    @GetMapping("/add")
    public String add(Funcionario funcionario, Model model) {
        funcionario = new Funcionario();
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("estado", "");
        model.addAttribute("ufs", cidadeRepository.findDistinctUf());
        return "/funcionarios/add";
    }

    @PostMapping("/create")
    public String create(@Valid Funcionario funcionario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/funcionarios/add";
        }

        repository.save(funcionario);
        return "redirect:/funcionarios/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("cliente", funcionario);
        model.addAttribute("estado", cidadeRepository.findById(funcionario.getCidades_id()).get().getUf());
        model.addAttribute("ufs", cidadeRepository.findDistinctUf());
        return "/funcionarios/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Funcionario funcionario,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            funcionario.setId(id);
            return "/funcionarios/update";
        }

        repository.save(funcionario);
        return "redirect:/funcionarios/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        repository.delete(funcionario);
        return "redirect:/funcionarios/";
    }

}
