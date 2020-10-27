package datn.base;

import datn.security.CurrentUserDetailsContainer;
import datn.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<E extends BaseEntity, R extends BaseRepository> {
    @Autowired
    protected R repository;

    @Autowired
    private CurrentUserDetailsContainer currentUserDetailsContainer;
    
    public CustomUserDetail getUserDetails(){
        return currentUserDetailsContainer.getUserDetail();
    }
    
    public E save(Long id){
        E entity = (E) repository.findOneById(id);
        if (entity == null){
            return null;
        }
        return (E) repository.save(entity);
    }
    public E update(Long id){
        E entity = (E) repository.findOneById(id);
        if (entity == null){
            return null;
        }
        return (E) repository.save(entity);
    }
    public Page<E> findAll(Pageable page) {
        return repository.findAll(page);
    }
    public E findById(Long id){
        return (E) repository.findOneById(id);
    }

}
