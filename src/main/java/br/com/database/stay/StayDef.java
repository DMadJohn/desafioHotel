package br.com.database.stay;

import br.com.database.guest.GuestDef;
import br.com.misc.converters.JpaConverterGuestDefJson;
import br.com.misc.converters.JpaConverterISODateCalendar;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@IdClass(StayId.class)
@Table(name = "stay")
public class StayDef implements Serializable {
    /* TODO Identificar o motivo do @Convert nao funcionar
    @Id
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = GuestDef.class)
    @JoinColumn(name = "documento", insertable = false, updatable = false)
    @Convert(converter = JpaConverterGuestDefJson.class)
    private GuestDef hospede;
    */

    @Id
    @Column(name = "documento", nullable = false)
    private String documento;


    /*@Temporal(TemporalType.TIMESTAMP) TODO Mesmo problema do @Convert
    @Convert(converter = JpaConverterISODateCalendar.class)
    private Calendar dataEntrada;*/
    @Id
    @Column(name = "dataEtrada", nullable = false)
    private String dataEntrada;

    /*@Temporal(TemporalType.TIMESTAMP) TODO Mesmo problema do @Convert
    @Convert(converter = JpaConverterISODateCalendar.class)
    private Calendar dataSaida;*/
    @Column(name = "dataSaida", nullable = false)
    private String dataSaida;

    @Column(name = "adicionalveiculo", nullable = false)
    private boolean adicionalVeiculo;

    public StayDef() {
    }

    public StayDef(String documento, String dataEntrada, String dataSaida, boolean adicionalVeiculo) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        this.documento = documento;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.adicionalVeiculo = adicionalVeiculo;
    }

    /* TODO Mesmo problema do @Convert
    @Convert(converter = JpaConverterGuestDefJson.class)
    public GuestDef getHospede() {
        return hospede;
    }

    @Convert(converter = JpaConverterGuestDefJson.class)
    public void setHospede(GuestDef hospede) {
        this.hospede = hospede;
    }
    */

    private Calendar getCalendarValue(String isoDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//("yyyy-MM-dd'T'HH:mm:ssX");
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(formatter.parse(isoDate));
            return date;
        } catch (Throwable any) {
            return null;
        }
    }

    @JsonIgnore
    public Calendar getDataEntradaCal() {
        return getCalendarValue(dataEntrada);
    }

    @JsonIgnore
    public Calendar getDataSaidaCal() {
        return getCalendarValue(dataSaida);
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public boolean isAdicionalVeiculo() {
        return adicionalVeiculo;
    }

    public void setAdicionalVeiculo(boolean adicionalVeiculo) {
        this.adicionalVeiculo = adicionalVeiculo;
    }
}
