package br.com.uol.teste.geoclima.api;

import br.com.uol.teste.geoclima.api.vo.ClientRequestVO;
import br.com.uol.teste.geoclima.api.vo.ClientResponseVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caroline Lopes on 10/08/18.
 */
@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @PostMapping
    public ClientResponseVO save(@RequestBody ClientRequestVO clientRequestVO, HttpServletRequest request) {
        ClientResponseVO response = new ClientResponseVO();
        response.setClient(clientRequestVO.getClient());
        return response;
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