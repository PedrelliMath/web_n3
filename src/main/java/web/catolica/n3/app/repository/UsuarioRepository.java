package web.catolica.n3.app.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.catolica.n3.app.schemas.UsuarioSchema;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioSchema, UUID> {
    Optional<UsuarioSchema> findByCpf(String cpf);
    Optional<UsuarioSchema> findByEmail(String email);
}
