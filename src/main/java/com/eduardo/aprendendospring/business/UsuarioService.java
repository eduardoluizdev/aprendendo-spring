package com.eduardo.aprendendospring.business;

import com.eduardo.aprendendospring.infrastructure.entity.Usuario;
import com.eduardo.aprendendospring.infrastructure.exceptions.ConflictException;
import com.eduardo.aprendendospring.infrastructure.exceptions.ResourceNotFoundException;
import com.eduardo.aprendendospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvaUsuario(Usuario usuario) {
        try {
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictException("Email ja existente" + email);
            }
        } catch(ConflictException e){
            throw new ConflictException("Email ja nao existente" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(
                "Email não econtrado" + email
        ));
    }

    public void deletarUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}
