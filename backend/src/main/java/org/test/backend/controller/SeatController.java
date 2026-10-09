package org.test.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.backend.dto.SeatLockRequest;
import org.test.backend.service.SeatLockService;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatLockService seatLockService;


    @PostMapping("/lock")
    public ResponseEntity<Boolean> lockSeat(@RequestBody SeatLockRequest request) {

        boolean success = seatLockService.lockSeat(request.seanceId(),request.seatNumber(),request.userId());
        if (success) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(false);

    }

    @PostMapping("/release")
    public ResponseEntity<Boolean> releaseSeat(@RequestBody SeatLockRequest request) {
        boolean released = seatLockService.releaseSeat(request.seanceId(), request.seatNumber(), request.userId());
        return ResponseEntity.ok(released);
    }
}
