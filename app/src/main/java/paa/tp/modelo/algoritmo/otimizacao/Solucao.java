package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.List;

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
    public double getDistanciaMinima() {
        return distanciaMinima;
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
}