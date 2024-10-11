package com.athletiquest.athletiquest_api.stadiums;

import com.athletiquest.athletiquest_api.utils.URIStringUtils;
import com.athletiquest.model.Stadium;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.athletiquest.athletiquest_api.utils.URIStringUtils.*;

@Component
public class StadiumService {


    private final String BASE_PATH = "https://equipements.sports.gouv.fr/api/explore/v2.1/catalog/datasets/data-es/records?select=inst_numero%2C%20inst_nom%2C%20inst_actif%2C%20coordonnees%2C%20equip_aps_code%2C%20equip_acc_libre&where=equip_aps_code%20LIKE%20%22%25%27501%27%25%22%20OR%20%22%25%27502%27%25%22%20OR%20%22%25%27503%27%25%22%20AND%20equip_acc_libre%20%3D%20%22true%22%20AND%20equip_ouv_public_bool%20%3D%20%22true";


    public Stadium getStadiumById(String id) {
        String path = BASE_PATH + SPACE + "AND" + SPACE + "inst_numero" + EQUAL + QUOTE + id + QUOTE;
        return null;
    }

    public List<Stadium> getStadiums() {
        return null;
    }

    public List<Stadium> getStadiumsByName(String name) {
        return null;
    }
}
