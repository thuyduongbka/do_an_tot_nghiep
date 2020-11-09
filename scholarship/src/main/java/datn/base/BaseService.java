package datn.base;

import datn.security.CurrentUserDetailsContainer;
import datn.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author kienta
 */
@Service
public abstract class BaseService<E extends BaseEntity, R extends BaseRepository> {

    @Autowired
    protected R repository;

    @Autowired
    private CurrentUserDetailsContainer currentUserDetailsContainer;

    public CustomUserDetails getCurrentUser() {
        return this.currentUserDetailsContainer.getUserDetails();
    }

//    public boolean auth(ApiMode apiMode) {
//        return true;
//    }

    /**
     * Create
     */
    public E save(E entity) {
        if (entity == null) {
            return null;
        }

        preSave(entity);

        return (E) repository.save(entity);
    }

    public void preSave(E entity) {
        /**
         * gen UUID code
         */
        if (entity.getId() != null) {
            throw new IllegalStateException("Cannot call save for saved entity");
        }

        if (getCurrentUser() != null) {
            entity.setCreatedByUserId(getCurrentUser().getAdminId());
        }

        if (getCurrentUser() != null) {
            entity.setUpdatedByUserId(getCurrentUser().getAdminId());
        }

        entity.setCreatedTime(new Date());
        entity.setUpdatedTime(new Date());
    }

    public List<E> saveList(List<E> entities) {
        for (E entity : entities) {
            preSave(entity);
        }
        return repository.saveAll(entities);
    }

    /**
     * Service Read
     */
    public E findById(Long id) {
        Object entity = repository.findById(id).orElse(null);

        if (entity == null) {
            return null;
        }

        return (E) entity;
    }

    /**
     * Update
     */
    public E update(E entity) {
        if (entity.getId() == null) {
            throw new IllegalStateException("Cannot call update entity for entity has id is null");
        }
        if (entity == null) {
            return null;
        } else {
            entity.setUpdatedTime(new Date());

            if (getCurrentUser() != null)
                entity.setUpdatedByUserId(getCurrentUser().getAdminId());

            return (E) repository.save(entity);
        }
    }

    /**
     * Delete
     */
    public void delete(E entity) {
        repository.deleteById(entity.getId());
    }

    /**
     * Get findAll
     *
     * @param page
     * @return
     */
    public Page<E> findAll(Pageable page) {
        return repository.findAll(page);
    }

    /**
     * Get findAll
     *
     * @param page
     * @return
     */
    public Page<E> findAll(Pageable page, boolean isDeleted) {
        return repository.findByIsDeleted(page, isDeleted);
    }
    
    public List<E> findAll() {
        return repository.findAll();
    }
}
