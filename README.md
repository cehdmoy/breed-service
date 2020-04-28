# breed-service

**Se trabaja bajo el supuesto que se ejecutarán estros script en SO del tipo Unix**

Para ejecutar estos servicios se debe tener las siguientes 
preciondiciones: 

- puerto: 8080 disponible
- puerto 8082 disponible

`Primero abrir una terminal en config-service y ejecutar el script start.sh por favor espere a que el servicio levante, no debería tomar más de un minuto`

`Luego abrir una terminal en breeds-api y ejecutar el script start.sh por favor espere a que el servicio levante, no debería tomar más de un minuto`

Estos scrips compilaran los MS usando los comandos maven y ejecurán java - jar, espere que cada uno de ellos termine y no cierre estas terminales

Una vez que los servicios estén arriba puede probar los siguientes ejemplplos:

- http://localhost:8080/breed/bulldog/ (este ejemplo debe dar una respuesta válida)
- http://localhost:8080/breed/myimaginarydog/ (este ejemplo dará un 404)

Para finalizar la aplicación por favor:

`Primero abrir una terminal en config-service y ejecutar el script stop.sh `

`Luego abrir una terminal en breeds-api y ejecutar el script stop.sh`

