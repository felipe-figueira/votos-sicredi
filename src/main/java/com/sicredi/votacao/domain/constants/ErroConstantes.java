package com.sicredi.votacao.domain.constants;

public final class ErroConstantes {

    private ErroConstantes(){

    }

    public static final String PAUTA_JA_EXISTE = "Pauta com a descrição %s já foi criada";
    public static final String CPF_INVALIDO = "CPF %s inválido";
    public static final String PAUTA_NAO_EXISTE = "Pauta com a descrição %s não existe";
    public static final String ASSOCIADO_JA_EXISTE = "Associado com CPF %s já foi criado";
    public static final String ASSOCIADO_NAO_EXISTE = "Associado com CPF %s não existe";
    public static final String ASSOCIADO_JA_VOTOU = "Associado com CPF %s já votou nesta pauta";
    public static final String SESSAO_VOTACAO_JA_ENCERRADA = "Sessão de votação para a pauta %s já encerrada";
    public static final String SESSAO_VOTACAO_JA_EXISTE = "Sessão de votação para a pauta %s já existe";
    public static final String SESSAO_VOTACAO_PARA_PAUTA_NAO_EXISTE = "Sessão de votação para a pauta %s não existe";
    public static final String SESSAO_VOTACAO_AINDA_ABERTA = "Sessão de votação para a pauta %s ainda está aberta";

}
