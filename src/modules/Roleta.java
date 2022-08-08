package modules;

import java.util.ArrayList;
import java.util.Random;

import modules.core.Individuo;

public class Roleta {
    // private double[] vetorProbabilidade;
    private int[][] listaRoleta;
    private int teste;

    // =====================================================================================
    public Roleta(int qtdLinhas) {
        this.listaRoleta = new int[qtdLinhas][2];
        this.teste = 0;
    }

    // =====================================================================================
    public void configurarRoleta(int id, int geracao, double funcApitidao) {
        int qtdEspacos = ((int) (funcApitidao * listaRoleta.length));
        int condParada = 0;
        for (int i = 0; (i < this.listaRoleta.length); i++) {
            if (this.listaRoleta[i][1] == 0) {
                condParada = i;
                break;
            }
        }
        for (int i = condParada; (i < condParada + qtdEspacos); i++) {
            this.listaRoleta[i][0] = (geracao);
            this.listaRoleta[i][1] = (id);
        }
    }

    public int[] sortearIndividuo() {
        while (true) {
            int valorAleatorio = valorAleatorio();
            int[] valores = new int[2];
            if ( getGeracao(valorAleatorio) > 0) {
                valores[0] = getGeracao(valorAleatorio);
                valores[1] = getId(valorAleatorio);
                return valores;
            }
        }
    }

    public int sortearGene() {
        while (true) {
            int valorAleatorio = valorAleatorio();
            int[] valores = new int[2];
            if ( getGeracao(valorAleatorio) > 0) {
                valores[0] = getGeracao(valorAleatorio);
                valores[1] = getId(valorAleatorio);
                return valorAleatorio;
            }
        }
    }

    // public void limparRoleta(){
    // this.listaRoleta.;
    // }
    // =====================================================================================
    public int getGeracao(int posicao) {
        return this.listaRoleta[posicao][0];
    }

    public int getId(int posicao) {
        return this.listaRoleta[posicao][1];
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
