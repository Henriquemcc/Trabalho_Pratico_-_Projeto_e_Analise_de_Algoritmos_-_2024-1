package paa.tp.modelo;

import java.io.*;
import java.util.*;

/**
 * Classe que armazena os pontos candidatos.
 */
public class ListaPontosCandidatos {

    /**
     * Array list dos pontos candidatos.
     */
    private Dictionary<Integer, List<PontoCandidato>> pontosCandidatos = new Hashtable<>();

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
                    pontosCandidatos = new Hashtable<>();
                while (line != null) {
                    final String[] dadosPontoCandidato = line.split(" ");
                    final PontoCandidato pontoCandidato = new PontoCandidato(Integer.parseInt(dadosPontoCandidato[0]), Integer.parseInt(dadosPontoCandidato[1]), Integer.parseInt(dadosPontoCandidato[2]), Integer.parseInt(dadosPontoCandidato[3]));
                    pontosCandidatos.get(pontoCandidato.getNumeroFranquia()).add(pontoCandidato);
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
                final Enumeration<Integer> keys = pontosCandidatos.keys();
                while (keys.hasMoreElements()) {
                    final Integer key = keys.nextElement();
                    final List<PontoCandidato> pontoCandidatosMesmaFranquia = pontosCandidatos.get(key);
                    for (final PontoCandidato pontoCandidato : pontoCandidatosMesmaFranquia) {
                        bufferedWriter.write(String.format("%d %d %d %d\n", pontoCandidato.getNumeroFranquia(), pontoCandidato.getCoordenadaX(), pontoCandidato.getCoordenadaY(), pontoCandidato.getCustoInstalacao()));
                    }
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
        pontosCandidatos = new Hashtable<>();
        for (int franquia = 0; franquia < quantidadeFranquias; franquia++)
        {
            pontosCandidatos.put(franquia+1, new ArrayList<>());
            for (int loja = 0; loja < quantidadeLojasPorFranquia; loja++)
            {
                pontosCandidatos.get(franquia+1).add(new PontoCandidato(franquia+1, random.nextInt(500), random.nextInt(500), random.nextInt(1000000)));
            }
        }
    }

    /**
     * Obtém todos os pontos candidatos.
     * @return Dicionário com todos os pontos candidatos.
     */
    public Dictionary<Integer, List<PontoCandidato>> getPontosCandidatos() {
        return pontosCandidatos;
    }
}
