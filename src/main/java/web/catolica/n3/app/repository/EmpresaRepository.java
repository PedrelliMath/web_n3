package web.catolica.n3.app.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.catolica.n3.app.schemas.EmpresaSchema;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaSchema, UUID> {
    List<EmpresaSchema> findByUsuarioId(UUID userId);

    Optional<EmpresaSchema> findByCnpj(String cnpj);
}
