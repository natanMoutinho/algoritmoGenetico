package modules.roleta;

import java.util.ArrayList;
import java.util.Random;

import modules.core.Individuo;

public class Roleta {
    // private double[] vetorProbabilidade;
    private int[][] listaRoleta;
    private int statusRoleta;
    private int qtdlinhas;

    // =====================================================================================
    public Roleta() {
        this.listaRoleta = new int[100][2];
        this.qtdlinhas = 100;
        this.statusRoleta = 0;
    }

    // =====================================================================================
    public void configurarRoleta(int id, int valorSecundario, double probabilidade) {
        System.out.println("\nFunção configurarRoleta");
        int qtdEspacos = ((int) Math.round(probabilidade * 100));
        // System.out.println("\nId: "+id+"\tprobabilidade: "+probabilidade+"\tQtdEspaços: "+qtdEspacos);
        // int condParada = 0;
        // for (int i = 0; (i < this.listaRoleta.length); i++) {
        //     if (this.listaRoleta[i][1] == 0) {
        //         condParada = i;
        //         break;
        //     }
        // }
        System.out.println("Somatório: "+(statusRoleta+qtdEspacos));
        for(int i = statusRoleta;i<(statusRoleta+qtdEspacos) && i<=100;i++){
            // System.out.print(this.statusRoleta+"->"+qtdEspacos+"  ");
            // System.out.println("\t=======> "+(i==100));
            if(i==100){
                break;
            }
            this.listaRoleta[i][0] = (valorSecundario);
            this.listaRoleta[i][1] = (id);
            
        }
        this.statusRoleta += qtdEspacos;
        // System.out.println("");
        // for (int i = 0; (i < statusRoleta + qtdEspacos); i++) {
        //     System.out.print(statusRoleta+"->"+qtdEspacos+"  ");
        //     this.listaRoleta[i][0] = (valorSecundario);
        //     this.listaRoleta[i][1] = (id);
        //     statusRoleta ++;
        // }
    }

    public int[] sortearIndividuo() {
        while (true) {
            int valorAleatorio = valorAleatorio();
            int[] valores = new int[2];
            if ( getGeracao(this.listaRoleta,valorAleatorio) > 0) {
                valores[0] = getGeracao(this.listaRoleta,valorAleatorio);
                valores[1] = getId(this.listaRoleta,valorAleatorio);
                return valores;
            }
        }
    }

    public int sortearGene() {
        while (true) {
            int valorAleatorio = valorAleatorio();
            int[] valores = new int[2];
            if ( getGeracao(this.listaRoleta,valorAleatorio) > 0) {
                valores[0] = getGeracao(this.listaRoleta,valorAleatorio);
                valores[1] = getId(this.listaRoleta,valorAleatorio);
                return valorAleatorio;
            }
        }
    }


    public void zerarRoleta(){
        this.statusRoleta = 0;
        // for(int i=0;i<this.listaRoleta.length;i++){
        //     this.listaRoleta[i][0] = 0;
        //     this.listaRoleta[i][1] = 0;
        // }
    }
    // public void limparRoleta(){
    // this.listaRoleta.;
    // }
    // =====================================================================================
    static public int getGeracao(int[][] listaRoleta, int posicao) {
        return listaRoleta[posicao][0];
    }

    static public int getId(int[][] listaRoleta, int posicao) {
        return listaRoleta[posicao][1];
    }

    // =====================================================================================
    public int[][] getListaRoleta() {
        return this.listaRoleta;
    }
    // =====================================================================================
    private int valorAleatorio(){
        int valorAleatorio = new Random().nextInt(100);
        return valorAleatorio;
    }
    // private class Valor{
    // private int id;
    // private int geracao;
    // public Valor(int id, int geracao){
    // this.id = id;
    // this.geracao = geracao;
    // }

    // public int getId(){
    // return this.id;
    // }
    // public int getGeracao(){
    // return this.geracao;
    // }
    // }

    // =====================================================================================
}
