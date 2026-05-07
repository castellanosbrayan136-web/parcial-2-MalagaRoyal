/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parcial2;

import view.ScreenManager;

/**
 * @author Brayan C
 */

public class Parcial2 {
    public static void main(String[] args) {
        ScreenManager.abrirLogin();
    }
}
/*
Especificaciones Técnicas: Casino "Málaga Royal"

El sector del entretenimiento digital en Santander busca renovar sus plataformas de juegos de azar tradicionales. 
Se requiere el desarrollo de un prototipo funcional de una Ruleta de Casino Francesa, que no solo permita el juego,
sino que gestione de forma integral la seguridad, el ingreso de usuarios y la trazabilidad financiera de sus saldos.

Se deben diseñar y programar una aplicación de escritorio robusta utilizando el lenguaje Java y la biblioteca Swing.
El sistema debe ser capaz de administrar múltiples usuarios,
permitiendo que cada uno mantenga su saldo y balance financiero de forma independiente durante la ejecución del programa.

 

Módulo de Autenticación y Registro

El sistema debe ser la "puerta de entrada" al casino.

Registro: El usuario debe poder crear una cuenta proporcionando sus datos personales y una contraseña segura. Cada nuevo usuario inicia con un saldo de $0.00.
Login: El sistema debe validar las credenciales contra una lista de jugadores existentes (ArrayList). Solo si el usuario y la contraseña coinciden,
el Manejador de Pantallas permitirá el acceso a la mesa de juego.
 

Módulo ruleta.

Dentro de la interfaz principal, el jugador debe tener el control total de sus fondos.

-Recarga de Saldo: El sistema debe permitir al usuario "inyectar" dinero a su cuenta en cualquier momento.
-Consulta en Tiempo Real: El saldo debe ser visible en todo momento y actualizarse automáticamente después de cada apuesta o recarga.
Juego:
-Mostrar números (0-36).
-Monto de la apuesta.
-Botón "Girar".
-Lógica del Juego (La Ruleta)

Mecánica de Apuesta: El jugador elige un número entre 0 y 36 y define un monto de dinero a arriesgar.
Validación de Riesgo: El sistema debe impedir que un jugador apueste más dinero del que tiene disponible.
El Giro: Al activar la ruleta, el software generará un número aleatorio.
Victoria: Si los números coinciden, el jugador recibe un premio de 35 veces su apuesta.
Derrota: Si no coinciden, el monto apostado se deduce permanentemente de su saldo.
Estándares de Ingeniería de Software

Para asegurar la calidad del producto, el proyecto debe cumplir con:

1. Arquitectura MVC: Separación estricta entre los datos (Modelo), la interfaz (Vista) y la lógica de control (Controlador).
2. Gestión de Memoria: Uso obligatorio de un Manejador de Pantallas que destruya las instancias de las ventanas anteriores al navegar, asegurando que no queden procesos ocultos consumiendo RAM.
3. Persistencia Temporal: Uso de colecciones (ArrayList) para mantener los datos de los jugadores mientras la aplicación esté en ejecución.


nota de ayuda: para generar aleatorios entre 0 y 36:

 int  numero = (int) (Math.random() * 37) ;
*/