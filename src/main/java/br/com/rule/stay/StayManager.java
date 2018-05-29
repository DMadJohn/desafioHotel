package br.com.rule.stay;

import br.com.database.guest.GuestDef;
import br.com.database.guest.GuestRepo;
import br.com.database.stay.StayDef;;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.com.misc.utils.isDayOfTheWeek;

public class StayManager {

    private static final int MID_WEEK_DAYLY_EXPENSES = 120;
    private static final int MID_WEEK_CAR_EXPENSES = 15;
    private static final int WEEKEND_DAYLY_EXPENSES = 150;
    private static final int WEEKEND_CAR_EXPENSES = 20;

    private static final int HOUR_TOLERANCE = 16;
    private static final int MINUTE_TOLERANCE = 30;

    private static final String NOME_JSON = "nome";
    private static final String DOCUMENTO_JSON = "documento";
    private static final String GASTO_JSON = "valor-gasto";

    private GuestRepo guestRepo;

    public StayManager(GuestRepo guestRepo) {
        this.guestRepo = guestRepo;
    }

    public void setGuestRepository(GuestRepo repo) {
        this.guestRepo = repo;
    }

    public ResponseEntity<Object> transformToJSON(ArrayList<StayDef> stays) {
        JSONObject entity = new JSONObject();
        for (StayDef stay: stays) {
            GuestDef guest = getGuestByDoc(stay.getDocumento());
            if (guest != null) {
                entity.put(NOME_JSON, guest.getNome());
                entity.put(DOCUMENTO_JSON, guest.getDocumento());
                entity.put(GASTO_JSON, calcExpenses(stay));
            }
        }
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    private GuestDef getGuestByDoc(String documento) {
        Iterable<GuestDef> repos = guestRepo.findAll();
        for (GuestDef guest : repos) {
            if (guest.getDocumento().equalsIgnoreCase(documento)) {
                return guest;
            }
        }
        return null;
    }

    private int calcExpenses(StayDef stay) {
        int total = 0;
        boolean usedCar = stay.isAdicionalVeiculo();
        Calendar checkIn = stay.getDataEntradaCal();
        Calendar checkOut = stay.getDataSaidaCal();
        Calendar today = Calendar.getInstance();

        Date currentDate = Calendar.getInstance().getTime();
        while (checkIn.compareTo(checkOut) < 0 || checkIn.compareTo(today) < 0) {
            if (isDayOfTheWeek(checkIn)) {
                total += (usedCar) ? WEEKEND_DAYLY_EXPENSES + WEEKEND_CAR_EXPENSES : WEEKEND_DAYLY_EXPENSES;
            } else {
                total += (usedCar) ? MID_WEEK_DAYLY_EXPENSES + MID_WEEK_CAR_EXPENSES : MID_WEEK_DAYLY_EXPENSES;
            }
            checkIn.add(Calendar.DAY_OF_YEAR, 1);
        }
        if (checkIn.get(Calendar.HOUR_OF_DAY) > HOUR_TOLERANCE || (checkIn.get(Calendar.HOUR_OF_DAY) == HOUR_TOLERANCE && checkIn.get(Calendar.MINUTE) > MINUTE_TOLERANCE)) {
            total += (checkIn.get(Calendar.DAY_OF_WEEK) > Calendar.FRIDAY || checkIn.get(Calendar.DAY_OF_WEEK) < Calendar.MONDAY) ? WEEKEND_DAYLY_EXPENSES : MID_WEEK_DAYLY_EXPENSES;
        }
        return total;
    }
}
