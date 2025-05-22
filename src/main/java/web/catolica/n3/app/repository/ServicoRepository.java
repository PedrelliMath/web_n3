package web.catolica.n3.app.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.catolica.n3.app.schemas.ServicoSchema;

@Repository
public interface ServicoRepository extends JpaRepository<ServicoSchema, UUID> {
    List<ServicoSchema> findByEmpresaId(UUID empresaId);
}
