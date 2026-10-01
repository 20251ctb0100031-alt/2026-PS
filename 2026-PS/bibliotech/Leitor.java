/*
Disciplina: 2026-PS
* Projeto : Bibliotech
* Arquivo : Leitor.java 
* Autor : Fernando
* Descricao : Leitor E UM TIPO DE Usuario: herda nome, matricula e entrar().
*/

public class Leitor extends Usuario { 
    private int limiteEmprestimos; 
    private int livrosEmMaos; 

    public Leitor(String nome, String matricula, int limiteEmprestimos) { 
        super(nome, matricula); // Chama o construtor de Usuario
        this.limiteEmprestimos = limiteEmprestimos; 
        this.livrosEmMaos = 0; 
    } 

    public int getLimiteEmprestimos() { return limiteEmprestimos; } 
    public int getLivrosEmMaos() { return livrosEmMaos; } 

    public boolean podePegarEmprestado() { 
        return livrosEmMaos < limiteEmprestimos; 
    } 

    public void pegouLivro() { this.livrosEmMaos += 1; } 
    public void devolveuLivro() { this.livrosEmMaos -= 1; } 

    public String toString() { 
        return "Leitor " + getNome() + " (" + getMatricula() + ") - " 
                + livrosEmMaos + " de " + limiteEmprestimos + " livros"; 
    } 
}
