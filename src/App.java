  /*
            [x] Primeiro será necessário gerar a população de forma aleatório
            [x] Agr será necessário avaliar a população para realizar a avaliação
            [x] Agr será necessário girar a roleta para escolhar um individuo
            [x] Dps será necessário girar para criar escolher outro individuo
            [x] será repetido essa girada na roleta até ter compor todos os pares possíveis
            [] agr deverá ter o giro para saber da mutação
            [] agr será girada a roleta mais uma vez para realizar para escolher o tipo de genes que seram trocados
            [] Dps disso TUDO, será necessário criar a nova geração
            [] Dps de criados será necessário subistituir os individuos que são menos apitdos 

         */
import java.util.ArrayList;
import java.util.Random;

import modules.*;
import modules.core.Objeto;
public class App {
    public static void main(String[] args) throws Exception {
        Teste teste = new Teste();
        teste.teste5();
        
        // for(int i=0;i<=10;i +=2){
        //     System.out.println(i+"\t"+(i+1));
        // }
        
        // int[] novo1 = new int[2];
        // novo1[0] = 5;
        // novo1[1] = 5;

        // int[] novo2 = new int[2];
        // novo2[0] = 1;
        // novo2[1] = 1;

        // ArrayList<Integer> asdf = new ArrayList<Integer>();

        //     asdf.add(novo1);
        //     asdf.add(novo2);
        
        

        // int[] qwert = aaa();
        // System.out.println("asdfasdf: "+qwert[0]);

        // Objeto[] objeto =  new Objeto[2];

        // System.out.println("Teste: "+objeto.length );

        //instância um objeto da classe Random usando o construtor básico
       
        // int [][] novo = new int[5][2];

        // int [] valor = new int[2];
        // valor[0] = 1;
        // valor[1] = 0;

        // novo[0] = valor;


        // class NovoTeste{
        //     public NovoTeste(){
        //         System.out.println("Teste");
        //     }   
        // }        

        // NovoTeste asdf = new NovoTeste();

        System.out.print("\n\n");
    }

    public static int[] aaa(){
        int[] vet = new int[2];
        vet[0] = 1;
        vet[1] = 2;
        return vet;
    }
}


