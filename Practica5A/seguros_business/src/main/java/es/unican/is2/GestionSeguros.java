package es.unican.is2;

import java.time.LocalDate;
import java.util.List;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    private final IClientesDAO clientesDAO;
    private final ISegurosDAO segurosDAO;

    public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
        this.clientesDAO = clientesDAO;
        this.segurosDAO = segurosDAO;
    }

    // ===== IGestionClientes =====
    
    

    @Override
    public Cliente nuevoCliente(Cliente c) throws DataAccessException {
        // El DAO ya devuelve null si no puede persistir porque ya existe
        return clientesDAO.creaCliente(c);
    }

    @Override
    public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {

        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            throw new OperacionNoValida("El cliente no existe");
        }

        // Si tiene seguros a su nombre, no se elimina
        List<Seguro> lista = c.getSeguros();
        if (lista != null && !lista.isEmpty()) {
            throw new OperacionNoValida("El cliente tiene seguros a su nombre");
        }

        // El DAO devuelve el cliente eliminado o null si no lo encuentra
        Cliente eliminado = clientesDAO.eliminaCliente(dni);
        if (eliminado == null) {
            throw new OperacionNoValida("El cliente no existe");
        }

        return eliminado;
    }

    // ===== IGestionSeguros =====

    @Override
    public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {

        // Debe existir el cliente
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            throw new OperacionNoValida("No existe cliente con ese DNI");
        }

        // La matrícula debe ser única en todo el sistema
        Seguro ya = segurosDAO.seguroPorMatricula(s.getMatricula());
        if (ya != null) {
            throw new OperacionNoValida("Ya existe un seguro para esa matrícula");
        }

        // Fecha de inicio: hoy o futura (no pasada)
        if (s.getFechaInicio() == null || s.getFechaInicio().isBefore(LocalDate.now())) {
            throw new OperacionNoValida("Fecha de inicio inválida");
        }

        // Persistimos el seguro
        Seguro creado = segurosDAO.creaSeguro(s);
        if (creado == null) {
            // por contrato: null si ya existe (aunque ya lo comprobamos)
            throw new OperacionNoValida("Ya existe un seguro para esa matrícula");
        }

        // Lo añadimos al cliente y actualizamos el cliente
        c.getSeguros().add(creado);
        clientesDAO.actualizaCliente(c);

        return creado;
    }

    @Override
    public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {

        // Debe existir seguro por matrícula
        Seguro s = segurosDAO.seguroPorMatricula(matricula);
        if (s == null) {
            throw new OperacionNoValida("No existe un seguro para esa matrícula");
        }

        // Debe existir el cliente
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            throw new OperacionNoValida("No existe cliente con ese DNI");
        }

        // El seguro debe pertenecer al cliente (miramos la lista del cliente)
        boolean pertenece = false;
        Seguro seguroEnCliente = null;

        List<Seguro> lista = c.getSeguros();
        if (lista != null) {
            for (Seguro seg : lista) {
                if (matricula.equals(seg.getMatricula())) {
                    pertenece = true;
                    seguroEnCliente = seg;
                }
            }
        }

        if (!pertenece) {
            throw new OperacionNoValida("El seguro no pertenece al cliente indicado");
        }

        // Quitamos de la lista del cliente y actualizamos cliente
        if (seguroEnCliente != null) {
            c.getSeguros().remove(seguroEnCliente);
            clientesDAO.actualizaCliente(c);
        }

        // Eliminamos el seguro por id
        Seguro eliminado = segurosDAO.eliminaSeguro(s.getId());
        if (eliminado == null) {
            // Si por lo que sea ya no está, lo tratamos como operación no válida
            throw new OperacionNoValida("No existe un seguro para esa matrícula");
        }

        return eliminado;
    }

    @Override
    public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {

        Seguro s = segurosDAO.seguroPorMatricula(matricula);
        if (s == null) {
            // La firma no permite OperacionNoValida, así que lo típico aquí es devolver null
            return null;
        }

        s.setConductorAdicional(conductor);

        // Persistimos el cambio
        return segurosDAO.actualizaSeguro(s);
    }

    // ===== IInfoSeguros =====

    @Override
    public Cliente cliente(String dni) throws DataAccessException {
        // Para "Consulta Cliente" basta devolver el cliente (se supone que trae su lista de seguros)
        return clientesDAO.cliente(dni);
    }

    @Override
    public Seguro seguro(String matricula) throws DataAccessException {
        return segurosDAO.seguroPorMatricula(matricula);
    }
}