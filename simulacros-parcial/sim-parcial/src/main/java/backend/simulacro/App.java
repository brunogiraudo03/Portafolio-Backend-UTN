package backend.simulacro;

import backend.simulacro.entity.Sucursal;
import backend.simulacro.repository.AbonadoRepository;
import backend.simulacro.repository.EjercicioPlanRepository;
import backend.simulacro.repository.SucursalRepository;
import backend.simulacro.service.AbonadoService;
import backend.simulacro.service.EjercicioPlanService;
import backend.simulacro.service.SucursalService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SucursalRepository sucursalRepository = new SucursalRepository();
        EjercicioPlanRepository ejercicioPlanRepository = new EjercicioPlanRepository();
        AbonadoRepository abonadoRepository = new AbonadoRepository();
        SucursalService sucursalService = new SucursalService(sucursalRepository);
        EjercicioPlanService ejercicioPlanService = new EjercicioPlanService(ejercicioPlanRepository);
        AbonadoService abonadoService = new AbonadoService(abonadoRepository, sucursalService, ejercicioPlanService);
        CsvLoader csvLoader = new CsvLoader(abonadoService);
        csvLoader.loadCsv();

        // IMPLEMENTAR MENU;
    }
}
