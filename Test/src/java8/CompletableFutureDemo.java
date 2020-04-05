package java8;

import java.util.concurrent.*;

/**
 * @author : arfaoui
 * @since : 14/01/2020
 * project : Test
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        //demo1();
        futureDemo();
    }

    private static void futureDemo() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Long> future = executorService.submit(()-> {
            System.out.println("thread : "+Thread.currentThread());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10l;
        });
        try {
            System.out.println("before get result future");
            long res = future.get().intValue();
            System.out.println("result is "+res);
            System.out.println("after get result future");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void demo1() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("supply thread : "+Thread.currentThread());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ok";
        }, executorService).thenAccept(r->System.out.println("retour accepté :"+r));
        System.out.println("faire autre choses");
    }
}
