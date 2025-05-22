package web.catolica.n3.app.schemas;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class EmpresaSchema {

    @Id
    private UUID id;

    private String cnpj;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsuarioSchema usuario;

    @Column(name = "inicio_expediente")
    private LocalTime inicioExpediente;

    @Column(name = "fim_expediente")
    private LocalTime fimExpediente;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<ServicoSchema> servicos = new ArrayList<>();

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UuidCreator.getTimeOrderedEpoch();
        }
    }

    public UUID getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuarioSchema getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSchema usuario) {
        this.usuario = usuario;
    }

    public LocalTime getInicioExpediente() {
        return inicioExpediente;
    }

    public void setInicioExpediente(LocalTime inicioExpediente) {
        this.inicioExpediente = inicioExpediente;
    }

    public LocalTime getFimExpediente() {
        return fimExpediente;
    }

    public void setFimExpediente(LocalTime fimExpediente) {
        this.fimExpediente = fimExpediente;
    }
}
