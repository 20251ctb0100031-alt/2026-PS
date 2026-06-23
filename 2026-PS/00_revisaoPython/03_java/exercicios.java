public class exercicios {

    static double calcularDesconto(double valor, double percentual) {
        return valor - (valor * percentual / 100);
    }
    static int maiorNumero(int a, int b) {
        return (a >= b) ? a : b;
    }
    static double calcularFrete(double peso) {
        if (peso <= 1) {
            return 10.0;
        } else if (peso <= 5) {
            return 20.0;
        }else {
            return 35.0;
        }
    }

    static int somar(int a, int b) {
        return a+b;
    }
    static double somar(double a, double b) {
        return a+b;
    }

    static void exibirProduto(String nome) {
        System.out.println("produto: "+ nome);
    }

    static void exibirProduto(String nome, double preco) {
        System.out.println("Produto: "+ nome);
        System.out.println("Preço: R$ "+ preco);
    }

    public static void main (String[]args){
        System.out.println("\n🧩 Problema 1");
        System.out.println(calcularDesconto(100,10));
        System.out.println(calcularDesconto(250,20));
        System.out.println(calcularDesconto(500,15));

        System.out.println("\n🧩 Problema 2");
        System.out.println(maiorNumero(10,20));
        System.out.println(maiorNumero(50,5));
        System.out.println(maiorNumero(30,30));

        System.out.println("\n🧩 Problema 3");
        System.out.println(calcularFrete(0.5));
        System.out.println(calcularFrete(3));
        System.out.println(calcularFrete(8));

        System.out.println("\n🧩 Problema 4");
        System.out.println(somar(5,3));
        System.out.println(somar(2.5, 3.5));
        System.out.println(somar(100, 50));    

        System.out.println("\n🧩 Problema 5");
        exibirProduto("Refrigerante");
        exibirProduto("Pizza",39.90);
        exibirProduto("Hamburger", 22.50);
    }

}