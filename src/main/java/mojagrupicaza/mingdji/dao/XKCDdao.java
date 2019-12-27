package mojagrupicaza.mingdji.dao;

import mojagrupicaza.mingdji.model.XKCD_infoObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XKCDdao extends MongoRepository<XKCD_infoObject, String> {




}
