# Metricas finales

Fecha de ejecucion local: 2026-11-23.

Comandos utilizados:

- `mvn clean verify`
- `mvn -B checkstyle:checkstyle pmd:pmd compile spotbugs:spotbugs`

## Tabla comparativa

| Herramienta | Resultado inicial | Mejora o analisis | Resultado final |
|---|---|---|---|
| JaCoCo | 77,97% instrucciones; 80,95% lineas; 50,00% ramas | Se habilito la prueba deshabilitada y se agregaron 11 pruebas adicionales para validaciones, excepciones, igualdad y representacion | 96,46% instrucciones (109/113); 92,86% lineas (26/28); 75,00% ramas (9/12) |
| Checkstyle | 39 advertencias: 36 de sangrado, 2 Javadoc de metodos y 1 Javadoc de clase | Se aplico sangrado uniforme de Google Checks y Javadoc en la API publica | 0 advertencias |
| PMD | 3 violaciones: campo privado sin uso, catch vacio y metodo privado sin uso | Se eliminaron los elementos sin uso y el parseo artificial; `devolver` quedo como operacion directa | 0 violaciones |
| SpotBugs | 2 defectos: comparacion `==` de Strings y campo sin leer | Se cambio a `Objects.equals`, se elimino el campo y se hizo `Libro` final para prevenir ataques por finalizers | 0 defectos |

## Detalle final de JaCoCo

El reporte real `target/site/jacoco/jacoco.xml` registra:

- Instrucciones: 109 cubiertas y 4 sin cubrir, 96,46%.
- Lineas: 26 cubiertas y 2 sin cubrir, 92,86%.
- Ramas: 9 cubiertas y 3 sin cubrir, 75,00%.

Lineas finales sin cubrir:

- 93: la rama de identidad cuando `equals` recibe el mismo objeto.
- 96: la rama de tipo contrario cuando `equals` recibe otro tipo.

Ramas finales sin cubrir:

- Tres ramas en `equals` y comparaciones de tipo/identidad.

La cobertura no alcanza 100% porque las pruebas no necesitan cubrir
artificialmente todas las ramas defensivas de `equals`. El aumento respecto del
estado inicial es de 18,49 puntos en instrucciones, 11,91 puntos en lineas y
25 puntos en ramas.

## Detalle final de las herramientas

### Checkstyle

Resultado real: 0 advertencias.

El archivo final `target/checkstyle-result.xml` contiene el archivo
`Libro.java` sin elementos `<error>`. Esto confirma sangrado, Javadoc y
estructura conforme al conjunto `google_checks.xml`.

### PMD

Resultado real: 0 violaciones.

El archivo final `target/pmd.xml` no contiene elementos `violation`.

### SpotBugs

Resultado real: 0 defectos.

El resumen final de `target/spotbugsXml.xml` indica `total_bugs='0'`.

## Pruebas JUnit

Resultado real en `target/surefire-reports/TEST-LibroTest.xml`:

- Pruebas ejecutadas: 16.
- Fallos: 0.
- Errores: 0.
- Omitidas: 0.

Las pruebas cubren:

- Creacion con datos validos.
- Prestamo disponible y repetido.
- Devolucion disponible y prestada.
- Comparacion por contenido.
- Titulos nulos y diferentes.
- Titulos y autores nulos o vacios.
- Eliminacion de espacios exteriores.
- `equals` y `hashCode`.
- `toString`.
