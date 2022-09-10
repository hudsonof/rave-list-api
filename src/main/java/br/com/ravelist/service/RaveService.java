package br.com.ravelist.service;

import br.com.ravelist.exception.*;
import br.com.ravelist.model.*;
import br.com.ravelist.payload.mapper.*;
import br.com.ravelist.payload.request.*;
import br.com.ravelist.payload.response.*;
import br.com.ravelist.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class RaveService {

    @Autowired
    private RaveRepository raveRepository;

    @Autowired
    private UserRepository userRepository;

    public PageableCustomResponse<RaveResponse> findAllActives(Pageable pageable) {
        Page<Rave> raves = raveRepository.findByActiveTrue(pageable);

        PageableCustomResponse<RaveResponse> raveResponse = new PageableCustomResponse<>();
        raveResponse.setContent(RaveMapper.MAPPER.toListResponse(raves.getContent()));

        raveResponse.setPageable(
                new PageableResponse(
                        raves.getSize(),
                        raves.getPageable().getOffset(),
                        raves.getPageable().getPageNumber(),
                        raves.getPageable().getPageSize(),
                        raves.getTotalPages(),
                        raves.getTotalElements())
        );

        return raveResponse;
    }

    public RaveResponse findById(UUID id) {
        Rave rave = raveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rave for id '" + id + "' not found!"));
        return RaveResponse.builder()
                .id(rave.getId())
                .name(rave.getName())
                .date(rave.getDate())
                .location(rave.getLocation())
                .city(rave.getCity())
                .state(rave.getState())
                .image(rave.getImage())
                .page(rave.getPage())
                .tickets(rave.getTickets())
                .build();
    }

    public MessageResponse create(RaveRequest raveRequest, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        Rave rave = Rave.builder()
                .name(raveRequest.getName())
                .date(raveRequest.getDate())
                .location(raveRequest.getLocation())
                .city(raveRequest.getCity())
                .state(raveRequest.getState())
                .image(raveRequest.getImage())
                .page(raveRequest.getPage())
                .tickets(raveRequest.getTickets())
                .user(user)
                .active(false)
                .build();

        raveRepository.save(rave);

        return new MessageResponse("Rave with name: '" + rave.getName() + "', created successfully!");
    }

    public MessageResponse update(UUID raveId, RaveRequest raveRequest) {
        Rave rave = Rave.builder()
                .id(raveId)
                .name(raveRequest.getName())
                .date(raveRequest.getDate())
                .location(raveRequest.getLocation())
                .city(raveRequest.getCity())
                .state(raveRequest.getState())
                .image(raveRequest.getImage())
                .page(raveRequest.getPage())
                .tickets(raveRequest.getTickets())
                .build();

        raveRepository.save(rave);

        return new MessageResponse("Rave with name: '" + rave.getName() + "', updated successfully!");
    }

    public MessageResponse delete(UUID id) {
        raveRepository.deleteById(id);
        return new MessageResponse("Rave with id: " + id + ", deleted successfully!");
    }

}
