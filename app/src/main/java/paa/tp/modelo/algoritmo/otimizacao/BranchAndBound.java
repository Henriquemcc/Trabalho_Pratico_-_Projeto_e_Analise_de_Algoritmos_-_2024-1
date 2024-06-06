package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Algoritmo Branch and Bound.
 */
public class BranchAndBound extends Algoritmo {

    /**
     * Constrói uma nova instância de Branch and Bound de Algoritmo.
     *
     * @param listaPontosCandidatos Lista de pontos candidatos a serem filiais.
     * @param distanciaMinima       Distância mínima permitida entre cada filial.
     */
    public BranchAndBound(List<PontoCandidato> listaPontosCandidatos, double distanciaMinima) {
        super(new ArrayList<>(listaPontosCandidatos), distanciaMinima);
        final Comparator<PontoCandidato> comparatorPontoCandidato = new Comparator<PontoCandidato>() {
            @Override
            public int compare(PontoCandidato pontoCandidato, PontoCandidato t1) {
                return Integer.compare(pontoCandidato.getCustoInstalacao(), t1.getCustoInstalacao());
            }
        };
        listaPontosCandidatos.sort(comparatorPontoCandidato);
    }

    /**
     * Executa o algoritmo Branch and Bound.
     */
    @Override
    public void executar() {

        // Pilha que será utilizada para transformar o problema recursivo em iterativo
        final Stack<Integer> pilhaIndice = new Stack<>();
        final Stack<List<PontoCandidato>> pilhaPontosEscolhidos = new Stack<>();

        // Adicionando primeiros elementos na pilha
        pilhaIndice.push(0);
        pilhaPontosEscolhidos.push(new ArrayList<>());

        // Enquanto as pilhas não estiverem vazias serão geradas as combinações
        while (!pilhaIndice.isEmpty()) {
            final List<PontoCandidato> pontosEscolhidos = pilhaPontosEscolhidos.pop();
            final int indice = pilhaIndice.pop();
            final Solucao solucao = new Solucao(pontosEscolhidos);

            // Verificando a restrição
            if (verificarRestricao(solucao)) {
                // Fim da (pseudo) recursão
                if (indice >= listaPontosCandidatos.size()) {
                    if (verificarOtimizacao(solucao)) {
                        melhorSolucao = solucao;
                    }
                }

                // Adicionando elementos
                else {
                    // Adicionando elemento indice
                    final List<PontoCandidato> novosPontosEscolhidos = new ArrayList<>(pontosEscolhidos);
                    novosPontosEscolhidos.add(listaPontosCandidatos.get(indice));
                    final Solucao novaSolucao = new Solucao(novosPontosEscolhidos);
                    if (melhorSolucao == null || calcularGanho(novaSolucao, indice) >= calcularGanho(melhorSolucao, indice)) {
                        pilhaIndice.push(indice + 1);
                        pilhaPontosEscolhidos.push(novosPontosEscolhidos);
                    }

                    // Não adicionando elemento indice
                    if (melhorSolucao == null || calcularGanho(solucao, indice) >= calcularGanho(melhorSolucao, indice)) {
                        pilhaIndice.push(indice + 1);
                        pilhaPontosEscolhidos.push(pontosEscolhidos);
                    }
                }
            }
        }
    }

    /**
     * Calcula o ganho de cada ramificação do Branch and Bound
     * @param solucao Solução da ramificação.
     * @param indice Índice
     * @return Ganho da ramificação.
     */
    private double calcularGanho(final Solucao solucao, final int indice) {

        // Calculando o somatório do valor
        final int somatorioValor = indice+1;

        // Calculando o somatório do custo
        int somatorioCusto = 0;
        for (int i = 0; i < indice; i++)
            somatorioCusto += listaPontosCandidatos.get(i).getCustoInstalacao();

        // Calculando o custo máximo
        int custoMaximo = Integer.MIN_VALUE;
        for (final PontoCandidato pontoCandidato : listaPontosCandidatos)
            if (pontoCandidato.getCustoInstalacao() > custoMaximo)
                custoMaximo = pontoCandidato.getCustoInstalacao();

        // Calculando a maxima relação de valor por custo dos pontos que ainda não foram incluídos
        double maximaRelacao = Double.MIN_VALUE;
        for (final PontoCandidato pontoCandidato : listaPontosCandidatos)
            if (!solucao.getPontosCandidatosEscolhidos().contains(pontoCandidato)) {
                final double relacao = 1.0 / pontoCandidato.getCustoInstalacao();
                if (relacao > maximaRelacao)
                    maximaRelacao = relacao;
            }

        // Gerando o ganho
        return somatorioValor + (double)(custoMaximo - somatorioCusto) * maximaRelacao;
    }

}
