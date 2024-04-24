package paa.tp.visao;

import javax.swing.*;

/**
 * Janela principal do programa.
 */
public class JanelaPrincipal extends JFrame
{
    private static class BarraMenus extends JMenuBar
    {
        // Menus
        /**
         * Menu 'Arquivo'.
         */
        private final JMenu jmenuArquivo = new JMenu("Arquivo");

        /**
         * Menu 'Executar'.
         */
        private final JMenu jmenuExecutar = new JMenu("Executar");

        // Itens do menu Arquivos
        /**
         * Item 'Abrir' de 'Arquivo'.
         */
        private final JMenuItem jMenuItemAbrirArquivo = new JMenuItem("Abrir");

        /**
         * Item 'Gerar' de 'Arquivo'.
         */
        private final JMenuItem jMenuItemGerarArquivo = new JMenuItem("Gerar");

        // Itens do menu Executar
        /**
         * Item 'Força Bruta' de 'Executar'.
         */
        private final JMenuItem jMenuItemExecutarForcaBruta = new JMenuItem("Força bruta");

        /**
         * Item 'Branch-and-Bound' de 'Executar.
         */
        private final JMenuItem jMenuItemExecutarBranchAndBound = new JMenuItem("Branch-and-Bound");

        /**
         * Constrói uma nova instância da barra de menus.
         */
        public BarraMenus() {
            // Adicionando itens ao menu
            jmenuArquivo.add(jMenuItemAbrirArquivo);
            jmenuArquivo.add(jMenuItemGerarArquivo);
            jmenuExecutar.add(jMenuItemExecutarForcaBruta);
            jmenuExecutar.add(jMenuItemExecutarBranchAndBound);

            // Adicionando menu
            add(jmenuArquivo);
            add(jmenuExecutar);
        }
    }

    /**
     * Constrói uma nova instância da JanelaPrincipal.
     */
    public JanelaPrincipal() {
        super("Trabalho Prático - Projeto e Análise de Algoritmos");
        setJMenuBar(new BarraMenus());
    }
}
