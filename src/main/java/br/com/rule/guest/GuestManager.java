package br.com.rule.guest;

import br.com.database.guest.GuestDef;
import br.com.database.guest.GuestRepo;

import java.util.ArrayList;

import static br.com.misc.utils.containsNumbers;

public class GuestManager {

    GuestRepo repository;

    public GuestManager(GuestRepo repository) {
        this.repository = repository;
    }

    public ArrayList<GuestDef> findCloseGuest(String anything) {
        ArrayList<GuestDef> guestList = new ArrayList<GuestDef>();
        for (GuestDef guest : repository.findAll()) {
            if (!containsNumbers(anything)) {
                if (guest.getDocumento().contains(anything) || guest.getTelefone().contains(anything)) {
                    guestList.add(guest);
                    continue;
                }
            }
            if (guest.getNome().contains(anything)) {
                guestList.add(guest);
            }
        }
        return guestList;
    }

    public GuestDef findGuest(String anything) {
        Iterable<GuestDef> loc = repository.findAll();
        ArrayList<GuestDef> allGuests = new ArrayList<GuestDef>();
        for (GuestDef guest : loc) {
            if (guest.getDocumento().equalsIgnoreCase(anything) || guest.getDocumento().equalsIgnoreCase(anything) || guest.getTelefone().equalsIgnoreCase(anything)) {
                return guest;
            } else {
                allGuests.add(guest);
            }
        }
        ArrayList<GuestDef> guestList = findCloseGuest(anything);
        return (guestList.size() > 0) ? guestList.get(0) : null;
    }
}
