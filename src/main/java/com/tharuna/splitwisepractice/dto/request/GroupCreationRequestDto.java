package com.tharuna.splitwisepractice.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupCreationRequestDto {
    private String name;
    private String description;
    private Long adminId; // Assuming admin is a User, we can use admin's ID
    private List<Long> memberIds; // Array of User IDs for members to be added to the group
}
