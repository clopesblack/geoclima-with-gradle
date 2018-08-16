package br.com.uol.test.geoclima.api;

import br.com.uol.test.geoclima.api.vo.ClientRequestVO;
import br.com.uol.test.geoclima.api.vo.ClientResponseVO;
import br.com.uol.test.geoclima.service.ClientService;
import br.com.uol.test.geoclima.service.dto.ClientDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static br.com.uol.test.geoclima.api.util.IpHelper.getIpFrom;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Caroline Lopes on 10/08/18.
 */
@RestController
@AllArgsConstructor
public class ClientResource {

    private final ClientService service;
    private final ModelMapper mapper;

    @PostMapping("/client")
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody final ClientRequestVO clientRequestVO,
                                          final HttpServletRequest request) {
        ClientDTO saved = service.save(mapper.map(clientRequestVO.getClient(), ClientDTO.class), getIpFrom(request));
        return new ResponseEntity<>(saved, CREATED);
    }

    @PutMapping("/client/{id}")
    public ClientDTO update(@PathVariable("id") final String id,
                            @RequestBody final ClientRequestVO clientRequestVO) {
        ClientDTO client = mapper.map(clientRequestVO.getClient(), ClientDTO.class);
        client.setId(id);
        return service.update(client);
    }

    @GetMapping("/client/{id}")
    public ClientDTO getById(@PathVariable String id) {
        return mapper.map(service.getClient(id), ClientDTO.class);
    }

    @GetMapping("/clients")
    public List<ClientResponseVO> getAll() {
        return mapper.map(service.list(), new TypeToken<List<ClientDTO>>() {}.getType());
    }

    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable String id) {
    }
}