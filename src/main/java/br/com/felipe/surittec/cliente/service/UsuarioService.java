package br.com.felipe.surittec.cliente.service;

import br.com.felipe.surittec.cliente.entity.Usuario;
import br.com.felipe.surittec.cliente.repository.IUsuarioRepository;
import br.com.felipe.surittec.cliente.security.UserPrincipal;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@NoArgsConstructor
public class UsuarioService extends ServiceAbstract<Usuario, IUsuarioRepository> implements UserDetailsService {

    @Autowired
    public UsuarioService(IUsuarioRepository repo){
        this.repository = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario usuario = repository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o Login: " + login));

        return UserPrincipal.create(usuario);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserById(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        return UserPrincipal.create(usuario);
    }
}
