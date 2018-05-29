package br.com.database.guest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@IdClass(GuestId.class)
@Table(name = "guest")
public class GuestDef implements Serializable {
    @Id
    @Column(name = "documento", nullable = false)
    private String documento;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hospede", targetEntity = StayDef.class)
    @JsonIgnore
    private List<StayDef> stayList;
    */

    public GuestDef(String documento, String nome, String telefone) {
        this.documento = documento;
        this.nome = nome;
        this.telefone = telefone;
    }

    public GuestDef() {
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
