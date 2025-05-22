package web.catolica.n3.app.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.catolica.n3.app.dto.request.ServicoDtoRequest;
import web.catolica.n3.app.dto.response.ServicoDtoResponse;
import web.catolica.n3.app.mapper.ServicoMapper;
import web.catolica.n3.app.repository.EmpresaRepository;
import web.catolica.n3.app.repository.ServicoRepository;
import web.catolica.n3.app.schemas.EmpresaSchema;
import web.catolica.n3.app.schemas.ServicoSchema;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final EmpresaRepository empresaRepository;

    @Autowired
    public ServicoService(
        ServicoRepository servicoRepository,
        EmpresaRepository empresaRepository
    ) {
        this.servicoRepository = servicoRepository;
        this.empresaRepository = empresaRepository;
    }

    public ServicoDtoResponse criarServico(ServicoDtoRequest dto) {
        EmpresaSchema empresa = empresaRepository
            .findById(dto.empresaId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Empresa não encontrada com ID: " + dto.empresaId()
                )
            );

        ServicoSchema entidade = ServicoMapper.toEntity(dto);
        entidade.setEmpresa(empresa);

        entidade = servicoRepository.save(entidade);

        return ServicoMapper.toDTO(entidade);
    }

    public List<ServicoDtoResponse> listarServicos() {
        return servicoRepository
            .findAll()
            .stream()
            .map(ServicoMapper::toDTO)
            .toList();
    }

    public ServicoDtoResponse buscarPorId(UUID id) {
        ServicoSchema entidade = servicoRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Serviço não encontrado com ID: " + id
                )
            );

        return ServicoMapper.toDTO(entidade);
    }

    public List<ServicoDtoResponse> listarPorEmpresa(UUID empresaId) {
        return servicoRepository
            .findByEmpresaId(empresaId)
            .stream()
            .map(ServicoMapper::toDTO)
            .toList();
    }

    public ServicoDtoResponse atualizarServico(UUID id, ServicoDtoRequest dto) {
        ServicoSchema entidade = servicoRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Serviço não encontrado com ID: " + id
                )
            );

        EmpresaSchema empresa = empresaRepository
            .findById(dto.empresaId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Empresa não encontrada com ID: " + dto.empresaId()
                )
            );

        entidade.setNome(dto.nome());
        entidade.setValor(dto.valor());
        entidade.setDuracao(dto.duracao());
        entidade.setEmpresa(empresa);

        entidade = servicoRepository.save(entidade);

        return ServicoMapper.toDTO(entidade);
    }

    public void deletarServico(UUID id) {
        ServicoSchema entidade = servicoRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Serviço não encontrado com ID: " + id
                )
            );

        servicoRepository.delete(entidade);
    }
}
