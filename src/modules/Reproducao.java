package modules;

import java.util.ArrayList;
import java.util.Random;

import modules.core.Individuo;
import modules.core.Objeto;
import modules.core.Populacao;
import modules.roleta.Roleta;

/*
    Essa classe tem a responsabilidade de realizar a reprodução de dois indivuduos
*/

public class Reproducao {

    private int taxaDeReproducao;

    // private ArrayList<Individuo> listaIndividuos;
    private ArrayList<Individuo> novosIndividuos;

    private Individuo[] listaIndividuosReproducao;
    private int posicaoIndividuosReproducao;
    private int taxaDeMutacao;

    private Roleta roletaIndividuos;
    private Roleta roletaGenes;

    private Populacao populacaoEditavel;

    // ==================================================================
    public Reproducao(Populacao populacao, int taxaDeReproducao, int taxaDeMutacao) throws CloneNotSupportedException {
        this.taxaDeReproducao = taxaDeReproducao / 100;
        // this.listaIndividuos = populacao.getListIndividuos();
        this.novosIndividuos = new ArrayList<Individuo>();
        // Cria uma cópia da população que possa ser editada
        // this.populacaoEditavel = new Populacao(populacao.getListIndividuos(), populacao.getSomaFitness(),populacao.getListObj());
        this.populacaoEditavel = populacao.clone();
        this.posicaoIndividuosReproducao = 0;

        this.listaIndividuosReproducao = new Individuo[calculaQtdIndividuosRepro(
                this.populacaoEditavel.qtdIndividusoAptos(),
                taxaDeReproducao)];
        this.roletaIndividuos = new Roleta(100);
        this.roletaGenes = new Roleta(populacao.getListIndividuos().get(0).getCromossos().length);
        this.taxaDeMutacao = taxaDeMutacao;
    }


    // ==================================================================
    // 0 - geração
    // 1 - id
    /*
     * Essa função tem como objetivo centralizar os outros métodos e realizar o
     * crossOver
     * Primeiro será feito a definição dos pares para realizar a reprodução
     * Dps será necessário percorrer a lista ordenada pela ordem de individuos que fará o crossover
     * 
     */

    public void gerarNovaGeracao() {
        System.out.println("\n ===> Função realizarCrossOver <===");
        setarRoletaParaSelecaoIndividuo();
        // imprimirRoletaIndividuo();
        System.out.print("\n");
        // Função que realiza a ordenação da lista de individuos pela ordem dos pares selecionado na roleta
        selecionarPares();
        // System.out.print("\n Lista Pares\n\n");
        imprimirListaPares();
        // System.out.print("\n Individuo Selecionado\n\n");
        // Agr será feito a realização do crossover dos individuos selecionandos
        // for(int i=0;i<this.listaIndividuosReproducao.length;i++){
        //     setarRoletaParaSelecaoGene(this.listaIndividuosReproducao[i]);
        // }
        // setarRoletaParaSelecaoGene(this.listaIndividuosReproducao[0]);

        System.out.print("\n");
        this.populacaoEditavel.limparIndividuos();//this.listaIndividuosReproducao.length
        for(int i=0;i<this.listaIndividuosReproducao.length;i+=2){
            realizarCrossOver(this.listaIndividuosReproducao[i], this.listaIndividuosReproducao[i+1], i);
        }


        System.out.print("\n\tNOVA GERAÇÃO DE INDIVIDUOS\n\n");
        this.populacaoEditavel.imprimirListIndividuos();

    }

    public void imprimirRoletaIndividuo() {
        System.out.print("\n[\n");
        // System.out.println("Roleta tamanho: " + roletaIndividuos.getListaRoleta().length);
        int cont = 1;
        for (int i = 0; i < roletaIndividuos.getListaRoleta().length; i++) {
            if (cont == 11) {
                System.out.print("\n");
                cont = 1;
            }
            System.out.print("(" + roletaIndividuos.getListaRoleta()[i][1] + ")" + ",  ");
            cont++;
        }
        System.out.print("\n]\n");
    }

    public void imprimirRoletaGenes() {
        System.out.print("[");
        for (int i = 0; i < roletaGenes.getListaRoleta().length; i++) {
            System.out.print("(" + roletaGenes.getListaRoleta()[i][0] + ")" + ",  ");
        }
        System.out.print("  ]\n");
    }

    // ==================================================================
    /*
     * Recebe os valores que foram sorteados da roleta e realiza a verificação para
     * encontrar um individuos com esses valores
     * Caso seja achado tal individuo eles será removido da lista da população
     * editavel
     * Por fim a função irá retornar o individuo removido
     */
    private Individuo selecionarIndividuo() {
        // System.out.println("\n ===> Função selecionarIndividuo <===");
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
     * Esse função recebe o individuo que foi sorteado e o adiciona dentro do vetor
     * de individuos pares
     * Por fim faz um refresh na roleta para que ela seja populada com uma lista de
     * individuos atualizada
     */
    private void selecionarPares() {
        // System.out.println("\n ===> Função selecionarPares <===");
        Individuo individuoSorteado = null;
        for (int i = 0; i < this.listaIndividuosReproducao.length; i++) {
            individuoSorteado = selecionarIndividuo();
            addIndividuoReproducao(individuoSorteado);
            setarRoletaParaSelecaoIndividuo();
        }
    }

    private void selecionaGenes(Individuo individuo) {
        setarRoletaParaSelecaoGene(individuo);

    }

    private void imprimirListaPares() {
        int cont = 1;
        for (int i = 0; i < this.listaIndividuosReproducao.length; i++) {
            System.out.print("Individuo: " + this.listaIndividuosReproducao[i].getId() + "\t");
            this.listaIndividuosReproducao[i].imprirmirVetSolucao();
            System.out.print("\t");
            if (cont == 1) {
                System.out.print("\t+\t");
            } else if (cont == 2) {
                System.out.print("\n\n");
                cont = 0;
            }
            cont++;
        }
    }

    // ==================================================================
    /*
     * Faz a configuração da roleta, passando os dados que estão presente na classe
     * para a roleta
     * Primeiro ela zera a roleta para que não haja erro nos valores
     * É percorrido a lista de individuos na população para realizar seu registro
     * dentro da roleta
     */
    private void setarRoletaParaSelecaoIndividuo() {
        // System.out.println("\n ===> Função setarRoletaParaSelecaoIndividuo <===");
        Individuo individuoAtual;
        roletaIndividuos.zerarRoleta();
        // this.populacaoEditavel.imprimirListIndividuos();
        for (int i = 0; i < this.populacaoEditavel.getListIndividuos().size(); i++) {
            individuoAtual = populacaoEditavel.getListIndividuos().get(i);
            roletaIndividuos.configurarRoleta(individuoAtual.getId(), individuoAtual.getGeracao(),
                    this.populacaoEditavel.calcularProbabilidade(individuoAtual));
        }
    }

    private void setarRoletaParaSelecaoGene(Individuo individuo) {

        // System.out.println("\n ===> Função setarRoletaParaSelecaoGene <===");

        roletaIndividuos.zerarRoleta();

        // System.out.print("\nIndividuo: " + individuo.getId()+"\t");
        // individuo.imprirmirVetSolucao();


        for (int i = 0; i < individuo.getCromossos().length; i++) {
            // this.roletaIndividuos.configurarRoleta(i, individuo.getCromossos()[i], 0.1);
            this.roletaGenes.configurarRoleta(i, individuo.getCromossos()[i], 0.01);
        }
        // System.out.print("\n\n");
        // imprimirRoletaGenes();
        // imprimirRoletaIndividuo();
    }


    private void realizarCrossOver(Individuo individuo1, Individuo individuo2, int ultimoId){
        // gerar um valor aleatório que vai de 1 a tamanho da lista de cromossos -1
        int valorAleatorio = new Random().nextInt(individuo1.getCromossos().length-1)+1;
        
        
        int[] parte1Individuo1 = new int[valorAleatorio];
        int[] parte2Individuo1 = new int[individuo1.getCromossos().length - valorAleatorio];
        
        int[] parte1Individuo2 = new int[valorAleatorio];
        int[] parte2Individuo2 = new int[individuo2.getCromossos().length - valorAleatorio];        


        int [] cromossomo1 = new int[individuo1.getCromossos().length];
        int [] cromossomo2 = new int[individuo1.getCromossos().length];

        for(int i=0;i<valorAleatorio;i++){
            cromossomo1[i] = individuo1.getCromossos()[i];
            cromossomo2[i] = individuo2.getCromossos()[i];
        }
        for(int i=valorAleatorio;i<individuo2.getCromossos().length;i++){
            cromossomo1[i] = individuo2.getCromossos()[i];
            cromossomo2[i] = individuo1.getCromossos()[i];
        }
        
        // individuo1.imprirmirVetSolucao();
        // System.out.println("");
        // individuo2.imprirmirVetSolucao();

        // System.out.println("\nValor sorteado: "+valorAleatorio);

        // Individuo novo1 = new Individuo((ultimoId+1), this.populacaoEditavel.getGeraca()+1, cromossomo1);
        // Individuo novo2 = new Individuo((ultimoId+2), this.populacaoEditavel.getGeraca()+1, cromossomo2);

        cromossomo1 = mutacao(cromossomo1);
        cromossomo2 = mutacao(cromossomo2);

        this.populacaoEditavel.addIndividuo(new Individuo((ultimoId+1), this.populacaoEditavel.getGeraca()+1, cromossomo1));
        this.populacaoEditavel.addIndividuo(new Individuo((ultimoId+2), this.populacaoEditavel.getGeraca()+1, cromossomo2));
    }

    private int[] mutacao(int[] cromossomo){
        if(possibilidadeMutacao(this.taxaDeMutacao)){
            int valorAleatorio = new Random().nextInt(cromossomo.length-1)+1;
            // System.out.println("\nTEVE MUTAÇÃOS\n");
            // for(int i=0;i<cromossomo.length;i++){
            //     System.out.print(cromossomo[i]+"\t");
            // }
            if(cromossomo[valorAleatorio] == 1){
                cromossomo[valorAleatorio] = 0;
            } else{
                cromossomo[valorAleatorio] = 1;
            }
            // System.out.println("\n\n");
            // for(int i=0;i<cromossomo.length;i++){
            //     System.out.print(cromossomo[i]+"\t");
            // }
            // System.out.println("\nFIM DA MUTAÇÃOS\n");
        }
        return cromossomo;
    }

    private boolean possibilidadeMutacao(int taxaDeMutacao){
        roletaIndividuos.zerarRoleta();
        // Agr é realizado a configuração da roleta para sorter se averá mutação
        roletaIndividuos.configurarRoleta(1, 0,(taxaDeMutacao/100));
        for(int i=taxaDeMutacao;i<100;i++){
            roletaIndividuos.configurarRoleta(0, 0,0.01);
        }

        int[] posicao = roletaIndividuos.sortearPosicao();

        if(posicao[0] == 1){
            return true;
        }
        return false;
    }

    // ==================================================================
    private int calculaQtdIndividuosRepro(int qtdIndividuos, double taxaDeReproducao) {
        int qtdP = (int) Math.floor(qtdIndividuos / (2 * (taxaDeReproducao / 100)));
        return qtdP * 2;
    }

    private void addIndividuoReproducao(Individuo individuo) {
        // System.out.println("\n ===> Função addIndividuoReproducao <===");
        // System.out.println("\n AKI Ó: "+this.posicaoIndividuosReproducao+"\n");
        this.listaIndividuosReproducao[this.posicaoIndividuosReproducao] = individuo;
        this.posicaoIndividuosReproducao += 1;
    }
    // ==================================================================
}