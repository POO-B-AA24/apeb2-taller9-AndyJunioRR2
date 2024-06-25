import java.util.ArrayList;
import java.util.List;

public class EjecutorTrabajador {
    public static void main(String[] args) {
        Jefe jefe = new Jefe("Felipe", "Duque", "Calle 10 de Agosto", "1109875678", 6000.0);
        
        FijoMensual fijoMensual = new FijoMensual("Maria", "Cuenca", "Calle Reinaldo Espinoza", "1900589090", jefe, 4000.0);
        Comisionista comisionista = new Comisionista("Daniel", "Campoverde", "Calle Argentina", "1900489878", jefe, 10.0);
        PorHoras porHoras = new PorHoras("Jose", "Burneo", "Ciudadela Julio Ordoñez", "1109897867", jefe, 20.0, 10.0);

        jefe.darAltaTrabajador(fijoMensual);
        jefe.darAltaTrabajador(comisionista);
        jefe.darAltaTrabajador(porHoras);

        comisionista.ventasRealizadas(30000.00);
        porHoras.registrarHoras(55);
        
        fijoMensual.setSalario(fijoMensual.calcularSalario());
        comisionista.setSalario(comisionista.calcularSalario());
        porHoras.setSalario(porHoras.calcularSalario());

        fijoMensual.imprimirNomina();
        comisionista.imprimirNomina();
        porHoras.imprimirNomina();
        jefe.imprimirNomina();
        jefe.imprimirListaEmpleados();
    }
}

class Trabajador {
    protected String nombre;
    protected String apellidos;
    protected String direccion;
    protected String dni;
    protected double salario;
    protected Jefe jefe;

    public Trabajador(String nombre, String apellidos, String direccion, String dni, Jefe jefe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    public double calcularSalario() {
        return 0.0;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void imprimirNomina() {
        System.out.println("Nombre: " + nombre + " " + apellidos);
        System.out.println("DNI: " + dni);
        System.out.println("Dirección: " + direccion);
        System.out.println("Salario: " + salario);
    }
}

class FijoMensual extends Trabajador {
    private double salarioMensual;

    public FijoMensual(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double salarioMensual) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.salarioMensual = salarioMensual;
    }

    @Override
    public double calcularSalario() {
        return salarioMensual;
    }
}

class Comisionista extends Trabajador {
    private double porcentajeComision;
    private double ventasRealizadas;

    public Comisionista(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double porcentajeComision) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.porcentajeComision = porcentajeComision;
        this.ventasRealizadas = 0.0;
    }

    public void ventasRealizadas(double ventas) {
        this.ventasRealizadas += ventas;
    }

    @Override
    public double calcularSalario() {
        return ventasRealizadas * (porcentajeComision / 100);
    }
}

class PorHoras extends Trabajador {
    private double precioHora;
    private double horasTrabajadas;
    private double precioHoraExtra;

    public PorHoras(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double precioHora, double precioHoraExtra) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.precioHora = precioHora;
        this.precioHoraExtra = precioHoraExtra;
        this.horasTrabajadas = 0.0;
    }

    public void registrarHoras(double horas) {
        this.horasTrabajadas += horas;
    }

    @Override
    public double calcularSalario() {
        double hNormales = Math.min(40, horasTrabajadas);
        double hExtra = Math.max(0, horasTrabajadas - 40);
        return (hNormales * precioHora) + (hExtra * precioHoraExtra);
    }
}

class Jefe {
    private String nombre;
    private String apellidos;
    private String direccion;
    private String dni;
    private double salario;
    private List<Trabajador> empleados;

    public Jefe(String nombre, String apellidos, String direccion, String dni, double salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.salario = salario;
        this.empleados = new ArrayList<>();
    }

    public void darAltaTrabajador(Trabajador trabajador) {
        empleados.add(trabajador);
    }

    public void imprimirNomina() {
        System.out.println("Nombre: " + nombre + " " + apellidos);
        System.out.println("DNI: " + dni);
        System.out.println("Dirección: " + direccion);
        System.out.println("Salario: " + salario);
        System.out.println("Empleados a cargo: " + empleados.size());
    }

    public void imprimirListaEmpleados() {
        System.out.println("Lista de empleados a cargo de " + nombre + " " + apellidos + ":");
        for (Trabajador empleado : empleados) {
            System.out.println("- " + empleado.nombre + " " + empleado.apellidos + ", DNI: " + empleado.dni + ", Salario: " + empleado.salario);
        }
    }
}
