package br.com.rest.guest;

import br.com.database.guest.GuestDef;
import br.com.database.guest.GuestRepo;
import br.com.rule.guest.GuestManager;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GuestRest {

    @Autowired
    private GuestRepo repo;

    @GetMapping("/guests")
    public List<GuestDef> returnAllGuests() {
        Iterable<GuestDef> loc = repo.findAll();
        ArrayList<GuestDef> res = new ArrayList<GuestDef>();
        for (GuestDef guest : loc) {
            res.add(guest);
        }
         return res;
    }

    @PostMapping("/guests/add")
    public ResponseEntity<Object> insertGuest(GuestDef guest) {
        if (guest == null ) return  null;

        JSONObject entity = new JSONObject();

        try {
            boolean duplicatedKey = false;
            for (GuestDef curGuest: repo.findAll()) {
                if (curGuest.getDocumento().equalsIgnoreCase(guest.getDocumento())) {
                    entity.put("Resultado", "Chave duplicada.");
                }
            }
            if (!duplicatedKey) {
                repo.save(guest);
                entity.put("Resultado", "Funcionou.");
            }
            return new ResponseEntity<Object>(entity, HttpStatus.OK);
        } catch (Throwable any) {
            return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/guests/find-exact")
    public GuestDef getGuest(String anything) {
        try {
            if (anything == null || anything.trim().length() == 0) return null;
            GuestDef guestToReturn = null;
            GuestManager manager = new GuestManager(repo);
            return manager.findGuest(anything);
        } catch (Throwable any) {
            any.printStackTrace();
            return null;
        }
    }

    @PostMapping("/guests/find-close")
    public List<GuestDef> getCloseGuest(String anything) {
        try {
            if (anything == null || anything.trim().length() == 0) return null;
            GuestDef guestToReturn = null;
            GuestManager manager = new GuestManager(repo);
            return manager.findCloseGuest(anything);
        } catch (Throwable any) {
            any.printStackTrace();
            return null;
        }
    }
}
