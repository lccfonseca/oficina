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
@Table(name = "veiculos")
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @NotBlank(message = "O id do cliente é obrigatório!")
    private Long clientes_id;
    @NotBlank(message = "O ano é obrigatório!")
    private String ano;
    @NotBlank(message = "A marca é obrigatória!")
    private Long marcas_id;
    @NotBlank(message = "O modelo é obrigatório!")
    private String modelo;
    private String cor;
    @NotBlank(message = "A placa é obrigatória!")
    private String placa;
    private Long km;
    @NotBlank(message = "O id do tipo de veiculo é obrigatório!")
    private Long tipos_veiculos_id;

}
