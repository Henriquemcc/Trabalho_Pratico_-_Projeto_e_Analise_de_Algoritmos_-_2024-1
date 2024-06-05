package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Algoritmo Branch and Bound.
 */
public class BranchAndBound extends Algoritmo{
    /**
     * Constrói uma nova instância de Branch and Bound de Algoritmo.
     * @param listaPontosCandidatos Lista de pontos candidatos a serem filiais.
     * @param distanciaMinima Distância mínima permitida entre cada filial.
     */
    public BranchAndBound(List<PontoCandidato> listaPontosCandidatos, double distanciaMinima) {
        super(listaPontosCandidatos, distanciaMinima);
    }

    @Override
    public void executar() {

        // Pilhas que serão utilizadas para transformar o problema recursivo em iterativo
        final Stack<Integer> pilhaIndice = new Stack<>();
        final Stack<List<PontoCandidato>> pilhaPontosEscolhidos = new Stack<>();


        // Adicionando primeiros elementos na pilha
        pilhaIndice.push(0);
        pilhaPontosEscolhidos.push(new ArrayList<>());

        // Enquanto as pilhas não estiverem vazias serão analisados os elementos
        while (!pilhaIndice.isEmpty()) {
            final int indice = pilhaIndice.pop();
            final List<PontoCandidato> pontosEscolhidos = pilhaPontosEscolhidos.pop();

            final Solucao solucao = new Solucao(pontosEscolhidos);

            // Fim da (pseudo) recursão
            if (indice >= listaPontosCandidatos.size()) {
                melhorSolucao = solucao;
            }

            // Adicionando ponto na posição indice
            else {
                // Verificando restrição
                if (solucao.getDistanciaMinima() >= distanciaMinima && solucao.contemApenasUmPontoCandidatoPorFranquia()) {

                    final ArrayList<PontoCandidato> novosPontosEscolhidos = new ArrayList<>(pontosEscolhidos);
                    novosPontosEscolhidos.add(listaPontosCandidatos.get(indice));
                    final Solucao novaSolucao = new Solucao(novosPontosEscolhidos);
                    final double ubNovaSolucao = getUb(novaSolucao);

                    // Adicionando elemento
                    // Verificando otimização
                    if (ubNovaSolucao > melhorSolucao.getQuantidadePontos()) {
                        pilhaIndice.push(indice+1);
                        pilhaPontosEscolhidos.push(novosPontosEscolhidos);
                    }

                    // Não adicionando elemento
                    // Verificando otimização
                    final double ubSolucao = getUb(solucao);
                    if (ubSolucao > melhorSolucao.getQuantidadePontos()) {
                        pilhaIndice.push(indice+1);
                        pilhaPontosEscolhidos.push(pontosEscolhidos);
                    }
                }
            }
        }

    }

    private double getUb(Solucao solucao) {
        final ArrayList<Double> ganhos = new ArrayList<>();
        for (final PontoCandidato pontoCandidato: listaPontosCandidatos) {
            if (!solucao.getPontosCandidatosEscolhidos().contains(pontoCandidato)) {
                ganhos.add(1.0 / pontoCandidato.getCustoInstalacao());
            }
        }
        double ganhoMaximo = ganhos.get(0);
        for (final double ganho: ganhos) {
            if (ganho > ganhoMaximo)
                ganhoMaximo = ganho;
        }

        final double ub = solucao.getQuantidadePontos() + (-1* solucao.getCustoTotal()) * ganhoMaximo;
        return ub;
    }
}
