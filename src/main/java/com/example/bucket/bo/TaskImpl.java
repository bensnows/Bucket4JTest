package com.example.bucket.bo;

import java.time.LocalDateTime;
import com.example.bucket.utils.TimeToStringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskImpl implements Task {

    private int id;

    @Override
    public TaskResponse execute(String batch) {

        LocalDateTime now = LocalDateTime.now();

        return TaskResponse.builder().batch(batch).taskName(id).time(now)
                .timeStr(TimeToStringUtils.convertToStr(now)).build();
    }

}
