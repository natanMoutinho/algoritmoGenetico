package modules.core;

import java.util.ArrayList;

/*

    Gerar os Objetos que serão utilizados
    Nessa classes deve ser necessário ordenar os individuos

*/
public class Populacao {

    private ArrayList<Objeto> listObjetos;
    private ArrayList<Individuo> populacao;
    private int qtdIndividuo;
    private double somatorioFitness;
    private int pesoMaximo;

    private Populacao geracaoAntiga;

    // ==============================================================================
    public Populacao(int qtdIndividuo, ArrayList<Objeto> listObjetos, int pesoMaximo) {
        this.qtdIndividuo = qtdIndividuo;
        this.listObjetos = listObjetos;
        this.populacao = new ArrayList<Individuo>();
        this.somatorioFitness = 0.0;
        this.pesoMaximo = pesoMaximo;
        criarIndividuos();
    }

    public Populacao(ArrayList<Individuo> listInviduos, double somaFitness) {
        this.populacao = (ArrayList)listInviduos.clone();
        this.somatorioFitness = somaFitness;
    }

    // ==============================================================================
    public void imprimirListIndividuos() {
        Individuo individuoAtual;
                        
        System.out.print("\n");
        for (int i = 0; i < this.populacao.size(); i++) {
            individuoAtual = this.populacao.get(i);
            System.out.print(i + " - Individuo: " + individuoAtual.getId() + "\t-> ");
            individuoAtual.imprirmirVetSolucao();
            System.out.println("\t| funcaoDeAptidao: "+calcularProbabilidade(individuoAtual));

        }
    }

    public void addIndividuo(Individuo individuo){
        avaliarIndividuo(individuo, this.listObjetos, this.pesoMaximo);
        this.populacao.add(posicaoListIndividuos(individuo), individuo);
        this.somatorioFitness += individuo.getFitnessValue();
        // calcularProbabilidade(individuo);
    }

    public void rmIndividuo(Individuo individuo) {
        this.somatorioFitness -= individuo.getFitnessValue();
        this.populacao.remove(individuo);
        // calcularProbabilidade(individuo);
    }

    public int qtdIndividusoAptos(){
        int cont=0;
        for(int i=0;i<this.populacao.size();i++){
            if(this.populacao.get(i).getFitnessValue() > 0){
                cont ++;
            }
        }
        return cont;
    }

    // =============== Métodos privados que são importantes
    private void criarIndividuos() {
        for (int i = 1; i <= this.qtdIndividuo; i++) {
            Individuo ind = new Individuo(i, 1, this.listObjetos.size(),1);
            addIndividuo(ind);
        }
    }

    // ==============================================================================
    private int posicaoListIndividuos(Individuo individuo) {
        // calcularValorFitness(individuo);
        if (this.populacao.size() == 0) {
            return 0;
        }
        if (this.populacao.get(this.populacao.size() - 1).getFitnessValue() >= individuo.getFitnessValue()) {
            return this.populacao.size();
        }

        for (int i = 0; i < this.populacao.size(); i++) {
            if (individuo.getFitnessValue() > this.populacao.get(i).getFitnessValue()) {
                return i;
            }
        }
        return 0;
    }

    private double calcularValorFitness(int[] cromossomo) {
        double valorTotal = 0;
        for (int i = 0; i < cromossomo.length; i++) {
            if (cromossomo[i] == 1) {
                valorTotal += this.listObjetos.get(i).getValor();
            }
        }
        return valorTotal;
    }

    private double calcularPeso(int[] cromossomo){
        double valorTotal = 0;
        for (int i = 0; i < cromossomo.length; i++) {
            if (cromossomo[i] == 1) {
                valorTotal += this.listObjetos.get(i).getPeso();
            }
        }
        return valorTotal;
    }
    public double calcularProbabilidade(Individuo individuo) {
        if(individuo.getFitnessValue() == 0){
            return 0.0;
        }
        double funcApitidao = individuo.getFitnessValue() / this.somatorioFitness;
        return funcApitidao;
        // for (int i = 0; i < this.populacao.size(); i++) {
        //     funcApitidao = this.populacao.get(i).getFitnessValue() / this.somatorioFitness;
        //     if(!(funcApitidao == 0)){
        //         this.populacao.get(i).setProbEscolha(funcApitidao);
        //     } else{
        //         this.populacao.get(i).setProbEscolha(0.0);
        //     }
        // }
    }

    
    private void avaliarIndividuo(Individuo individuo, ArrayList<Objeto> listaObjetos, int pesoMaximo){
        double fitnessValue = calcularValorFitness(individuo.getCromossos());
        double peso = calcularPeso(individuo.getCromossos());

        if(peso <= pesoMaximo){
            individuo.setFitnessValue(fitnessValue);
        } else{
            individuo.setFitnessValue(0.0);
        }
        individuo.setPeso(peso);
    }

    public void avaliarPopulacao(){
        
    }
    // ==============================================================================
    public ArrayList<Individuo> getListIndividuos() {
        return this.populacao;
    }

    public double getSomaFitness() {
        return this.somatorioFitness;
    }

}