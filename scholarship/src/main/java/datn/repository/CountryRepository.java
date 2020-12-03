package datn.repository;

import datn.base.BaseRepository;
import datn.entity.CountryEntity;

import java.util.List;

public interface CountryRepository extends BaseRepository<CountryEntity>{
    List<CountryEntity> findByOrderByNameAsc();
}
