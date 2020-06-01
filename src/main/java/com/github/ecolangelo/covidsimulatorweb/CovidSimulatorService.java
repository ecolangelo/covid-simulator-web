package com.github.ecolangelo.covidsimulatorweb;

import com.github.ecolangelo.ingestion.CovidDataService;
import com.github.ecolangelo.ingestion.CovidDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CovidSimulatorService {

    @Autowired
    private CovidDataService covidDataService;

    


    @GetMapping("/national")
    public SimulatorData getSimulation() throws Exception{
        CovidDataset national = covidDataService.getNational();

        SimulatorData simulatorData = new SimulatorData();




        return simulatorData;
    }

}
