package org.faclab.android.jeudepiste.util;

/**
 * Created by Tatiana Grange on 22/01/2015.
 */
public interface NFCListener {
    public void onEndRead(String result);
    public void onEndWrite();
    public void onError(Exception error);
}
