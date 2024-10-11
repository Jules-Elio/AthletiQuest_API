package com.athletiquest.athletiquest_api.stadiums;

import com.athletiquest.api.StadiumsApi;
import com.athletiquest.model.Stadium;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Getter
@Controller
@RequestMapping("${openapi.aPIAthletiQuest.base-path:}")
public class StadiumController implements StadiumsApi {

    final StadiumService stadiumService;

    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    /**
     * GET /stadiums
     *
     * @return the list of the stadiums (status code 200)
     * or Error (status code 400)
     * @see StadiumsApi#listStadium
     */
    @Override
    public ResponseEntity<List<Stadium>> listStadium() {
        ResponseEntity<List<Stadium>> response = new ResponseEntity<>(List.of(new Stadium("ID", "Stade1")), HttpStatus.OK);
        return response;
    }
}
