package paa.tp.visao;

import paa.tp.controlador.ControladorJanela;
import paa.tp.modelo.PontoCandidato;
import paa.tp.modelo.algoritmo.otimizacao.Solucao;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static paa.tp.visao.GerarCor.gerarCor;


/**
 * Janela principal do programa.
 */
public class JanelaPrincipal extends JFrame
{
    /**
     * Barra de menus da janela principal.
     */
    private final class BarraMenus extends JMenuBar
    {
        // Menus
        /**
         * Menu 'Dados'.
         */
        private final JMenu jMenuDados = new JMenu("Dados");

        /**
         * Menu 'Parâmetros'.
         */
        private final JMenu jMenuParametros = new JMenu("Parâmetros");

        /**
         * Menu 'Executar'.
         */
        private final JMenu jMenuExecutar = new JMenu("Executar");

        // Itens do menu Arquivos
        /**
         * Item 'Abrir' de 'Dados'.
         */
        private final JMenuItem jMenuItemAbrirArquivo = new JMenuItem("Abrir");

        /**
         * Item 'Salvar' de 'Dados'.
         */
        private final JMenuItem jMenuItemSalvarArquivo = new JMenuItem("Salvar");

        // Itens do menu Dados
        /**
         * Item 'Gerar' de 'Dados'.
         */
        private final JMenuItem jMenuItemGerarDadosEntrada = new JMenuItem("Gerar aleatoriamente");

        /**
         * Item 'Distância Mínima' de 'Parâmetros'.
         */
        private final JMenuItem jMenuItemDistanciaMinima = new JMenuItem("Distancia Minima");

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
            jMenuDados.add(jMenuItemAbrirArquivo);
            jMenuDados.add(jMenuItemSalvarArquivo);
            jMenuDados.add(jMenuItemGerarDadosEntrada);

            jMenuParametros.add(jMenuItemDistanciaMinima);

            jMenuExecutar.add(jMenuItemExecutarForcaBruta);
            jMenuExecutar.add(jMenuItemExecutarBranchAndBound);

            // Adicionando menu
            add(jMenuDados);
            add(jMenuParametros);
            add(jMenuExecutar);

            // Adicionando action listener nos itens do menu
            jMenuItemAbrirArquivo.addActionListener(actionEvent -> controladorJanela.abrirArquivo());
            jMenuItemSalvarArquivo.addActionListener(actionEvent -> controladorJanela.salvarArquivo());
            jMenuItemGerarDadosEntrada.addActionListener(actionEvent -> controladorJanela.gerarDadosAleatorios());
            jMenuItemDistanciaMinima.addActionListener(actionEvent -> controladorJanela.obterDistanciaMinima());
            jMenuItemExecutarForcaBruta.addActionListener(actionEvent -> controladorJanela.executarForcaBruta());
            jMenuItemExecutarBranchAndBound.addActionListener(actionEvent -> controladorJanela.executarBranchAndBound());
        }
    }

    /**
     * Area de desenho.
     */
    public final class AreaDesenho extends JPanel
    {
        /**
         * Desenha os pontos no JPanel.
         * @param g Objeto gráfico do Java.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            final Solucao solucao = controladorJanela.getSolucao();
            if (solucao != null) {
                final List<PontoCandidato> pontosCandidatosEscolhidos = solucao.getPontosCandidatosEscolhidos();

                // Franquias
                final ArrayList<Integer> franquias = new ArrayList<>();

                // Posição dos pontos
                int posicaoMenorHorizontalPontos = pontosCandidatosEscolhidos.get(0).getCoordenadaX();
                int posicaoMaiorHorizontalPontos = pontosCandidatosEscolhidos.get(0).getCoordenadaX();
                int posicaoMenorVerticalPontos = pontosCandidatosEscolhidos.get(0).getCoordenadaY();
                int posicaoMaiorVerticalPontos = pontosCandidatosEscolhidos.get(0).getCoordenadaY();

                // Obtendo a fronteira dos pontos e as franquias
                for (final PontoCandidato p : pontosCandidatosEscolhidos) {
                    if (p.getCoordenadaY() < posicaoMenorVerticalPontos)
                        posicaoMenorVerticalPontos = p.getCoordenadaY();
                    if (p.getCoordenadaY() > posicaoMaiorVerticalPontos)
                        posicaoMaiorVerticalPontos = p.getCoordenadaY();
                    if (p.getCoordenadaX() < posicaoMenorHorizontalPontos)
                        posicaoMenorHorizontalPontos = p.getCoordenadaX();
                    if (p.getCoordenadaX() > posicaoMaiorHorizontalPontos)
                        posicaoMaiorHorizontalPontos = p.getCoordenadaX();
                    if (!franquias.contains(p.getNumeroFranquia()))
                        franquias.add(p.getNumeroFranquia());
                }

                // Posição do painel
                final int posicaoMenorVerticalPainel = 0;
                final int posicaoMenorHorizontalPainel = 0;
                final int posicaoMaiorHorizontalPainel = this.getWidth();
                final int posicaoMaiorVerticalPainel = this.getHeight();

                // Altura e largura do painel e dos pontos
                final double larguraPainel = posicaoMaiorHorizontalPainel - posicaoMenorHorizontalPainel;
                final double alturaPainel = posicaoMaiorVerticalPainel - posicaoMenorVerticalPainel;
                final double larguraPontos = posicaoMaiorHorizontalPontos - posicaoMenorHorizontalPontos;
                final double alturaPontos = posicaoMaiorVerticalPontos - posicaoMenorVerticalPontos;

                // Desenhando os pontos
                for (final PontoCandidato p: pontosCandidatosEscolhidos) {

                    // Obtendo a cor
                    final Color cor = gerarCor(p.getNumeroFranquia());
                    g.setColor(cor);

                    // Obtendo as posições
                    final int posicaoX = (int) Math.floor(larguraPainel * (p.getCoordenadaX() - posicaoMenorHorizontalPontos) / larguraPontos);
                    final int posicaoY = (int) Math.floor(alturaPainel * (p.getCoordenadaY() - posicaoMenorVerticalPontos) / alturaPontos);

                    // Desenhando os pontos
                    g.fillOval(posicaoX, posicaoY, 10, 10);
                }
            }
        }
    }

    /**
     * Controlador de janela que instanciou esta classe.
     */
    private final ControladorJanela controladorJanela;

    /**
     * Área onde será desenhado os pontos.
     */
    private final AreaDesenho areaDesenho = new AreaDesenho();

    /**
     * Constrói uma nova instância da JanelaPrincipal.
     */
    public JanelaPrincipal(final ControladorJanela controladorJanela) {
        super("Trabalho Prático - Projeto e Análise de Algoritmos");
        this.controladorJanela = controladorJanela;
        setJMenuBar(new BarraMenus());
        add(areaDesenho);
    }

    /**
     * Obtém a área de desenho.
     * @return Área de desenho.
     */
    public AreaDesenho getAreaDesenho() {
        return areaDesenho;
    }
}
