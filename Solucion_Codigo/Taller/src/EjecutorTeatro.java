import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Teatro {
    private List<Zona> zonas;

    public Teatro() {
        zonas = new ArrayList<>();
        inicializarZonas();
    }

    private void inicializarZonas() {
        zonas.add(new Zona("Principal", 200, 25, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70, 40));
        zonas.add(new Zona("Central", 400, 20, 14));
        zonas.add(new Zona("Lateral", 100, 15.5, 10));
    }

    public String venderEntrada(String nombreZona, String nombreComprador, String tipoEntrada) {
        for (Zona zona : zonas) {
            if (zona.getNombre().equals(nombreZona)) {
                if (zona.disponibilidad()) {
                    Entrada entrada = zona.venderEntrada(nombreComprador, tipoEntrada);
                    return "ID: " + entrada.getId() + ", Precio: " + entrada.getPrecio();
                } else {
                    return "La zona elegida está completa.";
                }
            }
        }
        return "No existe ninguna zona con ese nombre.";
    }

    public String consultarEntrada(int id) {
        for (Zona zona : zonas) {
            Entrada entrada = zona.getEntradaPorId(id);
            if (entrada != null) {
                return "Nombre del comprador: " + entrada.getNomComprador() +
                       ", Precio: " + entrada.getPrecio() +
                       ", Zona: " + entrada.getZona().getNombre();
            }
        }
        return "No existe ninguna entrada con ese identificador.";
    }
}

class Zona {
    private String nombre;
    private int numLocalidades;
    private double precioNormal;
    private double precioAbonado;
    private int localidadesVendidas;
    private List<Entrada> entradas;
    private static int contadorEntradas = 0;

    public Zona(String nombre, int numLocalidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.numLocalidades = numLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.localidadesVendidas = 0;
        this.entradas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean disponibilidad() {
        return localidadesVendidas < numLocalidades;
    }

    public Entrada venderEntrada(String nombreComprador, String tipoEntrada) {
        Entrada entrada = null;
        switch (tipoEntrada) {
            case "normal":
                entrada = new EntradaNormal(contadorEntradas++, this, nombreComprador);
                break;
            case "abonado":
                entrada = new EntradaAbonado(contadorEntradas++, this, nombreComprador);
                break;
            case "reducida":
                entrada = new EntradaReducida(contadorEntradas++, this, nombreComprador);
                break;
        }
        entradas.add(entrada);
        localidadesVendidas++;
        return entrada;
    }

    public Entrada getEntradaPorId(int id) {
        for (Entrada entrada : entradas) {
            if (entrada.getId() == id) {
                return entrada;
            }
        }
        return null;
    }

    public double getPrecioNormal() {
        return precioNormal;
    }

    public double getPrecioAbonado() {
        return precioAbonado;
    }
}

abstract class Entrada {
    protected int id;
    protected String nomComprador;
    protected double precio;
    protected Zona zona;

    public Entrada(int id, Zona zona, String nomComprador) {
        this.id = id;
        this.zona = zona;
        this.nomComprador = nomComprador;
    }

    public int getId() {
        return id;
    }

    public String getNomComprador() {
        return nomComprador;
    }

    public double getPrecio() {
        return precio;
    }

    public Zona getZona() {
        return zona;
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(int id, Zona zona, String nomComprador) {
        super(id, zona, nomComprador);
        this.precio = zona.getPrecioNormal();
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(int id, Zona zona, String nomComprador) {
        super(id, zona, nomComprador);
        this.precio = zona.getPrecioNormal() * 0.85;
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, Zona zona, String nomComprador) {
        super(id, zona, nomComprador);
        this.precio = zona.getPrecioAbonado();
    }
}

public class EjecutorTeatro {
    public static void main(String[] args) {
        Teatro teatro = new Teatro();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Vender entrada");
            System.out.println("2. Consultar entrada");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la zona deseada: ");
                    String zona = scanner.nextLine();
                    System.out.print("Ingrese el nombre del comprador: ");
                    String nombreComprador = scanner.nextLine();
                    System.out.print("Ingrese el tipo de entrada (normal, abonado, reducida): ");
                    String tipoEntrada = scanner.nextLine();
                    String resultadoVenta = teatro.venderEntrada(zona, nombreComprador, tipoEntrada);
                    System.out.println(resultadoVenta);
                    break;
                case 2:
                    System.out.print("Ingrese el identificador de la entrada: ");
                    int idEntrada = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer
                    String resultadoConsulta = teatro.consultarEntrada(idEntrada);
                    System.out.println(resultadoConsulta);
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
