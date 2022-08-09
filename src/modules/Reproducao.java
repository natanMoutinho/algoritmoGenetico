package modules;

import java.util.ArrayList;

import modules.core.Individuo;
import modules.core.Objeto;
import modules.core.Populacao;
import modules.roleta.Roleta;

/*
    Essa classe tem a responsabilidade de realizar a reprodução de dois indivuduos
*/

public class Reproducao {

    private int taxaDeReproducao;

    private ArrayList<Individuo> listaIndividuos;
    private ArrayList<Individuo> novosIndividuos;

    private Individuo[] individuosReproducao;
    private int posicaoIndividuosReproducao;

    private Roleta roletaIndividuos;
    private Roleta roletaGenes;

    private Populacao populacaoEditavel;

    // ==================================================================
    public Reproducao(Populacao populacao, int taxaDeReproducao) {
        this.taxaDeReproducao = taxaDeReproducao / 100;
        // this.listaIndividuos = populacao.getListIndividuos();
        this.novosIndividuos = new ArrayList<Individuo>();
        // Cria uma cópia da população que possa ser editada
        this.populacaoEditavel = new Populacao(populacao.getListIndividuos(), populacao.getSomaFitness());
        this.posicaoIndividuosReproducao = 0;

        this.individuosReproducao = new Individuo[calculaQtdIndividuosRepro(
                this.populacaoEditavel.qtdIndividusoAptos(),
                taxaDeReproducao)];
        this.roletaIndividuos = new Roleta();
    }

    // ==================================================================
    // 0 - geração
    // 1 - id
    /*
     * Essa função tem como objetivo centralizar os outros métodos e realizar o crossOver
     */
    public void realizarCrossOver() {
        System.out.println("\n ===> Função realizarCrossOver <===");
        setarRoletaParaSelecaoIndividuo();
        imprimirRoleta();

        // System.out.println("Qtd de Pares: " + this.individuosReproducao.length);
        int cont = 1;
        selecionarPares();
        // // System.out.print("\nContator: " + this.posicaoIndividuosReproducao);
        // System.out.println("\t qtdIndividuosReproducao: " + this.individuosReproducao.length);
        for (int i = 0; i < this.individuosReproducao.length; i++) {
            System.out.print("Individuo: " + this.individuosReproducao[i].getId() + "\t");
            if (cont == 1) {
                System.out.print("+\t");
            } else if (cont == 2) {
                System.out.print("\n\n");
                cont = 0;
            }
            cont++;
        }
    }

    public void imprimirRoleta() {
        System.out.print("\n[  ");
        System.out.println("Roleta tamanho: " + roletaIndividuos.getListaRoleta().length);
        int cont =1;
        for (int i = 0; i < roletaIndividuos.getListaRoleta().length; i++) {
            if(cont == 26){
                System.out.print("\n");
                cont =1;
            }
            System.out.print("("+roletaIndividuos.getListaRoleta()[i][1]+")"+ ",  ");
            cont ++;
        }
        System.out.print("  ]\n");
    }
    // ==================================================================
    /*
     * Recebe os valores que foram sorteados da roleta e realiza a verificação para encontrar um individuos com esses valores
     * Caso seja achado tal individuo eles será removido da lista da população editavel
     * Por fim a função irá retornar o individuo removido
     */
    private Individuo selecionarIndividuo() {
        System.out.println("\n ===> Função selecionarIndividuo <===");
        int[] valores = this.roletaIndividuos.sortearIndividuo();
        Individuo individuoRemovido = null;
        for (int i = 0; i < this.populacaoEditavel.getListIndividuos().size(); i++) {

            if ((this.populacaoEditavel.getListIndividuos().get(i).getId() == valores[1])
                    && (this.populacaoEditavel.getListIndividuos().get(i).getGeracao() == valores[0])) {
                individuoRemovido = this.populacaoEditavel.getListIndividuos().get(i);
                populacaoEditavel.rmIndividuo(individuoRemovido);
            }

        }


        return individuoRemovido;
    }
    /*
     * Esse função recebe o individuo que foi sorteado e o adiciona dentro do vetor de individuos pares
     * Por fim faz um refresh na roleta para que ela seja populada com uma lista de individuos atualizada
     */
    private void selecionarPares() {
        System.out.println("\n ===> Função selecionarPares <===");
        Individuo individuoSorteado = null;
        for (int i = 0; i < this.individuosReproducao.length; i++) {
            individuoSorteado = selecionarIndividuo();
            addIndividuoReproducao(individuoSorteado);
            setarRoletaParaSelecaoIndividuo();
        }
    }

    /*
     * Faz a configuração da roleta, passando os dados que estão presente na classe para a roleta
     * Primeiro ela zera a roleta para que não haja erro nos valores
     * É percorrido a lista de individuos na população para realizar seu registro dentro da roleta
     */
    private void setarRoletaParaSelecaoIndividuo() {
        System.out.println("\n ===> Função setarRoletaParaSelecaoIndividuo <===");
        Individuo individuoAtual;
        roletaIndividuos.zerarRoleta();
        this.populacaoEditavel.imprimirListIndividuos();
        for (int i = 0; i < this.populacaoEditavel.getListIndividuos().size(); i++) {
            individuoAtual = populacaoEditavel.getListIndividuos().get(i);
            // System.out.println("individuoAtual: "+individuoAtual.getId());
            System.out.println("\nId: "+individuoAtual.getId()+"\tprobabilidade: "+this.populacaoEditavel.calcularProbabilidade(individuoAtual));
            roletaIndividuos.configurarRoleta(individuoAtual.getId(), individuoAtual.getGeracao(),
                    this.populacaoEditavel.calcularProbabilidade(individuoAtual));
        }
    }

    private void setarRoletaParaSelecaoGene(Individuo individuo) {
        System.out.println("\n ===> Função setarRoletaParaSelecaoGene <===");
        Objeto objetoAtual;
        roletaIndividuos.zerarRoleta();
        this.populacaoEditavel.imprimirListIndividuos();
        
    }

    private int calculaQtdIndividuosRepro(int qtdIndividuos, double taxaDeReproducao) {
        int qtdP = (int) Math.floor(qtdIndividuos/(2*(taxaDeReproducao/100)));
        return qtdP*2;
    }

    private void addIndividuoReproducao(Individuo individuo) {
        System.out.println("\n ===> Função addIndividuoReproducao <===");
        // System.out.println("\n AKI Ó: "+this.posicaoIndividuosReproducao+"\n");
        this.individuosReproducao[this.posicaoIndividuosReproducao] = individuo;
        this.posicaoIndividuosReproducao += 1;
    }
    // ==================================================================
}