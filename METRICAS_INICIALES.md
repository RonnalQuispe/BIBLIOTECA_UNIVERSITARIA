# Metricas iniciales

Fecha de ejecucion local: 2026-11-23.

Comandos utilizados:

- `mvn clean verify`
- `mvn -B checkstyle:checkstyle pmd:pmd compile spotbugs:spotbugs`

## JaCoCo

El reporte real `target/site/jacoco/jacoco.xml` analiza una clase:
`Libro`.

| Resultado | Porcentaje | Cubierto | Sin cubrir |
|---|---:|---:|---:|
| Instrucciones | 77,97% (46/59) | 46 | 13 |
| Lineas | 80,95% (17/21) | 17 | 4 |
| Ramas | 50,00% (2/4) | 2 | 2 |

La cobertura total del proyecto coincide con la clase `Libro`, porque solo existe
una clase principal.

Lineas sin cubrir en `Libro.java`:

- Linea 45: conversion usada para `Integer.parseInt("ABC")`.
- Linea 48: el bloque `catch` vacio de `NumberFormatException`.
- Linea 53: el metodo `esMismoTitulo`.
- Linea 58: el metodo privado `registrarAuditoriaInterna`.

Ramas sin cubrir:

- Las dos ramas de la condicion en `esMismoTitulo`, que usa `==`.
- Las ramas cubiertas por las pruebas son las dos posibilidades de
  `!disponible` en `prestar`.

Interpretacion: existen 13 instrucciones y 4 lineas sin ejecutar. La clase
no alcanza la cobertura total debido a codigo artificial deshabilitado o sin
uso, no por falta de una cantidad amplia de comportamiento funcional.

## Checkstyle

Total real: 39 advertencias en `src/main/java/Libro.java`.

| Regla | Cantidad |
|---|---:|
| `IndentationCheck` | 36 |
| `MissingJavadocMethodCheck` | 2 |
| `MissingJavadocTypeCheck` | 1 |

Advertencias reales:

- Linea 1: falta Javadoc para la clase `Libro`.
- Lineas 3, 4, 5, 8, 10 a 58: sangrado con Google Checks.
- Linea 27: falta Javadoc del metodo `prestar`.
- Linea 40: falta Javadoc del metodo `devolver`.

Significado y correccion:

- El sangrado incorrecto dificulta la lectura y hace que las revisiones detecten
  cambios innecesarios. Se corrige usando una sangria uniforme de dos espacios.
- Los metodos publicos deben explicar su contrato. Javadoc permite documentar
  parametros, valores de retorno y excepciones.
- Falta Javadoc en la clase. Se corrige agregando una descripcion breve de la
  responsabilidad de `Libro`.

## PMD

Total real: 3 violaciones.

| Linea | Regla | Ubicacion | Significado y correccion |
|---|---|---|---|
| 8 | `UnusedPrivateField` | `codigoInterno` | Campo privado que nunca se lee. Se elimina o se integra a una funcion real del dominio. |
| 46 a 48 | `EmptyCatchBlock` | `devolver` | El bloque captura una excepcion pero no realiza ninguna accion. Oculta errores y codigo sin proposito. Se elimina el parseo artificial. |
| 57 | `UnusedPrivateMethod` | `registrarAuditoriaInterna` | Metodo privado sin invocaciones. Aumenta mantenimiento sin aportar comportamiento. Se elimina. |

Las tres violaciones son prioridad 3.

## SpotBugs

Total real: 2 defectos.

| Tipo | Categoria | Prioridad | Linea | Significado y correccion |
|---|---|---|---:|---|
| `ES_COMPARING_PARAMETER_STRING_WITH_EQ` | `BAD_PRACTICE` | 1 | 53 | Compara cadenas con identidad de objeto. Dos cadenas con el mismo texto pueden ser diferentes instancias. Se reemplaza por `Objects.equals` o `String.equals`. |
| `URF_UNREAD_FIELD` | `PERFORMANCE` | 2 | 8 | `codigoInterno` se asigna pero nunca se lee. Es codigo muerto y confunde el modelo. Se elimina. |
