package com.example.bucket.bo;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class TaskResponse {

    private int taskName;

    private LocalDateTime time;

    private String timeStr;

    private String batch;

    public void echo() {
        System.out.println(this.toString());
    }
}
