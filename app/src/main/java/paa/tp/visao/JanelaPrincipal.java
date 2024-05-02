package paa.tp.visao;

import paa.tp.controlador.ControladorJanela;
import paa.tp.modelo.PontoCandidato;
import paa.tp.modelo.algoritmo.otimizacao.Solucao;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        // Itens do menu Dados
        /**
         * Item 'Gerar' de 'Dados'.
         */
        private final JMenuItem jMenuItemGerarDadosEntrada = new JMenuItem("Gerar dados de entrada aleatoriamente");

        /**
         * Item 'Distância Mínima' de 'Dados'.
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
            jMenuArquivo.add(jMenuItemAbrirArquivo);
            jMenuArquivo.add(jMenuItemSalvarArquivo);
            jMenuDados.add(jMenuItemGerarDadosEntrada);
            jMenuDados.add(jMenuItemDistanciaMinima);
            jMenuExecutar.add(jMenuItemExecutarForcaBruta);
            jMenuExecutar.add(jMenuItemExecutarBranchAndBound);

            // Adicionando menu
            add(jMenuArquivo);
            add(jMenuDados);
            add(jMenuExecutar);

            // Adicionando action listener nos itens do menu
            jMenuItemAbrirArquivo.addActionListener(actionEvent -> controladorJanela.abrirArquivo());
            jMenuItemSalvarArquivo.addActionListener(actionEvent -> controladorJanela.salvarArquivo());
            jMenuItemGerarDadosEntrada.addActionListener(actionEvent -> controladorJanela.gerarDadosAleatorios());
            jMenuItemDistanciaMinima.addActionListener(actionEvent -> controladorJanela.obterDistanciaMinima());
            jMenuItemExecutarForcaBruta.addActionListener(actionEvent -> controladorJanela.executarForcaBruta());
        }
    }

    /**
     * Area de desenho.
     */
    public final class AreaDesenho extends JPanel
    {
        /**
         * Número inteiro gerado aleatóriamente a ser utilizado para a geração da seed.
         */
        private final long randomInteger = (new SecureRandom()).nextInt();

        /**
         * Gera uma cor com base o índice e na divisão do espectro de cores.
         * @param indice Índice para ser utilizado como seed do random.
         * @return Cor gerada com base nas variáveis índice e divisão.
         */
        private Color gerarCor(final int indice) {

            // Random a ser utilizado na geração do RGB das cores
            final Random random = new Random(indice + randomInteger);

            // Gerando o RGB
            final int vermelho = random.nextInt(255);
            final int verde = random.nextInt(255);
            final int azul = random.nextInt(255);

            // Criando e retornando a nova cor
            return new Color(vermelho, verde, azul);
        }

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
                    final int indiceCor = franquias.indexOf(p.getNumeroFranquia()) + 1;
                    final Color cor = gerarCor(indiceCor);
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
