package javanesecoffee.com.blink.managers;

import org.json.JSONObject;

import java.util.ArrayList;

import javanesecoffee.com.blink.api.AsyncResponseHandler;
import javanesecoffee.com.blink.api.BLinkApiException;
import javanesecoffee.com.blink.api.BLinkEventObserver;

public abstract class Manager implements AsyncResponseHandler {
    public ArrayList<BLinkEventObserver> eventObservers = new ArrayList<>();

    public void registerObserver(BLinkEventObserver observer)
    {
        eventObservers.add(observer);
    }

    public void deregisterObserver(BLinkEventObserver observer)
    {
        eventObservers.remove(observer);
    }

    @Override
    public void onAsyncTaskComplete(JSONObject response, String taskId) {
        //automatically notifies observers
        notifyAllObservers(response, taskId);
    }

    @Override
    public void onAsyncTaskFailedWithException(BLinkApiException exception, String taskId) {
        for (BLinkEventObserver observer: eventObservers) {
            observer.onBLinkEventException(exception, taskId);
        }
    }

    public void notifyAllObservers(JSONObject response, String taskId)
    {
        for (BLinkEventObserver observer: eventObservers) {
            try {
                observer.onBLinkEventTriggered(response, taskId);
            } catch (BLinkApiException e) {
                e.printStackTrace();
                observer.onBLinkEventException(e, taskId);
            }
        }
    }
}
