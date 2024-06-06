package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.*;

/**
 * Uma Solução do problema de otimização.
 */
public class Solucao {
    /**
     * Lista de pontos candidatos que foram escolhidos
     */
    private final List<PontoCandidato> pontosCandidatosEscolhidos;

    /**
     * Custo total dessa solução.
     */
    private int custoTotal = 0;

    /**
     * Distância mínima entre duas filiais dessa solução.
     */
    private double distanciaMinima = Double.POSITIVE_INFINITY;

    /**
     * Constrói uma nova instância da classe Solucao.
     * @param pontosCandidatosEscolhidos Pontos candidatos escolhidos para resolver o problema.
     */
    public Solucao(final List<PontoCandidato> pontosCandidatosEscolhidos) {
        this.pontosCandidatosEscolhidos = pontosCandidatosEscolhidos;
        final Comparator<PontoCandidato> comparatorPontoCandidato = new Comparator<PontoCandidato>() {
            @Override
            public int compare(PontoCandidato pontoCandidato, PontoCandidato t1) {
                return Integer.compare(pontoCandidato.getCustoInstalacao(), t1.getCustoInstalacao());
            }
        };
        pontosCandidatosEscolhidos.sort(comparatorPontoCandidato);
        gerarCustoTotal();
        gerarDistanciaMinima();
    }

    /**
     * Gera o valor da variável custo total.
     */
    private void gerarCustoTotal() {
        for (PontoCandidato pontoCandidato: pontosCandidatosEscolhidos)
            custoTotal += pontoCandidato.getCustoInstalacao();
    }

    /**
     * Gera o valor da variável distância mínima.
     */
    private void gerarDistanciaMinima() {
        for (int i = 0; i < pontosCandidatosEscolhidos.size(); i++)
            for (int j = 0; j < pontosCandidatosEscolhidos.size(); j++)
                if (i != j)
                {
                    final double distancia = Math.sqrt(Math.pow(pontosCandidatosEscolhidos.get(i).getCoordenadaX() - pontosCandidatosEscolhidos.get(j).getCoordenadaX(), 2) + Math.pow(pontosCandidatosEscolhidos.get(i).getCoordenadaY() - pontosCandidatosEscolhidos.get(j).getCoordenadaY(), 2));
                    if (distancia < distanciaMinima)
                        distanciaMinima = distancia;
                }
    }

    /**
     * Verifica se contém apenas um ponto candidato por franquia.
     * @return Se contém apenas um ponto candidato por franquia.
     */
    public boolean contemApenasUmPontoCandidatoPorFranquia() {

        // Criando array para contar a quantidade de pontos candidatos por franquias
        final Hashtable<Integer, Integer> franquias = new Hashtable<>();

        // Contando a quantidade de pontos candidatos por franquia
        for (final PontoCandidato pontoCandidato: pontosCandidatosEscolhidos)
            franquias.merge(pontoCandidato.getNumeroFranquia(), 1, Integer::sum);

        // Verificando se existe mais de um ponto candidato por franquia
        final Enumeration<Integer> chaves = franquias.keys();
        while (chaves.hasMoreElements()) {
            if (franquias.get(chaves.nextElement()) > 1)
                return false;
        }

        return true;
    }

    /**
     * Obtém os pontos candidatos escolhidos.
     * @return Pontos candidatos escolhidos.
     */
    public List<PontoCandidato> getPontosCandidatosEscolhidos() {
        return pontosCandidatosEscolhidos;
    }

    /**
     * Obtém o custo total.
     * @return Custo total.
     */
    public int getCustoTotal() {
        return custoTotal;
    }

    /**
     * Obtém a distância mínima.
     * @return Distância mínima.
     */
    public double getMenorDistancia() {
        return distanciaMinima;
    }

    /**
     * Obtém a quantidade de pontos escolhidos.
     * @return Quantidade de pontos escolhidos.
     */
    public int getQuantidadePontos() {
        return pontosCandidatosEscolhidos.size();
    }

    /**
     * Converte a solução em uma string.
     * @return String com todos os dados da solução.
     */
    @Override
    public String toString() {
        return "Solucao{" +
                "pontosCandidatosEscolhidos=" + pontosCandidatosEscolhidos +
                ", custoTotal=" + custoTotal +
                ", distanciaMinima=" + distanciaMinima +
                '}';
    }

    /**
     * Verifica se dois objetos são iguais.
     * @param o Objeto a ser comparado com este.
     * @return Verificação de dois objetos são iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solucao solucao = (Solucao) o;
        boolean equals = true;
        if (custoTotal != solucao.custoTotal || Double.compare(distanciaMinima, solucao.distanciaMinima) != 0 || pontosCandidatosEscolhidos.size() != solucao.pontosCandidatosEscolhidos.size())
            equals = false;
        else for (int i = 0; i < pontosCandidatosEscolhidos.size(); i++) {
            if (!pontosCandidatosEscolhidos.get(i).equals(solucao.getPontosCandidatosEscolhidos().get(i))) {
                equals = false;
                break;
            }
        }
        return equals;
    }

    /**
     * Gera o código hash da Solução.
     * @return Código hash da Solução.
     */
    @Override
    public int hashCode() {
        return Objects.hash(pontosCandidatosEscolhidos, custoTotal, distanciaMinima);
    }
}