package datn.custom.specs;

import datn.custom.dto.UserFilter;
import datn.entity.user.AccountEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AccountSpecs {

    private AccountSpecs() {
        throw new IllegalStateException("Spring Sprecs class");
    }

    public static Specification<AccountEntity> filterById(long id) {
        return (Root<AccountEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder.equal(root.get("id"), id);
    }

    public static Specification<AccountEntity> filterByName(String name) {
        return (Root<AccountEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<AccountEntity> filterByDeleted(boolean isDeleted) {
        return (Root<AccountEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder.equal(root.get("isDeleted"), isDeleted);
    }

    public static Specification<AccountEntity> getUsers(UserFilter filter) {

        Specification specification = Specification.where(filterByDeleted(false));

        if (filter.getId() != null) {
            specification = Specification.where(specification).and(filterById(filter.getId()));
        }

        if (filter.getName() != null) {
            specification = Specification.where(specification).and(filterByName(filter.getName()));
        }

        return specification;
    }
}
