/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.consoleapp;

import daos.*;
import entidades.*;
import estrutura.CriadorEstrutura;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Scanner;
/**
 *
 * @author eff
 */
public class Principal {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        System.out.println("Bem-vindo");
        String option = "";
        Scanner entrada = new Scanner(System.in);
        while(option.equals("0") == false){
            System.out.println("-----------------------------");
            System.out.println("| 1 - Criar estrutura        |");
            System.out.println("| 2 - Adicionar um registro  |");
            System.out.println("| 3 - Atualizar um registro  |");
            System.out.println("| 4 - Excluir um registro    |");
            System.out.println("| 5 - Listar registro        |");
            System.out.println("| 0 - Sair                   |");
            System.out.println("-----------------------------");
            option = entrada.nextLine();
            switch(option) {
                case "1":
                    new Principal().subCriarEstrutura();
                    break;
                case "2":
                    new Principal().subAdicionar();
                    break;
                case "3":
                    new Principal().subAtualizar();
                    break;
                case "4":
                    new Principal().subExcluir();
                    break;
                case "5":
                    new Principal().subListar();
                    break;
                 case "0":
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Você não informou uma opção válida");
                    break;
            }
        }
    }
    
    public void subCriarEstrutura() throws  ClassNotFoundException, SQLException {
        
        String option = "";
        Scanner entrada = new Scanner(System.in);
        while(option.equals("0") == false){
            System.out.println("---------------------------Menu de criação--------------------------------");
            System.out.println("1 - Criar tabela Curso");
            System.out.println("2 - Criar tabela Aluno");
            System.out.println("3 - Criar tabela Professor");
            System.out.println("4 - Criar tabela Disciplina");
            System.out.println("0 - Voltar");
            option = entrada.nextLine();
            switch(option) {
                case "1":
                     System.out.println(new CriadorEstrutura().criarTabelaCurso());
                    break;
                case "2":
                   System.out.println(new CriadorEstrutura().criarTabelaAluno());
                    break;
                case "3":
                    System.out.println(new CriadorEstrutura().criarTabelaProfessor());
                    break;
                case "4":
                    System.out.println(new CriadorEstrutura().criarTabelaDisciplina());
                    break;
                 case "0":
                    System.out.println("Voltando para o menu anterior.");
                    break;
                default:
                    System.out.println("Você não informou uma opção válida");
                    break;
            }
        }
    }
    
    public void subAdicionar() throws ClassNotFoundException, SQLException, ParseException {
        String option = "";
        Scanner entrada = new Scanner(System.in);
        while(option.equals("0") == false){
            System.out.println("---------------------------Menu de Adição--------------------------------");
            System.out.println("1 - Adicionar Curso");
             System.out.println("2 - Adicionar Aluno");
            System.out.println("3 - Adicionar Professor");
            System.out.println("4 - Adicionar Disciplina");
            System.out.println("0 - Voltar");
            option = entrada.nextLine();
            switch(option) {
                case "1":
                     Curso curso1 = new Curso();
                     String r1 = "";
                     System.out.println("Informe o nome do Curso: ");
                     r1 = entrada.nextLine();
                     curso1.setCurso(r1);
                     CursoDAO operacao1 = new CursoDAO();
                     Integer resultado1 = operacao1.inserir(curso1);
                      System.out.println(resultado1 ==1?"Registro inserido.":"Falha ao tentar inserir.");
                    break;
                case "2":
                    Aluno aluno = new Aluno();
                    String r = "";
                    System.out.println("Informe o nome do aluno: ");
                    r =entrada.nextLine();
                    aluno.setNome(r);
                    System.out.println("CPF do aluno: ");
                    r =entrada.nextLine();
                    aluno.setCpf(r);
                    System.out.println("ID do curso pretendido: ");
                    System.out.println("Cursos disponíveis abaixo ");
                    ArrayList<Curso> lista = new CursoDAO().listar();
                    Iterator indice = lista.iterator();
                    Integer i = 0; 
                    while(indice.hasNext()){
                        System.out.println((Curso) lista.get(i++));
                        indice.next();
                    }
                    r =entrada.nextLine();
                    aluno.setCurso_id(Integer.parseInt(r));
                    System.out.println("E-mail: ");
                     r =entrada.nextLine();
                    aluno.setEmail(r);
                    System.out.println("Data de nascimento(15/12/2000): ");
                     r =entrada.nextLine();
                    aluno.setNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(r));

                    AlunoDAO operacao = new AlunoDAO();

                    Integer resultado = operacao.inserir(aluno);

                    System.out.println(resultado==1?"Registro inserido.":"Falha ao tentar inserir.");
                    break;
                case "3":
                    Professor professor = new Professor();
                    String r3 = "";
                    System.out.println("Informe o nome do professor: ");
                    r3 =entrada.nextLine();
                    professor.setProfessor(r3);
                    System.out.println("E-mail: ");
                    r3 =entrada.nextLine();
                    professor.setEmail(r3);
                    ProfessorDAO operacao3 = new ProfessorDAO();
                    Integer result3 = operacao3.inserir(professor);
                    System.out.println(result3==1?"Registro inserido.":"Falha ao tentar inserir.\n");
                    break;
                case "4":
                    Disciplina disciplina = new Disciplina();
                    String al = "";
                    System.out.println("Informe o nome da Disciplina abaixo: ");
                    al = entrada.nextLine();
                    disciplina.setDisciplina(al);
                    System.out.println("ID do ALUNO: ");
                    listarIdENomeAluno();
                    al = entrada.nextLine();
                    disciplina.setAluno_id(Integer.parseInt(al));
                    System.out.println("ID do PROFESSOR: ");
                    ArrayList<Professor> lista3 = new ProfessorDAO().listar();
                    Iterator indice3 = lista3.iterator();
                    Integer i3 = 0; 
                     System.out.println("------------------------------------------\n");
                    while(indice3.hasNext()){
                        System.out.println((Professor) lista3.get(i3++));
                        indice3.next();
                    }
                    System.out.println("------------------------------------------\n");
                    al = entrada.nextLine();
                    disciplina.setProfessor_id(Integer.parseInt(al));
                    System.out.println("\n\n---------------Atenção--------------");
                    System.out.println("Se não houver nota, será atribuído o valor zero ao campo de avaliação");
                    System.out.println("---------------Atenção--------------\n\n");
                    // set dos attr de disciplina
                    System.out.println("Nota da av1: ");
                    al = entrada.nextLine();
                    disciplina.setAv1(al.equals("")?0:Float.parseFloat(al));
                    System.out.println("Nota da av2: ");
                    al = entrada.nextLine();
                    disciplina.setAv2(al.equals("")?0:Float.parseFloat(al));
                    System.out.println("Nota da av3: ");
                    al = entrada.nextLine();
                    disciplina.setAv3(al.equals("")?0:Float.parseFloat(al));
                    System.out.println("Nota da aps1: ");
                    al = entrada.nextLine();
                    disciplina.setAps1(al.equals("")?0:Float.parseFloat(al));
                    System.out.println("Nota da aps2: ");
                    al = entrada.nextLine();
                    disciplina.setAps2(al.equals("")?0:Float.parseFloat(al));
                    // FIM set dos attr de disciplina
                    
                    DisciplinaDAO oper = new DisciplinaDAO();

                    Integer result = oper.inserir(disciplina);

                    System.out.println(result==1?"\nRegistro inserido.\n":"\nFalha ao tentar inserir.\n");
                    break;
                 case "0":
                    System.out.println("Voltando para o menu anterior.");
                    break;
                default:
                    System.out.println("Você não informou uma opção válida");
                    break;
            }
        }
    }
    
     public void subExcluir() throws ClassNotFoundException, SQLException {
        String option = "";
        Scanner entrada = new Scanner(System.in);
        while(option.equals("0") == false){
            System.out.println("---------------------------Menu de Exclusão--------------------------------");
            System.out.println("1 - Excluir Curso");
             System.out.println("2 - Excluir Aluno");
            System.out.println("3 - Excluir Professor");
            System.out.println("4 - Excluir Disciplina");
            System.out.println("0 - Voltar");
            option = entrada.nextLine();
            switch(option) {
                case "1":
                     Curso curso = new Curso();
                     String a = "";
                     System.out.println("Informe o nome do curso a ser apagado: ");
                     a = entrada.nextLine();
                     curso.setCurso(a);
                     CursoDAO op = new CursoDAO();
                     Integer res = op.apagarPeloNome(curso);
                     System.out.println(res==1?"Registro excluído.":res==-1?"Registro não é possível apagar devido a restrições, veja a mensagem anterior":"Registro não encontrado");
                    break;
                case "2":
                    Aluno aluno = new Aluno();
                    String r = "";
                    System.out.println("Informe o nome do aluno: ");
                    r =entrada.nextLine();
                    aluno.setNome(r);
                    AlunoDAO operacao = new AlunoDAO();
                    Integer resultado = operacao.apagarPeloNome(aluno);
                    System.out.println(resultado==1?"Registro excluído.":"Registro não encontrado.");
                    break;
                case "3":
                    Professor professor = new Professor();
                    String r3 = "";
                    System.out.println("Informe o nome do professor: ");
                    r3 =entrada.nextLine();
                    professor.setProfessor(r3);
                    ProfessorDAO operacao3 = new ProfessorDAO();
                    Integer resultado3 = operacao3.apagarPeloNome(professor);
                    System.out.println(resultado3==1?"Registro excluído.":"Registro não encontrado.");
                    break;
                case "4":
                    Disciplina disciplina = new Disciplina();
                    String in = "";
                    System.out.println("Informe o id do Aluno: ");
                    listarIdENomeAluno();
                    in = entrada.nextLine();
                    disciplina.setAluno_id(Integer.parseInt(in));
                    ArrayList<Disciplina> lista = new DisciplinaDAO().joinDisciplinaAluno(disciplina.getAluno_id());
                    Iterator indice = lista.iterator(); 
                    if(indice.hasNext()){
                        System.out.println("\n------Aluno tem registros nas seguintes disciplinas-----");
                        System.out.println("Informe a disciplina: ");
                        in = entrada.nextLine();
                        disciplina.setDisciplina(in);
                        System.out.println("Informe o id do Professor: ");
                        in = entrada.nextLine();
                        disciplina.setProfessor_id(Integer.parseInt(in));
                        DisciplinaDAO opera = new DisciplinaDAO();
                        Integer re = opera.apagarPeloNome(disciplina);
                        System.out.println(re==1?"Registro excluído.":"Registro não encontrado");
                    }
                    break;
                 case "0":
                    System.out.println("Voltando para o menu anterior.");
                    break;
                default:
                    System.out.println("Você não informou uma opção válida");
                    break;
            }
        }
    }
     
    public void subAtualizar() throws ClassNotFoundException, SQLException {
        System.out.println("---------------------------Menu de Atualização--------------------------------");
        String option = "";
        Scanner entrada = new Scanner(System.in);
        while(option.equals("0") == false){
            System.out.println("1 - Atualizar Curso");
             System.out.println("2 - Atualizar Aluno");
            System.out.println("3 - Atualizar Professor");
            System.out.println("4 - Atualizar Disciplina");
            System.out.println("0 - Voltar");
            option = entrada.nextLine();
            switch(option) {
                case "1":
                    try{
                        Curso curso = new Curso();
                        String a = "";
                        ArrayList<Curso> lista = new CursoDAO().listar();
                        Iterator indice = lista.iterator();
                        Integer i = 0;
                        System.out.println("------------------------------------------\n");
                        while(indice.hasNext()){
                            System.out.println((Curso) lista.get(i++));
                            indice.next();
                        }
                        System.out.println("------------------------------------------\n");
                        System.out.println("Informe o ID do curso: ");
                        a =entrada.nextLine();
                        curso.setId(Integer.parseInt(a));
                        System.out.println("Informe o nome para o qual será trocado: ");
                        a =entrada.nextLine();
                        curso.setCurso(a);
                        CursoDAO operacao = new CursoDAO();
                        Integer resultado1 = operacao.atualizarInfo(curso);
                        System.out.println(resultado1==1?"Registro atualizado.":"Registro não encontrado.");
                    } catch(NumberFormatException e) {
                        System.out.print("Ocorreu o seguinte erro: " + e.getMessage());
                    }
                    break;
                case "2":
                    Aluno aluno = new Aluno();
                    String r = "";
                    System.out.println("Informe o cpf do aluno: ");
                    r =entrada.nextLine();
                    aluno.setCpf(r);
                    System.out.println("E-mail: ");
                     r =entrada.nextLine();
                    aluno.setEmail(r);
                    System.out.println("Curso ID(opcional, deixe em branco caso não queira alterar): ");
                     r =entrada.nextLine();
                    aluno.setCurso_id(r.equals("")?0:Integer.parseInt(r));
                    AlunoDAO operacao1 = new AlunoDAO();
                    Integer resultado = operacao1.atualizarInfo(aluno);
                    System.out.println(resultado==1?"Registro atualizado.":resultado==-1?"Registro não foi "
                            + "possível atualizar devido a restrições, veja a mensagem anterior.":
                            "Registro não encontrado.");
                    break;
                case "3":
                    Professor professor = new Professor();
                    String r3 = "";
                    ArrayList<Professor> lista3 = new ProfessorDAO().listar();
                    Iterator indice3 = lista3.iterator();
                    Integer i3 = 0; 
                     System.out.println("------------------------------------------\n");
                    while(indice3.hasNext()){
                        System.out.println((Professor) lista3.get(i3++));
                        indice3.next();
                    }
                    System.out.println("------------------------------------------\n");
                    System.out.println("Informe o nome do professor que deseja alterar o E-mail: ");
                    r3 =entrada.nextLine();
                    professor.setProfessor(r3);
                    System.out.println("Informe o ID do professor que deseja alterar o E-mail: ");
                    r3 =entrada.nextLine();
                    professor.setId(r3.matches("[0-9]+")?Integer.parseInt(r3):0);
                    System.out.println("E-mail: ");
                    r3 =entrada.nextLine();
                    professor.setEmail(r3);
                    ProfessorDAO operacao3 = new ProfessorDAO();
                    Integer resultado3 = operacao3.atualizarInfo(professor);
                    System.out.println(resultado3==1?"Registro atualizado.":"Registro não encontrado.");
                    break;
                case "4":
                    Disciplina disciplina = new Disciplina();
                    String r4 = "";
                    System.out.println("Informe o id do Aluno que deseja atualizar informações da disciplina:");
                    this.listarIdENomeAluno();
                    r4 = entrada.nextLine();
                    disciplina.setAluno_id(r4.equals("")?0:Integer.parseInt(r4));
                    this.listarJoinAlunoDisciplina(disciplina.getAluno_id());
                    System.out.println("Informe o ID da Disciplina da lista acima:");
                    r4 = entrada.nextLine();
                    disciplina.setId(r4.equals("")?0:Integer.parseInt(r4));
                    System.out.println("\n\n---------------Atenção--------------");
                    System.out.println("Se não houver nota, será atribuído o valor zero ao campo de avaliação");
                    System.out.println("---------------Atenção--------------\n\n");
                    // set dos attr de disciplina
                    System.out.println("Nota da av1: ");
                    r4 = entrada.nextLine();
                    disciplina.setAv1(r4.equals("")?0:Float.parseFloat(r4));
                    System.out.println("Nota da av2: ");
                    r4 = entrada.nextLine();
                    disciplina.setAv2(r4.equals("")?0:Float.parseFloat(r4));
                    System.out.println("Nota da av3: ");
                    r4 = entrada.nextLine();
                    disciplina.setAv3(r4.equals("")?0:Float.parseFloat(r4));
                    System.out.println("Nota da aps1: ");
                    r4 = entrada.nextLine();
                    disciplina.setAps1(r4.equals("")?0:Float.parseFloat(r4));
                    System.out.println("Nota da aps2: ");
                    r4 = entrada.nextLine();
                    disciplina.setAps2(r4.equals("")?0:Float.parseFloat(r4));
                    DisciplinaDAO oper4 = new DisciplinaDAO();
                    Integer res4 = oper4.atualizarInfo(disciplina);
                    System.out.println(res4==1?"Registro atualizado.":"Registro não encontrado.");
                    break;
                 case "0":
                    System.out.println("Voltando para o menu anterior.");
                    break;
                default:
                    System.out.println("Você não informou uma opção válida");
                    break;
            }
        }
    }
    
    public void subListar() throws ClassNotFoundException, SQLException {
        System.out.println("---------------------------Menu de Listagem--------------------------------");
        String option = "";
        Scanner entrada = new Scanner(System.in);
        while(option.equals("0") == false){
            System.out.println("1 - Listar Cursos");
             System.out.println("2 - Listar Alunos");
            System.out.println("3 - Listar Professores");
            System.out.println("4 - Listar Disciplinas");
            System.out.println("0 - Voltar");
            option = entrada.nextLine();
            switch(option) {
                case "1":
                    ArrayList<Curso> lista = new CursoDAO().listar();
                    Iterator indice = lista.iterator();
                    Integer i = 0;
                    System.out.println("------------------------------------------\n");
                    while(indice.hasNext()){
                        System.out.println((Curso) lista.get(i++));
                        indice.next();
                    }
                    System.out.println("------------------------------------------\n");
                    break;
                case "2":
                   ArrayList<Aluno> lista1 = new AlunoDAO().listar();
                    Iterator indice1 = lista1.iterator();
                    Integer i1 = 0; 
                    System.out.println("------------------------------------------\n");
                    while(indice1.hasNext()){
                        System.out.println((Aluno) lista1.get(i1++));
                        indice1.next();
                    }
                    System.out.println("------------------------------------------\n");
                    break;
                case "3":
                    ArrayList<Professor> lista3 = new ProfessorDAO().listar();
                    Iterator indice3 = lista3.iterator();
                    Integer i3 = 0; 
                     System.out.println("------------------------------------------\n");
                    while(indice3.hasNext()){
                        System.out.println((Professor) lista3.get(i3++));
                        indice3.next();
                    }
                    System.out.println("------------------------------------------\n");
                    break;
                case "4":
                    ArrayList<Disciplina> lista2 = new DisciplinaDAO().listar();
                    Iterator indice2 = lista2.iterator();
                    Integer i2 = 0; 
                    System.out.println("------------------------------------------\n");
                    while(indice2.hasNext()){
                        System.out.println((Disciplina) lista2.get(i2++));
                        indice2.next();
                    }
                    System.out.println("------------------------------------------\n");
                    break;
                 case "0":
                    System.out.println("Voltando para o menu anterior.");
                    break;
                default:
                    System.out.println("Você não informou uma opção válida");
                    break;
            }
        }
    }
    
    public void listarIdENomeAluno() throws ClassNotFoundException, SQLException {
        ArrayList<Aluno> lista1 = new AlunoDAO().listar();
        Iterator indice1 = lista1.iterator();
        Integer i1 = 0; 
         System.out.println("------------------------------------------\n");
        while(indice1.hasNext()){
            System.out.println(lista1.get(i1++).nomeID());
            indice1.next();
        }
        System.out.println("------------------------------------------\n");
    }
    
    public void listarJoinAlunoDisciplina(Integer id) throws ClassNotFoundException, SQLException {
        ArrayList<Disciplina> lista = new DisciplinaDAO().joinDisciplinaAluno(id);
        Iterator indice = lista.iterator();
        Integer i = 0; 
        System.out.println("------------------------------------------\n");
        while(indice.hasNext()){
            System.out.println(lista.get(i++).joinDiscAluno());
            indice.next();
        }
        System.out.println("------------------------------------------\n");
    }
   
}
