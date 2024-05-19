package paa.tp.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que armazena todos os dados de um ponto candidato.
 */
public class PontoCandidato implements Serializable {

    /**
     * Número da franquia.
     */
    private final int numeroFranquia;

    /**
     * Coordenada X.
     */
    private final int coordenadaX;

    /**
     * Coordenada Y.
     */
    private final int coordenadaY;

    /**
     * Custo de instalação.
     */
    private final int custoInstalacao;

    /**
     * Constrói uma nova instância de PontoCandidato.
     * @param numeroFranquia Número da franquia.
     * @param coordenadaX Coordenada X.
     * @param coordenadaY Coordenada Y.
     * @param custoInstalacao Custo de instalação.
     */
    public PontoCandidato(final int numeroFranquia, final int coordenadaX, final int coordenadaY, final int custoInstalacao)
    {
        this.numeroFranquia = numeroFranquia;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * Obtém o número da franquia.
     * @return Número da franquia.
     */
    public int getNumeroFranquia() {
        return numeroFranquia;
    }

    /**
     * Obtém a coordenada X.
     * @return Coordenada X.
     */
    public int getCoordenadaX() {
        return coordenadaX;
    }

    /**
     * Obtém a coordenada Y.
     * @return Coordenada Y.
     */
    public int getCoordenadaY() {
        return coordenadaY;
    }

    /**
     * Obtém o custo de instalação.
     * @return Custa de instalação.
     */
    public int getCustoInstalacao() {
        return custoInstalacao;
    }

    /**
     * Converte o ponto candidato em uma string.
     * @return String com as informações do ponto candidato.
     */
    @Override
    public String toString() {
        return "PontoCandidato{" +
                "numeroFranquia=" + numeroFranquia +
                ", coordenadaX=" + coordenadaX +
                ", coordenadaY=" + coordenadaY +
                ", custoInstalacao=" + custoInstalacao +
                '}';
    }

    /**
     * Verifica se dois pontos candidatos são iguais.
     * @param o Outro ponto candidato a ser comparado com este.
     * @return Valor booleano indicando se os dois pontos são iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PontoCandidato that = (PontoCandidato) o;
        return numeroFranquia == that.numeroFranquia && coordenadaX == that.coordenadaX && coordenadaY == that.coordenadaY && custoInstalacao == that.custoInstalacao;
    }

    /**
     * Obtém o código hash do Ponto Candidato.
     * @return Código hash do Ponto Candidato.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numeroFranquia, coordenadaX, coordenadaY, custoInstalacao);
    }
}
