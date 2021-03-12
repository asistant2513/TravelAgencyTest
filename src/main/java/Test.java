import entities.Client;
import entities.Route;
import entities.Trip;
import entities.User;
import enums.Roots;
import enums.TravelType;
import utils.ClientProviderUtil;
import enums.Country;
import utils.RouteProviderUtil;
import utils.TripProviderUtil;
import utils.UserProviderUtil;

import java.time.Month;
import java.util.Date;

public class Test {

    public static void main(String[] args) {

        Route r = new Route();
        r.setPointBegin(Country.Australia);
        r.setPointEnd(Country.China);
        r.setCost(250);
            new RouteProviderUtil().save(r);
    }

}
