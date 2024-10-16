package com.other.app.NiralosFiveGCore.Repository.Slice;

import com.other.app.NiralosFiveGCore.model.Slice.SliceConfigrationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SliceMongoDb extends MongoRepository<SliceConfigrationModel, String> {
    List<SliceConfigrationModel> findBySstAndSd(Integer sst, String sd);
//    List<SliceConfigrationModel> findBySd(String sd);
}
