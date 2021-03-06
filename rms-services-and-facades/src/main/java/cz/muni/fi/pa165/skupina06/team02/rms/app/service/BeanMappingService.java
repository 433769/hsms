package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;

/**
 * @author github.com/fi-muni/PA165
 */
public interface BeanMappingService {
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();
}
