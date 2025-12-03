package org.event.manage.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegModel {
    private int reg_id;
    private int eid;
    private int sid;
    private LocalDate date;
}
