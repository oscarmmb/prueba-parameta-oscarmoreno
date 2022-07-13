#Prueba Tecnica Oscar Moreno

##El proyecto implementa en Java un servicio Rest que recibe como parámetros los atributos de un objeto empleado, el cual al sera invocado mediante el método POST.
##El servicio valida los formatos de las fechas y que los atributos no esten vacíos,adicionalmente valida que el empleado sea mayor de edad. 
##Una vez se hayan superado estas validaciones, el proyecto almacena la información en una base de datos mysql.
##El Servicio Web responde con un objeto en estructura JSON, en el que se muestra el tiempo de vinculacion y la edad del empleado con formato (yyyy-mm-dd).

#Tecnologias Utilizadas.
##- SpringBoot (Sprung Tool Suite 4 (Eclipse)).
##- Maven 3.8.
##- MySQL 8.
##- Postman 9.
##- Docker.
##- Github.

#Ruta del Ejecutable.
##target/prueba-0.0.1-SNAPSHOT.jar

#Modo de Ejecucion.
#-Docker:
##docker build -t prueba-omoreno . //construir imagen
##docker run -p 8080:8080 -name prueba-omoreno-c prueba-omoreno // corre container
##docker ps -a //revisar containers que estén corriendo
#-Java:
##java -jar prueba/target/prueba-0.0.1-SNAPSHOT.jar

