package tn.esprit.meetico.controller;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.esprit.meetico.entity.Comment;
import tn.esprit.meetico.entity.Publication;
import tn.esprit.meetico.entity.Trip;
import tn.esprit.meetico.entity.User;
import tn.esprit.meetico.repository.PublicationRepository;
import tn.esprit.meetico.repository.TripRepository;
import tn.esprit.meetico.repository.UserRepository;
import tn.esprit.meetico.service.PublicationServiceImpl;

@Controller

public class GraphController {

	@Autowired
	PublicationServiceImpl pubservice;
	@Autowired
	PublicationRepository pubrepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	TripRepository tripRepo;
	
	
    @GetMapping("/graphtrip")
    public String getPieChart(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
    	List<Trip> trip =tripRepo.findAll();
    	for(Trip t:trip) {
			int n = 0 ;
			for(Trip tr:trip) {
				if(t.getDestination().equalsIgnoreCase(tr.getDestination())) {
					n++;
				
					graphData.put(t.getDestination(), n);
				}
				
			}
		}
     
        model.addAttribute("chartData", graphData);
        return "google-charts";
    }
}

