package web.catolica.n3.app.schemas;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Entity
public class AgendamentoSchema {

    @Id
    private UUID id;

    private Date data;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsuarioSchema usuario;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private ServicoSchema servico;

    public AgendamentoSchema() {}

    public AgendamentoSchema(Date data, LocalTime horaInicio) {
        this.data = data;
        this.horaInicio = horaInicio;
    }

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UuidCreator.getTimeOrderedEpoch();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ServicoSchema getServico() {
        return servico;
    }

    public void setServico(ServicoSchema servico) {
        this.servico = servico;
    }

    public UsuarioSchema getUser() {
        return usuario;
    }

    public void setUser(UsuarioSchema usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
}
