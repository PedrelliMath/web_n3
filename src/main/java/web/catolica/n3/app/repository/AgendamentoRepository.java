package web.catolica.n3.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.catolica.n3.app.schemas.AgendamentoSchema;

@Repository
public interface AgendamentoRepository
    extends JpaRepository<AgendamentoSchema, UUID> {
    List<AgendamentoSchema> findByUsuarioId(UUID userId);

    List<AgendamentoSchema> findByServicoId(UUID servicoId);

    List<AgendamentoSchema> findByData(LocalDate data);

    List<AgendamentoSchema> findByUsuarioIdAndData(UUID userId, LocalDate data);
}
