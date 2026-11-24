import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibroTest {

  private Libro libro;

  @BeforeEach
  void crearLibroDisponible() {
    libro = new Libro("1984", "George Orwell");
  }

  @Test
  void deberiaCrearLibroDisponibleConDatosValidos() {
    assertEquals("1984", libro.getTitulo());
    assertEquals("George Orwell", libro.getAutor());
    assertTrue(libro.estaDisponible());
  }

  @Test
  void deberiaPrestarLibroDisponible() {
    libro.prestar();

    assertFalse(libro.estaDisponible());
  }

  @Test
  void deberiaLanzarErrorAlPrestarLibroNoDisponible() {
    libro.prestar();

    assertThrows(IllegalStateException.class, libro::prestar);
  }

  @Test
  void deberiaDevolverLibroPrestado() {
    libro.prestar();
    libro.devolver();

    assertTrue(libro.estaDisponible());
  }

  @Test
  void deberiaPermitirDevolverLibroYaDisponible() {
    libro.devolver();

    assertTrue(libro.estaDisponible());
  }

  @Test
  void deberiaCompararTitulosIgualesPorContenido() {
    String otroTitulo = new String("1984");

    assertTrue(libro.esMismoTitulo(otroTitulo));
  }

  @Test
  void deberiaRechazarTituloDiferente() {
    assertFalse(libro.esMismoTitulo("Rebelion en la granja"));
  }

  @Test
  void deberiaRechazarTituloNulo() {
    assertFalse(libro.esMismoTitulo(null));
  }

  @Test
  void deberiaRechazarTituloDeConstructorNulo() {
    assertThrows(
        NullPointerException.class,
        () -> new Libro(null, "George Orwell")
    );
  }

  @Test
  void deberiaRechazarTituloVacio() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Libro("  ", "George Orwell")
    );
  }

  @Test
  void deberiaRechazarAutorNulo() {
    assertThrows(
        NullPointerException.class,
        () -> new Libro("1984", null)
    );
  }

  @Test
  void deberiaRechazarAutorVacio() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Libro("1984", "")
    );
  }

  @Test
  void deberiaEliminarEspaciosExterioresDeDatosValidos() {
    Libro libroConEspacios = new Libro("  1984  ", "  George Orwell  ");

    assertEquals("1984", libroConEspacios.getTitulo());
    assertEquals("George Orwell", libroConEspacios.getAutor());
  }

  @Test
  void deberiaConsiderarIgualesLibrosConMismoTituloYAutor() {
    Libro otroLibro = new Libro("1984", "George Orwell");

    assertEquals(libro, otroLibro);
    assertEquals(libro.hashCode(), otroLibro.hashCode());
  }

  @Test
  void deberiaConsiderarDiferentesLibrosConDistintoAutor() {
    Libro otroLibro = new Libro("1984", "Orwell");

    assertNotEquals(libro, otroLibro);
  }

  @Test
  void deberiaIncluirDatosEnLaRepresentacionTextual() {
    assertTrue(libro.toString().contains("titulo='1984'"));
    assertTrue(libro.toString().contains("autor='George Orwell'"));
    assertTrue(libro.toString().contains("disponible=true"));
  }
}