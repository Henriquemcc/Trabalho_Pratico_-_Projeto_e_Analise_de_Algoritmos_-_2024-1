package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.*;

/**
 * Algoritmo Branch and Bound.
 */
public class BranchAndBound extends Algoritmo {
    /**
     * Constrói uma nova instância de Branch and Bound de Algoritmo.
     *
     * @param dicionarioPontosCandidatos Lista de pontos candidatos a serem filiais.
     * @param distanciaMinima       Distância mínima permitida entre cada filial.
     */
    public BranchAndBound(Dictionary<Integer, List<PontoCandidato>> dicionarioPontosCandidatos, double distanciaMinima) {
        super(dicionarioPontosCandidatos, distanciaMinima);
    }

    @Override
    public void executar() {

        // Pilha que será utilizada para transformar o problema recursivo em iterativo
        final Stack<Integer> pilhaIndice = new Stack<>();
        final Stack<List<PontoCandidato>> pilhaPontosEscolhidos = new Stack<>();

        // Adicionando primeiros elementos na pilha
        pilhaIndice.push(0);
        pilhaPontosEscolhidos.push(new ArrayList<>());

        // Chaves dos pontos candidatos
        final List<Integer> chaves = Collections.list(dicionarioPontosCandidatos.keys());

        // Enquanto as pilhas não estiverem vazias serão geradas as combinações
        while (!pilhaIndice.isEmpty()) {
            final List<PontoCandidato> pontosEscolhidos = pilhaPontosEscolhidos.pop();
            final int indice = pilhaIndice.pop();
            final Solucao solucao = new Solucao(pontosEscolhidos);

            // Verificando a restrição
            if (verificarRestricao(solucao) && verificarOtimizacao(solucao)) {
                melhorSolucao = solucao;
            }

            // Removendo elementos
            if (indice < quantidadeFranquias()) {
                for (PontoCandidato pontoCandidato : dicionarioPontosCandidatos.get(chaves.get(indice))) {

                    // Adicionando pontoCandidato
                    final List<PontoCandidato> novosPontosEscolhidos = new ArrayList<>(pontosEscolhidos);
                    novosPontosEscolhidos.add(pontoCandidato);
                    final Solucao novaSolucao = new Solucao(novosPontosEscolhidos);
                    if (verificarRestricao(novaSolucao)) {
                        pilhaIndice.push(indice + 1);
                        pilhaPontosEscolhidos.push(novosPontosEscolhidos);
                    }
                }

                // Não adicionando pontoCandidato
                pilhaIndice.push(indice + 1);
                pilhaPontosEscolhidos.push(pontosEscolhidos);
            }
        }
    }
}
