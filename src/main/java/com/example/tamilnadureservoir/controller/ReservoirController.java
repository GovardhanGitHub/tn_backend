package com.example.tamilnadureservoir.controller;


import com.example.tamilnadureservoir.dao.ReservoirEveryDayUpdateRepository;
import com.example.tamilnadureservoir.dao.ReservoirRepository;
import com.example.tamilnadureservoir.dao.UserDao;
import com.example.tamilnadureservoir.dto.ReservoirDto;
import com.example.tamilnadureservoir.mappers.ReservoirMapper;
import com.example.tamilnadureservoir.model.Reservoir;
import com.example.tamilnadureservoir.model.ReservoirEveryDayUpdate;
import com.example.tamilnadureservoir.model.ReservoirEveryDayUpdateDto;
import com.example.tamilnadureservoir.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/reservoir")
public class ReservoirController {
    private final ReservoirRepository reservoirRepository;

    private final UserDao userDao;
    private final ReservoirMapper reservoirMapper;

    private final ReservoirEveryDayUpdateRepository reservoirEveryDayUpdateRepository;

    public ReservoirController(ReservoirRepository reservoirRepository, UserDao userDao, ReservoirMapper reservoirMapper, ReservoirEveryDayUpdateRepository reservoirEveryDayUpdateRepository) {
        this.reservoirRepository = reservoirRepository;
        this.userDao = userDao;
        this.reservoirMapper = reservoirMapper;
        this.reservoirEveryDayUpdateRepository = reservoirEveryDayUpdateRepository;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/findAll")
    ResponseEntity<List<Reservoir>> findAllReservoirs() {
        List<Reservoir> reservoirList = reservoirRepository.findAll();
        return ResponseEntity.ok().body(reservoirList);

    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    ResponseEntity findById(@PathVariable Long id) {
        Optional<Reservoir> reservoirOptional = reservoirRepository.findById(id);
        if (reservoirOptional.isPresent()) return ResponseEntity.ok().body(reservoirOptional.get());
        else return ResponseEntity.badRequest().body("Bad request");
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/delete")
    ResponseEntity delete(@RequestBody Long id) {
        if (id == null) return ResponseEntity.badRequest().body("Bad request");
        else {
            Optional<Reservoir> reservoirOptional = reservoirRepository.findById(id);
            if (reservoirOptional.isPresent()) {
                Reservoir reservoir = reservoirOptional.get();
                reservoir.setIsDeleted(Boolean.TRUE);
                reservoirRepository.save(reservoir);
                return ResponseEntity.ok().body("successfully deleted");
            } else {
                return ResponseEntity.badRequest().body("something went wrong..");
            }
        }

    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/addReservoir")
    ResponseEntity addReservoir(@RequestBody ReservoirDto reservoirDto) {
        log.info("dto {}", reservoirDto);
        Reservoir reservoir = reservoirMapper.reservoirDto2Reservoir(reservoirDto);
        Reservoir savedEntity = reservoirRepository.save(reservoir);
        return ResponseEntity.ok().body(savedEntity);

    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/addReservoirLatestDetails")
    ResponseEntity addReservoirLatestDetails(@RequestBody ReservoirDto reservoirDto) {
        log.info("dto {}", reservoirDto);
        Reservoir reservoir = reservoirMapper.reservoirDto2Reservoir(reservoirDto);
        Reservoir savedEntity = reservoirRepository.save(reservoir);
        return ResponseEntity.ok().body(savedEntity);

    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/getReservoirEveryDayDetails/{id}")
    List<ReservoirEveryDayUpdate> getReservoirEveryDayDetails(@PathVariable Long id) {
        Optional<Reservoir> reservoirOptional = reservoirRepository.findById(id);
        return reservoirOptional.map(reservoirEveryDayUpdateRepository::findByReservoir).orElse(null);
    }




    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/findReservoirById/{id}")
    Reservoir findReservoirById(@PathVariable Long id) {
        Optional<Reservoir> reservoirOptional = reservoirRepository.findById(id);
        return reservoirOptional.orElse(null);
    }




    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/updateEveryDayDetails")
    ReservoirEveryDayUpdate updateEveryDayDetails(@RequestBody ReservoirEveryDayUpdateDto dto) {

        ReservoirEveryDayUpdate dayUpdate = new ReservoirEveryDayUpdate();

        Optional<User> optionalUser = userDao.findById(dto.getUserId());
        optionalUser.ifPresent(dayUpdate::setUser);

        Optional<Reservoir> optionalReservoir = reservoirRepository.findById(dto.getReservoirId());
        optionalReservoir.ifPresent(dayUpdate::setReservoir);

        dayUpdate.setDate(dto.getDate());

        dayUpdate.setCapacity(dto.getCapacity());
        dayUpdate.setInflow(dto.getInflow());
        dayUpdate.setOutflow(dto.getOutflow());
        dayUpdate.setFullHeight(dto.getFullHeight());

        dayUpdate.setRainfall(dto.getRainfall());
        dayUpdate.setPresentStorage(dto.getPresentStorage());
        dayUpdate.setPresentDepthOfStorage(dto.getPresentDepthOfStorage());


        return reservoirEveryDayUpdateRepository.save(dayUpdate);


    }

    //edit operation

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/editEveryDayDetails")
    ReservoirEveryDayUpdate editEveryDayDetails(@RequestBody ReservoirEveryDayUpdateDto dto) {

        if (dto.getId() != null) {
            Optional<ReservoirEveryDayUpdate> optionalReservoirEveryDayUpdate = reservoirEveryDayUpdateRepository.findById(dto.getId());
            ReservoirEveryDayUpdate dayUpdate = optionalReservoirEveryDayUpdate.get();
            dayUpdate.setInflow(dto.getInflow());
            dayUpdate.setOutflow(dto.getOutflow());
            dayUpdate.setRainfall(dto.getRainfall());
            dayUpdate.setPresentStorage(dto.getPresentStorage());
            dayUpdate.setPresentDepthOfStorage(dto.getPresentDepthOfStorage());
            return reservoirEveryDayUpdateRepository.save(dayUpdate);
        } else return null;


    }
}
