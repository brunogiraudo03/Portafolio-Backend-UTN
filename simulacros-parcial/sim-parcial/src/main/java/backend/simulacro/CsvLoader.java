package backend.simulacro;

import java.util.List;

import backend.simulacro.repository.AbonadoRepository;
import backend.simulacro.service.AbonadoService;
import backend.simulacro.utils.CsvUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CsvLoader {
    
    private AbonadoService abonadoService;


    public void loadCsv() {
        List<String[]> lines = CsvUtils.readCsv("abonados.csv");
        lines.forEach(
            line -> {
                String dni = line[0];
                abonadoService.findOrCreate(dni, line);
            }
        );
    }

}
