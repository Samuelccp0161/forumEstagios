package br.edu.facima.forum.services;

import br.edu.facima.forum.model.PublicarAnimal;
import br.edu.facima.forum.repository.PublicarRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PublicarServiceImplTest {
    @Mock
    PublicarRepository publicarRepository;

    @InjectMocks
    PublicarServiceImpl publicarService;

    @Nested
    class AoPublicar{
        @Test
        void a_publicacao_deve_ser_salva(){
            PublicarAnimal publicarAnimal = new PublicarAnimal();

            publicarService.publicar(publicarAnimal);

            verify(publicarRepository).save(publicarAnimal);
        }
    }
}
