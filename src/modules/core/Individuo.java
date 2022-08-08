package modules.core;

import java.util.ArrayList;
import java.util.Random;

public class Individuo {
    private int id;
    private double fitnessValue;
    private double peso;
    private int geracao;
    private double funcaoDeAptidao;
    private int[] cromossomo;
//==============================================================================   
    public Individuo (int id, int geracao, int qtdObj, int n){
        this.id = id;
        this.geracao = geracao;
        this.fitnessValue = 0.0;
        this.funcaoDeAptidao = 0.0;
        this.peso = 0.0;
        cromossomo = new int[qtdObj];
        for(int i=0;i<qtdObj;i++){
            this.cromossomo[i] = new Random().nextInt(2);
        }
        // System.out.println("Teste: "+this.cromossomo);
    }
//==============================================================================
    public void calcularValorFitness(ArrayList<Objeto> arrayObj){
        for(int i=0;i<arrayObj.size();i++){
            if(this.cromossomo[i] == 1){
                this.fitnessValue += arrayObj.get(i).getValor();
            }
        }
    }
    
    public void imprirmirVetSolucao(){
        System.out.print("[ ");
        for(int i=0;i<this.cromossomo.length;i++){
            System.out.print(this.cromossomo[i]+", ");
        }
        System.out.print("] -> "+this.fitnessValue+"\n");
    }
//==============================================================================
    public void setFitnessValue(double value){
        this.fitnessValue = value;
    }
    public void setProbEscolha(double valor){
        this.funcaoDeAptidao = valor;
    }
    public int getId(){
        return this.id;
    }
    public int getGeracao(){
        return this.geracao;
    }
    public double getFitnessValue(){
        return this.fitnessValue;
    }
    public double getProbDeEscolha(){
        return this.funcaoDeAptidao;
    }
    public int[] getCromossos(){
        return this.cromossomo;
    }
}
