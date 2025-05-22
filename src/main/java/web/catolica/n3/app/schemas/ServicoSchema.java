package web.catolica.n3.app.schemas;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class ServicoSchema {

    @Id
    private UUID id;

    private String nome;

    private BigDecimal valor;
    private int duracao;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaSchema empresa;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
    private List<AgendamentoSchema> agendamentos = new ArrayList<>();

    public ServicoSchema() {}

    public ServicoSchema(
        String nome,
        EmpresaSchema empresa,
        BigDecimal valor,
        int duracao
    ) {
        this.nome = nome;
        this.empresa = empresa;
        this.valor = valor;
        this.duracao = duracao;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EmpresaSchema getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaSchema empresa) {
        this.empresa = empresa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
