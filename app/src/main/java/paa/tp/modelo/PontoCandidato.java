package paa.tp.modelo;

import java.io.Serializable;

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
}
