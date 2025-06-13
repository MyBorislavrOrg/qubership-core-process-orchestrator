package org.qubership.core.scheduler.po.samples.tasks;

import com.github.kagkarlsson.scheduler.task.ExecutionContext;
import com.github.kagkarlsson.scheduler.task.TaskInstance;
import org.qubership.core.scheduler.po.ProcessOrchestrator;
import org.qubership.core.scheduler.po.context.TaskExecutionContext;
import org.qubership.core.scheduler.po.task.templates.AbstractProcessTask;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongRunningTask extends AbstractProcessTask {
    private static final Logger log = LoggerFactory.getLogger(LongRunningTask.class);

    public LongRunningTask() {
        super(LongRunningTask.class.getName());
    }


    @SneakyThrows
    @Override
    public void executeInternal(TaskInstance<TaskExecutionContext> taskInstance, ExecutionContext executionContext) {
        String taskName = ProcessOrchestrator.getInstance().getTaskInstanceRepository().getTaskInstance(taskInstance.getId()).getName();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            log.info("Ping {}", taskName);
        }
    }
}
