package com.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.dto.ReservationUpdateRequest;
import com.flight.entity.Reservation;
import com.flight.repository.ReservationRepository;

@RestController
public class ReservationRestController {
	@Autowired
	private ReservationRepository reservationRepo;
	
	//http://localhost:8080/flights/reservation/1
	@RequestMapping("/reservation/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		Reservation reservation= reservationRepo.findById(id).get();
		return reservation;
	}
	@RequestMapping("/reservation")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Reservation reservation= reservationRepo.findById(request.getId()).get();
		reservation.setCheckedIn(request.isCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		return reservationRepo.save(reservation);
	}

}
