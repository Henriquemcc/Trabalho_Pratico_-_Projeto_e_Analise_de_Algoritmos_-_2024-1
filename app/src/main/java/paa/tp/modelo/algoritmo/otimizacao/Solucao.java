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
    private final Hashtable<Integer, PontoCandidato> pontosCandidatosEscolhidos;

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
    public Solucao(final Hashtable<Integer, PontoCandidato> pontosCandidatosEscolhidos) {

        this.pontosCandidatosEscolhidos = pontosCandidatosEscolhidos;
        gerarCustoTotal();
        gerarDistanciaMinima();
    }

    /**
     * Gera o valor da variável custo total.
     */
    private void gerarCustoTotal() {
        final Enumeration<Integer> chaves = pontosCandidatosEscolhidos.keys();
        while (chaves.hasMoreElements()) {
            custoTotal += pontosCandidatosEscolhidos.get(chaves.nextElement()).getCustoInstalacao();
        }
    }

    /**
     * Gera o valor da variável distância mínima.
     */
    private void gerarDistanciaMinima() {

        final ArrayList<PontoCandidato> array = new ArrayList<>(pontosCandidatosEscolhidos.size());
        final Enumeration<Integer> keys = pontosCandidatosEscolhidos.keys();
        while (keys.hasMoreElements()) {
            array.add(pontosCandidatosEscolhidos.get(keys.nextElement()));
        }

        for (int i = 0; i < array.size(); i++)
            for (int j = i+1; j < array.size(); j++)
            {
                final double distancia = Math.sqrt(Math.pow(array.get(i).getCoordenadaX() - array.get(j).getCoordenadaX(), 2) + Math.pow(array.get(i).getCoordenadaY() - array.get(j).getCoordenadaY(), 2));
                if (distancia < distanciaMinima)
                    distanciaMinima = distancia;
            }
    }

    /**
     * Obtém os pontos candidatos escolhidos.
     * @return Pontos candidatos escolhidos.
     */
    public Hashtable<Integer, PontoCandidato> getPontosCandidatosEscolhidos() {
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
     * Obtém a quantidade de pontos escolhidos.
     * @return Quantidade de pontos escolhidos.
     */
    public int getQuantidadePontos() {
        return pontosCandidatosEscolhidos.keySet().size();
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

    /**
     * Converte os pontos candidatos de um HashTable para uma lista
     * @return Lista dos pontos candidatos.
     */
    public List<PontoCandidato> pontosCandidatosToList() {
        final List<PontoCandidato> lista = new ArrayList<>(pontosCandidatosEscolhidos.size());
        final Enumeration<Integer> keys = pontosCandidatosEscolhidos.keys();
        while (keys.hasMoreElements()) {
            lista.add(pontosCandidatosEscolhidos.get(keys.nextElement()));
        }
        return lista;
    }
}