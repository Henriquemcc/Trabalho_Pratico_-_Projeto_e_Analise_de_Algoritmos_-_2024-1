package paa.tp.visao;

import java.awt.*;

/**
 * Classe que armazena todas os métodos estáticos utilizados para a construção de interfaces gráficas.
 */
public class FuncoesGraficas {

    /**
     * Obtém a resolução 
     * @return Resolução da tela.
     */
    public static Dimension obterResolucaoTela()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
