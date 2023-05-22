package com.flight.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.dto.ReservationRequest;
import com.flight.entity.Flight;
import com.flight.entity.Passenger;
import com.flight.entity.Reservation;
import com.flight.repository.FlightRepository;
import com.flight.repository.PassengerRepository;
import com.flight.repository.ReservationRepository;
import com.flight.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		Passenger passenger  = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		
		passengerRepo.save(passenger);
		
		 Flight flight = flightRepo.findById(request.getId()).get();
		
		 Reservation reservation  = new Reservation();
			reservation.setFlight(flight);
			reservation.setPassenger(passenger);
			reservation.setCheckedIn(false);
			reservation.setNumberOfBags(0);
			
			reservationRepo.save(reservation);
			
			return reservation;
	}

}
