/*l,Exercicios java aula 31: SOMA, MEDIA, MENOSR VALOR, MAIOR VALOR, ACIMA DO LIMITE  */

//---SOMA---
int soma(int [] num) {
    int soma = 0;
    for (int i:num){
        soma +=1;
    }
    return soma;
}

//---MEDIA---
int media(int []num){
    int soma = 0;
    for (int i:num){
        soma +=1;
    }
    return soma/ num.legth;
}

//---MENOR VALOR---
int mev(int []num){
    int menor = num[0];
    for (int 1:num){
        if (i<menor){
            menor = 1;
        }
    }
    return menor;
}

//---MAIOR VALOR---
int mav(int []num){
        int maior = num[0];
    for (int 1:num){
        if (i>maior){
            maior = 1;
        }
    }
    return maior;
}

//---ACIMA DO LIMITE---
int al(int num, int limite){
    int c=0;
    for (int i:num){
        if (i>limite){
            c++;
        }
    }
    return c;
}

//Para realizar as operações com os tipo de número (double e float) basta modificar os tipos nas funções substituindo os INTs para o tipo desejado.