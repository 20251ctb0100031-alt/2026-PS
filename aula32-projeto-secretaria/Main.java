/*
Disciplina: 2026-PS
Estudante: Fernando
Data: 11/08/26
Projeto: aula32-projeto-secretaria
Arquivo: Main.java
*/

public class Main {
public static void main(String[] args) {

Aluno a1 = new Aluno("Ana Souza", "2026001", "Informatica");
Aluno a2 = new Aluno("Bia Lima", "2026002", "Mecanica");

System.out.println("Ficha 1: " + a1.getNome() + " - " + a2.getCurso());
System.out.println("Ficha 2: " + a2.getNome() + " - " + a1.getCurso());
}
}