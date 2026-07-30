public class Aula30 {

    private String nome;
    private double preco;
    private int estoque;
    private String categoria;

    public Aula30(String nome, double preco, int estoque, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getCategoria() {
        return categoria;
    }

    // Setters com validação
    public boolean setNome(String nome) {
        if (nome.isEmpty()) {
            return false;
        }
        this.nome = nome;
        return true;
    }

    public boolean setPreco(double preco) {
        if (preco <= 0) {
            return false;
        }
        this.preco = preco;
        return true;
    }

    public boolean setCategoria(String categoria) {
        if (categoria.isEmpty()) {
            return false;
        }
        this.categoria = categoria;
        return true;
    }

    // Métodos de comportamento
    public boolean vender(int quantidade) {
        if (quantidade <= 0 || quantidade > estoque) {
            return false;
        }

        estoque -= quantidade;
        return true;
    }

    public boolean adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            return false;
        }

        estoque += quantidade;
        return true;
    }

    public String resumo() {
        return "Nome: " + nome +
               " | Preço: R$ " + preco +
               " | Estoque: " + estoque +
               " | Categoria: " + categoria;
    }

    public static void main(String[] args) {

        Aula30 p1 = new Aula30("Caneta", 5.50, 20, "Papelaria");
        Aula30 p2 = new Aula30("Caderno", 25.00, 10, "Escolar");
        Aula30 p3 = new Aula30("Lápis", 2.50, 30, "Papelaria");

        System.out.println("\n🧩 Teste 1 - Objetos criados");
        System.out.println(p1.resumo());
        System.out.println(p2.resumo());
        System.out.println(p3.resumo());

        System.out.println("\n🧩 Teste 2 - Nome vazio");
        System.out.println(p1.setNome(""));

        System.out.println("\n🧩 Teste 3 - Preço negativo");
        System.out.println(p2.setPreco(-10));

        System.out.println("\n🧩 Teste 4 - Venda válida");
        System.out.println(p1.vender(5));
        System.out.println(p1.resumo());

        System.out.println("\n🧩 Teste 5 - Venda inválida");
        System.out.println(p2.vender(100));
        System.out.println(p2.resumo());

        System.out.println("\n🧩 Adicionar estoque");
        p3.adicionarEstoque(15);
        System.out.println(p3.resumo());

        System.out.println("\n🧩 Alteração válida");
        System.out.println(p3.setPreco(3.20));
        System.out.println(p3.resumo());
    }
}