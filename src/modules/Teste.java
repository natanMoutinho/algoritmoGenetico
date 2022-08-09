package modules;

import java.util.ArrayList;
import modules.core.*;
import modules.roleta.Roleta;

public class Teste {
    private ArrayList<Objeto> listaObj;
    private ArrayList<Individuo> listaIndi;
//==============================================================================
    public Teste(){
        this.listaObj = new ArrayList<Objeto>();
        this.listaIndi = new ArrayList<Individuo>();
    }
//==============================================================================
    public void teste1(){
        testeListObj1();
        criarIndi(6);
        for(int i=0;i<this.listaIndi.size();i++){
            exibirIndi(this.listaIndi.get(i));
            System.out.print("\n");
        }
    }

    public void teste2(){
        testeListObj1();
        Populacao pop = new Populacao(10,this.listaObj,10);
        
        System.out.print("[ ");
        for(int i=0;i<listaObj.size();i++){
            System.out.print(this.listaObj.get(i).getId()+"->"+this.listaObj.get(i).getValor()+", ");
        }
        System.out.print(" ]\n");
        pop.imprimirListIndividuos();
    }

    public void teste3 (){
        testeListObj1();
        Populacao pop = new Populacao(10,this.listaObj,10);
        pop.imprimirListIndividuos();

        Roleta roleta = new Roleta();

        Individuo individuoAtual;
        
        for(int i=0;i<pop.getListIndividuos().size();i++){
            individuoAtual = pop.getListIndividuos().get(i);
            roleta.configurarRoleta(individuoAtual.getId(),individuoAtual.getGeracao() , individuoAtual.getProbDeEscolha());
        }

        System.out.println("Roleta tamanho: "+roleta.getListaRoleta().length);
        for(int i=0;i<roleta.getListaRoleta().length;i++){
            System.out.print(roleta.getListaRoleta()[i]);
        }
        System.out.println("\nAbaixo");

        
    }
    /*
     * Foi gerado uma populaação 
     */
    public void teste4(){
        testeListObj1();
        Populacao pop = new Populacao(10,this.listaObj,2500);
        pop.imprimirListIndividuos();

        Roleta roletaIndividuos = new Roleta();
        Individuo individuoAtual;
        for (int i = 0; i < pop.getListIndividuos().size(); i++) {
            individuoAtual = pop.getListIndividuos().get(i);
            roletaIndividuos.configurarRoleta(individuoAtual.getId(), individuoAtual.getGeracao(),
                    pop.calcularProbabilidade(individuoAtual));
            
        }

        System.out.println("Roleta tamanho: "+roletaIndividuos.getListaRoleta().length);
        for(int i=0;i<roletaIndividuos.getListaRoleta().length;i++){
            System.out.print(roletaIndividuos.getListaRoleta()[i][1]);
        }
        // Reproducao reproducao = new Reproducao(pop);
        
    }

    public void teste5(){
        testeListObj1();
        Populacao pop = new Populacao(10,this.listaObj,2500);
        pop.imprimirListIndividuos();

        Reproducao reprodutores = new Reproducao(pop,100);

        reprodutores.realizarCrossOver();
        
    }
//==============================================================================
    private void exibirIndi(Individuo indi){
        System.out.print("[ ");
        for(int i=0;i<listaObj.size();i++){
            System.out.print(this.listaObj.get(i).getId()+", ");
        }
        System.out.print(" ]\n");

        System.out.print("[ ");
        for(int i=0;i<indi.getCromossos().length;i++){
            System.out.print(indi.getCromossos()[i]+", ");
        }
        System.out.print(" ]  Lucro = "+calcularValorFitness(indi)+"\n");
    }
    private double calcularValorFitness(Individuo indi){
        double valorTotal =0 ;
        for(int i=0;i<indi.getCromossos().length;i++){
            if(indi.getCromossos()[i] == 1){
                valorTotal += this.listaObj.get(i).getValor();
            }
        }
        return valorTotal;
    }
//==============================================================================
//  Valor de N é independente
    private void criarIndi(int n){
        for(int i=1;i<=n;i++){
            Individuo ind = new Individuo(i, 1, this.listaObj.size(),1);
            this.listaIndi.add(ind);
        }
    }
//  Qtd de obj é independente
    private void testeListObj1(){
        this.listaObj.add(new Objeto(1,400,200));
        this.listaObj.add(new Objeto(2,500,200));
        this.listaObj.add(new Objeto(3,700,300));
        this.listaObj.add(new Objeto(4,900,400));
        this.listaObj.add(new Objeto(5,600,400));
        this.listaObj.add(new Objeto(6,150,400));
        this.listaObj.add(new Objeto(7,100,400));
        this.listaObj.add(new Objeto(8,310,400));
        this.listaObj.add(new Objeto(9,725,400));
        this.listaObj.add(new Objeto(10,50,400));
    }
}
