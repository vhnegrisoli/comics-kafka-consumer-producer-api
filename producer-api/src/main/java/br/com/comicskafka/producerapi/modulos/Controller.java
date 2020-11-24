package br.com.comicskafka.producerapi.modulos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Controller {

    @Autowired
    private ComicsSender sender;

    @GetMapping
    public void enviarDados() {
        sender.enviarComics(ComicsDTO
            .builder()
            .nome("Superman")
            .alinhamento("Bem")
            .identidade("Secreta")
            .sexo("Masculino")
            .situacaoVida("Vivo")
            .totalAparicoes(16156L)
            .build());
    }
}
