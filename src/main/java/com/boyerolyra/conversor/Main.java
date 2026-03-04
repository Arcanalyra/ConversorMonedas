package com.boyerolyra.conversor;

import com.boyerolyra.conversor.service.ExchangeRateService;
import com.boyerolyra.conversor.util.Conversor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ExchangeRateService service = new ExchangeRateService();

        while (true) {

            try {
                System.out.println("\n=== CONVERSOR DE MONEDAS ===");
                System.out.println("1) Convertir moneda");
                System.out.println("0) Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer

                if (opcion == 0) {
                    System.out.println("Gracias por usar el conversor 👋");
                    break;
                }

                if (opcion == 1) {

                    System.out.print("Moneda base (USD, EUR, MXN, TRY, EGP, CZK, GBP, RUB): ");
                    String base = scanner.nextLine().toUpperCase();

                    System.out.print("Moneda destino: ");
                    String destino = scanner.nextLine().toUpperCase();

                    System.out.print("Monto a convertir: ");
                    double monto = scanner.nextDouble();
                    scanner.nextLine(); // limpiar buffer

                    double tasa = service.obtenerTasa(base, destino);
                    double resultado = Conversor.convertir(monto, tasa);

                    System.out.println("Resultado: " + resultado + " " + destino);
                } else {
                    System.out.println("Opción inválida.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // limpiar buffer si hay error
            }
        }

        scanner.close();
    }
}