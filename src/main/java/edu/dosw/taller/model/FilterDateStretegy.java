package edu.dosw.taller.model;

import java.time.LocalDateTime;

public class FilterDateStretegy implements FilterStrategy{
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    public FilterDateStretegy(LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public List<Task> filter(List<Task> tasks) {
        return;
    }
}

