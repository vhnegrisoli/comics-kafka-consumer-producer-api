package br.com.comicskafka.consumerapi.modulos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComicsDTO {

    private String nome;
    private String identidade;
    private String alinhamento;
    private String sexo;
    private String situacaoVida;
    private Long totalAparicoes;
}
