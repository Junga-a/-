package com.fastcampus.javaallinone.project3.mycontact.controller;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.GroupDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.Group;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.repository.GroupRepository;
import com.fastcampus.javaallinone.project3.mycontact.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/group")
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public Page<Group> getAll(@PageableDefault Pageable pageable) {
        return groupService.getAll((PageRequest) pageable);
    }

    @GetMapping("/{id}")
    public Group getGroup(@PathVariable Long id) {
        return groupService.getGroup(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postGroup(@RequestBody @Valid GroupDto groupDto) {
        groupService.put(groupDto);
    }

    @PatchMapping("/{id}")
    public void modifyGroup(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        groupService.modify(id, groupDto);
    }

    @GetMapping("/{id}/people")
    public List<Person> getPeopleInGroup(@PathVariable Long id) {
        return groupService.getPeopleInGroup(id);
    }

    @PutMapping("/{id}/person/{personId}")
    public void putPersonInGroup(@PathVariable Long id, @PathVariable Long personId) {
        groupService.putPersonInGroup(id, personId);
    }

}
