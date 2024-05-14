package paa.tp.visao;

import paa.tp.modelo.PontoCandidato;
import paa.tp.modelo.algoritmo.otimizacao.Solucao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static paa.tp.visao.GerarCor.gerarCor;

/**
 * Janel que mostra os resultados da execução dos algoritmos.
 */
public class JanelaResultados extends JFrame {

    /**
     * Solução do problema a ser exibida.
     */
    private final Solucao solucao;

    /**
     * Tabela utilizada para mostrar os resultados.
     */
    private final JTable jTable;

    /**
     * Scroll Pane que conterá a tabela.
     */
    private final JScrollPane jScrollPane;

    /**
     * Constrói uma nova instância da classe JanelaResultados.
     * @param solucao Solução a ser exibida
     */
    public JanelaResultados(final Solucao solucao) {
        super("Resultado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.solucao = solucao;

        // Criando a tabela
        final String[] nomesColunas = {"Número franquia", "Coordenada X", "Coordenada Y", "Custo", "Cor"};
        final List<PontoCandidato> pontosCandidatosEscolhidos = solucao.getPontosCandidatosEscolhidos();
        final Object[][] dados = new Object[pontosCandidatosEscolhidos.size()][5];
        for (int i = 0; i < pontosCandidatosEscolhidos.size(); i++)
        {
            dados[i][0] = String.valueOf(pontosCandidatosEscolhidos.get(i).getNumeroFranquia());
            dados[i][1] = String.valueOf(pontosCandidatosEscolhidos.get(i).getCoordenadaX());
            dados[i][2] = String.valueOf(pontosCandidatosEscolhidos.get(i).getCoordenadaY());
            dados[i][3] = String.valueOf(pontosCandidatosEscolhidos.get(i).getCustoInstalacao());

            // Gerando ícone da cor
            final BufferedImage bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
            final Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setColor(gerarCor(pontosCandidatosEscolhidos.get(i).getNumeroFranquia()));
            graphics2D.fillRect(0, 0, 16, 16);
            graphics2D.dispose();
            dados[i][4] = new ImageIcon(bufferedImage);
        }
        final DefaultTableModel model = new DefaultTableModel(dados, nomesColunas) {
          public Class getColumnClass(int column) {
              return getValueAt(0, column).getClass();
          }
        };
        jTable = new JTable(model);
        jScrollPane = new JScrollPane(jTable);
        add(jScrollPane);
    }
}
