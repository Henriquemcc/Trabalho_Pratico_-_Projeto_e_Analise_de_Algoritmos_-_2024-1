package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.ArrayList;
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
        super(listaPontosCandidatos, distanciaMinima);
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
                    pilhaIndice.push(indice + 1);
                    pilhaPontosEscolhidos.push(novosPontosEscolhidos);

                    // Não adicionando elemento indice
                    pilhaIndice.push(indice + 1);
                    pilhaPontosEscolhidos.push(pontosEscolhidos);
                }
            }
        }
    }
}
