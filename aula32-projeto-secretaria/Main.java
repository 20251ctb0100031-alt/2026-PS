import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Aluno> lista = new ArrayList<Aluno>();

        while (true) {
            System.out.println("=========================================");
            System.out.println("   SECRETARIA DO CAMPUS - por FERNANDO ");
            System.out.println("=========================================");
            System.out.println("[1] Cadastrar aluno");
            System.out.println("[2] Listar alunos");
            System.out.println("[3] Buscar por matricula");
            System.out.println("[4] Atualizar curso");
            System.out.println("[5] Remover aluno");
            System.out.println("[6] Relatorio");
            System.out.println("[0] Sair");
            System.out.print("Sua escolha: ");

            String opcao = teclado.nextLine().trim();

            if (opcao.equals("0")) {
                System.out.println("Secretaria fechada. Ate a proxima!");
                break;
            } else if (opcao.equals("1")) {
                cadastrar(lista, teclado);
            } else if (opcao.equals("2")) {
                listar(lista);
            } else if (opcao.equals("3")) {
                buscarPorMatricula(lista, teclado);
            } else if (opcao.equals("4")) {
                atualizarCurso(lista, teclado);
            } else if (opcao.equals("5")) {
                removerAluno(lista, teclado);
            } else if (opcao.equals("6")) {
                relatorio(lista);
            } else {
                System.out.println("Opcao invalida! Escolha de 0 a 6.");
            }
        }

        teclado.close();
    }

    static void cadastrar(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Nome: ");
        String nome = teclado.nextLine().trim();

        System.out.print("Matricula: ");
        String matricula = teclado.nextLine().trim();

        System.out.print("Curso: ");
        String curso = teclado.nextLine().trim();

        Aluno novo = new Aluno(nome, matricula, curso);
        lista.add(novo);

        System.out.println("Ficha de " + novo.getNome() + " arquivada!");
    }

    static void listar(ArrayList<Aluno> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhuma ficha cadastrada.");
        } else {
            System.out.println("--- FICHAS NO GAVETEIRO: " + lista.size() + " ---");
            for (Aluno aluno : lista) {
                System.out.println(
                    aluno.getNome() + " | " +
                    aluno.getMatricula() + " | " +
                    aluno.getCurso()
                );
            }
        }
    }

    static void buscarPorMatricula(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Digite a matricula para busca: ");
        String mat = teclado.nextLine().trim();

        boolean encontrado = false;
        for (Aluno aluno : lista) {
            if (aluno.getMatricula().equalsIgnoreCase(mat)) {
                System.out.println("--- FICHA ENCONTRADA ---");
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("Matricula: " + aluno.getMatricula());
                System.out.println("Curso: " + aluno.getCurso());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum aluno encontrado com a matricula: " + mat);
        }
    }

    static void atualizarCurso(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Digite a matricula do aluno: ");
        String mat = teclado.nextLine().trim();

        boolean encontrado = false;
        for (Aluno aluno : lista) {
            if (aluno.getMatricula().equalsIgnoreCase(mat)) {
                System.out.print("Novo Curso: ");
                String novoCurso = teclado.nextLine().trim();
                aluno.setCurso(novoCurso);
                System.out.println("Curso de " + aluno.getNome() + " atualizado para " + novoCurso + "!");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Aluno nao localizado para atualizacao.");
        }
    }

    static void removerAluno(ArrayList<Aluno> lista, Scanner teclado) {
        System.out.print("Digite a matricula do aluno a remover: ");
        String mat = teclado.nextLine().trim();

        Aluno alunoParaRemover = null;
        for (Aluno aluno : lista) {
            if (aluno.getMatricula().equalsIgnoreCase(mat)) {
                alunoParaRemover = aluno;
                break;
            }
        }

        if (alunoParaRemover != null) {
            lista.remove(alunoParaRemover);
            System.out.println("Ficha de " + alunoParaRemover.getNome() + " removida com sucesso!");
        } else {
            System.out.println("Aluno nao encontrado.");
        }
    }

    static void relatorio(ArrayList<Aluno> lista) {
        System.out.println("=== RELATORIO DE MATRICULAS ===");
        System.out.println("Total de alunos registrados: " + lista.size());

        if (!lista.isEmpty()) {
            System.out.println("-------------------------------");
            for (int i = 0; i < lista.size(); i++) {
                Aluno a = lista.get(i);
                System.out.println("[" + (i + 1) + "] " + a.getNome() + " - Mat: " + a.getMatricula() + " - Curso: " + a.getCurso());
            }
        }
    }
}