package com.fastcampus.javaallinone.project3.mycontact.service;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.GroupDto;
import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.Group;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.exception.PersonNotFoundException;
import com.fastcampus.javaallinone.project3.mycontact.exception.RenameIsNotPermittedException;
import com.fastcampus.javaallinone.project3.mycontact.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

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
        Group group = groupRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        if (!group.getDescription().equals(groupDto.getDescription())) {
            throw new RenameIsNotPermittedException();
        }

        group.set(groupDto);

        groupRepository.save(group);
    }

    @Transactional
    public void delete(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("그룹이 존재하지 않습니다."));

        group.setDeleted(true);

        groupRepository.save(group);
    }

}
