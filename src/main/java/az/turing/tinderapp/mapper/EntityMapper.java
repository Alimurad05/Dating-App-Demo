package az.turing.tinderapp.mapper;

public interface EntityMapper<E,D> {
    E toEntity(D dto);
    D toDto(E entity);
}
