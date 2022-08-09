package modules.core;

public class Objeto {
    private int id;
    private double valor;
    private double peso;

    public Objeto(int id,double valor, double peso){
        this.id = id;
        this.valor = valor;
        this.peso = peso;
    }

    public int getId(){
        return this.id;
    }
    public double getValor(){
        return this.valor;
    }
    public double getPeso(){
        return this.peso;
    }
}
