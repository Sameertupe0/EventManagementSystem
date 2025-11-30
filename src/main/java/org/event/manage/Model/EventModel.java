package org.event.manage.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventModel {
    private int eid;
    private String name;
    private LocalDate edate;
    private String venue;
    private int capacity;

}
