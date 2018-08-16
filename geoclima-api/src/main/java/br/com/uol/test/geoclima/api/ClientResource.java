package br.com.uol.test.geoclima.api;

import br.com.uol.test.geoclima.api.vo.ClientRequestVO;
import br.com.uol.test.geoclima.api.vo.ClientResponseVO;
import br.com.uol.test.geoclima.service.ClientService;
import br.com.uol.test.geoclima.service.dto.ClientDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static br.com.uol.test.geoclima.api.util.IpHelper.getIpFrom;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Caroline Lopes on 10/08/18.
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/clients")
public class ClientResource {

    private final ClientService service;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody final ClientRequestVO clientRequestVO,
                                          final HttpServletRequest request) {
        ClientDTO saved = service.save(mapper.map(clientRequestVO.getClient(), ClientDTO.class), getIpFrom(request));
        return new ResponseEntity<>(saved, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable("id") final String id,
                                            @RequestBody final ClientRequestVO clientRequestVO) {
        ClientDTO client = mapper.map(clientRequestVO.getClient(), ClientDTO.class);
        client.setId(id);
        ClientDTO updated = service.update(client);
        return new ResponseEntity<>(updated, OK);
    }

    @GetMapping("/{id}")
    public ClientResponseVO getById(@PathVariable String id) {
        return new ClientResponseVO();
    }

    @GetMapping
    public List<ClientResponseVO> getAll() {
        return new ArrayList<>();
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
    }
}