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
    private double menorDistancia = Double.POSITIVE_INFINITY;

    /**
     * Constrói uma nova instância da classe Solucao.
     * @param pontosCandidatosEscolhidos Pontos candidatos escolhidos para resolver o problema.
     */
    public Solucao(final List<PontoCandidato> pontosCandidatosEscolhidos) {
        this.pontosCandidatosEscolhidos = pontosCandidatosEscolhidos;
        gerarCustoTotal();
        gerarMenorDistancia();
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
    private void gerarMenorDistancia() {
        for (int i = 0; i < pontosCandidatosEscolhidos.size(); i++)
            for (int j = 0; j < pontosCandidatosEscolhidos.size(); j++)
                if (i != j)
                {
                    final double distancia = Math.sqrt(Math.pow(pontosCandidatosEscolhidos.get(i).getCoordenadaX() - pontosCandidatosEscolhidos.get(j).getCoordenadaX(), 2) + Math.pow(pontosCandidatosEscolhidos.get(i).getCoordenadaY() - pontosCandidatosEscolhidos.get(j).getCoordenadaY(), 2));
                    if (distancia < menorDistancia)
                        menorDistancia = distancia;
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
    public double getMenorDistancia() {
        return menorDistancia;
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
                ", distanciaMinima=" + menorDistancia +
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
        if (custoTotal != solucao.custoTotal || Double.compare(menorDistancia, solucao.menorDistancia) != 0 || pontosCandidatosEscolhidos.size() != solucao.pontosCandidatosEscolhidos.size())
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
        return Objects.hash(pontosCandidatosEscolhidos, custoTotal, menorDistancia);
    }

    /**
     * Verifica se contém apenas um ponto candidato por franquia.
     * @return Se contém apenas um ponto candidato por franquia.
     */
    public boolean contemApenasUmPontoCandidatoPorFranquia() {

        // Criando array para contar a quantidade de pontos candidatos por franquias
        final ArrayList<Integer> franquias = new ArrayList<>();

        // Verificando se o número da franquia não se repete
        for (final PontoCandidato pontoCandidato: pontosCandidatosEscolhidos)
            if (franquias.contains(pontoCandidato.getNumeroFranquia()))
                return false;
            else
                franquias.add(pontoCandidato.getNumeroFranquia());

        return true;
    }

    /**
     * Obtém a quantidade de franquias.
     * @return Quantidade de franquias.
     */
    public int getQuantidadeFranquia() {

        // Criando array para contar a quantidade de pontos
        final ArrayList<Integer> franquias = new ArrayList<>();

        // Contando a quantidade de pontos
        for (final PontoCandidato pontoCandidato: pontosCandidatosEscolhidos)
            if (!franquias.contains(pontoCandidato.getNumeroFranquia()))
                franquias.add(pontoCandidato.getNumeroFranquia());

        return franquias.size();
    }
}