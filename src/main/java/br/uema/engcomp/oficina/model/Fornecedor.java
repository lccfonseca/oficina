/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.engcomp.oficina.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author lccf
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "fornecedores")
public class Fornecedor implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @NotBlank(message = "O nome é obrigatório!")
    private String nome;
    @NotBlank(message = "O endereço é obrigatório!")
    private String endereco;
    private Long cidades_id;
    private String telefone;
    @NotBlank(message = "O Fax é obrigatório!")
    private String fax;
    private String email;
    @NotBlank(message = "O CNPJ é obrigatório!")
    private String cnpj;
    private String nome_contato;
    
}
