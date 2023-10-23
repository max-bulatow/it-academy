package by.itacademy.dao.provider;

import by.itacademy.dao.Dao;
import by.itacademy.model.BaseModel;

public interface DaoProvider {

    <M extends BaseModel, D extends Dao<M>> D provide(Class<M> modelClass);

}
