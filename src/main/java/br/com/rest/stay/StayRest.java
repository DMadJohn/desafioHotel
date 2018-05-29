package br.com.rest.stay;

import br.com.database.guest.GuestId;
import br.com.rest.guest.GuestRest;
import br.com.rule.stay.StayManager;
import br.com.database.guest.GuestDef;
import br.com.database.guest.GuestRepo;
import br.com.database.stay.StayDef;
import br.com.database.stay.StayRepo;
import br.com.rule.stay.TTimePeriodEnum;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static br.com.rule.stay.TTimePeriodEnum.PAST;
import static br.com.rule.stay.TTimePeriodEnum.PRESENT;

@RestController
public class StayRest {

    @Autowired
    StayRepo stayRepo;

    @Autowired
    GuestRepo guestRepo;

    @GetMapping("/stays")
    public List<StayDef> returnAllGuests() {
        Iterable<StayDef> loc = stayRepo.findAll();
        ArrayList<StayDef> res = new ArrayList<StayDef>();
        for (StayDef stays : loc) {
            res.add(stays);
        }
        return res;
    }

    @PostMapping("/stays/add")
    public ResponseEntity<Object> insertGuest(StayDef stay) {
        JSONObject entity = new JSONObject();
        try {
            GuestDef guest = getGuestByDocument(stay.getDocumento());
            if (guest != null) {
                stayRepo.save(stay);
                entity.put("Resultado", "OK");
                return new ResponseEntity<Object>(entity, HttpStatus.OK);
            } else {
                entity.put("Erro:", " Hospede nao cadastrado.");
                return new ResponseEntity<Object>(entity, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch ( Throwable any ) {
            return new ResponseEntity<Object>(entity, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/stays/people-view/{view}", method=RequestMethod.GET)
    public ResponseEntity<Object> getViewFrom(@PathVariable("view") int view) {
        try {
            Calendar curDate = Calendar.getInstance();
            ArrayList<StayDef> actStays = new ArrayList<StayDef>();
            for (StayDef stay : stayRepo.findAll()) {
                if (view == PRESENT.getCode()) {
                    if (stay.getDataEntradaCal().compareTo(curDate) < 0 && stay.getDataSaidaCal().compareTo(curDate) > 0) {
                        actStays.add(stay);
                    }
                } else if (view == PAST.getCode()) {
                    if (stay.getDataEntradaCal().compareTo(curDate) < 0 && stay.getDataSaidaCal().compareTo(curDate) < 0) {
                        actStays.add(stay);
                    }
                }
            }
            StayManager man = new StayManager(guestRepo);
            return new ResponseEntity<Object>(man.transformToJSON(actStays), HttpStatus.OK);
        } catch ( Throwable any ) {
            return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private GuestDef getGuestByDocument(String document) {
        Iterable<GuestDef> loc = guestRepo.findAll();
        GuestId id = new GuestId();
        ArrayList<GuestDef> res = new ArrayList<GuestDef>();
        for (GuestDef guest : loc) {
            res.add(guest);
        }
        for (GuestDef guest: res) {
            if (guest.getDocumento().equalsIgnoreCase(document)) {
                return guest;
            }
        }
        return null;
    }
}
