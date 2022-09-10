package br.com.ravelist.controller;

import br.com.ravelist.payload.request.*;
import br.com.ravelist.payload.response.*;
import br.com.ravelist.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("raves")
public class RaveController {

    @Autowired
    private RaveService raveService;

    @GetMapping
    public ResponseEntity<PageableCustomResponse<RaveResponse>> getAll(
            @RequestParam(value = "_offset", required = false, defaultValue = "0") Integer offset,
            @RequestParam(value = "_limit", required = false, defaultValue = "10") Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return ResponseEntity.ok(raveService.findAllActives(pageable));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> getAll(@RequestBody RaveRequest raveRequest, Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return ResponseEntity.ok(raveService.create(raveRequest, userDetails.getUsername()));
    }
}
