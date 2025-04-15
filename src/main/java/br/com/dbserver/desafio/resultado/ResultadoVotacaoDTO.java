package br.com.dbserver.desafio.resultado;

public record ResultadoVotacaoDTO(long totalSim, long totalNao, String resultado) {

    public ResultadoVotacaoDTO(long totalSim, long totalNao) {
        this(totalSim, totalNao,
                totalSim > totalNao ? "APROVADA" :
                totalSim < totalNao ? "REJEITADA" : "EMPATE");
    }
}
