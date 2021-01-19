package cz.cvut.fel.ear.semestralka.dao;

import cz.cvut.fel.ear.semestralka.domain.Editor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource
@PreAuthorize("hasAnyRole('ADMIN', 'DIRECTOR')")
public interface EditorRepository extends CrudRepository<Editor, Long> {

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'DIRECTOR', 'EDITOR', 'REVIEWER', 'AUTHOR')")
    Optional<Editor> findById(@Param("id") Long id);

    @Override
    @PreAuthorize("isAuthenticated()")
    Iterable<Editor> findAll();

    @Override
    @PreAuthorize("hasRole('ADMIN') or #editor?.email == authentication.name")
    Editor save(@Param("editor") Editor editor);

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@Param("editor") Editor editor);

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void deleteById(@Param("id") Long id);

    @Override
    @PreAuthorize("denyAll()")
    void deleteAll(Iterable<? extends Editor> entities);

    @Override
    @PreAuthorize("denyAll()")
    void deleteAll();

    @PreAuthorize("isAuthenticated()")
    Optional<Editor> findEditorByCategory_CategoryId(@Param("id") long id);

    @PreAuthorize("isAuthenticated()")
    Optional<Editor> findByEmail(@Param("email") String email);
}
