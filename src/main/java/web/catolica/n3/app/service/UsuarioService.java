package web.catolica.n3.app.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.catolica.n3.app.dto.request.UsuarioDtoRequest;
import web.catolica.n3.app.dto.response.UsuarioDtoResponse;
import web.catolica.n3.app.mapper.UsuarioMapper;
import web.catolica.n3.app.repository.UsuarioRepository;
import web.catolica.n3.app.schemas.UsuarioSchema;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDtoResponse criarUsuario(UsuarioDtoRequest dto) {
        UsuarioSchema entidade = UsuarioMapper.toEntity(dto);
        entidade = usuarioRepository.save(entidade);
        return UsuarioMapper.toDTO(entidade);
    }

    public List<UsuarioDtoResponse> listarUsuarios() {
        return usuarioRepository
            .findAll()
            .stream()
            .map(UsuarioMapper::toDTO)
            .toList();
    }

    public UsuarioDtoResponse buscarPorId(UUID id) {
        UsuarioSchema entidade = usuarioRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Usuário não encontrado com ID: " + id
                )
            );
        return UsuarioMapper.toDTO(entidade);
    }

    public UsuarioDtoResponse buscarPorCpf(String cpf) {
        UsuarioSchema entidade = usuarioRepository
            .findByCpf(cpf)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Usuário não encontrado com CPF: " + cpf
                )
            );
        return UsuarioMapper.toDTO(entidade);
    }

    public UsuarioDtoResponse buscarPorEmail(String email) {
        UsuarioSchema entidade = usuarioRepository
            .findByEmail(email)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Usuário não encontrado com Email: " + email
                )
            );
        return UsuarioMapper.toDTO(entidade);
    }

    public UsuarioDtoResponse atualizarUsuario(
        UUID id,
        UsuarioDtoRequest usuarioAtualizado
    ) {
        UsuarioSchema entidade = usuarioRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Usuário não encontrado com ID: " + id
                )
            );

        entidade.setName(usuarioAtualizado.name());
        entidade.setCpf(usuarioAtualizado.cpf());
        entidade.setEmail(usuarioAtualizado.email());

        entidade = usuarioRepository.save(entidade);
        return UsuarioMapper.toDTO(entidade);
    }

    public void deletarUsuario(UUID id) {
        UsuarioSchema entidade = usuarioRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Usuário não encontrado com ID: " + id
                )
            );
        usuarioRepository.delete(entidade);
    }
}
