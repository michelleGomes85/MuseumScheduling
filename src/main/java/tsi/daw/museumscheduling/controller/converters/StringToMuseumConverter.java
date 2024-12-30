package tsi.daw.museumscheduling.controller.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tsi.daw.museumscheduling.model.Museum;
import tsi.daw.museumscheduling.dao.DAO;

@Component
public class StringToMuseumConverter implements Converter<String, Museum> {

    @Override
    public Museum convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(source);
            DAO<Museum> dao = new DAO<>(Museum.class);
            return dao.findById(id); 
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID do museu inválido: " + source, e);
        }
    }
}

