package paa.tp.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe que armazena os pontos candidatos.
 */
public class ListaPontosCandidatos {

    /**
     * Array list dos pontos candidatos.
     */
    private ArrayList<PontoCandidato> pontosCandidatos = new ArrayList<>();

    /**
     * Abre arquivo contendo os pontos candidatos.
     * @param arquivo Objeto Arquivo a ser lido.
     * @throws IOException Exceção lançada caso ocorra erro de IO.
     */
    public void abrirArquivo(final File arquivo) throws IOException {
        try(final FileReader fileReader = new FileReader(arquivo)) {
            try(final BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line = bufferedReader.readLine();
                if (line != null)
                    pontosCandidatos.clear();
                while (line != null) {
                    final String[] dadosPontoCandidato = line.split(" ");
                    final PontoCandidato pontoCandidato = new PontoCandidato(Integer.parseInt(dadosPontoCandidato[0]), Integer.parseInt(dadosPontoCandidato[1]), Integer.parseInt(dadosPontoCandidato[2]), Integer.parseInt(dadosPontoCandidato[3]));
                    pontosCandidatos.add(pontoCandidato);
                    line = bufferedReader.readLine();
                }
            }
        }
    }

    /**
     * Salva arquivo contendo pontos candidatos.
     * @param arquivo Objeto Arquivo a ser salvo.
     * @throws IOException Exceção lançada caso ocorra erro de IO.
     */
    public void salvarArquivo(final File arquivo) throws IOException {
        try(final FileWriter fileWriter = new FileWriter(arquivo)) {
            try(final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                for (final PontoCandidato pontoCandidato: pontosCandidatos) {
                    bufferedWriter.write(String.format("%d %d %d %d\n", pontoCandidato.getNumeroFranquia(), pontoCandidato.getCoordenadaX(), pontoCandidato.getCoordenadaY(), pontoCandidato.getCustoInstalacao()));
                }
            }
        }
    }

    /**
     * Gera aleatóriamente um arquivo de dados.
     * @param quantidadeFranquias Quantidade de franquias.
     * @param quantidadeLojasPorFranquia Quantidade de lojas por franquia.
     * @param random Objeto Random para gerar aleatóriamente os dados dos pontos candidatos.
     */
    public void gerarArquivo(final int quantidadeFranquias, final int quantidadeLojasPorFranquia, final Random random)
    {
        final ArrayList<PontoCandidato> pontoCandidatosGerados = new ArrayList<>(quantidadeFranquias * quantidadeLojasPorFranquia);
        for (int franquia = 0; franquia < quantidadeFranquias; franquia++)
        {
            for (int loja = 0; loja < quantidadeLojasPorFranquia; loja++)
            {
                pontoCandidatosGerados.add(new PontoCandidato(franquia, random.nextInt(), random.nextInt(), random.nextInt()));
            }
        }
        pontosCandidatos = pontoCandidatosGerados;
    }

    /**
     * Obtém todos os pontos candidatos.
     * @return Lista com todos os pontos candidatos.
     */
    public List<PontoCandidato> getPontosCandidatos() {
        return pontosCandidatos;
    }
}
