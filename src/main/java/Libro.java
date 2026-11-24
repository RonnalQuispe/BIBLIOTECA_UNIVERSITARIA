import java.util.Objects;

/**
 * Representa un libro disponible en la biblioteca universitaria.
 */
public final class Libro {

  private final String titulo;
  private final String autor;
  private boolean disponible = true;

  /**
   * Crea un libro disponible con titulo y autor.
   *
   * @param titulo titulo del libro
   * @param autor autor del libro
   * @throws IllegalArgumentException si el titulo o autor estan vacios
   * @throws NullPointerException si el titulo o autor son nulos
   */
  public Libro(String titulo, String autor) {
    this.titulo = requireText(titulo, "El titulo no puede ser nulo ni vacio");
    this.autor = requireText(autor, "El autor no puede ser nulo ni vacio");
  }

  /**
   * Obtiene el titulo del libro.
   *
   * @return titulo del libro
   */
  public String getTitulo() {
    return titulo;
  }

  /**
   * Obtiene el autor del libro.
   *
   * @return autor del libro
   */
  public String getAutor() {
    return autor;
  }

  /**
   * Indica si el libro esta disponible para prestar.
   *
   * @return true si esta disponible
   */
  public boolean estaDisponible() {
    return disponible;
  }

  /**
   * Marca el libro como prestado.
   *
   * @throws IllegalStateException si el libro ya estaba prestado
   */
  public void prestar() {
    if (!disponible) {
      throw new IllegalStateException("Libro no disponible");
    }

    disponible = false;
  }

  /**
   * Marca el libro como devuelto.
   */
  public void devolver() {
    disponible = true;
  }

  /**
   * Compara el titulo recibido con el titulo del libro.
   *
   * @param otroTitulo titulo que se desea comparar
   * @return true si ambos titulos tienen el mismo contenido
   */
  public boolean esMismoTitulo(String otroTitulo) {
    return Objects.equals(titulo, otroTitulo);
  }

  private static String requireText(String value, String message) {
    Objects.requireNonNull(value, message);
    if (value.trim().isEmpty()) {
      throw new IllegalArgumentException(message);
    }
    return value.trim();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Libro)) {
      return false;
    }

    Libro libro = (Libro) other;
    return Objects.equals(titulo, libro.titulo)
        && Objects.equals(autor, libro.autor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titulo, autor);
  }

  @Override
  public String toString() {
    return "Libro{titulo='" + titulo + "', autor='" + autor
        + "', disponible=" + disponible + '}';
  }
}