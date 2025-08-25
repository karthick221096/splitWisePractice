package com.tharuna.splitwisepractice.service;

import com.tharuna.splitwisepractice.dto.request.GroupCreationRequestDto;
import com.tharuna.splitwisepractice.model.Expense;
import com.tharuna.splitwisepractice.model.Group;
import com.tharuna.splitwisepractice.repository.GroupRepostiroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepostiroy groupRepostiroy;

    @Autowired
    private UserService  userService;

    public Group createGroup(GroupCreationRequestDto groupCreationRequestDto) {
        Group group = new Group();
        group.setName(groupCreationRequestDto.getName());
        group.setDescription(groupCreationRequestDto.getDescription());
        group.setAdmin(userService.getUserById(groupCreationRequestDto.getAdminId()));

        group.setMembers(userService.findAllByIds(groupCreationRequestDto.getMemberIds()));

        return groupRepostiroy.save(group);
    }

    public Group getGroupById(long id) {
        return groupRepostiroy.findById(id).orElseThrow(
                () -> new RuntimeException("Group not found with id: " + id));
    }

    public void deleteGroup(long id) {
        Group group = getGroupById(id);
        groupRepostiroy.delete(group);
    }

    public Group addGroupMember(long groupId, long userId) {
        Group group = getGroupById(groupId);
        group.getMembers().add(userService.getUserById(userId));
        return groupRepostiroy.save(group);
    }

    public Group removeGroupMember(long groupId, long userId) {
        Group group = getGroupById(groupId);
        group.getMembers().removeIf(user -> user.getId() == userId);
        return groupRepostiroy.save(group);
    }

    public List<Group> getAllUserGroup(long userId) {
        return groupRepostiroy.findAll().stream()
                .filter(group -> group.getMembers().stream()
                        .anyMatch(user -> user.getId() == userId))
                .toList();
    }

    public List<Expense> findExpenseList(Long groupId) {
        Group group = getGroupById(groupId);
        return group.getExpenses();
    }
}
