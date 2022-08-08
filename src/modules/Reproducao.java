package modules;

import java.util.ArrayList;

import modules.core.Individuo;
import modules.core.Objeto;
import modules.core.Populacao;

/*
    Essa classe tem a responsabilidade de realizar a reprodução de dois indivuduos
    

*/

public class Reproducao {

    private int taxaDeReproducao;

    private ArrayList<Individuo> listaIndividuos;
    private ArrayList<Individuo> novosIndividuos;

    private Individuo[] individuosReproducao;
    
    private Roleta roletaIndividuos;
    private Roleta roletaGenes;

    private Populacao populacaoEditavel;

    // ==================================================================
    public Reproducao(Populacao populacao, int taxaDeReproducao) {
        this.taxaDeReproducao = taxaDeReproducao/100;
        this.listaIndividuos = populacao.getListIndividuos();
        this.novosIndividuos = new ArrayList<Individuo>();
        this.individuosReproducao = new Individuo[calcularQtdPares(123, taxaDeReproducao)];
        this.populacaoEditavel = new Populacao(populacao.getListIndividuos(), populacao.getSomaFitness());
        this.roletaIndividuos = new Roleta(100);
        this.roletaGenes = new Roleta(this.listaIndividuos.get(0).getCromossos().length);
    }

    // ==================================================================
    // 0 - geração
    // 1 - id
    public void realizarCrossOver() {
        setarRoletaParaSelecaoIndividuo();
        for(int i=0;i<Math.floor(this.listaIndividuos.size()/2);i++){
            
        }
    }

    /*
     sortear roleta retirarar e guardar em algum lugar

    */

    // ==================================================================
    private Individuo selecionarIndividuo() {
        int[] valores = this.roletaIndividuos.sortearIndividuo();
        Individuo individuoRemovido;
        for (int i = 0; i < this.listaIndividuos.size(); i++) {
            if ((this.listaIndividuos.get(i).getId() == valores[1])
                    && (this.listaIndividuos.get(i).getGeracao() == valores[0])) {
                individuoRemovido = this.listaIndividuos.get(i);
                populacaoEditavel.rmIndividuo(this.listaIndividuos.get(i));
                return individuoRemovido;
            }
        }
        return null;
    }

    private void setarRoletaParaSelecaoIndividuo() {
        Individuo individuoAtual;
        for (int i = 0; i < listaIndividuos.size(); i++) {
            individuoAtual = populacaoEditavel.getListIndividuos().get(i);
            roletaIndividuos.configurarRoleta(individuoAtual.getId(), individuoAtual.getGeracao(),
                    individuoAtual.getProbDeEscolha());
        }
    }

    private void setarRoletaParaSelecaoGene(Individuo individuo){
        // int obj;
        // for (int i = 0; i < individuo.getCromossos().length; i++) {
        //     roleta.configurarRoleta(individuoAtual.getId(), individuoAtual.getGeracao(),
        //             individuoAtual.getProbDeEscolha());
        // }

    }

    private int calcularQtdPares(int qtdIndividuos, int taxaDeReproducao){
        return (int)Math.floor(qtdIndividuos*(taxaDeReproducao/100));
    }
    // ==================================================================
}