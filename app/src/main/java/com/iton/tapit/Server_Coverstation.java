package com.iton.tapit;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;

class Server_Conversation {

    private Socket socket;
    {
        try {
            socket = IO.socket("http://10.0.0.57:8080/");
            System.out.println("rrr");
        } catch (java.net.URISyntaxException e) {}
    }

    public void addHandlers(){
        socket.on("newgame", newgame);
}

    public void connect (){
        //addHandlers();
        try {
            socket.connect();
            System.out.println("Connection Success");
        }
        catch (Exception e){

        }
    }

    public void createGame(){
        socket.emit("newgame");
    }

    private Emitter.Listener newgame = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            String data = (String)args[0];
        }
    };

}