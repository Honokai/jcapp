/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author eff
 */
public class Aluno {
    
    private Integer id, curso_id;
    private String nome, email, cpf;
    private Date nascimento;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Integer curso_id) {
        this.curso_id = curso_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getNascimento() {
        return nascimento;
    }
    
    public String getNascimentoString() {
        String data = new SimpleDateFormat("dd/MM/yyyy").format(this.nascimento);
        return data;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return "ID: "+getId()+"\n"
                +"Nome: "+getNome()+"\n"
                +"E-mail: "+getEmail()+"\n"
                +"Cpf: "+getCpf()+"\n"
                +"Data de nascimento: "+getNascimentoString()+"\n";
    }
    
    public String nomeID() {
        return "ID: "+getId()+"  ||  "+"Nome: "+getNome()+"\n";
    }
    
}
