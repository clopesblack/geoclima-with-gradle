package br.com.uol.test.geoclima.api;

import br.com.uol.test.geoclima.api.vo.ClientRequestVO;
import br.com.uol.test.geoclima.api.vo.ClientResponseVO;
import br.com.uol.test.geoclima.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static br.com.uol.test.geoclima.api.util.IpHelper.getIpFrom;
import static br.com.uol.test.geoclima.api.vo.mapper.ClientVOMapper.toClientDTO;

/**
 * Created by Caroline Lopes on 10/08/18.
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/clients")
public class ClientResource {

    private final ClientService service;

    @PostMapping
    public ResponseEntity save(@RequestBody ClientRequestVO clientRequestVO, HttpServletRequest request) {
        service.save(toClientDTO(clientRequestVO.getClient()), getIpFrom(request));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ClientResponseVO update(@RequestBody ClientRequestVO clientRequestVO) {
        ClientResponseVO response = new ClientResponseVO();
        response.setClient(clientRequestVO.getClient());
        response.getClient().setName("Update");
        return response;
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