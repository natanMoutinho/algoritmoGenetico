package modules.core;

import java.util.ArrayList;

/*

    Gerar os Objetos que serão utilizados
    Nessa classes deve ser necessário ordenar os individuos

*/
public class Populacao {

    // private ArrayList<Objeto> listObjetos;
    private ArrayList<Individuo> listIndividuos;
    private int qtdIndividuo;
    private double somatorioFitness;

    private Populacao geracaoAntiga;

    // ==============================================================================
    public Populacao(int qtdIndividuo, ArrayList<Objeto> listObjetos) {
        this.qtdIndividuo = qtdIndividuo;
        // this.listObjetos = listObjetos;
        this.listIndividuos = new ArrayList<Individuo>();
        this.somatorioFitness = 0.0;
        
        criarIndividuos();
        calculaFuncAptidao();

    }

    public Populacao(ArrayList<Individuo> listInviduos, double somaFitness) {
        this.listIndividuos = (ArrayList)listInviduos.clone();
        this.somatorioFitness = somaFitness;
    }

    // ==============================================================================
    public void imprimirListIndividuos() {
        Individuo individuoAtual;
        for (int i = 0; i < this.listIndividuos.size(); i++) {
            individuoAtual = this.listIndividuos.get(i);
            System.out.print(i + " - Individuo: " + individuoAtual.getId() + " -> ");
            individuoAtual.imprirmirVetSolucao();
        }
    }

    public void rmIndividuo(Individuo individuo) {
        this.somatorioFitness -= individuo.getFitnessValue();
        this.listIndividuos.remove(individuo);
        calculaFuncAptidao();
    }

    // =============== Métodos privados que são importantes
    private void criarIndividuos() {
        for (int i = 1; i <= this.qtdIndividuo; i++) {
            Individuo ind = new Individuo(i, 1, this.listObjetos.size(),1);
            calcularValorFitness(ind);
            
            this.listIndividuos.add(posicaoListIndividuos(ind), ind);
            this.somatorioFitness += ind.getFitnessValue();
        }
    }

    // ==============================================================================
    private int posicaoListIndividuos(Individuo individuo) {
        // calcularValorFitness(individuo);
        if (this.listIndividuos.size() == 0) {
            return 0;
        }
        if (this.listIndividuos.get(this.listIndividuos.size() - 1).getFitnessValue() >= individuo.getFitnessValue()) {
            return this.listIndividuos.size();
        }

        for (int i = 0; i < this.listIndividuos.size(); i++) {
            if (individuo.getFitnessValue() > this.listIndividuos.get(i).getFitnessValue()) {
                return i;
            }
        }
        return 0;
    }

    private void calcularValorFitness(Individuo individuo) {
        double valorTotal = 0;
        for (int i = 0; i < individuo.getCromossos().length; i++) {
            if (individuo.getCromossos()[i] == 1) {
                valorTotal += this.listObjetos.get(i).getValor();
            }
        }
        individuo.setFitnessValue(valorTotal);
    }

    public void calculaFuncAptidao() {
        double funcApitidao = 0.0;
        for (int i = 0; i < this.listIndividuos.size(); i++) {
            funcApitidao = this.listIndividuos.get(i).getFitnessValue() / this.somatorioFitness;
            this.listIndividuos.get(i).setProbEscolha(funcApitidao);
        }
    }

    public int avaliarIndividuo(){
        return 0;
    }
    // ==============================================================================
    public ArrayList<Individuo> getListIndividuos() {
        return this.listIndividuos;
    }

    public double getSomaFitness() {
        return this.somatorioFitness;
    }

}