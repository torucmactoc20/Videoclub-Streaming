=============================================
  SISTEMA DE VIDEOCLUB / STREAMING
  IFES - Análisis de Sistemas - Programación 1
=============================================

INTEGRANTES
-----------
- Tiago Silenzi - DNI: 46-404-501
- Tomas Barraza - DNI: 47-369-779

DESCRIPCIÓN
-----------
Sistema de gestión para un videoclub con funcionalidad de streaming.
Permite registrar contenido (películas, series y documentales),
gestionar suscriptores y empleados, controlar el alquiler y
devolución de contenido, y persistir toda la información entre
ejecuciones mediante serialización en archivos .dat.

CONCEPTOS APLICADOS
-------------------
- Herencia: Persona -> Suscriptor / Empleado
           Contenido -> Pelicula / Serie / Documental
- Polimorfismo: listas de Contenido con reproducir() y mostrarInfo()
- Encapsulamiento: atributos privados/protegidos con getters y setters
- Enum: EstadoContenido (DISPONIBLE, ALQUILADO, RESERVADO)
- Comparable / Comparator: ordenamiento de personas y contenido
- Java Collections: ArrayList, HashMap, HashSet
- Streams y Lambdas: filter, map, sorted, forEach, collect
- Excepciones personalizadas: ContenidoNoDisponibleException (checked)
- Serialización: persistencia con ObjectOutputStream / ObjectInputStream
- Genéricos: IRepositorio<T> / RepositorioArchivo<T>

ESTRUCTURA DEL PROYECTO
-----------------------
src/
  club/
    dominio/       -> Persona, Suscriptor, Empleado, Contenido,
                      Pelicula, Serie, Documental, EstadoContenido,
                      IReproducible
    excepcion/     -> ContenidoNoDisponibleException
    repositorio/   -> IRepositorio, RepositorioArchivo
    util/          -> CompararPorTitulo
    ui/            -> Videoclub, Menu, Main

INSTRUCCIONES DE EJECUCIÓN
--------------------------
1. Compilar desde la raíz del proyecto (carpeta club/):

   javac -d bin -sourcepath src src/club/ui/Main.java

2. Ejecutar:

   java -cp bin club.ui.Main

3. El archivo catalogo.dat se genera automáticamente en la
   carpeta desde donde se ejecuta el programa.

MENÚ PRINCIPAL
--------------
  1. Agregar Contenido al Catálogo
  2. Registrar Suscriptor
  3. Alquilar / Reproducir Contenido
  4. Devolver Contenido
  5. Mostrar Contenido Disponible
  6. Mostrar Catálogo Completo Ordenado por Título
  0. Salir

NOTAS
-----
- El catálogo se persiste automáticamente en catalogo.dat
  cada vez que se agrega o alquila contenido.
- Al iniciar, el sistema carga automáticamente los datos
  guardados en sesiones anteriores.
- Suscriptores de prueba precargados:
	
=============================================
