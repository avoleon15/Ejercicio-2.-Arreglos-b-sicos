import java.util.ArrayList;
import java.util.Scanner;

//ALVARO JOSE LEON AGUILAR 23274
//UVG EJERCICIO 2 ARREGLOS DE OBJETOS


public class Hotel {
    public static void main(String[] args){
        //Se nombran las variables
        Scanner sc = new Scanner(System.in);
        String tipo_cliente = "";
        int precio_habitacion = 0;
        String tipo_habitacion = "";
        int clase_habitacion = 0;

        //Dos listas, una para mostrara las habitaciones y otra para guardar el tipo de la habitacion
        ArrayList<String> habitaciones = new ArrayList<>();
        ArrayList<String> accesibilidad = new ArrayList<>();


        int opcion1;
        boolean salir = false;

        //Menu con todas las opciones
        while (!salir) {
            System.out.println("\n\n1. Empleado");
            System.out.println("2. Cliente");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion1 = sc.nextInt();

            //opcion 1 en donde se ingresan las habitaciones disponibles a la base
            switch (opcion1) {
                case 1:
                    //Dependiendo de la cantidad de habitaciones que se quiera ingresar se repite el ciclo
                    sc.nextLine();
                    System.out.print("Ingresar cantidad de habitaciones que desea ingresar: ");
                    int num_solicitudes = sc.nextInt();

                    for (int i = 0; i < num_solicitudes; i++) {

                        System.out.print("Ingresar piso de habitacion: ");
                        int piso_habitacion = sc.nextInt();

                        System.out.print("Ingresar numero de habitacion: ");
                        int numero_habitacion = sc.nextInt();

                        sc.nextLine();
                        System.out.print("Ingresar clase de habitacion: \n1. Estandar \n2. Deluxe \n3. Suite \n");
                        clase_habitacion = sc.nextInt();

                        if (clase_habitacion == 1){
                            tipo_habitacion = "Estandar";
                        } else if (clase_habitacion == 2){
                            tipo_habitacion = "Deluxe";
                        } else if (clase_habitacion == 3){
                            tipo_habitacion = "Suite";
                        }
                        else{
                            System.out.println("Opcion invalida");
                        }
                        //El numero de piso y el numero de cuarto se suman para dar un numero al cuarto.
                        System.out.println(" Se genero la habitacion #" + piso_habitacion + numero_habitacion + " - " + tipo_habitacion );

                        //Se ingresan los datos a las listas
                        habitaciones.add(" habitacion #" + piso_habitacion + numero_habitacion + " - " + tipo_habitacion );
                        accesibilidad.add(tipo_habitacion);
                    }

                    break;

                case 2:
                    //Primer paso es verificar el estatus del cliente, dependiendo de la cantidad de visitas a este se le define un estatus
                    sc.nextLine();
                    System.out.print("Ingresar el numero de veces que nos ha visitado: ");
                    int num_visitas = sc.nextInt();
                    if (num_visitas < 5){
                        tipo_cliente = "Regulares";
                    } else if (num_visitas >= 5 && num_visitas < 10){
                        tipo_cliente = "Frecuente";
                    } else if (num_visitas >= 10){
                        tipo_cliente = "VIP";
                    }

                    //Mostrar a que habitaciones tiene acceso
                    sc.nextLine();
                    System.out.println("El estatus del cliente es '" + tipo_cliente + "'");
                    if (tipo_cliente == "Regulares"){
                        System.out.println("Unicamente tiene acceso a los cuartos estandar.");
                    } else if (tipo_cliente == "Frecuente"){
                        System.out.println("Tiene acceso a las habitaciones estandar y las deluxe.");
                    } else if (tipo_cliente == "VIP"){
                        System.out.println("Tiene acceso a todas las habitaciones, incluyendo las suites.");
                    }

                    //Imprimir la lista con la informacion de las habitaciones dispoibles
                    System.out.println("HABITACIONES DISPONIBLES");
                    for (int i = 0; i < habitaciones.size(); i++) {
                        String habitacion = habitaciones.get(i);
                        System.out.println( (i + 1) + ": " + habitacion);
                    }
                    //El usuario ingresa la habitacion que desea reservar
                    System.out.println("\nIngrese la posicion de la habitacion como aparece en la lista que desea reservar: ");
                    int numero_solicitud = sc.nextInt();
                    String habitacion_solicitud = habitaciones.get(numero_solicitud - 1);
                    System.out.println(habitacion_solicitud);

                    //Para confirmar si es la habitacion que eligio
                    System.out.println("\n¿Es esta la habitacion que desea reservar? \n1. Si \n2. No");
                    int res = sc.nextInt();
                    if (res == 1){
                        String confirmacion = "";
                        confirmacion = accesibilidad.get(numero_solicitud - 1);
                        //Si la habitacion es de tipo estandar, cualquier cliente la puede reservar
                        if (confirmacion == "Estandar"){
                            System.out.println("Su habitacion a sido reservada exitosamente");
                            //Si la habitacion fue reservada exitosamente se elimina de la lista de opciones
                            habitaciones.remove(numero_solicitud - 1);
                            accesibilidad.remove(numero_solicitud - 1);

                            //Si la habitacion es de tupo deluxe, solo los clientes con estatus frecuente y VIP la pueden reservar
                        } else if (confirmacion == "Deluxe"){
                            if (tipo_cliente == "Frecuente" || tipo_cliente == "VIP"){
                                System.out.println("Su habitacion a sido reservada exitosamente");
                                //Si la habitacion fue reservada exitosamente se elimina de la lista de opciones
                                habitaciones.remove(numero_solicitud - 1);
                                accesibilidad.remove(numero_solicitud - 1);
                            } else {
                                //Si el usuario no tiene el estatus adecuado se manda a la lista de espera
                                System.out.println("USTED FUE MANDADO A LA LISTA DE ESPERA");
                            }

                            //Si la habitacion es una suite solo los clientes VIP la pueden reservar
                        } else if (confirmacion == "Suite"){
                            if (tipo_cliente == "VIP"){
                                System.out.println("Su habitacion a sido reservada exitosamente");
                                //Si la habitacion fue reservada exitosamente se elimina de la lista de opciones
                                habitaciones.remove(numero_solicitud - 1);
                                accesibilidad.remove(numero_solicitud - 1);

                            //Si el usuario no tiene el estatus adecuado se manda a la lista de espera
                            } else {
                                System.out.println("USTED FUE MANDADO A LA LISTA DE ESPERA");
                            }
                        }
                    }
                    break;

                case 3:
                    //Terminar el programa
                    System.out.println("¡Hasta luego!");
                    salir = true;
                    break;
                    //Si el usuario no elije una opcion del menu saldra este mensaje.
                default:
                    System.out.println("Opción inválida. Por favor, elija una opción válida.");
                
            }
        }
    }
}

