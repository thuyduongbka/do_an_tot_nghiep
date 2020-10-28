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
        entity.setCode(UUID.randomUUID());

        if (getCurrentUser() != null) {
            entity.setCreatedByUserId(getCurrentUser().getAdminId());
        }

        if (getCurrentUser() != null) {
            entity.setUpdatedByUserId(getCurrentUser().getAdminId());
        }

        entity.setCreatedTime(new Date());
        entity.setUpdatedTime(new Date());
    }

    /**
     * Save List (Author: Hoang Van Toan)
     */

    public List<E> saveList(List<E> entities) {
        for (E entity : entities) {
            preSave(entity);
        }
        return repository.saveAll(entities);
    }


    /**
     * Service Read
     */
    public E findByCode(String code) {
        return findByCode(UUID.fromString(code));
    }

    public Long findIdByCode(String code) {
        Object entity = repository.findByCode(UUID.fromString(code));

        if (entity == null) {
            return null;
        }

        return ((E) entity).getId();
    }

    public String findCodeById(Long id) {
        Optional<E> entity = repository.findById(id);

        if (entity == null || entity.isPresent() == false) {
            return null;
        }

        return (entity.get()).getCode().toString();
    }


    /**
     * Service Read
     */
    public E findByCode(UUID code) {
        Object entity = repository.findByCode(code);

        if (entity == null) {
            return null;
        }

        return (E) entity;
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
    public boolean delete(String code) {
        return delete(UUID.fromString(code));
    }

    /**
     * Delete
     */
    public boolean delete(UUID code) {
        Object entityObj = repository.findByCode(code);
        if (entityObj == null) {
            return false;
        } else {
            E entity = (E) entityObj;
            entity.setDeleted(true); // logic delete
            entity.setUpdatedByUserId(getCurrentUser().getAdminId());
            repository.save(entity);
            return true;
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

}
