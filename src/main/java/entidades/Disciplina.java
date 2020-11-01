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
public class Disciplina {
    
    private Integer id, professor_id, aluno_id;
    private String disciplina;
    private Float av1, av2, av3, aps1, aps2;
    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Float getAv1() {
        return av1;
    }

    public void setAv1(Float av1) {
        this.av1 = av1;
    }

    public Float getAv2() {
        return av2;
    }

    public void setAv2(Float av2) {
        this.av2 = av2;
    }

    public Float getAv3() {
        return av3;
    }

    public void setAv3(Float av3) {
        this.av3 = av3;
    }

    public Float getAps1() {
        return aps1;
    }

    public void setAps1(Float aps1) {
        this.aps1 = aps1;
    }

    public Float getAps2() {
        return aps2;
    }

    public void setAps2(Float aps2) {
        this.aps2 = aps2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(Integer professor_id) {
        this.professor_id = professor_id;
    }

    public Integer getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Integer aluno_id) {
        this.aluno_id = aluno_id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Float getNascimento() {
        return nascimento;
    }

    public void setNascimento(Float nascimento) {
        this.nascimento = nascimento;
    }
    private Float nascimento;

    @Override
    public String toString() {
        return "ID: "+getId() +"\n"
                +"Disciplina: "+getDisciplina()+"\n"
                +"Aluno_id: "+getAluno_id()+"\n"
                +"Av1: "+getAv1()+"\n"
                +"Av2: "+getAv2()+"\n"
                +"Av3: "+getAv3()+"\n"
                +"Aps1: "+getAps1()+"\n"
                +"Aps2: "+getAps2()+"\n"
                +"Professor_id: "+getProfessor_id()+"\n";
    }
    
    /**
     * Retorna estilo toString() join das tabelas disciplina e aluno
     * @return string
     */
    public String joinDiscAluno() {
        return "ID disciplina: "+getId() +"\n"
                +"Disciplina: "+getDisciplina()+"\n"
                +"Aluno: "+aluno.getNome()+"  ||  "
                +"Av1: "+getAv1()+"  ||  "
                +"Av2: "+getAv2()+"  ||  "
                +"Av3: "+getAv3()+"  ||  "
                +"Aps1: "+getAps1()+"  ||  "
                +"Aps2: "+getAps2()+"  ||  "
                +"Professor_id: "+getProfessor_id()+"\n";
    }
    
}
