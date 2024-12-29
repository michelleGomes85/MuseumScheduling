package tsi.daw.museumscheduling.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ReservationDAO {

    private EntityManager manager;

    public ReservationDAO() {
        manager = new JPAUtil().getEntityManager(); // Certifique-se de que o JPAUtil esteja implementado corretamente
    }

    /**
     * Obtém o número total de pessoas reservadas para um museu em uma data e hora específicos.
     *
     * @param museumId ID do museu.
     * @param date     Data da reserva.
     * @param time     Hora da reserva.
     * @return Número de pessoas reservadas.
     */
    public int getReservedPeople(Long museumId, LocalDate date, LocalTime time) {
    	
        String jpql = "SELECT SUM(h.reservedPeople) FROM HourlyReservation h WHERE h.museum.id = :museumId AND h.date = :date AND h.time = :time";

        TypedQuery<Long> query = manager.createQuery(jpql, Long.class);
        query.setParameter("museumId", museumId);
        query.setParameter("date", date);
        query.setParameter("time", time);

        return Optional.ofNullable(query.getSingleResult()).orElse(0L).intValue();
    }
}
