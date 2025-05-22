package web.catolica.n3.app.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.catolica.n3.app.dto.request.EmpresaDtoRequest;
import web.catolica.n3.app.dto.response.EmpresaDtoResponse;
import web.catolica.n3.app.mapper.EmpresaMapper;
import web.catolica.n3.app.repository.EmpresaRepository;
import web.catolica.n3.app.repository.UsuarioRepository;
import web.catolica.n3.app.schemas.EmpresaSchema;
import web.catolica.n3.app.schemas.UsuarioSchema;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EmpresaService(
        EmpresaRepository empresaRepository,
        UsuarioRepository usuarioRepository
    ) {
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public EmpresaDtoResponse criarEmpresa(EmpresaDtoRequest dto) {
        UUID user_id = dto.userId();
        UsuarioSchema usuario = usuarioRepository
            .findById(user_id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Usuário não encontrado com ID: " + user_id
                )
            );

        EmpresaSchema entidade = EmpresaMapper.toEntity(dto, usuario);
        return EmpresaMapper.toDTO(empresaRepository.save(entidade));
    }

    public List<EmpresaDtoResponse> listarEmpresas() {
        return empresaRepository
            .findAll()
            .stream()
            .map(EmpresaMapper::toDTO)
            .toList();
    }

    public EmpresaDtoResponse buscarPorId(UUID id) {
        EmpresaSchema entidade = empresaRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Empresa não encontrada com ID: " + id
                )
            );

        return EmpresaMapper.toDTO(entidade);
    }

    public EmpresaDtoResponse buscarPorCnpj(String cnpj) {
        EmpresaSchema entidade = empresaRepository
            .findByCnpj(cnpj)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Empresa não encontrada com cnpj: " + cnpj
                )
            );

        return EmpresaMapper.toDTO(entidade);
    }

    public List<EmpresaDtoResponse> listarPorUsuario(UUID userId) {
        return empresaRepository
            .findByUsuarioId(userId)
            .stream()
            .map(EmpresaMapper::toDTO)
            .toList();
    }

    public EmpresaDtoResponse atualizarEmpresa(
        UUID id,
        EmpresaDtoRequest empresaAtualizada
    ) {
        EmpresaSchema empresa = empresaRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Empresa não encontrada com ID: " + id
                )
            );

        empresa.setNome(empresaAtualizada.nome());
        empresa.setCnpj(empresaAtualizada.cnpj());
        empresa.setInicioExpediente(empresaAtualizada.inicioExpediente());
        empresa.setFimExpediente(empresaAtualizada.fimExpediente());

        return EmpresaMapper.toDTO(empresaRepository.save(empresa));
    }

    public void deletarEmpresa(UUID id) {
        EmpresaSchema empresa = empresaRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Empresa não encontrada com ID: " + id
                )
            );
        empresaRepository.delete(empresa);
    }
}
