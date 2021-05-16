import { Injectable } from '@angular/core';
declare var SockJS;
declare var Stomp;
@Injectable({ providedIn: 'root' })

export class WebSocketService {
    // Our socket connection private socket;
    private serverUrl = 'http://localhost:8080/ws'; 

    constructor() {}

    // Open connection with the back-end socket 
    public connect() { 
        let socket = new SockJS(this.serverUrl);
        let stompClient = Stomp.over(socket);
        return stompClient; 
    } 
}
