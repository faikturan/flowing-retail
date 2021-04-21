package io.flowing.retail.zeebe.inventory;

import java.time.Duration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import io.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.client.api.worker.JobHandler;
import io.zeebe.client.api.worker.JobWorker;


@Component
public class FetchGoodsAdapter {

  @ZeebeWorker(type = "fetch-goods-z")
  public void fetchGoods(JobClient client, ActivatedJob job) {
    System.out.println("fetch goods");
    client.newCompleteCommand(job.getKey()).send()
      .exceptionally( throwable -> { throw new RuntimeException("Could not complete job " + job, throwable); });
  }

}