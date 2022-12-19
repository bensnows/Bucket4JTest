package com.example.bucket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bucket.bo.Task;
import com.example.bucket.bo.TaskResponse;
import com.example.bucket.configuration.QueryConfiguration;
import com.example.bucket.service.TaskInterface;
import io.github.bucket4j.Bucket;

@Service
public class TaskServiceImpl implements TaskInterface {

    @Autowired
    private Bucket bucket;

    @Autowired
    private QueryConfiguration configuration;

    /**
     * 每次僅接受100個請求
     */
    @Override
    public List<TaskResponse> queryTask(List<Task> taskList) {

        taskList = taskList.subList(100, taskList.size());

        return consumeAndGetResult(taskList);
    }

    private synchronized List<TaskResponse> consumeAndGetResult(List<Task> tasklist) {

        int count = 0;

        final int finalCount = 3;

        List<List<Task>> partitions =
                ListUtils.partition(tasklist, configuration.getPartitionCount());

        List<TaskResponse> collection = new ArrayList<>();
        for (int i = 0; i < partitions.size();) {
            List<Task> partition = partitions.get(i);
            if (bucket.tryConsume(partitions.get(i).size())) {
                String batch = "Batch" + i;
                List<TaskResponse> result = partition.stream().map(task -> task.execute(batch))
                        .collect(Collectors.toList());
                collection.addAll(result);

                i++;
                continue;
            } else {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                if (count >= finalCount) {
                    break;
                }
            }

        }

        return collection;

    }

}
