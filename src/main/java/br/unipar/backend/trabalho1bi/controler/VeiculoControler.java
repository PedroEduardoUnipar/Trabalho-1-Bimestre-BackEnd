package br.unipar.backend.trabalho1bi.controler;

import br.unipar.backend.trabalho1bi.model.Veiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControler {

    List<Veiculo> bancoDados = new ArrayList<>();

    @GetMapping("/{veiculo}")
    public String veiculo(@PathVariable String veiculo) {
        return "Seu Veiculo é " + veiculo;
    }
    @GetMapping("/pesquisa")
    public ResponseEntity<List<Veiculo>> pesquisa(@RequestParam(required = false) String modelo,@RequestParam(required = false) String marca,@RequestParam(required = false) Integer ano){

        List<Veiculo> veiculosEncontrados = new ArrayList<>();

        for (Veiculo veiculo : bancoDados) {

            boolean corresponde = true;
            if (modelo != null && !veiculo.getModelo().equalsIgnoreCase(modelo))
            { corresponde = false;}

            if (marca != null && !veiculo.getMarca().equalsIgnoreCase(marca))
            { corresponde = false;}

            if (ano != null && !veiculo.getAno().equals(ano))
            { corresponde = false; }

            if (corresponde) { veiculosEncontrados.add(veiculo); }
        }
        if (veiculosEncontrados.isEmpty())
        { return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(veiculosEncontrados);
    }
    @GetMapping("/listar-todos")
    public ResponseEntity<List<Veiculo>> listarTodos() {

        return ResponseEntity.ok(bancoDados);
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<Veiculo> listarID(@PathVariable Integer id) {

        for (Veiculo veiculo : bancoDados) {

            if (veiculo.getId() == id) {
                return ResponseEntity.ok(veiculo);
            }
        }

        return ResponseEntity.notFound().build();
    }
    @GetMapping("/marca")
    public ResponseEntity<List<Veiculo>> veiculoMarca(@RequestParam String marca) {

        List<Veiculo> veiculosEncontrados = new ArrayList<>();

        for (Veiculo veiculo : bancoDados) {

            if (Objects.equals(veiculo.getMarca(), marca)) {
                veiculosEncontrados.add(veiculo);
            }
            if (veiculosEncontrados.isEmpty()) {
                return ResponseEntity.notFound().build();}

        }

        return ResponseEntity.ok(veiculosEncontrados);
    }
    @GetMapping("/ano")
    public ResponseEntity<List<Veiculo>> veiculoAno(@RequestParam Integer ano) {

        List<Veiculo> veiculosEncontrados = new ArrayList<>();

        for (Veiculo veiculo : bancoDados) {

            if (Objects.equals(veiculo.getAno(), ano)) {
                veiculosEncontrados.add(veiculo);
            }
        if (veiculosEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }}
        return ResponseEntity.ok(veiculosEncontrados);}

    @PostMapping("/gravar")
    public ResponseEntity<String> salvarVeiculo(
            @RequestBody(required = false) Veiculo veiculo) {

        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        } else {
            bancoDados.add(veiculo);
            return ResponseEntity.ok("ID: " + veiculo.getId()+"\n"+"Nome: " + veiculo.getNome()+"\n"+"Modelo: " + veiculo.getModelo()+"\n"+"Marca: " + veiculo.getMarca()+"\n"+"Ano: " + veiculo.getAno()+"\n");
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarVeiculo(@PathVariable Integer id) {

        for (int i = 0; i < bancoDados.size(); i++) {

            if (bancoDados.get(i).getId() == id) {

                bancoDados.remove(i);

                return ResponseEntity.ok("Veiculo " + id + " deletado");
            }
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/editar/{id}") // editar
    public ResponseEntity<Veiculo> editarVeiculo(
            @PathVariable int id,
            @RequestBody(required = false) Veiculo veiculo) {

        if (veiculo == null) {
            return ResponseEntity.noContent().build();
        }

        for (Veiculo u : bancoDados) {

            if (u.getId() == id) {

                u.setNome(veiculo.getNome());
                u.setMarca(veiculo.getMarca());
                u.setModelo(veiculo.getModelo());
                u.setAno(veiculo.getAno());

                return ResponseEntity.ok(u);
            }
        }
        return ResponseEntity.notFound().build();
    }
}