package com.bw.jiguoshuai2020028;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VollerUtiuls  {

    RequestQueue queue;

    private VollerUtiuls() {
        queue = Volley.newRequestQueue(App.getApplication());
    }
    private static class SingInstance{
        private static VollerUtiuls IBEND=new VollerUtiuls();
    }
    public static VollerUtiuls getInstance(){
        return SingInstance.IBEND;
    }
    public interface ICallback{
        void onSuccess(String json);
        void onError(String json);
    }
    public void doGet(String url, final ICallback iCallback){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallback.onError(error.toString());

            }
        });
        queue.add(stringRequest);
    }


}
