package datn.repository;

import datn.base.BaseRepository;
import datn.entity.SchoolEntity;

import java.util.List;

public interface SchoolRepository extends BaseRepository<SchoolEntity> {
    List<SchoolEntity> findByCountryIdIn(List<Long> countryId);
}
