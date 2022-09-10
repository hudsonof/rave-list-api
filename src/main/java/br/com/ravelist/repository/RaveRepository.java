package br.com.ravelist.repository;

import br.com.ravelist.model.*;
import br.com.ravelist.payload.response.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Repository;

import java.awt.print.*;
import java.util.*;

@Repository
public interface RaveRepository extends PagingAndSortingRepository<Rave, UUID> {
    Page<Rave> findByActiveTrue(Pageable pageable);
}
