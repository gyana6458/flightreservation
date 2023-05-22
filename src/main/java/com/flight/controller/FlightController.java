package com.flight.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.entity.Flight;
import com.flight.repository.FlightRepository;

@Controller
public class FlightController {
	@Autowired
	private FlightRepository flightRepo;
	@RequestMapping("/findFlights")
	public String findFlight(@RequestParam("from") String from,@RequestParam("to") String to, ModelMap modelMap) {
		List<Flight> findFlights = flightRepo.findFlights(from,to);
		modelMap.addAttribute("flights", findFlights);
		return "displayFlights";
	}

}
