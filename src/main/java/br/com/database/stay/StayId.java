package br.com.database.stay;

import br.com.database.guest.GuestDef;

import java.io.Serializable;


//@Embeddable
public class StayId implements Serializable{
    //private GuestDef hospede;
    private String documento;
    private String dataEntrada;
}
