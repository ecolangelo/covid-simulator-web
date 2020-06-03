package com.github.ecolangelo.covidsimulatorweb;

import com.github.ecolangelo.ReportGenerator;
import com.github.ecolangelo.ingestion.CovidDataService;
import com.github.ecolangelo.ingestion.CovidDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
public class CovidSimulatorService {

    private final Logger LOG = LoggerFactory.getLogger(CovidSimulatorService.class);

    @Autowired
    private CovidDataService covidDataService;


    private Map<LocalDate,SimulatorData> noexpirableCache = new ConcurrentHashMap<>();


    @GetMapping("/national")
    public SimulatorData getSimulation() throws Exception{
        LocalDate now = LocalDate.now();
        if(noexpirableCache.get(now)!= null) {
            return noexpirableCache.get(now);
        }

        CovidDataset national = covidDataService.getNational();
        double[][] simulate = ReportGenerator.runLinearRegression(national.records().collect(Collectors.toList()), now, LOG::info);




        String[] dates = new String[simulate.length];
        double[] values = new double[simulate.length];


        for(int i = 0;i<simulate.length;i++) {
            double nDays = simulate[i][0]+1;
            double estimateInfections = simulate[i][1];

            LocalDate localDate = now.plusDays(i+1);
            dates[i] = localDate.toString();
            values[i] = estimateInfections;

        }
        SimulatorData simulatorData = new SimulatorData();

        simulatorData.setPlottyLineForInfections(new PlottyLineForInfections(dates, values));
        simulatorData.setLastUpdated(now.toString());

        simulatorData.setEstimatedDayOfEnding(dates[dates.length-1]);


        return simulatorData;
    }

}
