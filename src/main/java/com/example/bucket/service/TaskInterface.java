package com.example.bucket.service;

import java.util.List;
import com.example.bucket.bo.Task;
import com.example.bucket.bo.TaskResponse;

public interface TaskInterface {

    List<TaskResponse> queryTask(List<Task> task);

}
