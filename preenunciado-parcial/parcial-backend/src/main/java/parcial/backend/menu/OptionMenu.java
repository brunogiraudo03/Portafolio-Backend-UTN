package parcial.backend.menu; // <-- Paquete ajustado

@FunctionalInterface
public interface OptionMenu<T> {
    void invocar(T context);
}