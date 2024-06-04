package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.Hashtable;
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
        final Stack<Hashtable<Integer, PontoCandidato>> pilhaPontosEscolhidos = new Stack<>();
        final Stack<Integer> pilhaIndice = new Stack<>();

        // Adicionando primeiros elementos na pilha
        pilhaPontosEscolhidos.push(new Hashtable<>());
        pilhaIndice.push(0);

        // Enquanto as pilhas não estiverem vazias. (As pilhas são manipuladas juntas)
        while (!pilhaIndice.isEmpty()) {

            // Desempilhando
            final int indice = pilhaIndice.pop();
            final Hashtable<Integer,PontoCandidato> pontosEscolhidos = pilhaPontosEscolhidos.pop();

            final Solucao solucao = new Solucao(pontosEscolhidos);

            // Fim da (pseudo) recursão
            if (indice >= listaPontosCandidatos.size()) {

                // Adicionando melhor solução
                if (solucao.getDistanciaMinima() >= distanciaMinima && (melhorSolucao == null || solucao.getPontosCandidatosEscolhidos().size() > melhorSolucao.getPontosCandidatosEscolhidos().size() || (solucao.getPontosCandidatosEscolhidos().size() == melhorSolucao.getPontosCandidatosEscolhidos().size() && solucao.getCustoTotal() < melhorSolucao.getCustoTotal())))
                    melhorSolucao = solucao;
            }

            // Adicionando pontos
            else
            {
                // Adicionando elemento i na mochila
                final Hashtable<Integer, PontoCandidato> novosPontosEscolhidos = new Hashtable<>(pontosEscolhidos);
                novosPontosEscolhidos.put(listaPontosCandidatos.get(indice).getNumeroFranquia(), listaPontosCandidatos.get(indice));
                final Solucao novaSolucao = new Solucao(novosPontosEscolhidos);
                if (novaSolucao.getQuantidadePontos() > solucao.getQuantidadePontos() || (novaSolucao.getQuantidadePontos() == solucao.getQuantidadePontos() && novaSolucao.getCustoTotal() < solucao.getCustoTotal()))
                {
                    // Adicionando elementos na pilha
                    pilhaIndice.push(indice+1);
                    pilhaPontosEscolhidos.push(novosPontosEscolhidos);
                }

                // Não adicionando elemento i na mochila
                pilhaIndice.push(indice+1);
                pilhaPontosEscolhidos.push(pontosEscolhidos);
            }
        }

    }
}
