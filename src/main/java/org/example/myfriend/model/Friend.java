package org.example.myfriend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend {
    private int id;
    private User sender;
    private User friend;
    private Date date;
    private boolean accepted = false;
}
