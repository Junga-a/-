package com.fastcampus.javaallinone.project3.mycontact.service;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.GroupDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.Group;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.repository.GroupRepository;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public Page<Group> getAll(PageRequest pageable) {
        return groupRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Group getGroup(Long id) {
        Group group = groupRepository.findById(id).orElse(null);

        return group;
    }

    @Transactional
    public void put(GroupDto groupDto) {
        Group group = new Group();
        group.set(groupDto);
        group.setDescription(groupDto.getDescription());

        groupRepository.save(group);
    }

    @Transactional
    public void modify(Long id, GroupDto groupDto) {
        Group group = groupRepository.findById(id).orElseThrow();

        group.set(groupDto);

        groupRepository.save(group);
    }

    @Transactional
    public void delete(Long id) {
        Group group = groupRepository.findById(id).orElseThrow();

        group.setDeleted(true);

        groupRepository.save(group);
    }

    @Transactional
    public void putPersonInGroup(Long id, Long personId) {
        Group group = groupRepository.findById(id).orElseThrow();

        group.addPerson(personService.getPerson(personId));

        groupRepository.save(group);
    }

    @Transactional(readOnly = true)
    public List<Person> getPeopleInGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow();

        List<Person> personList = personRepository.findByGroupId(id);

        return personList;

    }

}
