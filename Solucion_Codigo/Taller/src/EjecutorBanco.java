class Cuenta {
    public String nomCliente;
    public String numCuenta;
    public double balance;

    public Cuenta(String nomCliente, String numCuenta) {
        this.nomCliente = nomCliente;
        this.numCuenta = numCuenta;
        this.balance = 0.0;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            balance += cantidad;
        }
    }

    // Implementación genérica de retirar
    public void retirar(double cantidad) {
        if (balance >= cantidad) {
            balance -= cantidad;
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Cliente: " + nomCliente + ", Numero de Cuenta: " + numCuenta + ", Balance: " + balance + "\n";
    }
}

// Subclase Cheque
class Cheque extends Cuenta {
    public Cheque(String nomCliente, String numCuenta) {
        super(nomCliente, numCuenta);
    }

    @Override
    public void retirar(double cantidad) {
        balance -= cantidad; 
    }
}

// Subclase Ahorros
class Ahorros extends Cuenta {
    private static final double INTERES = 0.02; 

    public Ahorros(String nomCliente, String numCuenta) {
        super(nomCliente, numCuenta);
    }

    @Override
    public void retirar(double cantidad) {
        if (balance >= cantidad) {
            balance -= cantidad; 
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public void calcularInteres() {
        balance += balance * INTERES;
    }
}

// Subclase Platino
class Platino extends Cuenta {
    private static final double INTERES = 0.10; 

    public Platino(String nomCliente, String numCuenta) {
        super(nomCliente, numCuenta);
    }

    @Override
    public void retirar(double cantidad) {
        balance -= cantidad; 
    }

    public void calcularInteres() {
        balance += balance * INTERES;
    }
}

public class EjecutorBanco {
    public static void main(String[] args) {
        // Creando cuentas
        Cheque cuentaCheque = new Cheque("Luis Campos", "1178695878");
        Ahorros cuentaAhorros = new Ahorros("Jennifer Jarro", "1387675647");
        Platino cuentaPlatino = new Platino("Daniela Cuenca", "1234567891");

        cuentaCheque.depositar(1000);
        cuentaCheque.retirar(500);
        System.out.println(cuentaCheque);

        cuentaAhorros.depositar(2000);
        cuentaAhorros.retirar(1000);
        cuentaAhorros.calcularInteres();
        System.out.println(cuentaAhorros);

        cuentaPlatino.depositar(3000);
        cuentaPlatino.retirar(1500);
        cuentaPlatino.calcularInteres();
        System.out.println(cuentaPlatino);
    }
}