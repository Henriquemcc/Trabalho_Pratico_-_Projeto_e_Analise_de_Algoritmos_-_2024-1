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
                // Verificando restrição
                if (solucao.getMenorDistancia() >= distanciaMinimaPermitida && solucao.contemApenasUmPontoCandidatoPorFranquia()) {
                    // Verificando otimização
                    if (melhorSolucao == null || solucao.getQuantidadePontos() > melhorSolucao.getQuantidadePontos() || (solucao.getQuantidadePontos() == melhorSolucao.getQuantidadePontos() && solucao.getCustoTotal() < melhorSolucao.getCustoTotal())) {
                        melhorSolucao = solucao;
                    }
                }
            }

            // Adicionando ponto na posição indice
            else {
                // Verificando restrição
                if (solucao.getMenorDistancia() >= distanciaMinimaPermitida && solucao.contemApenasUmPontoCandidatoPorFranquia()) {

                    final ArrayList<PontoCandidato> novosPontosEscolhidos = new ArrayList<>(pontosEscolhidos);
                    novosPontosEscolhidos.add(listaPontosCandidatos.get(indice));
                    final Solucao novaSolucao = new Solucao(novosPontosEscolhidos);

                    // Adicionando elemento
                    pilhaIndice.push(indice+1);
                    pilhaPontosEscolhidos.push(novosPontosEscolhidos);

                    // Não adicionando elemento
                    pilhaIndice.push(indice + 1);
                    pilhaPontosEscolhidos.push(pontosEscolhidos);
                }
            }
        }

    }


    private double obterUpperBound(final Solucao solucao, final int indice) {
        // g(n) = custo até chegar ao ponto atual
        final int g_n = solucao.getCustoTotal();

        // h(n) = estimativa do custo até o final (melhor caso)
        int menorCusto = Integer.MAX_VALUE;
        for (int i = indice; i < listaPontosCandidatos.size(); i++)
        {
            if (listaPontosCandidatos.get(i).getCustoInstalacao() < menorCusto)
            {
                menorCusto = listaPontosCandidatos.get(i).getCustoInstalacao();
            }
        }
        final double h_n = (listaPontosCandidatos.size() - solucao.getQuantidadePontos()) * menorCusto;

        // f(n) = g(n) + h(n)
        return g_n + h_n;
    }
}
