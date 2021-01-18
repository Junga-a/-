package com.fastcampus.javaallinone.project3.mycontact.domain;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.GroupDto;
import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.dta.Birthday;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ColumnDefault("0")
    private boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Person> personList;

    public void set(GroupDto groupDto) {
        if (!StringUtils.isEmpty(groupDto.getDescription())) {
            this.setDescription(groupDto.getDescription());
        }
    }

    public void addPerson(Person person)
    {
        person.setGroupId(id);
        personList.add(person);
        person.setGroup(this);
    }

}
