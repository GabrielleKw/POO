package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String nome;
    @Column
    private String telefone;
    @Column
    private String cpf;
    @Column
    private String cnpj;
    @Column
    private StatusEnum status;
    @Column
    private LocalDateTime dataCadastro;
    @ManyToOne
    @JoinColumn(name = "idEndereco")
    private Endereco endereco;

}
