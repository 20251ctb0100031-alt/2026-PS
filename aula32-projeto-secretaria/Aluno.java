/*
Diciplina: 2026-PS
Estudante: Fernando
Data: 13/08/26
Projeto: aula32-projeto-secretaria
Arquivo: Aluno.java
 */
public class Aluno {
    private String nome;
    private String matricula;
    private String curso;

    public Aluno(String nome, String matricula, String curso){
        this.nome=nome;
        this.matricula=matricula;
        this.curso=curso;
    }
    public String getNome(){
        return nome;
    }

    public String getMatricula(){
        return matricula;
    }

        public String getCurso(){
        return curso;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public void setCurso(String curso){
        this.curso = curso;
        
    }
}


