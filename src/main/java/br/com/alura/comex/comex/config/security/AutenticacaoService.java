package br.com.alura.comex.comex.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.comex.comex.models.Usuario;
import br.com.alura.comex.comex.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Dados inválidos para autenticação!");
        }

        return usuario.get();
    }

}
