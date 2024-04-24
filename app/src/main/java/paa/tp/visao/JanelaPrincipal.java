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
        private final JMenu jMenuArquivo = new JMenu("Arquivo");

        /**
         * Menu 'Dados'.
         */
        private final JMenu jMenuDados = new JMenu("Dados");

        /**
         * Menu 'Executar'.
         */
        private final JMenu jMenuExecutar = new JMenu("Executar");

        // Itens do menu Arquivos
        /**
         * Item 'Abrir' de 'Arquivo'.
         */
        private final JMenuItem jMenuItemAbrirArquivo = new JMenuItem("Abrir");

        /**
         * Item 'Salvar' de 'Arquivo'.
         */
        private final JMenuItem jMenuItemSalvarArquivo = new JMenuItem("Salvar");

        /**
         * Item 'Gerar' de 'Dados'.
         */
        private final JMenuItem jMenuItemGerarDados = new JMenuItem("Gerar");

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
            jMenuArquivo.add(jMenuItemAbrirArquivo);
            jMenuArquivo.add(jMenuItemSalvarArquivo);
            jMenuDados.add(jMenuItemGerarDados);
            jMenuExecutar.add(jMenuItemExecutarForcaBruta);
            jMenuExecutar.add(jMenuItemExecutarBranchAndBound);

            // Adicionando menu
            add(jMenuArquivo);
            add(jMenuDados);
            add(jMenuExecutar);
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
