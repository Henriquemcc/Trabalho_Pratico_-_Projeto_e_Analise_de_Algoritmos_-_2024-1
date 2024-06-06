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
        final Stack<List<PontoCandidato>> pilhaPontosEscolhidos = new Stack<>();
        final Stack<Integer> pilhaIndice = new Stack<>();

        // Adicionando primeiros elementos na pilha
        pilhaPontosEscolhidos.push(new ArrayList<>(listaPontosCandidatos));
        pilhaIndice.push(0);

        // Enquanto as pilhas não estiverem vazias serão analisados os elementos
        while (!pilhaIndice.isEmpty() || ! pilhaPontosEscolhidos.isEmpty()) {
            final int indice = pilhaIndice.pop();
            final List<PontoCandidato> pontosEscolhidos = pilhaPontosEscolhidos.pop();

            final Solucao solucao = new Solucao(pontosEscolhidos);

            // Fim da (pseudo) recursão
            if (solucao.getMenorDistancia() >= distanciaMinimaPermitida && (melhorSolucao == null || solucao.getPontosCandidatosEscolhidos().size() > melhorSolucao.getPontosCandidatosEscolhidos().size() || (solucao.getPontosCandidatosEscolhidos().size() == melhorSolucao.getPontosCandidatosEscolhidos().size() && solucao.getCustoTotal() < melhorSolucao.getCustoTotal())))
                melhorSolucao = solucao;

            // Removendo pontos
            else if (indice < listaPontosCandidatos.size()) {

                // Removendo o elemento atual
                final List<PontoCandidato> novosPontosEscolhidos = new ArrayList<>(pontosEscolhidos);
                novosPontosEscolhidos.remove(listaPontosCandidatos.get(indice));
                pilhaPontosEscolhidos.push(novosPontosEscolhidos);
                pilhaIndice.push(indice+1);

                // Não removendo o elemento atual
                pilhaPontosEscolhidos.push(pontosEscolhidos);
                pilhaIndice.push(indice+1);

            }
        }

    }
}
