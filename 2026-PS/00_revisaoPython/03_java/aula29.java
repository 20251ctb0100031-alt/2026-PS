import java.util.ArrayList;

public class aula29 {
    public static double calcularMedia(double[]nota) {
        System.out.println("🧩 Exercicio 1");
        double soma = 0;
        for (int i = 0; i < nota.length; i++) {
            soma = soma + nota[i];
        }
        return soma / nota.length;
    }

    public static int contarAprovados(double[] nota){
        System.out.println("🧩 Exercicio 2");
        int contador = 0;
        for (int i=0; i<nota.length; i++){
            if (nota[i] >= 6.0){
                contador=contador +1;
            }
        }
        return contador;
    }

        public static void adicionarProduto (ArrayList<String> lista, String nome){
        System.out.println("🧩 Exercicio 3");
        lista.add(nome);
        }

        public static void listarProdutos(ArrayList<String> lista){
        for (int i=0; i<lista.size(); i++){
            int numero=i+1;
            System.out.println(numero + " -" + lista.get(i));
        }
        }

        public static int maiorValor(int[] valores){
        System.out.println("🧩 Exercicio 4");
        int maior=valores[0];
        for (int i =1; i<valores.length; i++){
            if(valores[i]>maior){
                maior=valores[i];
            }
        }
        return maior;
    }

    public static int maiorValor(int a, int b){
        if(a>b){
            return a;
        }else{
            return b;
        }
    }

    public static void exibirboletim(double[] nota){
        System.out.println("🧩 Exercicio 5");
        double media=calcularMedia(nota);
        int aprovados = contarAprovados(nota);
        
        String passouAno;
        if (media>= 6.0){
            passouAno="Aprovado";
        }else{
            passouAno="Reprovado";
        }
        System.out.println("Média: "+media);
        System.out.println("Aprovados: "+aprovados);
        System.out.println("Situação: "+passouAno);

    }
}