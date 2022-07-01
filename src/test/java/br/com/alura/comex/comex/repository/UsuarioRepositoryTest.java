package br.com.alura.comex.comex.repository;

import br.com.alura.comex.comex.models.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void shouldReturnAUserByEmail() {
        String email = "teste@teste.com";
        Optional<Usuario> user = usuarioRepository.findByEmail(email);

        Assertions.assertNotNull(user.get());
        Assertions.assertEquals(user.get().getEmail(), "teste@teste.com");
    }
}
