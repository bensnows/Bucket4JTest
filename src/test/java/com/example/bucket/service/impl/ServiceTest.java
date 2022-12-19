package com.example.bucket.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.bucket.bo.Task;
import com.example.bucket.bo.TaskImpl;
import com.example.bucket.bo.TaskResponse;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private TaskServiceImpl impl;

    @Test
    void testService() {

        List<Task> tasks =
                IntStream.range(0, 120).mapToObj(value -> TaskImpl.builder().id(value).build())
                        .collect(Collectors.toList());

        List<TaskResponse> result = impl.queryTask(tasks);

        for (TaskResponse res : result) {
            res.echo();
        }

        assertThat(result.size()).isLessThan(tasks.size());
    }

}
