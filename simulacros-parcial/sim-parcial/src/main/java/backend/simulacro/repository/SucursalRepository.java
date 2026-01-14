package backend.simulacro.repository;

import backend.simulacro.entity.Sucursal;

public class SucursalRepository  extends BaseRepository<Long, Sucursal> {
    public SucursalRepository() {
        super(Sucursal.class);
    }
}
