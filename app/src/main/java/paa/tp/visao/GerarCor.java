package paa.tp.visao;

import java.awt.*;
import java.util.Random;

/**
 * Classe que contém o método estático gerar cor.
 */
public class GerarCor {
    /**
     * Gera uma cor com base o índice e na divisão do espectro de cores.
     * @param indice Índice para ser utilizado como seed do random.
     * @return Cor gerada com base nas variáveis índice e divisão.
     */
    public static Color gerarCor(final int indice) {

        // Random a ser utilizado na geração do RGB das cores
        final Random random = new Random(indice);

        // Gerando o RGB
        final int vermelho = random.nextInt(255);
        final int verde = random.nextInt(255);
        final int azul = random.nextInt(255);

        // Criando e retornando a nova cor
        return new Color(vermelho, verde, azul);
    }
}
