package br.unipar.backend.trabalho1bi.controler;

import br.unipar.backend.trabalho1bi.model.Veiculo;
import br.unipar.backend.trabalho1bi.repository.VeiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControler {

    private final VeiculoRepository veiculoRepository;

    public VeiculoControler(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @GetMapping("/{veiculo}")
    public String veiculo(@PathVariable String veiculo) {
        return "Seu Veiculo é " + veiculo;
    }
    @GetMapping("/pesquisa")
    public ResponseEntity<List<Veiculo>> pesquisa(@RequestParam(required = false) String modelo,@RequestParam(required = false) String marca,@RequestParam(required = false) Integer ano){

        List<Veiculo> veiculosEncontrados = veiculoRepository.pesquisa(modelo, marca, ano);
        if (veiculosEncontrados.isEmpty())
        { return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(veiculosEncontrados);
    }
    @GetMapping("/listar-todos")
    public ResponseEntity<List<Veiculo>> listarTodos() {

        return ResponseEntity.ok(veiculoRepository.findAll());
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<Veiculo> listarID(@PathVariable Integer id) {

        Veiculo veiculo = veiculoRepository.findById(id).orElse(null);

        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(veiculo);
    }
    @GetMapping("/marca")
    public ResponseEntity<List<Veiculo>> veiculoMarca(@RequestParam String marca) {

        List<Veiculo> veiculosEncontrados = veiculoRepository.findByMarca(marca);

        if (veiculosEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(veiculosEncontrados);
    }
    @GetMapping("/ano")
    public ResponseEntity<List<Veiculo>> veiculoAno(@RequestParam Integer ano) {

        List<Veiculo> veiculosEncontrados = veiculoRepository.findByAno(ano);

        if (veiculosEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(veiculosEncontrados);
    }

    @PostMapping("/gravar")
    public ResponseEntity<String> salvarVeiculo(
            @RequestBody(required = false) Veiculo veiculo) {

        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        } else {
            veiculo.setId(null);
            veiculo = veiculoRepository.save(veiculo);
            return ResponseEntity.ok("ID: " + veiculo.getId()+"\n"+"Nome: " + veiculo.getNome()+"\n"+"Modelo: " + veiculo.getModelo()+"\n"+"Marca: " + veiculo.getMarca()+"\n"+"Ano: " + veiculo.getAno()+"\n");
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarVeiculo(@PathVariable Integer id) {

        Veiculo veiculo = veiculoRepository.findById(id).orElse(null);

        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }

        veiculoRepository.delete(veiculo);
        return ResponseEntity.ok("Veiculo " + id + " deletado");
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<Veiculo> editarVeiculo(
            @PathVariable int id,
            @RequestBody(required = false) Veiculo veiculo) {

        if (veiculo == null) {
            return ResponseEntity.noContent().build();
        }

        Veiculo veiculoEncontrado = veiculoRepository.findById(id).orElse(null);

        if (veiculoEncontrado == null) {
            return ResponseEntity.notFound().build();
        }

        veiculoEncontrado.setNome(veiculo.getNome());
        veiculoEncontrado.setMarca(veiculo.getMarca());
        veiculoEncontrado.setModelo(veiculo.getModelo());
        veiculoEncontrado.setAno(veiculo.getAno());

        return ResponseEntity.ok(veiculoRepository.save(veiculoEncontrado));
    }
}
