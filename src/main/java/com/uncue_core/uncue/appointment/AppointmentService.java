package com.uncue_core.uncue.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppoitmentRepository repository;




    public Appointment insertAppointment(Appointment appointmentDto) {

      return  repository.save(appointmentDto);

    }

    public List<Appointment> getAppointments(int saloonId) {
        return repository.findBySaloonidOrderByAppointmentidDesc(saloonId);
    }

    public Appointment getAppointment(int appointmentid) {
        return repository.findById(appointmentid).get();
    }


}
