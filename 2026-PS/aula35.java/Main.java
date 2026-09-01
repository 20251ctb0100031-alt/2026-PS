import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) {

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("\n=== SISTEMA DE PRODUTOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar preço");
            System.out.println("4 - Remover");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            if (opcao == 1) {
                cadastrar();

            } else if (opcao == 2) {
                listar();

            } else if (opcao == 3) {
                alterarPreco();

            } else if (opcao == 4) {
                remover();

            } else if (opcao != 5) {
                System.out.println("Opção inválida.");
            }
        }

        System.out.println("Sistema encerrado.");
    }

    // Cadastrar produto
    static void cadastrar() {

        System.out.print("Código: ");
        int codigo = teclado.nextInt();
        teclado.nextLine();

        // Verifica se já existe um produto com esse código
        Produto produtoExistente = buscarPorCodigo(codigo);

        if (produtoExistente != null) {
            System.out.println("Cadastro recusado. Esse código já existe.");
            return;
        }

        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        System.out.print("Preço: ");
        double preco = teclado.nextDouble();

        Produto p = new Produto(codigo, nome, preco);

        produtos.add(p);

        System.out.println("Produto cadastrado com sucesso.");
    }

    // Listar produtos
    static void listar() {

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n=== PRODUTOS ===");

        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    // Buscar produto pelo código
    static Produto buscarPorCodigo(int codigo) {

        for (Produto p : produtos) {

            if (p.getCodigo() == codigo) {
                return p;
            }
        }

        return null;
    }

    // Alterar preço
    static void alterarPreco() {

        System.out.print("Código: ");
        int codigo = teclado.nextInt();

        Produto p = buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo preço: ");
        double preco = teclado.nextDouble();

        p.alterarPreco(preco);

        System.out.println("Preço alterado com sucesso.");
    }

    // Remover produto
    static void remover() {

        System.out.print("Código: ");
        int codigo = teclado.nextInt();

        Produto p = buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        produtos.remove(p);

        System.out.println("Produto removido com sucesso.");
    }
}
